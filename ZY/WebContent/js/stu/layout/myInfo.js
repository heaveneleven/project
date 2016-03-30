$('#tt').tree({
	lines:true,
	data: [{
		id : 1,
		iconCls :"icon-mydesktop",    
		text: '资料查看'
		
	},{
		id : 2,
		iconCls :"icon-page",    
		text: '成绩查询' 
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

