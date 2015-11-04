package com.njqs.domain.query.ST_ORIG_MSG;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author heaven
 *	原始报文表
 *  保存终端传回的原始报文信息
 */
@Entity
@Table(name="ORIG_MSG")
public class ORIG_MSG {
	private int id;
	private char stcd;
	private Date tm;
	private String msg;
	private char crc;
	private Date moditime;
	/**信道，0为串口，1为GPRS*/
	private int Channel;
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
	@Column(columnDefinition = "text")
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Column(columnDefinition="CHAR(16)")
	public char getCrc() {
		return crc;
	}
	public void setCrc(char crc) {
		this.crc = crc;
	}
	@Column(columnDefinition="DATETIME")
	public Date getModitime() {
		return moditime;
	}
	public void setModitime(Date moditime) {
		this.moditime = moditime;
	}
	@Column(nullable=true)
	public int getChannel() {
		return Channel;
	}
	public void setChannel(int channel) {
		Channel = channel;
	}
}
