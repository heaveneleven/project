package com.njqs.service.sys.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.njqs.dao.sys.inter.AccountManagerDaoInter;
import com.njqs.service.sys.inter.AccountMangerServiceInter;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
@Service("accountManagerService")
public class AccountMangerServiceImpl implements AccountMangerServiceInter{
	@Resource(name="accountManagerDao")
	private AccountManagerDaoInter accountManagerDao;
	@Override
	public DataGrid<Map<String, Object>> getAccountInfo(PageInfo pager) {
		return accountManagerDao.getAccountInfo(pager);
	}
}
