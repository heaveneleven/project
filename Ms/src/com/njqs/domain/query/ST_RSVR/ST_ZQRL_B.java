package com.njqs.domain.query.ST_RSVR;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 水位流量关系曲线表，用于存储测站测验断面水位和流量的相关关系
 * */
@Entity
@Table(name="ST_ZQRL_B")
public class ST_ZQRL_B {
	private int id;
	/**测站编码*/
	private String stcd;
	/**曲线名称*/
	private String lnnm;
	/**启用时间*/
	private Date bgtm;
	/**点序号*/
	private double ptno;
	/**水位*/
	private double z;
	/**流量*/
	private double q;
	/**备注*/
	private String comments;
	/**时间戳*/
	private Date moditime;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	public String getLnnm() {
		return lnnm;
	}
	public void setLnnm(String lnnm) {
		this.lnnm = lnnm;
	}
	public Date getBgtm() {
		return bgtm;
	}
	public void setBgtm(Date bgtm) {
		this.bgtm = bgtm;
	}
	public double getPtno() {
		return ptno;
	}
	public void setPtno(double ptno) {
		this.ptno = ptno;
	}
	@Column(nullable=true,columnDefinition="DECIMAL(7,3)")
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	@Column(nullable=true,columnDefinition="DECIMAL(9,3)")
	public double getQ() {
		return q;
	}
	public void setQ(double q) {
		this.q = q;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getModitime() {
		return moditime;
	}
	public void setModitime(Date moditime) {
		this.moditime = moditime;
	}
}
