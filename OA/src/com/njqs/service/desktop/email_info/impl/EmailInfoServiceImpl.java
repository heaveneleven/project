package com.njqs.service.desktop.email_info.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.njqs.dao.desktop.email_info.inter.EmailInfoDaoInter;
import com.njqs.formmodel.emailInfo.EmailOutForm;
import com.njqs.service.desktop.email_info.inter.EmailInfoServiceInter;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
import com.njqs.utils.Tree;
import com.njqs.utils.exception.BusinessException;
/**我的邮箱Service*/
@Service("emailService")
public class EmailInfoServiceImpl implements EmailInfoServiceInter{
	@Resource(name="emailDao")
	private EmailInfoDaoInter emailDao;
	/**
	 * 获取我的邮箱目录树
	 */
	@Override
	public List<Tree> getEmailRootTree() {
		return emailDao.getEmailTree();
	}
	/**获取发件箱信息*/
	@Override
	public DataGrid<Map<String, Object>> getEmailOutInfo(PageInfo pager,int login_id) {
		StringBuilder sql=new StringBuilder();
		sql.append("select o.id as email_out_id,o.login_id,o.priority,o.is_read,o.is_reply,");
		sql.append("o.title,o.sender,o.recipient,o.tm");
		sql.append(" from dbo.mail_out as o where id='");
		sql.append(login_id);
		sql.append("'");
		System.out.println("查询发件箱sql："+sql.toString());
		try{
			long total=emailDao.count(sql.toString());
			int []fm=pager.getFirstindexAndMaxindex();
			List<Map<String,Object>> list=emailDao.searchForMap(sql.toString(),fm[0],fm[1]," tm desc ");
			return new DataGrid<Map<String, Object>>(total, list);
		}catch(Exception e){
			throw new BusinessException("获取发件箱信息出错！",e);
		}
	}
}
