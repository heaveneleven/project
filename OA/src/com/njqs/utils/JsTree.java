package com.njqs.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author heaven
 *	JStree模型
 */
public class JsTree {
	private HashMap<String,String> attributes;
	private String state="";
	private HashMap<String,String> data;
	private List<JsTree> children=new ArrayList<JsTree>();
	
	public HashMap<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public HashMap<String, String> getData() {
		return data;
	}
	public void setData(HashMap<String, String> data) {
		this.data = data;
	}
	public List<JsTree> getChildren() {
		return children;
	}
	public void setChildren(List<JsTree> children) {
		this.children = children;
	}
}
