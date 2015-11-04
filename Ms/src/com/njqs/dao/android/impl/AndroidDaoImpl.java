package com.njqs.dao.android.impl;

import org.springframework.stereotype.Repository;

import com.njqs.dao.android.inter.AndroidDaoInter;
import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.domain.query.ST_STBPRP.ST_STBPRP_B;
@Repository("androidDao")
public class AndroidDaoImpl extends BaseDaoImpl<ST_STBPRP_B, Integer> implements AndroidDaoInter{

}
