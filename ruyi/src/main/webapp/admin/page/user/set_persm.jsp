<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 开启高速模式   用户窗口    -->
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 开启高速模式 -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<!-- 加入移动布局 -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no" />
<!-- 加入移动布局 -->

<!-- 引入easyui支持 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/easy-ui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/easy-ui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/easy-ui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/easy-ui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/easy-ui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/easy-ui/locale/easyui-lang-zh_CN.js"></script>
<!-- 引入easyui支持 -->

<!--添加 layui  支持加载-->
<link href="${pageContext.request.contextPath}/static/layui_1.0.9/css/layui.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/static/layui_1.0.9/layui.js"></script>
<!--添加 layui  支持加载-->


<script>
var userId = '${userId}';
//shouquantree

var url = '/tree/getCheckedTreeMenu.do?userId='+userId;

$(function(){
	$.post(url,{},function(result){ 
		   $("#shouquantree").tree({
			  data:eval(result),
			  lines:true,
			  onLoadSuccess:function(){
				   $("#shouquantree").tree('expandAll');
			  },
			  onCheck:function(node,checked){
			  		if(checked){
						checkNode($('#shouquantree').tree('getParent',node.target));
					}
			  }
		  });	
	});
	
});


function save(){
	
	var nodes = $("#shouquantree").tree("getChecked");
	var ids=[];
	for(var i=0;i<nodes.length;i++){
		ids.push(nodes[i].id);
	}
	var str = ids.join(",");
	
	$.post('/admin/wxuser/update',{openid:userId,treeIds:str},function(result){
		if(result.success){
			//调用 父窗口的  关闭所有窗口 并且刷新 页面
			window.parent.closeDlg(result.msg);
		}else{
			alert(result.msg);
		}
		
	},'json');
}



</script>
<style>
html, body {
margin: 0px;
padding: 0px;
}

</style>
</head>
<body>
<ul id="shouquantree" class="easyui-tree" data-options="checkbox:true,cascadeCheck:false,lines:true"></ul>



<button onclick="save()" class="layui-btn" style="margin-top: 50px; margin-left: 20px; margin-bottom: 20px; ">保存</button>
 
</body>
</html>