$(function(){
	$("#person_log_datagrid").datagrid({    
	    url:'person_log/log_info.do', 
	    loadMsg:'数据加载中···',
	    fit:'true',
	    idField:'log_id',
	    pagination:true,
	    checkOnSelect : true,
		selectOnCheck : true,
	    columns:[[
	    {
			field : "ck",
			checkbox : true
		}, {
			field : "id",
			hidden : true
		},     
	    {
			field:'log_type',
			title:'日志类型',
			width:100,
			align:'center',
	        formatter: function(value,row,index){
					if (row.log_type==0){
						return '个人日志';
					}else if (row.log_type==1){
						return '工作日志';
					}else{
						return ' ';
					}
	        	}
	    },    
	    {
	    	field:'log_title',
	    	title:'日志标题',
	    	width:200,
	    	align:'center'
	     },
	     {
	    	 field:'tm',
	    	 title:'修改时间',
	    	 width:200,
	    	 align:'center',
	    	 formatter: function(value,row,index){
	        		var myDate=new String(value);
	        		return myDate.split(" ",1);
	        	}
	    },{

	    	field:'log_content',
	    	title:'日志内容',
	    	width:500,align:'center',
	        formatter: function(value,row,index){
	        		return value.substr(0,16)+"···";
	        	}},
	    ]],
	    fitColumns:true,
	    toolbar:'#person_log_tb'
	});  	
});
/**添加日志*/
function addLog(){
   $("#add_log").dialog({    
    title: '添加日志',    
    width: 550,   
    maximizable:true,
    height: 518,    
    closed: false,    
    cache: false,
    iconCls:'icon-add',
    href: 'person_log/add_log',    
    modal: true   
});    

}
/**删除日志*/
function delLog(){
	var rows = $("#person_log_datagrid").datagrid('getChecked');
	if (rows && rows.length > 0) {
		parent.$.messager.confirm('提示', '是否删除这些记录?', function(r) {
			if (r) {
				var ids = [];
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].log_id);
				}
				$.ajax({
					url : "person_log/delete.do?ids="+ids.join(","),
					dataType : "json",
					success : function(data) {
						if (data && data.success) {
							if (data.msg && data.msg != "")
								parent.$.messager.alert('提示', data.msg);
							else
								parent.$.messager.alert('提示', "删除成功");
							$("#person_log_datagrid").datagrid('reload');
							$("#person_log_datagrid").datagrid('uncheckAll');// 把选择的checked记录全部清空
						} else {
							parent.$.messager.alert('错误', data.msg);
						}
			}
		});
			}});
	} else {
		parent.$.messager.alert('提示', "请选择需要删除的记录！");
	}
}
/**修改日志*/
function modifyLog(){
	var rows = $("#person_log_datagrid").datagrid('getChecked');
	if (rows && rows.length == 1) {
			/**修改日志*/
		   $("#modify_log").dialog({    
		    title: '修改日志',    
		    width: 550,   
		    maximizable:true,
		    height: 518,    
		    closed: false,    
		    cache: false,
		    iconCls:'icon-edit',
		    href: 'person_log/edit_log?log_id='+rows[0].log_id,    
		    modal: true   
		});    
	} else {
		parent.$.messager.alert('提示', "请选择需要修改的一条记录！");
		$("#person_log_datagrid").datagrid('uncheckAll');
	}
}
/**查看日志*/
function checkLog(){
	var rows = $("#person_log_datagrid").datagrid('getChecked');
	if (rows && rows.length == 1) {
			/**修改日志*/
		   $("#check_log").dialog({    
		    title: '查看日志',    
		    width: 550,   
		    maximizable:true,
		    height: 515,    
		    closed: false,    
		    cache: false,
		    iconCls:'icon-eye',
		    href: 'person_log/check_log?log_id='+rows[0].log_id,    
		    modal: true   
		});    
	} else {
		parent.$.messager.alert('提示', "请选择需要查看的一条记录！");
		$("#person_log_datagrid").datagrid('uncheckAll');
	}
}
