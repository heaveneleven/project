package com.njqs.service.desktop.log_info.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import com.njqs.dao.desktop.log_info.inter.LogInfoDaoInter;
import com.njqs.domain.log.PersonLog;
import com.njqs.formmodel.person_log.PersonLogForm;
import com.njqs.service.desktop.log_info.inter.LogInfoServiceInter;
import com.njqs.utils.AjaxMsg;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
import com.njqs.utils.exception.BusinessException;
@Transactional
@Service("logInfoService")
public class LogInfoServiceImpl implements LogInfoServiceInter{
	@Resource(name="logInfoDao")
	private LogInfoDaoInter logInfoDao;
	/**获取日志信息*/
	@Override
	public DataGrid<Map<String, Object>> getLogInfo(PageInfo pager, int id) {
		StringBuilder sql=new StringBuilder();
		sql.append("select id as log_id,login_id,log_type,log_title,tm,log_content ");
		sql.append("from person_log ");
		sql.append(" where login_id='");
		sql.append(id);
		sql.append("'");
		try{
			long total=logInfoDao.count(sql.toString());
			int []fm=pager.getFirstindexAndMaxindex();
			List<Map<String,Object>> list=logInfoDao.searchForMap(sql.toString(),fm[0],fm[1]," tm desc ");
			return new DataGrid<Map<String, Object>>(total, list);
		}catch(Exception e){
			throw new BusinessException("获取日志信息出错！",e);
		}
	}
	/**保存日志*/
	@Override
	public AjaxMsg saveLog(PersonLogForm form,int id){
		PersonLog log=new PersonLog();
		AjaxMsg msg=new AjaxMsg();
		try{
		log.setLogin_id(id);
		log.setLog_type(form.getLog_type());
		log.setLog_title(form.getLog_title());
		log.setLog_content(form.getLog_content());
		
		if (StringUtils.isNotBlank(form.getTm())){
				try {
					log.setTm(DateUtils.parseDate(form.getTm(), "yyyy-MM-dd"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			logInfoDao.save(log);
			msg.setSuccess(true);
			msg.setMsg("保存成功！");
			return msg;
		}catch(Exception e){
			msg.setSuccess(false);
			msg.setMsg(e.toString());
		}
		return msg;
	}
	/**删除日志*/
	@Override
	public AjaxMsg delLogs(String ids) {
		AjaxMsg msg=new AjaxMsg();
		String [] id_list =ids.split(",");
		try{
			for(String id : id_list){
				logInfoDao.delete(PersonLog.class,Integer.valueOf(id));
			}
			msg.setSuccess(true);
			msg.setMsg("删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg(e.toString());
		}
		return msg;
	}
	/**根据id获取log实体类保存入form类中*/
	@Override
	public PersonLogForm getLogForm(int log_id) {
		PersonLog log=logInfoDao.find(PersonLog.class,log_id);
		try{
			PersonLogForm form=new PersonLogForm(log);
			return form;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**保存修改日志*/
	@Override
	public AjaxMsg saveModifyLog(PersonLogForm form) {
		AjaxMsg msg=new AjaxMsg();
		try{
			PersonLog log=logInfoDao.find(PersonLog.class, form.getLog_id());
			log.setLog_title(form.getLog_title());
			log.setLog_type(form.getLog_type());
			log.setLog_content(form.getLog_content());
			try {
				log.setTm(	DateUtils.parseDate(form.getTm(), "yyyy-MM-dd"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			msg.setSuccess(true);
			msg.setMsg("修改成功！");
		}catch(Exception e){
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("修改失败！");
		}
		return msg;
	}
}
