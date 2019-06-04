<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<!-- 开启高速模式    -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<!-- 开启高速模式 -->

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<!-- 加入移动布局 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
<!-- 加入移动布局 -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<!--添加  本地 mui  支持-->
<script src="${pageContext.request.contextPath}/static/mui/js/mui.min.js"></script>
<link href="${pageContext.request.contextPath}/static/mui/css/mui.css" rel="stylesheet"/>
<!--添加  本地  mui  支持-->


<!--添加layer移动  弹出窗口的 插件支持-->
<script src="${pageContext.request.contextPath}/static/layer_mobile/layer.js"></script>
<!--添加layer移动  弹出窗口的 插件支持-->

<!--添加  jq  支持加载-->
<script	src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<!--添加 jq 支持加载-->

<title>${title}</title>
<style type="text/css">
*{
-webkit-user-select:text;
-moz-user-select:text;
-o-user-select:text;
user-select:text;
}


a {
	text-decoration: none;
}
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: none;
}
a:active {
	text-decoration: none;
}

</style>
</head>


<script type="text/javascript" charset="utf-8">
	mui.init();
</script>



<script>
//这里需要 添加输入 框的监听  如果有焦点了。把下面菜单 隐藏
$(function() {
	
	mui('#bottom_menu').on('tap','a',function(){document.location.href=this.href;});
	
	var inputs = $(":text");
	var textarea = $("textarea");
	
	textarea.focus(function() {
		//失去焦点  隐藏 下面的菜单
		$("#bottom_menu").hide();
	});
	textarea.blur(function() {
		//失去焦点  显示下面的菜单
		$("#bottom_menu").show();
	});
	
	inputs.focus(function() {
		//失去焦点  隐藏 下面的菜单
		$("#bottom_menu").hide();
	});
	inputs.blur(function() {
		//失去焦点  显示下面的菜单
		$("#bottom_menu").show();
	});
})

</script>

<body>

<jsp:include page="${page }"/>


<nav id="bottom_menu" class="mui-bar mui-bar-tab">
		<a  class="mui-tab-item" href="/wap_index.html">
			<span class="mui-icon mui-icon-home"></span>
			<span class="mui-tab-label">首页</span>
		</a>

		<a  class="mui-tab-item" href="/task/wap/daiban">
			<span class="mui-icon mui-icon-bars"></span>
			<span class="mui-tab-label">待办任务</span>
		</a>
		
		
		<a  class="mui-tab-item" href="/renwu/wap/dingdan?title=我的销售单&currentUserId=${currentUser.id_}&processDefinitionKey=xiaoshou">
			<span class="mui-icon mui-icon-arrowthinup"></span>
			<span class="mui-tab-label">我的销售单</span>
		</a>
		
		
		
		<a class="mui-tab-item" href="/wo" >
			<span class="mui-icon mui-icon-contact"></span>
			<span class="mui-tab-label">我</span>
		</a>
</nav>

</body>
</html>