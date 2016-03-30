$('#major_tree').tree({
	lines:true,
	data: [{
		id : 1,
		iconCls :"icon-time_go",    
		text: '选择专业',
		
	},{
		id : 2,
		iconCls :"icon-book",    
		text: '专业目录' 
	},{
		id : 3,
		iconCls :"icon-find",    
		text: '查看已选' 
	}],
	onSelect : function(node){
		/**验证当前选项卡是否已存在*/
    	if($('#tabs').tabs('exists',node.text)==true){
    		$('#tabs').tabs('select',node.text);
    	}else{
    	$('#tabs').tabs('add',{    
    	    title:node.text,  
    	    iconCls:node.iconCls,
    	    //href:'sys/view?id='+node.id,
    	    closable:true    
    	});  
    }
	}
});

