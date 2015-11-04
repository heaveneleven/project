package com.njqs.service.setting.inter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.njqs.domain.query.ST_STBPRP.location;
import com.njqs.model.ManagerModel;
import com.njqs.utils.AjaxMsg;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;

public interface SysSettingServiceInter {
	public AjaxMsg restPassword(HttpServletRequest request,String newpassword,String origpassword);
	public AjaxMsg submitAddUser(String username,String password,String authority);
	boolean judeUsername(String username);
	public String ecodeByMD5(String password);
	/**导入Excel2003*/
	public AjaxMsg uploadExcel2003(String stcd,String path);
	/**导入Excel2007*/
	public AjaxMsg uploadExcel2007(String stcd,String path);
	/**导入水位流量关系Excel2003*/
	public AjaxMsg uploadVRelationExcel2003(String stcd,String path);
	/**导入水位流量关系Excel2007*/
	public AjaxMsg uploadVRelationExcel2007(String stcd,String path);
	/**保存水位基值*/
	public AjaxMsg saveBase(String stcd,String base);
	/**获取基站基本信息表，包括水位基值或水位初始报警值*/
	public DataGrid<Map<String, Object>> getStations(String type,PageInfo pager);
	/**保存水位预警值*/
	public AjaxMsg saveWarn(String stcd,String warn,boolean valid);
	/**地图经纬度设置提交*/
	public AjaxMsg saveMap(String stcd,String lgtd,String lttd);
	/**获取省份信息*/
	public List<Map<String,Object>> getProvince();
	/**根据省份id获取相应所有城市信息*/
	public List<Map<String,Object>> getCity(String pid);
	/**获取中心位置经纬度*/
	public location getLocation();
	/**设置地图中心经纬度*/
	public AjaxMsg subMapCenter(String lgtd,String lttd);
	/**获取管理人员信息*/
	public DataGrid<Map<String, Object>> getManager(PageInfo pager);
	/**保存管理人员信息*/
	public AjaxMsg subManager(ManagerModel m);
	/**获取所有登陆用户信息*/
	public DataGrid<Map<String,Object>> getUsers(HttpServletRequest request,PageInfo pager);
	/**删除登陆用户信息*/
	public AjaxMsg delUser(String id);
	/**提交其它用户账户密码修改*/
	public AjaxMsg resetPass(String id,String repassword,int authority);
}
