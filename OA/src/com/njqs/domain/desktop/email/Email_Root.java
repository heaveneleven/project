package com.njqs.domain.desktop.email;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author heaven
 * 邮箱目录，树
 */
@Entity
@Table(name="email_root")
public class Email_Root {
   private int id;
   /**tree id*/
   private String tid;
   /**树节点名称*/
   private String text;
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
