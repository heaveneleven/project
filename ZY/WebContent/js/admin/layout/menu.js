$('#menuTree').tree({
	lines:true,
	data: [{
		id : 1,
		iconCls :"icon-mydesktop",    
		text: '学员信息管理'
		
	},{
		id : 2,
		iconCls :"icon-page",    
		text: '学员成绩管理' 
	}],
	onSelect : function(node){
		/**验证当前选项卡是否已存在*/
    	if($('#tabs').tabs('exists',node.text)==true){
    		$('#tabs').tabs('select',node.text);
    	}else{
    	$('#tabs').tabs('add',{    
    	    title:node.text,  
    	    iconCls:node.iconCls,
    	    href:'admin/manage/stu_info?id='+node.id,
    	    closable:true    
    	});  
    }
	}
});

