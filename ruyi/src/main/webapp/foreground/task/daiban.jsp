<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!-- JSTL -->

<!--添加 选项卡  支持加载-->
<script	src="${pageContext.request.contextPath}/static/mui/js/mui.pullToRefresh.js"></script>
<script	src="${pageContext.request.contextPath}/static/mui/js/mui.pullToRefresh.material.js"></script>
<!--添加 选项卡  支持加载-->

<!--加载 待办的 css js -->
<script	src="${pageContext.request.contextPath}/static/common/daiban/daiban.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/common/daiban/daiban.css">
<!--加载 待办的 css js -->

<script>
var prodefkey = '${prodefkey}';
</script>


<header class="mui-bar mui-bar-nav">
	<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left "><span
		style="font-size: 16px; line-height: 20px; height: 20px;">返回</span></a>
	<h1 class="mui-title">${mui_title}</h1>
	<a href="javascript:location.replace(location.href);"
		style="display: inline-block; height: 45px; right: 11px; line-height: 45px; position: absolute;">刷新</a>
</header>


<div class="mui-content">
<div id="slider" class="mui-slider mui-fullscreen">
		<div id="sliderSegmentedControl"
			class="mui-scroll-wrapper mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
			<div class="mui-scroll">
				<a class="mui-control-item mui-active" href="#weifenpei">未分配<span id="weifenpei_count"
				class="mui-badge mui-badge-success">0</span></a>
				<a class="mui-control-item" href="#yifenpei">已分配<span id="yifenpei_count"
				class="mui-badge mui-badge-success">0</span></a>
			</div>
		</div>
		
		<div class="mui-slider-group">
		<div id="weifenpei"
				class="mui-slider-item mui-control-content mui-active">
				<div id="scroll" class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<ul class="mui-table-view" style="margin-bottom: 70px; padding: 10px 10px 10px 10px;"  id="ul_weifenpei" page="1" rows="20" url="/admin/task/weifenpei" count_span_id="weifenpei_count" >
								
								<!--
								<a style="display: block; z-index:100; overflow: hidden;" onclick="banli(task.id,'task.name')" >
								<div class="item">
								<div style="overflow: hidden; margin-bottom: 5px;">
									<span style="overflow: hidden; display: inline-block; float: left; font-size: 14px; font-weight: bold; ">
									这里显示任务的标题
									</span>
								</div>
								<div class="entity_item">
									<div class="div_left">任务类型：</div><div class="div_right">售后</div>
								</div>
								<div class="entity_item">
									<div class="div_left">任务名称：</div><div class="div_right">售后处理</div>
								</div>
								<div class="entity_item">
									<div class="div_left">发起时间：</div><div class="div_right">yyyy-MM-dd HH:mm</div>
								</div>
								<div class="entity_item">
									<div class="div_left">任务金额：</div><div class="div_right">100.22</div>
								</div>
								<div class="entity_item">
									<div class="div_left">正在办理人：</div><div class="div_right">100.22</div>
								</div>
								</div>
								</a>
								-->
								 
								<button type="button" id="loading"  onclick="loading('ul_weifenpei')" class="mui-btn mui-btn-success mui-btn-block" style=" padding: 4px 0; margin-top: 5px;" >加载更多</button>
						</ul>
						
					</div>
				</div>
			</div>
			
			
			
			
			<div id="yifenpei" class="mui-slider-item mui-control-content ">
				<div id="scroll" class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<ul class="mui-table-view" style="margin-bottom: 70px; padding: 10px 10px 10px 10px;"  id="ul_yifenpei" page="1" rows="20" url="/admin/task/yifenpei" count_span_id="yifenpei_count" >
								<!--  
								<a style="display: block; z-index:100; overflow: hidden;"  onclick="banli(task.id,'task.name')" >
								<div class="item">
								<div style="overflow: hidden; margin-bottom: 5px;">
									<span style="overflow: hidden; display: inline-block; float: left; font-size: 14px; font-weight: bold; ">
									这里显示任务的标题
									</span>
								</div>
								<div class="entity_item">
									<div class="div_left">任务类型：</div><div class="div_right">售后</div>
								</div>
								<div class="entity_item">
									<div class="div_left">任务名称：</div><div class="div_right">售后处理</div>
								</div>
								<div class="entity_item">
									<div class="div_left">发起时间：</div><div class="div_right">yyyy-MM-dd HH:mm</div>
								</div>
								<div class="entity_item">
									<div class="div_left">任务金额：</div><div class="div_right">0.00</div>
								</div>
								</div>
								</a>
								-->
								<button type="button" id="loading"  onclick="loading('ul_yifenpei')" class="mui-btn mui-btn-success mui-btn-block" style=" padding: 4px 0; margin-top: 5px;" >加载更多</button>
						</ul>
					</div>
				</div>
			</div>
			
	
		</div>
	</div>
</div>		


