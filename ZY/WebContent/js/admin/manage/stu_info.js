$(function(){
	$("#stu_info_datagrid").datagrid({    
	    url:'admin/manage/stu_info.do', 
	    loadMsg:'数据加载中···',
	    fit:'true',
	    idField:'id',
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
			field:'stuno',
			title:'学员学号',
			width:270,
			align:'center',
	    },    
	    {
	    	field:'stuname',
	    	title:'学员姓名',
	    	width:250,
	    	align:'center'
	     },
	     {
	    	 field:'type',
	    	 title:'学员类别',
	    	 width:220,
	    	 align:'center',
	        formatter: function(value,row,index){
					if (row.type==0){
						return '士兵生';
					}else{
						return '青年生';
					}
	        	}
	    },{
	    	 field:'classification',
	    	 title:'文理',
	    	 width:220,
	    	 align:'center',
	        formatter: function(value,row,index){
					if (row.type==0){
						return '文科';
					}else{
						return '理科';
					}
	        	}
	    },{

	    	field:'birth',
	    	title:'出生年月',
	    	width:400,
	    	align:'center',
	    	formatter: function(value,row,index){
	        		var myDate=new Date(value);
	        		return myDate.toLocaleDateString().replace(/(\d+)[^\d](\d+)[^\d](\d+)[^\d]/, "$1-$2-$3");
	       
	        	}
	    },{
	    	field:'sex',
	    	title:'性别',
	    	width:220,
	    	align:'center',
	        formatter: function(value,row,index){
				if (row.sex==0){
					return '女';
				}else{
					return '男';
				}
        	}
	      },{
		    	field:'tel',
		    	title:'联系电话',
		    	width:500,align:'center'
		  },{
		    	field:'email',
		    	title:'E-mail',
		    	width:500,align:'center'
		      }
	    ]],
	    fitColumns:true,
	    toolbar:'#stu_info_tb'
	});  	
});
/**添加学员*/
function addStu(){
   $("#add_stu").dialog({    
    title: '添加学员',    
    width: 500,   
    maximizable:true,
    height: 450,    
    closed: false,    
    cache: false,
    iconCls:'icon-add',
    href: 'admin/manage/add_stu',    
    modal: true   
});    

}
/**删除学员信息*/
function delStu(){
	var rows = $("#stu_info_datagrid").datagrid('getChecked');
	if (rows && rows.length > 0) {
		parent.$.messager.confirm('提示', '是否删除该学员记录?', function(r) {
			if (r) {
				var ids = [];
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
				}
				$.ajax({
					url : "admin/manage/del_stu_info.do?ids="+ids.join(","),
					dataType : "json",
					success : function(data) {
						if (data && data.success) {
							if (data.msg && data.msg != "")
								parent.$.messager.alert('提示', data.msg);
							else
								parent.$.messager.alert('提示', "删除成功");
							$("#stu_info_datagrid").datagrid('reload');
							$("#stu_info_datagrid").datagrid('uncheckAll');// 把选择的checked记录全部清空
						} else {
							parent.$.messager.alert('错误', data.msg);
						}
			}
		});
			}});
	} else {
		parent.$.messager.alert('提示', "请选择需要删除的学员记录！");
	}
}
/**修改学员信息*/
function modifyStu(){
	var rows = $("#stu_info_datagrid").datagrid('getChecked');
	if (rows && rows.length == 1) {
			/**修改日志*/
		   $("#mod_stu_info").dialog({    
		    title: '修改学员信息',    
		    width: 400,   
		    maximizable:true,
		    height: 450,    
		    closed: false,    
		    cache: false,
		    iconCls:'icon-edit',
		    href: 'admin/manage/mod_stu?stuno='+rows[0].stuno,    
		    modal: true   
		});    
	} else {
		parent.$.messager.alert('提示', "请选择需要修改的一条记录！");
		$("#stu_info_datagrid").datagrid('uncheckAll');
	}
}
