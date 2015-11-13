package com.njqs.dao.desktop.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.desktop.inter.DesktopDaoInter;
import com.njqs.domain.desktop.Desktop;
import com.njqs.utils.Tree;
@Repository("desktopDao")
public class DesktopDaoImpl extends BaseDaoImpl<Desktop, Integer> implements DesktopDaoInter{
	/***
	 * 返回我的桌面菜单树
	 */
	@Override
	public List<Tree> getDesktopTree() {
		String sql="select * from desktop ";
		List<Desktop> list=this.search(Desktop.class, sql);
		List<Tree> tList=new ArrayList<Tree>();
		for (Desktop node : list) {
			Tree newNode=new Tree();
			newNode.setId(node.getTid());
			newNode.setText(node.getText());
			newNode.setPid("0");
			newNode.setIconCls(node.getIco());
			tList.add(newNode);
		}
		return tList;
	}
	/**
	 * 获取系统管理菜单树
	 */
	@Override
	public List<Tree> getSysTree() {
		String sql="select * from sys_root ";
		List<Desktop> list=this.search(Desktop.class, sql);
		List<Tree> tList=new ArrayList<Tree>();
		for (Desktop node : list) {
			Tree newNode=new Tree();
			newNode.setId(node.getTid());
			newNode.setText(node.getText());
			newNode.setPid("0");
			newNode.setIconCls(node.getIco());
			tList.add(newNode);
		}
		return tList;
	}
}
