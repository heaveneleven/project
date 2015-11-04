package com.njqs.domain.query.ST_PPTN;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author heaven
 * 天气状况表
 */
@Entity
@Table(name="ST_PPTN_WTH")
public class ST_PPTN_WTH {
	private int id;
	/**天气状况代码*/
	private char wth;
	/**代码对应的具体天气状况*/
	private char content;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getWth() {
		return wth;
	}
	public void setWth(char wth) {
		this.wth = wth;
	}
	@Column(columnDefinition="CHAR(10)")
	public char getContent() {
		return content;
	}
	public void setContent(char content) {
		this.content = content;
	}
}
