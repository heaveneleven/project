package com.njqs.domain.sys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 系统管理树
 * @author heaven
 *
 */
@Entity
@Table(name="sys_root")
public class SysRoot {
	public int id;
	/**tree id*/
	public String tid;
	/**菜单名*/
	public String text;
	/**菜单对应url*/
	public String url;
	/**菜单对应图标名*/
	public String ico;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIco() {
		return ico;
	}
	public void setIco(String ico) {
		this.ico = ico;
	}
}
