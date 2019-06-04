<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<!-- shiro标签 授权   -->
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- shiro标签 授权  -->


<header class="mui-bar mui-bar-nav">
	<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"><span style="font-size: 16px; line-height: 20px; height: 20px;">返回</span></a>
	<h1 class="mui-title">${mui_title}</h1>
</header>

<div class="mui-content">
	
	<ul class="mui-table-view" style="margin-bottom: 20px;">
		
		<li class="mui-table-view-cell"><a href="/renwu/wap/dingdan?title=我的销售单&currentUserId=${currentUser.id_}&processDefinitionKey=xiaoshou"
			class="mui-navigate-right">我的销售单</a></li>
		
		
		<shiro:hasPermission name="xiaoshou:dianpu">
		<li class="mui-table-view-cell"><a href="/renwu/wap/dingdan?title=我的店铺销售单&fendianId=${currentUser.weiXinUserInfo.fendianId}&processDefinitionKey=xiaoshou"
			class="mui-navigate-right">我的店铺销售单</a></li>
		</shiro:hasPermission>
		
		
		<shiro:hasPermission name="xiaoshou:all">
		<li class="mui-table-view-cell"><a href="/renwu/wap/dingdan?title=所有店铺全部销售单&processDefinitionKey=xiaoshou"
			class="mui-navigate-right">所有店铺全部销售单</a></li>
		</shiro:hasPermission>
		
		
	</ul>
	
	
<div style="overflow: hidden; width:0px; height: 70px;"></div>


</div>