package com.njqs.service.sysManager.inter;

import java.util.List;

import com.njqs.utils.JsTree;
import com.njqs.utils.Tree;

public interface MenuManagerServiceInter {
	public List<Tree> getMenuTree();
	public List<Tree> getNodes(Tree node);
	public List<Tree> getAsyMenuTree(String id);
	public List<JsTree> getMenuTree2();
	public List<JsTree> getNodes(JsTree node);
	public List<JsTree> getAsyMenuTree2(String id);
}
