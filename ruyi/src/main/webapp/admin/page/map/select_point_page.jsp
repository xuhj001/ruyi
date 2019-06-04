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

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<!--添加layer移动  弹出窗口的 插件支持-->
<script src="${pageContext.request.contextPath}/static/layer_mobile/layer.js"></script>
<!--添加layer移动  弹出窗口的 插件支持-->

<script>

var x = '${renwu.xiaoshou.baidu_x}'; 
var y = '${renwu.xiaoshou.baidu_y}';

var map;

$(function(){
	init_baidu_map_height();
	
	init_baidu_map();
});


function init_baidu_map(){
	// 百度地图API功能
	map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point(112.866683,34.172988), 20);
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	
	if(x){
		//添加坐标
		add_point(x,y);
	}else{
		layer.open({
		    content: '没有定位坐标呢'
		    ,btn: '我知道了'
		});
	}
	
	
	function showInfo(e){
		//alert(e.point.lng + ", " + e.point.lat);
		add_point(e.point.lng,e.point.lat);
		
		x= e.point.lng;
		y = e.point.lat;
	}
	map.addEventListener("click", showInfo);
}

function init_baidu_map_height(){
	var body_height = document.body.offsetHeight;
	$("#allmap").css("height",body_height+"px");
}

function add_point(x,y){
	map.clearOverlays();//清除覆盖物
	var marker = new BMap.Marker(new BMap.Point(x, y));
	map.addOverlay(marker);
	map.panTo(new BMap.Point(x, y));//移动到这个位置
}


function save(){
	$.post("/admin/xiaoshou/update",{id:${renwu.xiaoshou.id},baidu_x:x,baidu_y:y},function(result){
		
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
	
	<div id="allmap">
	</div>
	
	<input onclick="save()" style="position: absolute; bottom: 5px; right: 5px; height: 40px; width: 120px;" type="button" value="保存坐标" />
	
</body>
</html>