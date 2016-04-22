package com.nuaa.service.admin.inter;

import java.util.Map;

import com.nuaa.domain.stu.StuInfo;
import com.nuaa.utils.AjaxMsg;
import com.nuaa.utils.DataGrid;
import com.nuaa.utils.PageInfo;

public interface AdminManageServiceInter {
	public DataGrid<Map<String, Object>> getStuInfo(PageInfo pager);
	public AjaxMsg addStuInfo(StuInfo stuInfo);
	public AjaxMsg delStuInfo(String ids);
	public AjaxMsg modStuInfo(StuInfo stuInfo);
	public DataGrid<Map<String,Object>> getStuMarks(PageInfo pager);
}
