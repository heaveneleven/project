package com.njqs.service.sysManager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.njqs.dao.sysManager.inter.MenuManagerDaoInter;
import com.njqs.domain.sysManager.Menu;
import com.njqs.service.sysManager.inter.MenuManagerServiceInter;
import com.njqs.utils.JsTree;
import com.njqs.utils.Tree;
@Service("menuService")
public class MenuManagerServiceImpl implements MenuManagerServiceInter{
	@Resource(name="menuDao")
	private MenuManagerDaoInter menuDao;
	/**
	 * EasyUI获得功能菜单树之同步加载
	 */
	@Override
	public List<Tree> getMenuTree() {
		List<Tree> list=new ArrayList<Tree>();
		Tree root=new Tree();//最上层根节点
		root.setId("0");
		root.setState("open");
		root.setText("信息服务");
		root.setChildren(this.getNodes(root));
		list.add(root);
		return list;
	}

	/**
	 * EasyUI递归获得子树节点
	 */
	@Override
	public List<Tree> getNodes(Tree node) {
		List<Menu> list= menuDao.getChilds(node.getId());
		List<Tree> childs=new ArrayList<Tree>();
		for(Menu menu : list){
			Tree child=new Tree();
			child.setId(Integer.toString(menu.getId()));
			child.setText(menu.getText());
			child.setState("closed");
			child.setChildren(this.getNodes(child));
			childs.add(child);
		}
		return childs;
	}
	/**
	 * EasyUI获得功能菜单树之异步加载
	 */
	@Override
	public List<Tree> getAsyMenuTree(String id) {
		List<Tree> childs=new ArrayList<Tree>();
		List<Menu> list= menuDao.getChilds(id);
		for(Menu menu : list){
			Tree child=new Tree();
			child.setId(Integer.toString(menu.getId()));
			child.setText(menu.getText());
			if(menuDao.hasChildren(child.getId()))
				child.setState("closed");
			childs.add(child);
		}
		return childs;
	}
	/**
	 * JStree获得功能菜单树之同步加载
	 */
	@Override
	public List<JsTree> getMenuTree2() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * JStree递归获得子树节点
	 */
	@Override
	public List<JsTree> getNodes(JsTree node) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * JStree获得功能菜单树之异步加载
	 */
	@Override
	public List<JsTree> getAsyMenuTree2(String id) {
		List<JsTree> childs=new ArrayList<JsTree>();
		List<Menu> list= menuDao.getChilds(id);
		for(Menu menu : list){
			JsTree child=new JsTree();
			HashMap<String,String> attributes=new HashMap<String,String>();
			attributes.put("id", Integer.toString(menu.getId()));
			child.setAttributes(attributes);
			HashMap<String,String> data=new HashMap<String,String>();
			data.put("title",menu.getText());
			child.setData(data);
			if(menuDao.hasChildren( Integer.toString(menu.getId()))){
				child.setState("closed");
				data.put("icon", "/Ms/images/tree/attach.png");
			}else{
			
				data.put("icon", "/Ms/images/tree/ok.png");
			}
			childs.add(child);
		}
		return childs;
	}
	
}
