$(function(){
	/**我的桌面树菜单*/
	$('#desktop_tree').tree({    
	    url:'nav/tree/desktop.do',
	    lines:true,
	    onSelect:function(node){
	    	/**验证当前选项卡是否已存在*/
	    	if($('#tabs').tabs('exists',node.text)==true){
	    		$('#tabs').tabs('select',node.text);
	    	}else{
	    	$('#tabs').tabs('add',{    
	    	    title:node.text,  
	    	    iconCls:node.iconCls,
	    	    href:'desktop/view?id='+node.id,
	    	   // content:'Tab Body',    
	    	    closable:true    
//	    	    tools:[{    
//	    	        iconCls:'icon-mini-refresh',    
//	    	        handler:function(){    
//	    	            alert('refresh');    
//	    	        }    
//	    	    }]    
	    	});  
	    	
	    }
	    }
	});  
});