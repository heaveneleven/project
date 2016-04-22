package com.nuaa.dao.admin.inter;

import com.nuaa.dao.base.inter.BaseDaoInter;
import com.nuaa.domain.admin.AdminInfo;
import com.nuaa.utils.AdminSessionInfo;

public interface AdminLoginDaoInter extends BaseDaoInter<AdminInfo, Integer>{

	AdminSessionInfo getUser(String login_name, String login_password);

}
