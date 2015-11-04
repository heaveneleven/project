package com.njqs.domain.query.ST_STBPRP;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**保存实时地图中心位置经纬度坐标,该实体类对象的数据库表理论上只会保存一条数据*/
@Entity
public class location {
	private int id;
	/**经度*/
	private String lgtd;
	/**维度*/
	private String lttd;
	/**显示等级*/
	private int range;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLgtd() {
		return lgtd;
	}
	public void setLgtd(String lgtd) {
		this.lgtd = lgtd;
	}
	public String getLttd() {
		return lttd;
	}
	public void setLttd(String lttd) {
		this.lttd = lttd;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
}
