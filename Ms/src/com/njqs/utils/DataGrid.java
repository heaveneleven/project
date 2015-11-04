package com.njqs.utils;
import java.util.List;

public class DataGrid<T> {
	private long total=0;
	private List<T> rows;
	public DataGrid(){}
	public DataGrid(long total,List<T> rows){
		this.setTotal(total);
		this.setRows(rows);
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
