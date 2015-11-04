package com.njqs.domain.query.ST_STBPRP;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author heaven
 * 报讯等级表
 */
@Entity
@Table(name="ST_STBPRP_FRGRD")
public class ST_STBPRP_FRGRD {
	private int id;
	/**等级标识*/
	private int level;
	/**对应等级含义*/
	private String definition;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
}
