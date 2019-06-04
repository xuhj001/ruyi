<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 开启高速模式    -->
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

<!--添加  jq  支持加载-->
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<!--添加 jq 支持加载-->


<!--添加 layui  支持加载-->
<link href="${pageContext.request.contextPath}/static/layui_1.0.9/css/layui.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/static/layui_1.0.9/layui.js"></script>
<!--添加 layui  支持加载-->


<!--添加 layer 弹出 窗口  支持加载-->
<script src="${pageContext.request.contextPath}/static/layer-v3.0.3/layer/layer.js"></script>
<!--添加 layer 弹出 窗口  支持加载-->


<script>
layui.use(['form'], function(){
	  var form = layui.form()
	  ,layer = layui.layer;
});
	
	
$(function(){
	$("#fendianId").val('${fendianId}');
});



var url = '${url}';


function save(){
	var index = layer.load(1, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	
	var fendianId = $("#fendianId").val();

	$.post(url,{openid:'${userId}',fendianId:fendianId},function(result){
		if(result.success){
			//提交成功 设置按钮不可用 disabled="disabled"
			layer.closeAll();//关闭loading
			
			//调用 父窗口的  关闭所有窗口
			window.parent.closeDlg(result.msg);
		}else{
			layer.closeAll();//关闭loading
			layer.msg(result.msg);
		}
	},'json');
}


</script>
<style>
html, body {
	height: 100%;
}
</style>
</head>
<body>


	<div style="padding: 10px;">
		<form class="layui-form" action="" >
			  <div class="layui-form-item">
			    <label class="layui-form-label">选择分店</label>
			    <div class="layui-input-block">
			      <select id="fendianId" name="interest" lay-filter="aihao">
			      			<option value="">无</option>
			      		<c:forEach var="fendian" items="${fendianList }">
			      			<option value="${fendian.id}">${fendian.name}</option>
			      		</c:forEach>
			      </select>
			    </div>
			  </div>
		</form>
		<button id="save" onclick="save()" class="layui-btn layui-btn-normal">保存</button>
	</div>
	
	
	
	
</body>
</html>