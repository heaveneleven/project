package com.njqs.domain.query.ST_RSVR;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author heaven
 *	水库水情表
 *  用于存储水库站测报的水库水情信息
 */
@Entity
@Table(name="ST_RSVR_R")
public class ST_RSVR_R {
	private int id;
	private char stcd;
	private Date tm;
	private double rz;
	private double inq;
	private double w;
	private double blrz;
	private double otq;
	private char rwchrcd;
	private char rwptn;
	private double inqdr;
	private char msqmt;
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
	@Column(columnDefinition="DECIMAL(7,3)")
	public double getRz() {
		return rz;
	}
	public void setRz(double rz) {
		this.rz = rz;
	}
	@Column(columnDefinition="DECIMAL(9,3)")
	public double getInq() {
		return inq;
	}
	public void setInq(double inq) {
		this.inq = inq;
	}
	@Column(columnDefinition="DECIMAL(9,3)")
	public double getW() {
		return w;
	}
	public void setW(double w) {
		this.w = w;
	}
	@Column(columnDefinition="DECIMAL(7,3)")
	public double getBlrz() {
		return blrz;
	}
	public void setBlrz(double blrz) {
		this.blrz = blrz;
	}
	@Column(columnDefinition="DECIMAL(9,3)")
	public double getOtq() {
		return otq;
	}
	public void setOtq(double otq) {
		this.otq = otq;
	}
	@Column(nullable=true)
	public char getRwchrcd() {
		return rwchrcd;
	}
	public void setRwchrcd(char rwchrcd) {
		this.rwchrcd = rwchrcd;
	}
	@Column(nullable=true)
	public char getRwptn() {
		return rwptn;
	}
	public void setRwptn(char rwptn) {
		this.rwptn = rwptn;
	}
	@Column(columnDefinition="DECIMAL(5,2)")
	public double getInqdr() {
		return inqdr;
	}
	public void setInqdr(double inqdr) {
		this.inqdr = inqdr;
	}
	@Column(nullable=true)
	public char getMsqmt() {
		return msqmt;
	}
	public void setMsqmt(char msqmt) {
		this.msqmt = msqmt;
	}
}
