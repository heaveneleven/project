package com.njqs.dao.desktop.email_info.inter;

import java.util.List;

import com.njqs.dao.base.inter.BaseDaoInter;
import com.njqs.domain.desktop.email.Email_Root;
import com.njqs.utils.Tree;

public interface EmailInfoDaoInter extends BaseDaoInter<Email_Root, Integer> {
	public List<Tree> getEmailTree();
}
