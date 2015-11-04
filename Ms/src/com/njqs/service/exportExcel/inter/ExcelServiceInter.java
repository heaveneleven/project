package com.njqs.service.exportExcel.inter;

import javax.servlet.http.HttpServletResponse;

import com.njqs.utils.AjaxMsg;

public interface ExcelServiceInter {
	public AjaxMsg exportTable(HttpServletResponse response);
}
