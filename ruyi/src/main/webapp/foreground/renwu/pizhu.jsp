<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<style>
.mui-collapse-content {
	padding: 0px !important;
}
.mui-collapse-content table tr td {
	font-size: 13px;
	color: #333;
	border: 1px solid black;
}
</style>

<script>
		mui.previewImage();
</script>


<ul class="mui-table-view" style="margin-bottom: 10px;">

<li class="mui-table-view-cell mui-collapse">
		<a class="mui-navigate-right" href="#">查看【执行过程列表】</a>
		<div class="mui-collapse-content">
		<table style="width: 100%;">
			<tr>
				<td>节点名称</td>
				<td>开始时间</td>
				<td>结束时间</td>
			</tr>
			<c:forEach var="hai" items="${haiList }">
			<tr>
				<td>${hai.activityName}</td>
				<td><fmt:formatDate value="${hai.startTime}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td><fmt:formatDate value="${hai.endTime}" pattern="yyyy-MM-dd HH:mm"/></td>
			</tr>
			</c:forEach>
		</table>
		</div>
	</li>
	
	
	<li class="mui-table-view-cell mui-collapse">
		<a class="mui-navigate-right" href="#">查看【执行过程明细】</a>
		<div class="mui-collapse-content">
			
			<c:forEach var="comment" items="${myCommentList }" varStatus="status">
			
			
			<div style="overflow: hidden; border-bottom: 1px solid #D4D4D4;">
			<div style=" display: flex;display: -webkit-flex; overflow: hidden;">
				<div style="overflow: hidden; flex:1;-webkit-flex:1; padding:10px 0px 0px 10px; font-size: 14px;  color: #777;  <c:if test="${status.index==0}">color: #F38221; </c:if>     
    font-weight: bold;font-family: "Helvitica Neue", Helvitica, Arial, "Hiragino Sans GB","Microsoft YaHei", "Arial Regular", "Microsoft JhengHei", sans-serif;  ">
					${comment.userId }
				</div>
				<div style="overflow: hidden; flex:1;-webkit-flex:1; padding:10px 0px 0px 10px; color: #777; <c:if test="${status.index==0}">color: #F38221; </c:if>   font-size: 14px; ">
					<fmt:formatDate value="${comment.time}" pattern="yyyy-MM-dd HH:mm"/>
				</div>
			</div>
			<div style="overflow: hidden;">
				<div style="padding: 10px; font-size: 14px; font-family: "Helvitica Neue", Helvitica, Arial, "Hiragino Sans GB",
		"Microsoft YaHei", "Arial Regular", "Microsoft JhengHei", sans-serif;">
					${comment.message}
				</div>
			</div>
			
			
			<div style="overflow: hidden;">
				<c:forEach var="url" items="${comment.imageList }">
				<div style="float: left; width: 50px; height: 50px; overflow: hidden; margin-left: 10px; margin-bottom: 10px;">
					<img style="width: 100%; height: 100%;" src="${url }" data-preview-src="" data-preview-group="1" />
				</div>
				</c:forEach>
			</div>
			</div>
			
			
			</c:forEach>
		</div>
	</li>
	
	
	
</ul>



