<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->
<style>
</style>
<script>

function refresh_token(){
	
	// $.post("/admin/wx/refresh_token",{},function(result){
	$.post("${pageContext.request.contextPath}/admin/wx/refresh_token",{},function(result){
		if(result.success){
			//墨绿深蓝风
			layer.msg('刷新成功');
		}else{
			layer.msg('刷新失败');
		}
	},'json');
	
}




</script>


<div style="overflow: hidden; height: 60px; width: 100%; background-color: #23262E; display: flex;display: -webkit-flex;">
	<div style="overflow: hidden; flex:1;-webkit-flex:1;">
	</div>
	
	<div style="width: 500px; line-height: 60px;">
		<div class="layui-btn-group">
<%--			<a href="/admin/main" class="layui-btn layui-btn-small layui-btn-normal" style="float: left;vertical-align:middle;"><i class="layui-icon">&#x1002;</i> 刷新一下</a>--%>
			<a href="${pageContext.request.contextPath}/admin/main" class="layui-btn layui-btn-small layui-btn-normal" style="float: left;vertical-align:middle;"><i class="layui-icon">&#x1002;</i> 刷新一下</a>
			<a onclick="refresh_token()" class="layui-btn layui-btn-small" style="float: left;vertical-align:middle;"><i class="layui-icon">&#x1002;</i> 刷新微信token</a>
<%--			<a href="/user/pc/logout" class="layui-btn layui-btn-danger layui-btn-small" style="float: left;vertical-align:middle;"><i class="layui-icon">&#xe64d;</i> 注销登陆</a>--%>
			<a href="${pageContext.request.contextPath}/user/pc/logout" class="layui-btn layui-btn-danger layui-btn-small" style="float: left;vertical-align:middle;"><i class="layui-icon">&#xe64d;</i> 注销登陆</a>
		</div>
	</div>
</div>