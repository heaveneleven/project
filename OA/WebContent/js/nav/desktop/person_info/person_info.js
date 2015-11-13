$(function(){
	$('#person_info').panel({   
		  border:false,
		  fit:true,  
		  title: ''   
//		  tools: [{    
//		    iconCls:'icon-add',    
//		    handler:function(){alert('new')}    
//		  },{    
//		    iconCls:'icon-save',    
//		    handler:function(){alert('save')}    
//		  }]    
		});   
	/**layout*/
	$('#person_info_layout').layout(); 
	$("#person_info_layout").layout('add',{    
	    region: 'west',
	    iconCls:'icon-photo',
	    width: 350,    
	    title: '头像',    
	    split: true,
	    collapsible:false,
	    href:'person_info/person_head'
	});  
	$("#person_info_layout").layout('add',{    
	    region: 'center',
	    iconCls:'icon-user_suit', 
	    title: '个人信息',    
	    split: false,
	    href:'person_info/person_detail'
	});  
});
