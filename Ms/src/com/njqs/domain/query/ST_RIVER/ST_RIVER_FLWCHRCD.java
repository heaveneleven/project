package com.njqs.domain.query.ST_RIVER;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author heaven
 * 河水特征及其代码表
 */
@Entity
@Table(name="ST_RIVER_FLWCHRCD")
public class ST_RIVER_FLWCHRCD {
	private int id;
	private char flwchrcd;
	private char content;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getFlwchrcd() {
		return flwchrcd;
	}
	public void setFlwchrcd(char flwchrcd) {
		this.flwchrcd = flwchrcd;
	}
	@Column(columnDefinition="CHAR(20)")
	public char getContent() {
		return content;
	}
	public void setContent(char content) {
		this.content = content;
	}
}
