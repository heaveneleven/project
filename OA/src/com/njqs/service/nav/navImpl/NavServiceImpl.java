package com.njqs.service.nav.navImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.njqs.dao.desktop.inter.DesktopDaoInter;
import com.njqs.service.nav.navInter.NavServiceInter;
import com.njqs.utils.Tree;
@Service("navService")
public class NavServiceImpl implements NavServiceInter{
	@Resource(name="desktopDao")
	private DesktopDaoInter desktopDao;
	@Override
	public List<Tree> getDesktopTree() {
		return desktopDao.getDesktopTree();
	}
	@Override
	public List<Tree> getSysTree() {
		return desktopDao.getSysTree();
	}
}
