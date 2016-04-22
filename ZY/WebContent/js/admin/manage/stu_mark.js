$(function(){
	$("#stu_mark_datagrid").datagrid({    
	    url:'admin/manage/stu_mark.do', 
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
			width:300,
			align:'center',
	    },    
	    {
	    	field:'stuname',
	    	title:'学员姓名',
	    	width:350,
	    	align:'center'
	     },
	     {
	    	 field:'type',
	    	 title:'学员类别',
	    	 width:320,
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
	    	 width:320,
	    	 align:'center',
	        formatter: function(value,row,index){
					if (row.type==0){
						return '文科';
					}else{
						return '理科';
					}
	        	}
	    },
	    {
		    	field:'mark',
		    	title:'成绩',
		    	width:300,align:'center'
		  },{
		    	field:'grade',
		    	title:'排名',
		    	width:300,align:'center'
		      }
	    ]],
	    fitColumns:true,
	    toolbar:'#stu_mark_tb'
	});  	
});

/**修改学员成绩*/
function modifyMark(){
	var rows = $("#stu_mark_datagrid").datagrid('getChecked');
	if (rows && rows.length == 1) {
			/**修改日志*/
		   $("#mod_stu_mark").dialog({    
		    title: '修改学员成绩',    
		    width: 400,   
		    maximizable:true,
		    height: 450,    
		    closed: false,    
		    cache: false,
		    iconCls:'icon-edit',
		    href: 'admin/manage/mod_mark?stuno='+rows[0].stuno,    
		    modal: true   
		});    
	} else {
		parent.$.messager.alert('提示', "请选择需要修改的一条记录！");
		$("#stu_mark_datagrid").datagrid('uncheckAll');
	}
}
