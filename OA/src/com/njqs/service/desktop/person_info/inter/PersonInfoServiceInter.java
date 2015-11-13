package com.njqs.service.desktop.person_info.inter;

import com.njqs.formmodel.personInfo.PersonInfoForm;
import com.njqs.utils.AjaxMsg;

public interface PersonInfoServiceInter {
	public PersonInfoForm getPersonInfoForm(int id);
	public AjaxMsg savePersonForm(PersonInfoForm form,int id);
}
