
$(function () {
    $("#menuTree").tree({
      data  : {
        type  : "json", 
        url: "sysManager/getMenu3.do",
        async : true
      },
      callback : {
          onselect : function(node,obj){//节点单击事件 
        	 // alert($(node).data("title"));
  	  		//console.info($(node).attr("id"));
     	}}
    });
});
