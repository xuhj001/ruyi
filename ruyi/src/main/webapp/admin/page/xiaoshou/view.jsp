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
layui.use(['element', 'layer'], function(){
	  var element = layui.element();
	  var layer = layui.layer;
	  //监听折叠
	  element.on('collapse(test)', function(data){
	    //layer.msg('展开状态：'+ data.show);
	    
	  });
});
</script>
<style>
html, body {
}

.layui-form-item {
    margin-bottom: 3px;
}

.layui-colla-content {
	color: #000;
}

</style>
</head>
<body>

<div style="padding: 0px 10px 10px 10px; overflow: hidden;">
	<table class="layui-table" lay-skin="line">
	  <colgroup>
	    <col width="150">
	    <col>
	  </colgroup>
	  
	  <tbody>
	    <tr>
	      <td>客户姓名</td>
	      <td>${renwu.xiaoshou.clientName}</td>
	    </tr>
	    <tr>
	      <td>客户电话</td>
	      <td>${renwu.xiaoshou.clientPhone}</td>
	    </tr>
	    <tr>
	      <td>客户地址</td>
	      <td>${renwu.xiaoshou.clientAddress}</td>
	    </tr>
	    <tr>
	      <td>创建人</td>
	      <td>${renwu.createUser.first_}</td>
	    </tr>
	    <tr>
	      <td>创建时间</td>
	      <td><fmt:formatDate value="${renwu.createDateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
	    </tr>
	    <tr>
	      <td>是否安装</td>
	      <td>${renwu.xiaoshou.install_}</td>
	    </tr>
	    
	    <tr>
	      <td>机型</td>
	      <td>${renwu.xiaoshou.jixing.name}</td>
	    </tr>
	    <tr>
	      <td>水源</td>
	      <td>${renwu.xiaoshou.shuiyuan.name}</td>
	    </tr>
	    <tr>
	      <td>水压</td>
	      <td>${renwu.xiaoshou.shuiya}</td>
	    </tr>
	    <tr>
	      <td>安装位置</td>
	      <td>${renwu.xiaoshou.installPos.name}</td>
	    </tr>
	    
	    <tr>
	      <td>百度坐标x</td>
	      <td>${renwu.xiaoshou.baidu_x}</td>
	    </tr>
	    
	    <tr>
	      <td>百度坐标y</td>
	      <td>${renwu.xiaoshou.baidu_y}</td>
	    </tr>
	    
	    <tr>
	      <td>是否提成</td>
	      <td>${renwu.xiaoshou.ticheng}</td>
	    </tr>
	    
	    <tr>
	      <td>提成金额</td>
	      <td>${renwu.xiaoshou.ticheng_jine}</td>
	    </tr>
	    
	    <tr>
	      <td>定金</td>
	      <td>${renwu.xiaoshou.ding_jine}</td>
	    </tr>
	    
	    <tr>
	      <td>余款</td>
	      <td>${renwu.xiaoshou.yu_jine}</td>
	    </tr>
	    
	    <tr>
	      <td>备注</td>
	      <td>${renwu.xiaoshou.remark}</td>
	    </tr>
	    
	    <tr>
	      <td>最后一次换滤芯时间</td>
	      <td><fmt:formatDate value="${renwu.xiaoshou.last_change_xin_dataTime}" pattern="yyyy-MM-dd HH:mm"/></td>
	    </tr>
	    
	    
	  </tbody>
	</table>   
	
	
	
	
	<div class="layui-collapse" lay-filter="test">
	
		<div class="layui-colla-item">
	    <h2 class="layui-colla-title">查看【流程执行过程列表】</h2>
	    <div class="layui-colla-content" style="padding: 10px 3px;">
	    	<div class="layui-form">
				  <table class="layui-table">
				    <colgroup>
				      <col width="200"><!-- 任务节点名称 -->
				      <col width="200"><!-- 开始时间 -->
				      <col>            <!-- 结束时间 -->
				    </colgroup>
				    <thead>
				      <tr>
				        <th>任务节点名称</th>
				        <th>开始时间 </th>
				        <th>结束时间</th>
				      </tr> 
				    </thead>
				    <tbody>
				    
				    <c:forEach var="hai" items="${haiList }">
						<tr>
							<td>${hai.activityName}</td>
							<td><fmt:formatDate value="${hai.startTime}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td><fmt:formatDate value="${hai.endTime}" pattern="yyyy-MM-dd HH:mm"/></td>
						</tr>
					</c:forEach>
				      
				   </tbody>
	        </table>
	        </div>
	    	
	    </div>
	  </div>
	  
	  
	  
		<div class="layui-colla-item">
	    <h2 class="layui-colla-title">查看【流程执行过程明细】</h2>
	    <div class="layui-colla-content ">
	    	<c:forEach var="comment" items="${myCommentList }" varStatus="status">
	    	
	      	<div style="overflow: hidden; display: flex;display: -webkit-flex; ">
	      		<div style="overflow: hidden; width: 155px;">
	      			<fmt:formatDate value="${comment.time}" pattern="yyyy-MM-dd HH:mm"/>
	      		</div>
	      		 <div style="overflow: hidden; flex:1;-webkit-flex:1;">
	      		 	${comment.userId }
	      		 </div>
	      	</div>
	      	<div style="overflow: hidden;">
	      		${comment.message}
	      	</div>
	      	<div style="overflow: hidden; padding-bottom: 10px; border-bottom: 1px solid #9E9A9A;">
	      		<c:forEach var="url" items="${comment.imageList }">
	      		<img width="50px" style="cursor: pointer;" height="50px" onclick="window.open('${url}');" src="${url}" />
	      		</c:forEach>
	      	</div>
	      	</c:forEach>
	    </div>
	  </div>
	  
	  
	  
  </div>
  
  <div style="overflow:hidden; height: 200px; width: 0px;">
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