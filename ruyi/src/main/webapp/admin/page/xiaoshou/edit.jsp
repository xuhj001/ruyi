<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 开启高速模式   用户窗口    -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
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
var save_url =  '${save_url}';


$(function (){
	$("#install_").val('${xiaoshou.install_}');
	$("#jixingId").val('${xiaoshou.jixingId}');
	$("#shuiyuanId").val('${xiaoshou.shuiyuanId}');
	$("#installPosId").val('${xiaoshou.installPosId}');
	$("#ticheng").val('${xiaoshou.ticheng}');
});


function add(){
	var index = layer.load(1, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	
	var clientName = $("#clientName").val();
	var clientPhone = $("#clientPhone").val();
	var clientAddress = $("#clientAddress").val();
	var install_ = $("#install_").val();
	var ding_jine =  $("#ding_jine").val();
	var yu_jine = $("#yu_jine").val();
	var remark = $("#remark").val();
	var jixingId = $("#jixingId").val();
	var shuiyuanId = $("#shuiyuanId").val();
	var shuiya  = $("#shuiya").val();
	var installPosId = $("#installPosId").val();
	
	var ticheng = $("#ticheng").val();
	var ticheng_jine =  $("#ticheng_jine").val();
	
	if(ticheng_jine.length<1){
		ticheng_jine = 0 ; 
	}
	
	
	$.post(save_url,{clientName:clientName,clientPhone:clientPhone,clientAddress:clientAddress
		,install_:install_,ding_jine:ding_jine,yu_jine:yu_jine,remark:remark,jixingId:jixingId
		,shuiyuanId:shuiyuanId,shuiya:shuiya,installPosId:installPosId,ticheng:ticheng,ticheng_jine:ticheng_jine},function(result){
			
			if(result.success){
				//调用 父窗口的  关闭所有窗口 并且刷新 页面
				window.parent.closeDlg("修改成功");
			}else{
				layer.closeAll();
				layer.alert('修改失败!!!!!!!!');
			}
	},'json');
}

</script>
<style>
html, body {
height: 100%;
}
.layui-form-item {
    margin-bottom: 3px;
}
</style>

</head>
<body>

<div style="padding: 0px 10px 10px 10px;">
	<form class="layui-form layui-form-pane">
	  <div class="layui-form-item">
	    <label class="layui-form-label">客户姓名</label>
	    <div class="layui-input-block">
	      <input type="text" id="clientName"    autocomplete="off" value="${xiaoshou.clientName}"  class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">客户电话</label>
	    <div class="layui-input-block">
	      <input type="text"   id="clientPhone" value="${xiaoshou.clientPhone}"   autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">客户地址</label>
	    <div class="layui-input-block">
	      <input type="text"   id="clientAddress" value="${xiaoshou.clientAddress}"   autocomplete="off" class="layui-input">
	    </div>
	  </div>
	   
	  <div class="layui-form-item">
	    <label class="layui-form-label">是否安装</label>
	    <div class="layui-input-block">
	    <select id="install_">
		   <option value="安装">安装</option>
		   <option value="不安装">不安装</option>
        </select>
	    </div>
	   </div>
	   
	   <div class="layui-form-item">
	    <label class="layui-form-label">机型</label>
	    <div class="layui-input-block">
	    <select id="jixingId">
			<c:forEach var="jixing" items="${jixingList}">
		  	 <option value="${jixing.id}">${jixing.name}</option>
		   </c:forEach>
        </select>
	    </div>
	   </div>
	   
	   <div class="layui-form-item">
	    <label class="layui-form-label">水源</label>
	    <div class="layui-input-block">
	    <select id="shuiyuanId">
			<c:forEach var="shuiyuan" items="${shuiyuanList}">
		  	 <option value="${shuiyuan.id}">${shuiyuan.name}</option>
		   </c:forEach>
        </select>
        
	    </div>
	   </div>
	   
	   <div class="layui-form-item">
	    <label class="layui-form-label">水压</label>
	    <div class="layui-input-block">
	    	<input type="text"  id="shuiya" value="${xiaoshou.shuiya}"   autocomplete="off" class="layui-input">
	    </div>
	   </div>
	   
	   <div class="layui-form-item">
	    <label class="layui-form-label">安装位置</label>
	    <div class="layui-input-block">
	    <select id="installPosId">
			<c:forEach var="installPos" items="${installPosList}">
		  	 <option value="${installPos.id}">${installPos.name}</option>
		   </c:forEach>
        </select>
	    </div>
	   </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">定金</label>
	    <div class="layui-input-block">
	      <input type="text"  id="ding_jine" value="${xiaoshou.ding_jine}"   autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">余款</label>
	    <div class="layui-input-block">
	      <input type="text"   id="yu_jine" value="${xiaoshou.yu_jine}" placeholder="请输入" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">是否提成</label>
	    <div class="layui-input-block">
	    <select id="ticheng">
		   <option value="提成">提成</option>
		   <option value="不提成">不提成</option>
        </select>
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">提成金额</label>
	    <div class="layui-input-block">
	      <input type="text"  id="ticheng_jine" value="${xiaoshou.ticheng_jine}"   autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  
	  
	  
	  <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">备注</label>
	    <div class="layui-input-block">
	      <textarea   id="remark" class="layui-textarea">${xiaoshou.remark}</textarea>
	    </div>
	  </div>
	  
	  
	  

  	</form>
	
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <a class="layui-btn" onclick="add()"  lay-filter="demo1">立即修改</a>
	    </div>
	  </div>
	  
</div>     

<script>
	layui.use(['form'], function(){
	  var form = layui.form();
	});
</script>
</body>
</html>