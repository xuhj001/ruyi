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
	
	
	var id = $("#id").val();
	var da_ka_count = $("#da_ka_count").val();
	var web_site_url = $("#web_site_url").val();
	var days = $("#days").val();
	
	$.post(save_url,{id:id,da_ka_count:da_ka_count,web_site_url:web_site_url,days:days},function(result){
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
	    <label class="layui-form-label">id</label>
	    <div class="layui-input-block">
	    		<c:choose>
	    			<c:when test="${config!=null}">
	    				<input readonly="readonly" type="text" id="id" autocomplete="off" value="${config.id}" placeholder="请输入id" class="layui-input">
	    			</c:when>
	    		</c:choose>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">打卡次数</label>
	    <div class="layui-input-block">
		      <input type="text" id="da_ka_count" autocomplete="off" value="${config.da_ka_count }" placeholder="请输入打卡次数" class="layui-input">
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">web_url</label>
	    <div class="layui-input-block">
		      <input type="text" id="web_site_url" autocomplete="off" value="${config.web_site_url }" placeholder="请输入URL" class="layui-input">
	    </div>
	  </div>
	  
	   <div class="layui-form-item">
	    <label class="layui-form-label">换芯周期</label>
	    <div class="layui-input-block">
		      <input type="text" id="days" autocomplete="off" value="${config.days}" placeholder="请输入换芯周期" class="layui-input">
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