package com.njqs.domain.query.ST_RSVR;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * @author heaven
 * 水库水容表，根据该表通过库水位计算当前库容
 */
@Entity
@Table(name="ST_ZVARL_B")
public class ST_ZVARL_B {
	private int id;
	/**测站编码*/
	private String stcd;
	/**施测时间*/
	private Date mstm;
	/**点序号*/
	private double ptno;
	/**库水位*/
	private double rz;
	/**蓄水量*/
	private double w;
	/**水面面积*/
	private double wsfa;
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
	@Column(nullable=false,columnDefinition="CHAR(8)")
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	@Column(nullable=false,columnDefinition="DATETIME")
	public Date getMstm() {
		return mstm;
	}
	public void setMstm(Date mstm) {
		this.mstm = mstm;
	}
	@Column(nullable=false,columnDefinition="DECIMAL(5,0)")
	public double getPtno() {
		return ptno;
	}
	public void setPtno(double ptno) {
		this.ptno = ptno;
	}
	@Column(nullable=true,columnDefinition="DECIMAL(7,3)")
	public double getRz() {
		return rz;
	}
	public void setRz(double rz) {
		this.rz = rz;
	}
	@Column(nullable=true,columnDefinition="DECIMAL(9,3)")
	public double getW() {
		return w;
	}
	public void setW(double w) {
		this.w = w;
	}
	@Column(nullable=true,columnDefinition="DECIMAL(7,0)")
	public double getWsfa() {
		return wsfa;
	}
	public void setWsfa(double wsfa) {
		this.wsfa = wsfa;
	}
	@Column(nullable=true,columnDefinition="DATETIME")
	public Date getModitime() {
		return moditime;
	}
	public void setModitime(Date moditime) {
		this.moditime = moditime;
	}
}
