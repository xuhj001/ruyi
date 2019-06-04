<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- 开启高速模式    -->
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<!-- 开启高速模式 -->


<!-- 加入移动布局 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
<!-- 加入移动布局 -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--添加 mui  支持-->
<script src="${pageContext.request.contextPath}/static/mui/js/mui.min.js"></script>
<link href="${pageContext.request.contextPath}/static/mui/css/mui.min.css" rel="stylesheet"/>
<!--添加 mui  支持-->


<!--添加  jq  支持加载-->
<script	src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<!--添加 jq 支持加载-->


<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<!--异步上传 js-->
<script src="${pageContext.request.contextPath}/static/js/ajaxfileupload.js"></script>
<!--异步上传 js-->

<!--添加layer移动  弹出窗口的 插件支持-->
<script src="${pageContext.request.contextPath}/static/layer_mobile/layer.js"></script>
<!--添加layer移动  弹出窗口的 插件支持-->

<!--添加 图片 点击放大-->
<script src="${pageContext.request.contextPath}/static/mui/js/mui.zoom.js"></script>
<script src="${pageContext.request.contextPath}/static/mui/js/mui.previewimage.js"></script>
<!--添加 图片 点击放大-->


<!--添加  model页面的插件  包含有放大缩小的css   支持-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/common/renwu/model.css">
<!--添加  model页面的插件  包含有放大缩小的css   支持-->



<title>${pageTitle }</title>
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
.mui-navigate-right{
   font-size: 14px;
   font-family: "Helvitica Neue",Helvitica,Arial,"Hiragino Sans GB","Microsoft YaHei", "Arial Regular","Microsoft JhengHei",sans-serif;
}
</style>

</head>

<script type="text/javascript" charset="utf-8">
mui.init();
var taskName = '${taskName}';//taskName 放到外面。有些地方需要用。
</script>


<body>
<header id="header"  class="mui-bar mui-bar-nav">
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left "><span style="font-size: 16px; line-height: 20px; height: 20px;">返回</span></a>
    <h1 class="mui-title">${title}</h1>
    <button onclick="javascript:location.replace(location.href);" class="mui-btn mui-btn-blue mui-btn-link mui-pull-right">刷新</button>
</header>

<div class="mui-content">
	<jsp:include page="${detailPage}"/>
	<jsp:include page="${acceptPage}"/>
	<jsp:include page="${pizhuPage}"/>
	<jsp:include page="${addPizhuPage}"/>
	<jsp:include page="${banliPage}"/>
	
	<div style="height: 80px; width: 0px; overflow: hidden;">
	</div>
	
	
	<c:if test="${renwu.xiaoshou != null}">
		<jsp:include page="${map_select_point_page}"/>
	</c:if>
	
	
	<div style="height: 200px; width: 0px; overflow: hidden;">
	</div>
	
</div>









</body>
</html>