package com.njqs.service.setting.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Encoder;

import com.njqs.dao.login.inter.SysLoginDaoInter;
import com.njqs.dao.setting.inter.managerDaoInter;
import com.njqs.dao.setting.inter.mapDaoInter;
import com.njqs.dao.setting.inter.vRelationDaoInter;
import com.njqs.dao.setting.inter.wRelationDaoInter;
import com.njqs.dao.setting.inter.warnDaoInter;
import com.njqs.domain.query.ST_RSVR.ST_ZQRL_B;
import com.njqs.domain.query.ST_RSVR.ST_ZVARL_B;
import com.njqs.domain.query.ST_STBPRP.location;
import com.njqs.domain.user.TUser;
import com.njqs.model.ManagerModel;
import com.njqs.service.setting.inter.SysSettingServiceInter;
import com.njqs.utils.AjaxMsg;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
import com.njqs.utils.SessionKey;
import com.njqs.utils.UserInfo;
@Transactional
@Service("sysSettingService")
public class SysSettingServiceImpl implements SysSettingServiceInter{
	@Resource(name="sysLoginDao")
	private SysLoginDaoInter sysLoginDao;
	@Resource(name="wRelationDao")
	private wRelationDaoInter wRelationDao;
	@Resource(name="vRelationDao")
	private vRelationDaoInter vRelationDao;
	@Resource(name="warnDao")
	private warnDaoInter warnDao;
	@Resource(name="mapDao")
	private mapDaoInter mapDao;
	@Resource(name="managerDao")
	private managerDaoInter managerDao;
	/**修改用户密码*/
	@Override
	public AjaxMsg restPassword(HttpServletRequest request, String newpassword,
			String origpassword) {
		UserInfo userInfo=(UserInfo)request.getSession().getAttribute(SessionKey.UserInfoKey);
		TUser user=sysLoginDao.find(TUser.class, userInfo.getUser().getId());
		AjaxMsg msg=new AjaxMsg();
		/**转换成MD5加密后的密码*/
		origpassword = this.ecodeByMD5(origpassword);
		if(!user.getPassword().equals(origpassword)){
			msg.setSuccess(false);
			msg.setMsg("原始密码输入错误，请重新输入！");
		}else{
			//String sql="update tuser set password = '"+newpassword+"' where id = '"+user.getId()+"' ";
			/**转换成MD5加密后的密码*/
			newpassword = this.ecodeByMD5(newpassword);
			user.setPassword(newpassword);
			sysLoginDao.update(user);
			msg.setSuccess(true);
			msg.setMsg("修改密码成功！");
		}
		return msg;
	}
	/**添加账户*/
	@Override
	public AjaxMsg submitAddUser(String username, String password,
			String authority) {
		AjaxMsg msg=new AjaxMsg();
		/**如果当前的用户名已存在*/
		if(this.judeUsername(username)){
			msg.setSuccess(false);
			msg.setMsg("该用户名已存在！");
			return msg;
		}
		TUser newUser=new TUser();
		newUser.setUsername(username);
		if(this.ecodeByMD5(password)!=null){
		newUser.setPassword(this.ecodeByMD5(password));
		}else{
			msg.setSuccess(false);
			msg.setMsg("MD5加密失败！");
			return msg;
		}
		newUser.setAuthority(authority);	
		try {
			sysLoginDao.save(newUser);
			msg.setSuccess(true);
			msg.setMsg("添加账户成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("添加账户失败！");
			e.printStackTrace();
		}
		return msg;
	}
	/**判断该用户名是否已经存在*/
	@Override
	public boolean judeUsername(String username) {
		String sql="select * from tuser where username='"+username+"' ";
		List<Map<String, Object>> list = sysLoginDao.searchForMap(sql);
		if(list.isEmpty())
			return false;
		else
			return true;
	}
	/**通过MD5对用户密码进行加密*/
	@Override
	public String ecodeByMD5(String password) {
		try {
			/**确定使用MD5进行加密*/
			MessageDigest md5=MessageDigest.getInstance("MD5");
			BASE64Encoder base64en=new BASE64Encoder();
			/**产生加密后的字符串（密码）*/
			String newPassword=base64en.encode(md5.digest(password.getBytes("utf-8")));
			return newPassword;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**解析上传的Excel2003水容关系表*/
	@Override
	public AjaxMsg uploadExcel2003(String stcd,String path) {
		/**选出关系表中原先所对应的站的记录，在保存新的关系记录成功后将原先的删除*/
		String sql="select * from ST_ZVARL_B where stcd='"+stcd+"'";
		List<ST_ZVARL_B> list=this.wRelationDao.search(ST_ZVARL_B.class,sql,-1,-1);
		
		AjaxMsg msg=new AjaxMsg();
		try {
			InputStream IS = new FileInputStream(path);
			HSSFWorkbook book = new HSSFWorkbook(IS);
			for(int numSheet=0;numSheet < book.getNumberOfSheets();numSheet++){
				HSSFSheet sheet=book.getSheetAt(numSheet);
				if(sheet==null){
					continue;
				}
				
				/**对row进行循环，Excel中的第一行为中文的名称故丢掉*/
				for(int rowNum=1;rowNum<=sheet.getLastRowNum();rowNum++){
					HSSFRow row=sheet.getRow(rowNum);
					if(row!=null){
						ST_ZVARL_B wRelation=new ST_ZVARL_B();
						wRelation.setStcd(stcd);
						wRelation.setModitime(new Date());
						wRelation.setRz(Double.parseDouble(row.getCell(0).toString()));
						wRelation.setW(Double.parseDouble(row.getCell(1).toString()));
						wRelation.setMstm(new Date());
						wRelationDao.save(wRelation);
					}
				}
				book.close();
				/**设置成功后删除原先该站点所对应的水容关系记录*/
				for(ST_ZVARL_B z : list){
					wRelationDao.delete(z);
				}
				/**只对第一个sheet（不为null的）进行保存*/
				msg.setMsg("设置成功！");
				msg.setSuccess(true);
				return msg;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("设置水容关系出错！");
			return msg;
		}
		msg.setSuccess(false);
		msg.setMsg("请输入有效水容关系表！");
		return msg;
	}
	/**解析上传的Excel2007水容关系表*/
	@Override
	public AjaxMsg uploadExcel2007(String stcd,String path) {
		/**选出关系表中原先所对应的站的记录，在保存新的关系记录成功后将原先的删除*/
		String sql="select * from ST_ZVARL_B where stcd='"+stcd+"'";
		List<ST_ZVARL_B> list=this.wRelationDao.search(ST_ZVARL_B.class,sql,-1,-1);
		
		AjaxMsg msg=new AjaxMsg();
		try {
			InputStream IS = new FileInputStream(path);
			XSSFWorkbook book = new XSSFWorkbook(IS);
			for(int numSheet=0;numSheet < book.getNumberOfSheets();numSheet++){
				XSSFSheet sheet=book.getSheetAt(numSheet);
				if(sheet==null){
					continue;
				}
				
				/**对row进行循环，Excel中的第一行为中文的名称故丢掉*/
				for(int rowNum=1;rowNum<=sheet.getLastRowNum();rowNum++){
					XSSFRow row=sheet.getRow(rowNum);
					if(row!=null){
						ST_ZVARL_B wRelation=new ST_ZVARL_B();
						wRelation.setStcd(stcd);
						wRelation.setModitime(new Date());
						wRelation.setRz(Double.parseDouble(row.getCell(0).toString()));
						wRelation.setW(Double.parseDouble(row.getCell(1).toString()));
						wRelation.setMstm(new Date());
						wRelationDao.save(wRelation);
					}
				}
				book.close();
				/**设置成功后删除原先该站点所对应的水容关系记录*/
				for(ST_ZVARL_B z : list){
					wRelationDao.delete(z);
				}
				/**只对第一个sheet（不为null的）进行保存*/
				msg.setMsg("设置成功！");
				msg.setSuccess(true);
				return msg;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("设置水容关系出错！");
			return msg;
		}
		msg.setSuccess(false);
		msg.setMsg("请输入有效水容关系表！");
		return msg;
	}
	@Override
	public AjaxMsg uploadVRelationExcel2003(String stcd, String path) {
		/**选出关系表中原先所对应的站的记录，在保存新的关系记录成功后将原先的删除*/
		String sql="select * from ST_ZQRL_B where stcd='"+stcd+"'";
		List<ST_ZQRL_B> list=this.vRelationDao.search(ST_ZQRL_B.class,sql,-1,-1);
		
		AjaxMsg msg=new AjaxMsg();
		try {
			InputStream IS = new FileInputStream(path);
			HSSFWorkbook book = new HSSFWorkbook(IS);
			for(int numSheet=0;numSheet < book.getNumberOfSheets();numSheet++){
				HSSFSheet sheet=book.getSheetAt(numSheet);
				if(sheet==null){
					continue;
				}
				
				/**对row进行循环，Excel中的第一行为中文的名称故丢掉*/
				for(int rowNum=1;rowNum<=sheet.getLastRowNum();rowNum++){
					HSSFRow row=sheet.getRow(rowNum);
					if(row!=null){
						ST_ZQRL_B vRelation=new ST_ZQRL_B();
						vRelation.setStcd(stcd);
						vRelation.setModitime(new Date());
						vRelation.setZ(Double.parseDouble(row.getCell(0).toString()));
						vRelation.setQ(Double.parseDouble(row.getCell(1).toString()));
						vRelationDao.save(vRelation);
					}
				}
				book.close();
				/**设置成功后删除原先该站点所对应的水容关系记录*/
				for(ST_ZQRL_B z : list){
					vRelationDao.delete(z);
				}
				/**只对第一个sheet（不为null的）进行保存*/
				msg.setMsg("设置成功！");
				msg.setSuccess(true);
				return msg;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("设置水位流量关系出错！");
			return msg;
		}
		msg.setSuccess(false);
		msg.setMsg("请输入有效水位流量关系表！");
		return msg;
	}
	@Override
	public AjaxMsg uploadVRelationExcel2007(String stcd, String path) {
		/**选出关系表中原先所对应的站的记录，在保存新的关系记录成功后将原先的删除*/
		String sql="select * from ST_ZQRL_B where stcd='"+stcd+"'";
		List<ST_ZQRL_B> list=this.vRelationDao.search(ST_ZQRL_B.class,sql,-1,-1);
		AjaxMsg msg=new AjaxMsg();
		try {
			InputStream IS = new FileInputStream(path);
			XSSFWorkbook book = new XSSFWorkbook(IS);
			for(int numSheet=0;numSheet < book.getNumberOfSheets();numSheet++){
				XSSFSheet sheet=book.getSheetAt(numSheet);
				if(sheet==null){
					continue;
				}
				
				/**对row进行循环，Excel中的第一行为中文的名称故丢掉*/
				for(int rowNum=1;rowNum<=sheet.getLastRowNum();rowNum++){
					XSSFRow row=sheet.getRow(rowNum);
					if(row!=null){
						ST_ZQRL_B vRelation=new ST_ZQRL_B();
						vRelation.setStcd(stcd);
						vRelation.setModitime(new Date());
						vRelation.setZ(Double.parseDouble(row.getCell(0).toString()));
						vRelation.setQ(Double.parseDouble(row.getCell(1).toString()));
						vRelationDao.save(vRelation);
					}
				}
				book.close();
				/**设置成功后删除原先该站点所对应的水容关系记录*/
				for(ST_ZQRL_B z : list){
					vRelationDao.delete(z);
				}
				/**只对第一个sheet（不为null的）进行保存*/
				msg.setMsg("设置成功！");
				msg.setSuccess(true);
				return msg;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("设置水容关系出错！");
			return msg;
		}
		msg.setSuccess(false);
		msg.setMsg("请输入有效水容关系表！");
		return msg;
	}
	/**保存水位基值*/
	@Override
	public AjaxMsg saveBase(String stcd, String base) {
		AjaxMsg msg=new AjaxMsg();
		String sql="update ST_STBPRP_B set dtmel='"+base+"' where stcd='"+stcd+"'";
		try{
			this.wRelationDao.update(sql);
			msg.setSuccess(true);
			msg.setMsg("修改成功！");
			return msg;
		}catch(Exception e){
			System.out.println("修改水位基值出错！");
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("修改水位基值出错！");
			return msg;
		}
	}
	@Override
	public DataGrid<Map<String, Object>> getStations(String type, PageInfo pager) {
		return wRelationDao.getStations(type,pager);
	}
	/**保存水位预警值*/
	@Override
	public AjaxMsg saveWarn(String stcd, String warn, boolean valid) {
		return warnDao.saveWarn(stcd, warn, valid);
	}
	/**地图设置提交*/
	@Override
	public AjaxMsg saveMap(String stcd, String lgtd, String lttd) {
		AjaxMsg msg=new AjaxMsg();
		String sql="update dbo.ST_STBPRP_B set lgtd='"+lgtd+"' ,lttd='"+lttd+"' where stcd='"+stcd+"'";
		try{
			this.warnDao.update(sql);
			msg.setSuccess(true);
			msg.setMsg("设置成功！");
			return msg;
		}catch(Exception e){
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("设置失败！");
			return msg;
		}
	}
	/**获取省份信息*/
	@Override
	public List<Map<String, Object>> getProvince() {
		String sql="select p.proID as id,proName as text from promary as p";
		try{
		List<Map<String,Object>> list=warnDao.searchForMap(sql);
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**根据省份id获取相应城市**/
	@Override
	public List<Map<String, Object>> getCity(String pid) {
		String sql="select c.cityID as id,c.cityName as text from dbo.city as c where c.proID="+pid;
		try{
			List<Map<String,Object>> list=warnDao.searchForMap(sql);
				return list;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
	}
	/**获取经纬度坐标实体类*/
	@Override
	public location getLocation(){
		List<Map<String,Object>> list=warnDao.searchForMap("select top 1* from dbo.location");
		if(list.size()>0){
			location loc=new location();
			loc.setId(Integer.parseInt(list.get(0).get("id").toString()));
			loc.setLgtd(list.get(0).get("lgtd").toString());
			loc.setLttd(list.get(0).get("lttd").toString());
			loc.setRange(Integer.parseInt(list.get(0).get("range").toString()));
			return loc;
		}else{
			return null;
		}
	}
	/**设置中心经纬度*/
	@Override
	public AjaxMsg subMapCenter(String lgtd, String lttd) {
		return mapDao.subCenter(lgtd, lttd);
	}
	/**获取管理人员信息*/
	@Override
	public DataGrid<Map<String, Object>> getManager(PageInfo pager){
		return managerDao.getManager(pager);
	}
	/**提交管理人员信息*/
	@Override
	public AjaxMsg subManager(ManagerModel m) {
		return managerDao.subManager(m);
	}
	/**获取所有登陆用户信息*/
	@Override
	public DataGrid<Map<String, Object>> getUsers(HttpServletRequest request,PageInfo pager) {
		return sysLoginDao.getUsers(request,pager);
	}
	/**删除用户信息*/
	@Override
	public AjaxMsg delUser(String id) {
		AjaxMsg msg=new AjaxMsg();
		TUser user=this.sysLoginDao.find(TUser.class, Integer.parseInt(id));
		if(user==null){
			msg.setSuccess(false);
			msg.setMsg("该用户不存在！");
			return msg;
		}
			
		try{
				sysLoginDao.delete(TUser.class, Integer.parseInt(id));
				msg.setSuccess(true);
				msg.setMsg("删除成功！");
			}catch(Exception e){
				e.printStackTrace();
				msg.setSuccess(false);
				msg.setMsg("删除用户信息失败！");
			}
			return msg;
	}
	@Override
	public AjaxMsg resetPass(String id, String repassword,int authority) {
		AjaxMsg msg=new AjaxMsg();
		if("".equals(repassword)){
			msg.setSuccess(false);
			msg.setMsg("密码不能为空！");
			return msg;
		}
		try{
		   TUser user=sysLoginDao.find(TUser.class, Integer.parseInt(id));
		   /**转换成MD5加密后的密码*/
		   repassword = this.ecodeByMD5(repassword);
			user.setPassword(repassword);
			user.setAuthority(String.valueOf(authority));
			sysLoginDao.update(user);
			msg.setSuccess(true);
			msg.setMsg("修改密码成功！");
		}catch(Exception e){
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("修改密码失败！");
		}
		return msg;
	}
}

