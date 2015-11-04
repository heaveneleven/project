package com.njqs.dao.realtime.inter;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.query.ST_RSVR.ST_ZVARL_B;

public interface CapacityDaoInter extends BaseDaoInter<ST_ZVARL_B, Integer> {
	public void addItems();
}
