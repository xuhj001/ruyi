<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- 开启高速模式    -->
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<!-- 开启高速模式 -->

<!-- 加入移动布局 -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no" />
<!-- 加入移动布局 -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--添加 mui  支持-->
<script
	src="${pageContext.request.contextPath}/static/mui/js/mui.min.js"></script>
<link
	href="${pageContext.request.contextPath}/static/mui/css/mui.min.css"
	rel="stylesheet" />
<!--添加 mui  支持-->

<!--添加  jq  支持加载-->
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<!--添加 jq 支持加载-->

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<!--添加layer移动  弹出窗口的 插件支持-->
<script
	src="${pageContext.request.contextPath}/static/layer_mobile/layer.js"></script>
<!--添加layer移动  弹出窗口的 插件支持-->

<title>${title}</title>
<style type="text/css">
* {
	-webkit-user-select: text;
	-moz-user-select: text;
	-o-user-select: text;
	user-select: text;
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

.mui-navigate-right {
	font-size: 14px;
	font-family: "Helvitica Neue", Helvitica, Arial, "Hiragino Sans GB",
		"Microsoft YaHei", "Arial Regular", "Microsoft JhengHei", sans-serif;
}
</style>
</head>

<script type="text/javascript" charset="utf-8">
	
	mui.init();
	
	function bind(){
		
		//显示正在上传附件  进度条
		layer.open({
		  type: 2
		  ,content: '正在提交'
		  ,shadeClose:false
		});
		
		var id_ = $("#id_").val();
		var openid = $("#openid").val();
		
		if(id_.length<1){
			layer.closeAll();
			layer.open({
			    content: '请输入帐号!!!!!!'
			    ,btn: '我知道了'
			});
			return ; 
		}
		//   /admin/user/set_trueOpenid
		
		$.post("/admin/user/set_trueOpenid",{id_:id_,openid:openid},function(result){
			
			if(result.success){
				layer.closeAll();
				
				
				layer.open({
				    content: '绑定成功!!!!!!'
				    ,btn: '我知道了'
			    	,yes: function(index){
			    		window.opener=null;
			    		window.open('','_self');
			    		window.close();
			    		
					      layer.close(index);
					}
				});
				
				
				
				
			}else{
				layer.closeAll();
				layer.open({
				    content: '绑定出错了!!!!!!'
				    ,btn: '我知道了'
				});
				
			}
			
		},'json');
		
		
		
	}
	
</script>

<body>
	<header id="header" class="mui-bar mui-bar-nav">  
	<h1 class="mui-title">${mui_title}</h1>
	</header>
	
	<div class="mui-content">
		<div style="overflow: hidden; background-color: white;">
			<form class="mui-input-group" style="margin-top: 5px;">
				<div class="mui-input-row">
					<label>openid</label> <input readonly="readonly" type="text" id="openid" value="${wxuserinfo.openid }"
						class="mui-input-clear"   />
				</div>
				<div class="mui-input-row">
					<label>昵称</label> <input type="text" readonly="readonly" id="nickname" value="${wxuserinfo.nickname }"
						class="mui-input-clear"   />
				</div>
				<div class="mui-input-row">
					<label>帐号</label> <input type="text" id="id_"
						class="mui-input-clear" placeholder="" />
				</div>
			</form>
			请如实填写自己的帐号，此帐号是用于发送微信消息的。
			<div style="margin-top: 50px; padding: 10px 10px 10px 10px; ">
				<button type="button" id="save"  onclick="bind()" class="mui-btn mui-btn-success mui-btn-block" style="padding: 4px 0px;">绑定openid</button>
			</div>
		</div>
		<div style="height: 250px; width: 0px; overflow: hidden;"></div>
	</div>




</body>
</html>