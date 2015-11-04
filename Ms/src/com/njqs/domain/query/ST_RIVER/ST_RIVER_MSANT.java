package com.njqs.domain.query.ST_RIVER;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author heaven
 *	测积方法及其代码
 */
@Entity
@Table(name="ST_RIVER_MSANT")
public class ST_RIVER_MSANT {
	private int id;
	private char msant;
	private char content;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getMsant() {
		return msant;
	}
	public void setMsant(char msant) {
		this.msant = msant;
	}
	@Column(columnDefinition="CHAR(20)")
	public char getContent() {
		return content;
	}
	public void setContent(char content) {
		this.content = content;
	}
}
