package com.njqs.domain.query.ST_RIVER;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/***
 * @author heaven
 * 河道水情表
 * 用于存储河道水文（水位）站测报的河道水情信息
 */
@Entity
@Table(name="ST_RIVER_R")
public class ST_RIVER_R {
	private int id;
	/**测站编码*/
	private char stcd;
	/**来报时间*/
	private Date tm;
	/**水位*/
	private double z;
	/**流量*/
	private double q;
	/**断面过水面积*/
	private double xsa;
	/**断面平均流速*/
	private double xsavv;
	/**断面最大流速*/
	private double xsmxv;
	/**河水特征码 明细见表ST_RIVER_FLWCHRCD*/
	private char flwchrcd;
	/**水势 明细见表ST_RIVER_WPTN*/
	private char wptn;
	/**测流方法 明细见表ST_RIVER_MSQMT*/
	private char msqmt;
	/**测积方法 明细见表ST_RIVER_MSANT*/
	private char msamt;
	/**测速方法 明细见表ST_RIVER_MSVMT*/
	private char msvmt;
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
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	@Column(columnDefinition="DECIMAL(9,3)")
	public double getQ() {
		return q;
	}
	public void setQ(double q) {
		this.q = q;
	}
	@Column(columnDefinition="DECIMAL(9,3)")
	public double getXsa() {
		return xsa;
	}
	public void setXsa(double xsa) {
		this.xsa = xsa;
	}
	@Column(columnDefinition="DECIMAL(5,3)")
	public double getXsavv() {
		return xsavv;
	}
	public void setXsavv(double xsavv) {
		this.xsavv = xsavv;
	}
	@Column(columnDefinition="DECIMAL(5,3)")
	public double getXsmxv() {
		return xsmxv;
	}
	public void setXsmxv(double xsmxv) {
		this.xsmxv = xsmxv;
	}
	@Column(nullable=true)
	public char getFlwchrcd() {
		return flwchrcd;
	}
	public void setFlwchrcd(char flwchrcd) {
		this.flwchrcd = flwchrcd;
	}
	@Column(nullable=true)
	public char getWptn() {
		return wptn;
	}
	public void setWptn(char wptn) {
		this.wptn = wptn;
	}
	@Column(nullable=true)
	public char getMsqmt() {
		return msqmt;
	}
	public void setMsqmt(char msqmt) {
		this.msqmt = msqmt;
	}
	@Column(nullable=true)
	public char getMsamt() {
		return msamt;
	}
	public void setMsamt(char msamt) {
		this.msamt = msamt;
	}
	@Column(nullable=true)
	public char getMsvmt() {
		return msvmt;
	}
	public void setMsvmt(char msvmt) {
		this.msvmt = msvmt;
	}
}
