<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- 开启高速模式    -->
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<!-- 开启高速模式 -->


<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<!-- 加入移动布局 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
<!-- 加入移动布局 -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<!--添加 mui  支持-->
<script src="http://apps.bdimg.com/libs/mui/2.8.0/js/mui.min.js"></script>
<link href="http://apps.bdimg.com/libs/mui/2.8.0/css/mui.css" rel="stylesheet"/>
<!--添加 mui  支持-->


<!--添加  jq  支持加载-->
<script	src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<!--添加 jq 支持加载-->

<title>登陆</title>
</head>
<script type="text/javascript" charset="utf-8">


	var flag = false ;
	mui.init();
    function set(){
    	 var user = $("#id_").val();
    	 var ps = $("#pwd_").val();
    	 window.localStorage.setItem("user", user);
    	 window.localStorage.setItem("ps", ps);
    }
    function get(){
    	 var user = window.localStorage.getItem("user");
         var ps=window.localStorage.getItem("ps");
         if(user){
        	 $("#id_").val(user);
         }
         if(ps){
        	 $("#pwd_").val(ps);
         }
    }
    $(function (){
    	get();
    });
    
    function sub(){
    	flag = $("#isRemeber").hasClass('mui-active');
    	if(flag){
    		set();
    	}
    	$("#form").submit();
    }
    

    
</script>
<body>
<header class="mui-bar mui-bar-nav">
		<h1 class="mui-title">登陆</h1>
</header>



<div class="mui-content">
	<div style=" padding-top:80px;  margin-bottom: 10px; background-color: white;">
		<form id="form" class="mui-input-group" action="${pageContext.request.contextPath}/user/wap/login" >
			<div class="mui-input-row">
				<label>用户名</label>
				<input type="text" class="mui-input-clear" id="id_"  name="id_" placeholder="请输入用户名">
			</div>
			<div class="mui-input-row">
				<label>密码</label>
				<input type="password" id="pwd_" name="pwd_" class="mui-input-clear" placeholder="请输入密码">
			</div>
			
			<div class="mui-input-row">
				<label>记住密码</label>
				<div id="isRemeber" class="mui-switch mui-active">
						<div class="mui-switch-handle"></div>
				</div>
			</div>
			
			<input type="hidden" name="err_url" class="mui-input-clear" value="android_login" >
			<input type="hidden" name="bj" class="mui-input-clear" value="android" >
			<div style="margin-bottom: 10px; margin-top: 50px;">
				 <button type="button" onclick="sub()"   class="mui-btn mui-btn-warning mui-btn-block" style="padding:5px 0px 5px 0px;">登陆</button>
			</div>
		</form>
	</div>	
	
	

</body>
</html>