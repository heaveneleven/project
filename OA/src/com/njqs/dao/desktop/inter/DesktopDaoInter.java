package com.njqs.dao.desktop.inter;

import java.util.List;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.desktop.Desktop;
import com.njqs.utils.Tree;

public interface DesktopDaoInter extends BaseDaoInter<Desktop,Integer>{
	public List<Tree> getDesktopTree();
	public List<Tree> getSysTree();
}
