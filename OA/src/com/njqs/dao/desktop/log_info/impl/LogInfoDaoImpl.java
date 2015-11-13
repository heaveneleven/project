package com.njqs.dao.desktop.log_info.impl;

import org.springframework.stereotype.Repository;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.desktop.log_info.inter.LogInfoDaoInter;
import com.njqs.domain.log.PersonLog;
@Repository("logInfoDao")
public class LogInfoDaoImpl extends BaseDaoImpl<PersonLog, Integer> implements LogInfoDaoInter{

}
