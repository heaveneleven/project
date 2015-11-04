package com.njqs.domain.query.ST_RIVER;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author heaven
 *	水流速度的测量方法，测速方法及其代码
 */
@Entity
@Table(name="ST_RIVER_MSVMT")
public class ST_RIVER_MSVMT {
	private int id;
	private char msvmt;
	private char content;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getMsvmt() {
		return msvmt;
	}
	public void setMsvmt(char msvmt) {
		this.msvmt = msvmt;
	}
	@Column(columnDefinition="CHAR(20)")
	public char getContent() {
		return content;
	}
	public void setContent(char content) {
		this.content = content;
	}
}
