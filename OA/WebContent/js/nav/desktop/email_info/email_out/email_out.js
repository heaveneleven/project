$(function(){
	$("#email_out_datagrid").datagrid({    
	    url:'email_info/email_out.do', 
	    loadMsg:'数据加载中···',
	    pagination:true,
	    checkOnSelect : true,
		selectOnCheck : true,
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
	    singleSelect:true,
	    toolbar:'#email_out_tb'
	});  	
});
/**新建邮件*/
function addEmail(){
	/**隐藏掉中央layout，新建编辑center*/
	$("#email_info").layout('remove','center');
	$("#email_info").layout('add',{    
	    region: 'center', 
	    iconCls:"icon-email_add",
	    title: '新建邮件',    
	    split: false,
	    href:'email_info/add_email'
	}); 
}
/**删除邮件*/
function delEmail(){
	alert("del");
}