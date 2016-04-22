$('#order_manager').tree({
	lines:true,
	data: [{
		id : 1,
		iconCls :"icon-lock",    
		text: '待审核业务订单'
		
	},{
		id : 2,
		iconCls :"icon-undo",    
		text: '未配送业务订单' 
	},{
		id : 3,
		iconCls :"icon-book",    
		text: '所有订单' 
	}],
	onSelect : function(node){
		/**验证当前选项卡是否已存在*/
    	if($('#tabs').tabs('exists',node.text)==true){
    		$('#tabs').tabs('select',node.text);
    	}else{
    	$('#tabs').tabs('add',{    
    	    title:node.text,  
    	    iconCls:node.iconCls,
    	    href:'customer/change_business/show?id='+node.id,
    	    closable:true    
    	});  
    }
	}
});
$('#repertory_manager').tree({
	lines:true,
	data: [{
		id : 1,
		iconCls :"icon-basket_add",    
		text: '增加新商品'
		
	},{
		id : 2,
		iconCls :"icon-book_next",    
		text: '按客户编号管理入库' 
	},{
		id : 3,
		iconCls :"icon-cart_go",    
		text: '发货出库' 
	},{
		id : 4,
		iconCls :"icon-book_open",    
		text: '按客户编号商品一览' 
	}],
	onSelect : function(node){
		/**验证当前选项卡是否已存在*/
    	if($('#tabs').tabs('exists',node.text)==true){
    		$('#tabs').tabs('select',node.text);
    	}else{
    	$('#tabs').tabs('add',{    
    	    title:node.text,  
    	    iconCls:node.iconCls,
    	    href:'customer/transfer_business/show?id='+node.id,
    	    closable:true    
    	});  
    }
	}
});
$('#deliver_manager').tree({
	lines:true,
	data: [{
		id : 1,
		iconCls :"icon-undo",    
		text: '未配送和发货的订单'
	},{
		id : 2,
		iconCls :"icon-report",    
		text: '发货记录' 
	}],
	onSelect : function(node){
		/**验证当前选项卡是否已存在*/
    	if($('#tabs').tabs('exists',node.text)==true){
    		$('#tabs').tabs('select',node.text);
    	}else{
    	$('#tabs').tabs('add',{    
    	    title:node.text,  
    	    iconCls:node.iconCls,
    	    href:'customer/overseas_business/show?id='+node.id,
    	    closable:true    
    	});  
    }
	}
});
$('#sys_manager').tree({
	lines:true,
	data: [{
		id : 1,
		iconCls :"icon-user",    
		text: '用户管理'
		
	},{
		id : 2,
		iconCls :"icon-user_suit",    
		text: '客户管理' 
	},{
		id : 3,
		iconCls :"icon-group",    
		text: '通知公号管理' 
	},{
		id : 3,
		iconCls :"icon-joystick",    
		text: '角色权限管理' 
	}],
	onSelect : function(node){
		/**验证当前选项卡是否已存在*/
    	if($('#tabs').tabs('exists',node.text)==true){
    		$('#tabs').tabs('select',node.text);
    	}else{
    	$('#tabs').tabs('add',{    
    	    title:node.text,  
    	    iconCls:node.iconCls,
    	    href:'stu/info/person_detail?id='+node.id,
    	    closable:true    
    	});  
    }
	}
});