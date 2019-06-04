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

<!--添加  jq  支持加载-->
<script	src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<!--添加 jq 支持加载-->

<!-- 引入 百度地图 -->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=C356fa79e683d3234f089c5dfd5fda2f"></script>
<!-- 引入 百度地图 -->

<!--添加 mui  支持-->
<script src="${pageContext.request.contextPath}/static/mui/js/mui.min.js"></script>
<link href="${pageContext.request.contextPath}/static/mui/css/mui.min.css" rel="stylesheet"/>
<!--添加 mui  支持-->


<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<!--添加layer移动  弹出窗口的 插件支持-->
<script src="${pageContext.request.contextPath}/static/layer_mobile/layer.js"></script>
<!--添加layer移动  弹出窗口的 插件支持-->

<script>
var longitude ; //经度
var latitude; //纬度
var x , y;
var map;

$(function(){
	
	getLocationByXiaoshouId();
	init_baidu_map();
	init_baidu_map_height();
});


function getLocationByXiaoshouId(){
	
	
	$.post("/admin/xiaoshou/findById",{id:${param.xiaoshouId}},function(result){
		if(result.baidu_x){
			add_point(result.baidu_x,result.baidu_y);
		}
	},'json');
	
}

function getLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition, showError);
	} else {
		alert("浏览器不支持地理定位。");
	}
}

function showPosition(position) {
	//alert('，经度:' + position.coords.longitude + ",,,纬度:"+ position.coords.latitude);
	longitude = position.coords.longitude;
	latitude = position.coords.latitude;
	
	//将 经纬度转成，百度坐标
	$.post("/baidu/format_point",{longitude:longitude,latitude:latitude},function(result){
		x = result.x;
		y = result.y;
		add_point(x,y);
	},'json');
}

function showError(error) {
	switch (error.code) {
	case error.PERMISSION_DENIED:
		alert("定位失败,用户拒绝请求地理定位");
		break;
	case error.POSITION_UNAVAILABLE:
		alert("定位失败,位置信息是不可用");
		break;
	case error.TIMEOUT:
		alert("定位失败,请求获取用户位置超时");
		break;
	case error.UNKNOWN_ERROR:
		alert("定位失败,定位系统失效");
		break;
	}
}

function init_baidu_map(){
	// 百度地图API功能
	map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 20);
	function showInfo(e){
		//alert(e.point.lng + ", " + e.point.lat);
		add_point(e.point.lng,e.point.lat);
		
		x= e.point.lng;
		y = e.point.lat;
	}
	map.addEventListener("click", showInfo);
}

function init_baidu_map_height(){
	var header_height = $("#header").height();
	var body_height = document.body.offsetHeight;
	$("#allmap").css("height",(body_height-header_height)+"px");
}


function add_point(x,y){
	map.clearOverlays();//清除覆盖物
	var marker = new BMap.Marker(new BMap.Point(x, y));
	map.addOverlay(marker);
	map.panTo(new BMap.Point(x, y));//移动到这个位置
}

function save(){
	layer.open({
		  type: 2
		  ,content: '正在提交'
		  ,shadeClose:false
	});
	$.post("/admin/xiaoshou/update",{id:${param.xiaoshouId},baidu_x:x,baidu_y:y},function(result){
		
		if(result.success){
			layer.closeAll();
			layer.open({
			    content: '保存成功'
			    ,btn: '我知道了'
			});
			
		}else{
			layer.closeAll();
			layer.open({
			    content: '保存失败'
			    ,btn: '我知道了'
			});
			
		}
	},'json');
	
}

</script>
<style>
body,html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-family:"微软雅黑";}
#allmap{width:100%;height:500px;}
</style>
</head>
<body>
<header id="header"  class="mui-bar mui-bar-nav">
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left "><span style="font-size: 16px; line-height: 20px; height: 20px;">返回</span></a>
    <h1 class="mui-title">${mui_title}</h1>
    <button onclick="javascript:location.replace(location.href);" class="mui-btn mui-btn-blue mui-btn-link mui-pull-right">刷新</button>
</header>
<div class="mui-content">
	<div id="allmap">
	</div>
	
	
	<input onclick="getLocation()" style="position: absolute; bottom: 5px; right: 130px; height: 40px; width: 120px;" type="button" value="定位我的坐标" />
	<input onclick="save()" style="position: absolute; bottom: 5px; right: 5px; height: 40px; width: 120px;" type="button" value="保存坐标" />
	
</div>
</body>
</html>