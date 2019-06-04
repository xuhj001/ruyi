<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- 开启高速模式    -->
<meta name="renderer" content="webkit">
<!-- 开启高速模式 -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<!-- 加入移动布局 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
<!-- 加入移动布局 -->

<!--添加  jq  支持加载-->
<script	src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<!--添加 jq 支持加载-->


<!--添加 layui  支持加载-->
<link href="${pageContext.request.contextPath}/static/layui_1.0.9/css/layui.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/static/layui_1.0.9/layui.js"></script>
<!--添加 layui  支持加载-->

<script>
var add_flag = true;
layui.use(['form'], function(){
	  var form = layui.form();
});

layui.use('element', function(){
	var element = layui.element(); //导航的hover效果、二级菜单等功能，需要依赖element模块
	//监听导航点击
	element.on('nav(demo)', function(elem){
		//console.log(elem);
		//layer.msg(elem.text());
		var id = $(elem).attr("id");
		var url = $(elem).attr("url");
		var text = $(elem).attr("text");
		add_flag = true;
		//拿到所有 选项卡  判断下有没有存在，如果存在直接切换
		$(".layui-tab-title li").each(function(){
			var lay_id = $(this).attr("lay-id");
			console.log(lay_id);
			if(lay_id){
				if(lay_id==id){
					element.tabChange('layer_tab', id); //切换到 用户点的
					set_layui_tab_item_height();
					add_flag = false;
				}
			}
		});
		
		
		if(add_flag){
			if(url){
				element.tabAdd('layer_tab', {
					title:text //用于演示
					,content: "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}"+url+"'></iframe>"
					,id: id //实际使用一般是规定好的id，这里以时间戳模拟下
				});
				element.tabChange('layer_tab', id); //切换到 用户点的
				set_layui_tab_item_height();
			}
		}
		
		
	});
});


//可以从子页面传来个 关闭  由我关闭
function delete_tab_id(id){
	 var element = layui.element(); 
	 element.tabDelete('layer_tab', id); //删除：“商品管理”
}


$(function(){
	set_layui_tab_item_height();
	
	window.onresize = function(){
		set_layui_tab_item_height();
	}
});


function set_layui_tab_item_height(){
	//计算layui-tab-item 需要 设置多少的高 顶部的60  layui-tab-title=41
	var body_height = document.body.offsetHeight;
	var layui_tab_item_height = (body_height-60-41);
	$(".layui-tab-item").css("height",layui_tab_item_height+"px");
}



</script> 
<style>
html,body{
	 height:100%; 
	 overflow: hidden;
}
</style>
</head>
<body>
<div style="display: flex;display: -webkit-flex; flex-direction:column; width: 100%; height: 100%;">
	
	<jsp:include page="/admin/common/manage_top.jsp"/>
	
	<div style=" flex:1;-webkit-flex:1;   display: flex;display: -webkit-flex; flex-direction:row;  ">
		<jsp:include page="${leftPage}"/>
		
		<div id="main_scroll" style="flex:1;-webkit-flex:1;padding-left: 200px;">
		
			<div class="layui-tab layui-tab-brief"  style="margin: 0;"  lay-filter="layer_tab">
				<ul class="layui-tab-title">
				</ul>
				<div class="layui-tab-content" style="padding:0px;">
				</div>
			</div>
		</div>
		
	</div>
	
	
</div>
</body>
</html>