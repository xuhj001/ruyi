<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--添加 layer 弹出 窗口  支持加载-->
<script src="${pageContext.request.contextPath}/static/layer-v3.0.3/layer/layer.js"></script>
<!--添加 layer 弹出 窗口  支持加载-->
<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<script>

$(function(){
	$("#token").addClass("layui-this");
});


function refresh_token(){
	
	var index = layer.load(1, {
		shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	
	
	$.post("/admin/wx/refresh_token",{},function(result){
		if(result.success){
			layer.closeAll();
			
			//墨绿深蓝风
			layer.alert('刷新token成功', {
			  skin: 'layui-layer-molv' //样式类名
			  ,closeBtn: 0
			}, function(){
				location.replace(location.href);
			});
			
		}else{
			
		}
		
	},'json');
	
}

</script>

<style>
.layui-table-toolbar{
	overflow: hidden;
	width: 100%;
}
</style>


<fieldset class="layui-elem-field layui-field-title">
  <legend>token(一般是1个小时自动刷新一次，如果发送不了 微信消息可以尝试强制刷新token)</legend>
</fieldset>
 
<blockquote class="layui-elem-quote">${access_token.access_token}</blockquote>


<button class="layui-btn" onclick="refresh_token()">强制刷新</button>
