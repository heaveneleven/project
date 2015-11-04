package com.njqs.domain.query.ST_STBPRP;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author heaven
 * 交换管理单位取值表
 */
@Entity
@Table(name="ST_STBPRP_LOCALITY")
public class ST_STBPRP_LOCALITY {
	private int id;
	private char locality;
	private char value;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(nullable = false, columnDefinition = "CHAR(10)")
	public char getLocality() {
		return locality;
	}
	public void setLocality(char locality) {
		this.locality = locality;
	}
	@Column(nullable = false, columnDefinition = "CHAR(10)")
	public char getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
	}
}
