package com.nuaa.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author heaven
 * EasyUI tree模型
 * id：绑定节点的标识值。
 *  text：显示的节点文本。
 * iconCls：显示的节点图标CSS类ID。
 * checked：该节点是否被选中。
 * state：节点状态，'open' 或 'closed'。
 * attributes：绑定该节点的自定义属性。
 */
public class Tree {
	private static final long serialVersionUID = -146960362437091341L;
	private String id;
	private String text;
	private String state="open";
	private boolean checked=false;
	private List<Tree> children=new ArrayList<Tree>();
	private String iconCls;
	private String pid;
	private HashMap<String,String> attributes;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public HashMap<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
