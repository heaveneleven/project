package com.njqs.dao.sysManager.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.sysManager.inter.MenuManagerDaoInter;
import com.njqs.domain.sysManager.Menu;
@Repository("menuDao")
public class MenuManagerDaoImpl extends BaseDaoImpl<Menu, Integer> implements MenuManagerDaoInter{
	/**
	 * 根据父节点来获取子节点
	 */
	@Override
	public List<Menu> getChilds(String pid) {
			String sql="select * from menu where pid = '"+pid+"' ";
			List<Menu> list=this.search(Menu.class, sql);
		return list;
	}
	/**
	 * 判断当前节点是否含有子节点
	 */
	@Override
	public boolean hasChildren(String pid) {
		String sql="select * from menu where pid = '"+pid+"' ";
		List<Menu> list=this.search(Menu.class, sql);
		if(list.isEmpty())
			return false;
		else
			return true;
	}
}
