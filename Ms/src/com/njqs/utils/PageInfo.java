package com.njqs.utils;

import java.io.Serializable;

/**
 * @author heaven
 * jqGrid工具类
 */
public class PageInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	/**共多少页,当前页为1*/
	private int page=1;
	/**每页显示多少行*/
	private int rows;
	/**是否进行查找*/
	public boolean _search;
	/**要查找的字段*/
	private String searchField;
	/**查找所要匹配的字符串*/
	private String searchString;
	/**排序方式*/
	private String sord;
	/**
	 * 获取firstindex,maxindex
	 * 
	 * @param total
	 * @return
	 */
	public int[] getFirstindexAndMaxindex() {
		return new int[] { rows * (page - 1), rows };
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public boolean is_search() {
		return _search;
	}
	public void set_search(boolean _search) {
		this._search = _search;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
}
