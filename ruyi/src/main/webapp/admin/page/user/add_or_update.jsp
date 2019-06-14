<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 开启高速模式    -->
<meta name="renderer" content="webkit|ie-comp|ie-stand">
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
	<%--var save_url = '${save_url}';--%>
	var save_url = '${pageContext.request.contextPath}/${save_url}';


function save(){
	//loading
	var index = layer.load(1, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
		
	var id_ = $("#id_").val();
	var first_ = $("#first_").val();
	var last_ = $("#last_").val();
	var pwd_ = $("#pwd_").val();
	var email_ = $('input[name="email_"]:checked').val();
	if(email_){
	}else{
		email_ = 0 ;
	}
	
	if(id_.length<1){
		layer.closeAll();//关闭loading
		layer.msg('请输入帐号!!!!!!');
		return ; 
	}
	if(first_.length<1){
		layer.closeAll();//关闭loading
		layer.msg('请输入真实姓名!!!!!!');
		return ; 
	}
	if(last_.length<1){
		layer.closeAll();//关闭loading
		layer.msg('请输入电话!!!!!!');
		return ; 
	}
	
	
	$.post(save_url,{id_:id_,first_:first_,last_:last_,pwd_:pwd_,email_:email_},function(result){
		if(result.success){
			//调用 父窗口的  关闭所有窗口 并且刷新 页面
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
}
.layui-form-item {
    margin-bottom: 3px;
}
</style>
</head>
<body>

<div style="padding: 10px; overflow: hidden;">
	<form class="layui-form layui-form-pane">
	
	  <div class="layui-form-item">
	    <label class="layui-form-label">帐号</label>
	    <div class="layui-input-block">
	    		<c:choose>
	    			<c:when test="${user!=null}">
	    				<input readonly="readonly" type="text" id="id_" autocomplete="off" value="${user.id_ }" placeholder="请输入帐号" class="layui-input">
	    			</c:when>
	    			<c:when test="${user==null}">
	    				<input type="text" id="id_" autocomplete="off" value="${user.id_ }" placeholder="请输入帐号" class="layui-input">
	    			</c:when>
	    		</c:choose>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">真实姓名</label>
	    <div class="layui-input-block">
		      <input type="text" id="first_" autocomplete="off" value="${user.first_ }" placeholder="请输入真实姓名" class="layui-input">
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">电话</label>
	    <div class="layui-input-block">
		      <input type="text" id="last_" autocomplete="off" value="${user.last_ }" placeholder="请输入电话" class="layui-input">
	    </div>
	  </div>
	  
	  
	  <c:choose>
		    <c:when test="${user!=null}">
		    	<input type="hidden" id="pwd_" autocomplete="off" value="" placeholder="请输入密码" class="layui-input">
		    </c:when>
		    
		     <c:when test="${user==null }">
		     	<div class="layui-form-item">
				    <label class="layui-form-label">密码</label>
				    <div class="layui-input-block">
					      <input type="text" id="pwd_" autocomplete="off" value="${user.pwd_}" placeholder="请输入密码" class="layui-input">
				    </div>
				  </div>
		     </c:when>
	 </c:choose>
	
	
	  <!-- checkbox 默认开  checked="" 去掉就是默认关  -->
	  <div class="layui-form-item" pane="">
         	<label class="layui-form-label">是否电脑</label>
		    <div class="layui-input-block">
		    <c:choose>
		    	<c:when test="${user!=null}">
		    		<c:if test="${user.email_=='1'}">
		    			<input type="checkbox" id="email_" checked="" name="email_" value="1" lay-filter="email_"  lay-skin="switch" lay-text="开|关" />
		    		</c:if>
		    		<c:if test="${user.email_=='0'}">
		    			<input type="checkbox" id="email_" name="email_" value="1" lay-filter="email_"  lay-skin="switch" lay-text="开|关" />
		    		</c:if>
		    	</c:when>
		    	
		    	<c:when test="${user==null}">
		    		<input type="checkbox" id="email_" name="email_" value="1" lay-filter="email_"  lay-skin="switch" lay-text="开|关" />
		    	</c:when>
		    	
		    </c:choose>
		    </div>
		    
	  </div>
	  
	  
	  </form>
	  
		<div class="site-demo-button" style="margin-top: 20px;">
		  <button id="save" onclick="save()" class="layui-btn site-demo-layedit" data-type="content">${btn_text }</button>
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