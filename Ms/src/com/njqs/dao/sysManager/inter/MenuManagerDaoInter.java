package com.njqs.dao.sysManager.inter;

import java.util.List;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.sysManager.Menu;

public interface MenuManagerDaoInter extends BaseDaoInter<Menu, Integer> {
	public List<Menu> getChilds(String pid);
	public boolean hasChildren(String pid);
}
