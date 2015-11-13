package com.njqs.dao.desktop.person_info.impl;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.desktop.person_info.inter.PersonInfoDaoInter;
import com.njqs.domain.user.UserInfo;
@Repository("personInfoDao")
public class PersonInfoDaoImpl extends BaseDaoImpl<UserInfo, Integer> implements PersonInfoDaoInter{

}
