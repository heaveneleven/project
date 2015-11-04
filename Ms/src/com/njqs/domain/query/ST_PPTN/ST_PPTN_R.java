package com.njqs.domain.query.ST_PPTN;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ST_PPIN_R")
public class ST_PPTN_R {
	private int id;
	/**测站编码*/
	private char stcd;
	/**来报时间*/
	private Date tm;
	/**时段降水量*/
	private double drp;
	/**时段长*/
	private double inty;
	/**降水历时*/
	private double pdr;
	/**日降水量*/
	private double dyp;
	/**天气状况 见表ST_PPTN_WTH_DETAIL*/
	private char wth;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(nullable=false,columnDefinition="CHAR(8)")
	public char getStcd() {
		return stcd;
	}
	public void setStcd(char stcd) {
		this.stcd = stcd;
	}
	@Column(nullable=false,columnDefinition="DATETIME")
	public Date getTm() {
		return tm;
	}
	public void setTm(Date tm) {
		this.tm = tm;
	}
	@Column(nullable=true,columnDefinition="DECIMAL(5,1)")
	public double getDrp() {
		return drp;
	}
	public void setDrp(double drp) {
		this.drp = drp;
	}

	@Column(nullable=true,columnDefinition="DECIMAL(5,2)")
	public double getInty() {
		return inty;
	}
	public void setInty(double inty) {
		this.inty = inty;
	}
	@Column(nullable=true,columnDefinition="DECIMAL(5,2)")
	public double getPdr() {
		return pdr;
	}
	public void setPdr(double pdr) {
		this.pdr = pdr;
	}
	@Column(nullable=true,columnDefinition="DECIMAL(5,1)")
	public double getDyp() {
		return dyp;
	}
	public void setDyp(double dyp) {
		this.dyp = dyp;
	}
	@Column(nullable=true)
	public char getWth() {
		return wth;
	}
	public void setWth(char wth) {
		this.wth = wth;
	}
}
