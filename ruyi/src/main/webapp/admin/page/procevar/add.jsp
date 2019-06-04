<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 开启高速模式 设置短信窗口    -->
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

<script>

</script>
<style>

html, body {
	height:100%;
	overflow: hidden;
}


/*让 表单 行与行之间    间距缩小  */
.layui-form-item {
    margin-bottom: 3px;
}
</style>


<script>

layui.use(['form'], function(){
  var form = layui.form();
});


//加载layer 弹出窗口的支持
layui.use('layer', function(){ //独立版的layer无需执行这一句
	  var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
});

function add(){

	var index = layer.load(1, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	
	var taskName = $("#taskName").val();
	var variableName = $("#variableName").val();
	var variableValue = $("#variableValue").val();
	
	console.log(taskName);
	console.log(variableName);
	console.log(variableValue);

	if(variableName.length<1){
		layer.closeAll();
		layer.msg('请输入变量名称!!!!!!');
		return ; 
	}
	
	if(variableValue.length<1){
		layer.closeAll();
		layer.msg('请输入变量值!!!!!!');
		return ; 
	}
	
	
	$.post('/admin/procevar/add',{taskName:taskName,variableName:variableName
		,variableValue:variableValue,procdefId:'${procdefId}' },function(result){
		if(result.success){
			//调用 父窗口的  关闭所有窗口 并且刷新 页面
			window.parent.closeDlg(result.msg);
			
		}else{
			layer.closeAll();
			
			layer.msg(result.msg);
		}
	},'json');
	
}

</script>
</head>

<body>
<div style="padding: 10px 30px 10px 10px; height:100%;  overflow: hidden;">


<form class="layui-form layui-form-pane">
	  <div class="layui-form-item">
	    <label class="layui-form-label">任务名称</label>
	    <div class="layui-input-block">
	      <select id="taskName" lay-filter="aihao">
	      	<c:forEach var="smsTask" items="${taskList }">
		        <option value="${smsTask.taskName}">${smsTask.taskName}</option>
	        </c:forEach>
	      </select>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">流程变量名称</label>
	    <div class="layui-input-block">
	      <input type="text" id="variableName" name="smsModel" autocomplete="off" placeholder="流程变量名称" class="layui-input">
	    </div>
	  </div>
	
  	  <div class="layui-form-item">
	    <label class="layui-form-label">流程变量值</label>
	    <div class="layui-input-block">
	      <select id="variableValue" lay-filter="aihao">
		        <option value="当前用户">当前用户</option>
	        	<option value="创建人">创建人</option>
	        	<option value="是否安装">是否安装</option>
	      </select>
	    </div>
	  </div>
</form>


 <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" id="add"  onclick="add()"  lay-submit="" lay-filter="demo1">立即提交</button>
    </div>
 </div>


</div>
</body>
</html>