$('#change_business').tree({
	lines:true,
	data: [{
		id : 1,
		iconCls :"icon-time_go",    
		text: '提交交换订单'
		
	},{
		id : 2,
		iconCls :"icon-page",    
		text: '我的交换订单' 
	},{
		id : 3,
		iconCls :"icon-page_green",    
		text: '提交补充材料' 
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
$('#transfer_business').tree({
	lines:true,
	data: [{
		id : 1,
		iconCls :"icon-time_go",    
		text: '提交中转订单'
		
	},{
		id : 2,
		iconCls :"icon-page",    
		text: '我的中转订单' 
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
$('#overseas_business').tree({
	lines:true,
	data: [{
		id : 1,
		iconCls :"icon-time_go",    
		text: '提交备货发货订单'
	},{
		id : 2,
		iconCls :"icon-page",    
		text: '提交本地配送订单' 
	},{
		id : 3,
		iconCls :"icon-package",    
		text: '我的库存' 
	},{
		id : 4,
		iconCls :"icon-car",    
		text: '我的本地配送车' 
	},{
		id : 5,
		iconCls :"icon-page_green",    
		text: '我的本地配送订单' 
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
$('#return_business').tree({
	lines:true,
	data: [{
		id : 1,
		iconCls :"icon-time_go",    
		text: '提交退货订单'
		
	},{
		id : 2,
		iconCls :"icon-page",    
		text: '我的退货订单' 
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