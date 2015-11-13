package com.njqs.service.user.register.inter;

import com.njqs.formmodel.personInfo.RegUserForm;
import com.njqs.utils.AjaxMsg;

public interface RegisterServiceInter {
	public AjaxMsg saveNewUser(RegUserForm module);
}
