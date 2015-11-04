package com.njqs.domain.query.ST_RIVER;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author heaven
 * 水势及其代码表
 */
@Entity
@Table(name="ST_RIVER_WPTN")
public class ST_RIVER_WPTN {
	private int id;
	private char wpth;
	private char content;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getWpth() {
		return wpth;
	}
	public void setWpth(char wpth) {
		this.wpth = wpth;
	}
	@Column(columnDefinition="CHAR(4)")
	public char getContent() {
		return content;
	}
	public void setContent(char content) {
		this.content = content;
	}
}
