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

<!--添加  jq  支持加载-->
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<!--添加 jq 支持加载-->


<!--添加 layui  支持加载-->
<link href="${pageContext.request.contextPath}/static/layui_1.0.9/css/layui.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/static/layui_1.0.9/layui.js"></script>
<!--添加 layui  支持加载-->

<script>

var url = '/admin/user/modify_ps';

function save(){
	//loading
	var index = layer.load(1, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
		
	var id_ = $("#id_").val();
	var pwd_ =  $("#pwd_").val();
	
	if(id_.length<1){
		layer.closeAll();//关闭loading
		
		layer.msg('请输入帐号!!!!!!');
		
		return ; 
	}
	if(pwd_.length<1){
		layer.closeAll();//关闭loading
		layer.msg('请输入密码!!!!!!');
		return ; 
	}
	
	$.post(url,{id_:id_,pwd_:pwd_},function(result){
		if(result.success){
			layer.closeAll();//关闭loading
				
				//询问框
				layer.confirm('密码修改成功', {
				  btn: ['确定','取消关闭窗口'] //按钮
				}, function(){
					window.parent.closeDlg(result.msg);
				}, function(){
				  
				});
			
			
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
	overflow: hidden;
}

.layui-form-item {
    margin-bottom: 3px;
}

</style>
</head>
<body>


<div style="padding: 10px; overflow: hidden;">
	<form class="layui-form layui-form-pane" action="">
	  <div class="layui-form-item">
	    <label class="layui-form-label">帐号</label>
	    <div class="layui-input-block">
		    <input type="text" readonly="readonly" id="id_" autocomplete="off" value="${user.id_ }" placeholder="请输入帐号" class="layui-input">
	    </div>
	  </div>
	 
			<div class="layui-form-item">
			    <label class="layui-form-label">新密码</label>
			    <div class="layui-input-block">
			      <input type="text" id="pwd_"  autocomplete="off" placeholder="请输入新密码" class="layui-input">
			    </div>
			  </div>
	  
	</form>
	
	<div style="overflow: hidden; margin-bottom: 10px;">
		 <button id="save" onclick="save()" class="layui-btn layui-btn-normal">提交修改</button>
	</div>
</div>
	


<script>

	layui.use(['form'], function(){
	  var form = layui.form();
	});
	
	layui.use('layer', function(){ //独立版的layer无需执行这一句
		  var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
	});
	
	
</script>


</body>
</html>