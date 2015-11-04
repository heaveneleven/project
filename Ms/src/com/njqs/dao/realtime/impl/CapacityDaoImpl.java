package com.njqs.dao.realtime.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.njqs.dao.base.impl.BaseDaoImpl;
import com.njqs.dao.realtime.inter.CapacityDaoInter;
import com.njqs.domain.query.ST_RSVR.ST_ZVARL_B;
@Transactional
@Repository("addItems")
public class CapacityDaoImpl extends BaseDaoImpl<ST_ZVARL_B, Integer> implements CapacityDaoInter{
	public void addItems(){
		double rz=48.2;
		double w=225;
		for(int i=0;i<1000;i++){
			ST_ZVARL_B B=new ST_ZVARL_B();
			B.setRz(rz);
			B.setW(w);
			B.setMstm(new Date());
			B.setStcd("0000008"); 
			B.setPtno(1);
			this.save(B);
			rz+=0.2;
			w+=25;
		}
	}
}
