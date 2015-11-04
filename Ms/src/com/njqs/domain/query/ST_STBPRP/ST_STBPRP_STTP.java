package com.njqs.domain.query.ST_STBPRP;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author heaven
 *	测站类别表，标识测站类别类型的两位字母代码
 */
@Entity
@Table(name="ST_STBPRP_STTP")
public class ST_STBPRP_STTP {
	private int id;
	private String type;
	private char sttp;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(columnDefinition="CHAR(2)")
	public char getSttp() {
		return sttp;
	}
	public void setSttp(char sttp) {
		this.sttp = sttp;
	}
}
