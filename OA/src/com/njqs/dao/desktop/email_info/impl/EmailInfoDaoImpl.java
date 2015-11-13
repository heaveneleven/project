package com.njqs.dao.desktop.email_info.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.desktop.email_info.inter.EmailInfoDaoInter;
import com.njqs.domain.desktop.email.Email_Root;
import com.njqs.utils.Tree;

/***
 * 
 * @author heaven
 * 我的邮箱Dao
 */
@Repository("emailDao")
public class EmailInfoDaoImpl extends BaseDaoImpl<Email_Root,Integer> implements EmailInfoDaoInter{

	/**获取我的邮箱目录树*/
	@Override
	public List<Tree> getEmailTree() {
		String sql="select * from email_root ";
		List<Email_Root> list=this.search(Email_Root.class, sql);
		List<Tree> tList=new ArrayList<Tree>();
		for (Email_Root node : list) {
			Tree newNode=new Tree();
			newNode.setId(node.getTid());
			newNode.setPid("0");
			newNode.setText(node.getText());
			newNode.setIconCls(node.getIco());
			tList.add(newNode);
		}
		return tList;
	}
}
