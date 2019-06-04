<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<!-- shiro标签 授权   -->
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!--  shiro标签 授权 -->

<div class="mui-content">
	
	<ul class="mui-table-view mui-grid-view mui-grid-9" style="margin-top: 0px;">
		
		<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
			<a	href="/task/wap/daiban?prodefkey=xiaoshou&title=销售待办任务"> <span class="mui-icon mui-icon-list"><span class="mui-badge">${daibanTotal}</span></span>
				<div class="mui-media-body">销售待办任务</div>
			</a>
		</li>
		
		<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a
			href="/xiaoshou/wap/create"> <span class="mui-icon mui-icon-flag"></span>
				<div class="mui-media-body">创建销售</div></a>
		</li>
		
		
		<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
			<a href="/renwu/wap/dingdan?title=我的销售单&currentUserId=${currentUser.id_}&processDefinitionKey=xiaoshou"> 
				<span class="mui-icon mui-icon-redo"></span>
				<div class="mui-media-body">我的销售单</div>
			</a>
		</li>
		
		
 
		
		
		<!--  
		<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a
			href="/renwu/query.html"> <span class="mui-icon mui-icon-search"></span>
				<div class="mui-media-body">查询订单</div></a></li>
		
		
		<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a
			href="/renwu/myDingdan.html"> <span class="mui-icon mui-icon-list"></span>
		<div class="mui-media-body">我的订单</div></a></li>
		
		
		<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a
			href="/leave/wap/create.html"> <span class="mui-icon mui-icon-list"></span>
		<div class="mui-media-body">我要请假</div></a></li>
		-->
		
		
	</ul>

</div>