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



<!--异步上传 js-->
<script src="${pageContext.request.contextPath}/static/js/ajaxfileupload.js"></script>
<!--异步上传 js-->

<script>

var url = '${url}';


function save(){
	//loading
	var index = layer.load(1, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	ajaxUploadFile();
}



function ajaxUploadFile(){
    $.ajaxFileUpload({  
        url : url, //用于文件上传的服务器端请求地址  
        secureuri : false, //一般设置为false
        fileElementId : 'deployFile', //文件上传空间的id属性  <input type="file" id="file" name="file" />  
        type : 'post',  
        dataType : 'text', //返回值类型 一般设置为json  
        success : function(result) //服务器成功响应处理函数  
        {  
            var result=eval('('+result+')');
            if(result.success){
            	//调用 父窗口的  关闭所有窗口
    			window.parent.closeDlg(result.msg);
            }
        },  
        error : function(result)//服务器响应失败处理函数  
        {  
        }
    });  
    return false;
}

</script>
<style>
html, body {
	height: 100%;
	overflow: hidden;
}
</style>
</head>
<body>

	<div style="padding: 10px; overflow: hidden;">
		<div style="overflow: hidden; border-bottom: 1px solid black; padding-bottom: 10px; margin-bottom: 10px;">
			<input type="file" name="deployFile" id="deployFile">
		</div>
		
		<button id="save" onclick="save()" class="layui-btn layui-btn-normal">上传部署</button>
		
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