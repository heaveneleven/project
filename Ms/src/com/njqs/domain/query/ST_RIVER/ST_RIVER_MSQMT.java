package com.njqs.domain.query.ST_RIVER;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author heaven
 * 测流方法及其代码
 */
@Entity
@Table(name="ST_RIVER_MSQMT")
public class ST_RIVER_MSQMT {
	private int id;
	private char msqmt;
	private char content;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(columnDefinition="CHAR(20)")
	public char getMsqmt() {
		return msqmt;
	}
	public void setMsqmt(char msqmt) {
		this.msqmt = msqmt;
	}
	public char getContent() {
		return content;
	}
	public void setContent(char content) {
		this.content = content;
	}
}
