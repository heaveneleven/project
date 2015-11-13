package com.njqs.service.desktop.email_info.inter;

import java.util.List;
import java.util.Map;

import com.njqs.formmodel.emailInfo.EmailOutForm;
import com.njqs.utils.DataGrid;
import com.njqs.utils.PageInfo;
import com.njqs.utils.Tree;

public interface EmailInfoServiceInter {
	public List<Tree> getEmailRootTree();
	/**获取发件箱表格信息*/
	public DataGrid<Map<String, Object>> getEmailOutInfo(PageInfo pager,int login_id);
}
