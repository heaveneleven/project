package com.njqs.service.desktop.log_info.inter;

import java.util.Map;

import com.njqs.formmodel.person_log.PersonLogForm;
import com.njqs.utils.AjaxMsg;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;

public interface LogInfoServiceInter {
	public DataGrid<Map<String,Object>> getLogInfo(PageInfo pager,int id);
	public AjaxMsg saveLog(PersonLogForm form,int id);
	public AjaxMsg saveModifyLog(PersonLogForm form); 
	public AjaxMsg delLogs(String ids);
	public PersonLogForm getLogForm(int log_id);
}
