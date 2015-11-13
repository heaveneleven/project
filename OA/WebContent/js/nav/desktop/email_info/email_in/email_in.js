$(function(){
	$("#email_in_datagrid").datagrid({    
	    url:'', 
	    loadMsg:'数据加载中···',
	    pagination:true,
	    columns:[[    
	        {field:'priority',title:'优先级',width:300,align:'center'},    
	        {field:'is_read',title:'阅读状态',width:300,align:'center'},    
	        {field:'is_reply',title:'回复状态',width:300,align:'center'},
	        {field:'title',title:'邮件主题',width:600,align:'center'},
	        {field:'sender',title:'发件人',width:500,align:'center'},
	        {field:'recipient',title:'收件人',width:500,align:'center'},
	        {field:'tm',title:'发件时间',width:500,align:'center'},
	    ]],
	    fitColumns:true,
	    singleSelect:true
//	    toolbar: [{
//			iconCls: 'icon-edit',
//			handler: function(){alert('编辑按钮');}
//		},'-',{
//			iconCls: 'icon-help',
//			handler: function(){alert('帮助按钮');}
//		}]
	});  	
});