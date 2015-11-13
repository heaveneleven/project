$(function(){
	$("#email_info").layout(); 
	$("#email_info").layout('add',{    
	    region: 'center', 
	    iconCls:'icon-in_box',
	    title: '收件箱',    
	    split: false,
	    href:'email_info/email_in'
	}); 
	
	$('#email_root').tree({    
	    url:'email_info/root.do' ,
	    lines:true,
	    onClick: function(node){
	    	$("#email_info").layout('remove','center');
	    	$("#email_info").layout('add',{    
	    	    region: 'center', 
	    	    iconCls:node.iconCls,
	    	    title: node.text,    
	    	    split: false,
	    	    href:'email_info/view?id='+node.id
	    	}); 
		}
	});  
});
