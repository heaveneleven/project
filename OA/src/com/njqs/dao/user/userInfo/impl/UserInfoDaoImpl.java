package com.njqs.dao.user.userInfo.impl;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.user.userInfo.inter.UserInfoDaoInter;
import com.njqs.domain.user.UserInfo;
/***用户具体信息Dao*/
@Repository("userInfoDao")
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo, Integer> implements UserInfoDaoInter{

}
