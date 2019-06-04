<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 开启高速模式    -->
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<!-- 开启高速模式-->

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

/*表格 左边的显示的字太少了。 宽度变一下  */
.layui-form-pane .layui-form-label {
    width: 115px;
}
.layui-form-pane .layui-input-block {
    margin-left: 115px;
    left: -1px;
}
/*表格 左边的显示的字太少了。 宽度变一下  */


/*让 表单 行与行之间    间距缩小  */
.layui-form-item {
    margin-bottom: 3px;
}
/*让 表单 行与行之间    间距缩小  */
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
	var smsModel = $("#smsModel").val();
	var groupId = $("#group").val();
	var msg = $("#msg").val();
	
	//var createUser = $('input[name="createUser_cb"]:checked').val();
	
	// phone_sms_cb  wx_sms_cb
	var client = $('input[name="client_cb"]:checked').val();
	var phone_sms = $('input[name="phone_sms_cb"]:checked').val();
	var wx_sms = $('input[name="wx_sms_cb"]:checked').val();
	
	if(client){
	}else{
		client = 0;
	}
	if(phone_sms){
	}else{
		phone_sms = 0;
	}
	if(wx_sms){
	}else{
		wx_sms = 0;
	}
	
	
	
	
	if(smsModel.length<1){
		layer.closeAll();
		layer.msg('请输入短信模板!!!!!!');
		return ; 
	}
	
	$.post('/admin/smsTask/add.do',{taskName:taskName,smsModel:smsModel
		,procdefId:'${procdefId}',groupId:groupId,wx_sms:wx_sms,client:client,phone_sms:phone_sms,type:'${type}',msg:msg },function(result){
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
	      	<c:forEach var="smsTask" items="${list }">
		        <option value="${smsTask.taskName}">${smsTask.taskName}</option>
	        </c:forEach>
	      </select>
	    </div>
	  </div>
  		
  		
  	 <div class="layui-form-item">
	    <label class="layui-form-label">任务状态</label>
	    <div class="layui-input-block">
	      <select id="msg" lay-filter="aihao">
	        <option value="">无</option>
	      	<option value="通过">通过</option>
	      	<option value="驳回">驳回</option>
	      	<option value="关闭">关闭</option>
	      </select>
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">短信模板</label>
	    <div class="layui-input-block">
	      <input type="text" id="smsModel" name="smsModel" autocomplete="off" placeholder="短信模板" class="layui-input">
	    </div>
	  </div>
		
  	  <div class="layui-form-item">
	    <label class="layui-form-label">发送部门&组</label>
	    <div class="layui-input-block">
	      <select id="group" lay-filter="aihao">
	        <option value="-1">无</option>
	      	<c:forEach var="group" items="${groups }">
		        <option value="${group.id_}">${group.name_}</option>
	        </c:forEach>
	      </select>
	    </div>
	  </div>
	  
	   <div class="layui-form-item" pane="">
	    <label class="layui-form-label">客户</label>
	    <div class="layui-input-block">
	      <input type="checkbox" id="client_cb" value="1" name="client_cb" title="发送">
	    </div>
	  </div>
	  
	 
	 <div class="layui-form-item" pane="">
	    <label class="layui-form-label">手机短信</label>
	    <div class="layui-input-block">
	      <input type="checkbox" id="phone_sms_cb" value="1" name="phone_sms_cb" title="发送">
	    </div>
	 </div>
  		
  	<div class="layui-form-item" pane="">
	    <label class="layui-form-label">微信消息</label>
	    <div class="layui-input-block">
	      <input type="checkbox" id="wx_sms_cb" value="1" name="wx_sms_cb" title="发送">
	    </div>
	  </div>
	  
  		
  	 
	  

</form>

 <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" id="add" onclick="add()"  lay-submit="" lay-filter="demo1">立即提交</button>
    </div>
  </div>

</div>
</body>
</html>