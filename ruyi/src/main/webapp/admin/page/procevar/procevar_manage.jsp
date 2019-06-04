<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 开启高速模式 设置短信窗口    -->
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
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

</script>
<style>
html, body {
	height: 100%;
}


.layui-form-item {
    margin-bottom: 3px;
}
</style>


<script>
var procdefId = '${procdefId}';
var page =1 ; 
var rows = 100;
var url = '/admin/procevar/list';
var result_json ;//拿到的 具体数据


layui.use(['form'], function(){
  var form = layui.form();
});

layui.use('layer', function(){ //独立版的layer无需执行这一句
	  var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
});

//smstask.add.html传入  procdefId;//流程定义id 需要 根据这个id查节点，
function add(){
	layer.open({
	  type: 2,
	  title: '增加流程变量',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['600px', '400px'],
	  content: '/procevar/pc/add?procdefId=${procdefId}'
	}); 
}




function del(id){
	$.post('/admin/procevar/delete',{id:id},function(result){
		if(result.success){
			layer.msg(result.msg);
			loadData();
		}else{
			layer.msg(result.msg);
		}
	},'json');
}

//格式化str 如果是null则不显示。 起过虑作用
function format(str){
	if(str){
		return str;
	}else{
		return "";
	}
}




//重新加载数据
//加载数据
function loadData(){
	$.post(url,{page:page,rows:rows,procdefId:procdefId},function(result){
		result_json = result.rows;
		$("#dg").empty();
		for(var i=0;i<result_json.length;i++){
			$("#dg").append(
					'<tr>'+
			        '<td>'+format(result_json[i].taskName)+'</td>'+
			        '<td>'+format(result_json[i].variableName)+'</td>'+
			        '<td>'+format(result_json[i].variableValue)+'</td>'+
			        '<td>'+
			        '<button onclick="del(\''+result_json[i].id+'\')" class="layui-btn layui-btn-danger layui-btn-mini">删除</button>'+
			        '</td>'+
			      '</tr>'
			);
		}
		
	},'json');
}



//子窗口调用 的  关闭窗口方法 
function closeDlg(msg){
	 layer.closeAll();
	 layer.msg(msg);
	 //刷新数据
	 loadData();
}



$(function(){
	loadData();
});

</script>
</head>

<body>
<div style="padding: 10px; overflow: hidden;">

<div class="layui-form">

<div class="layui-table-toolbar">
	<div class="layui-btn-group">
	    <button onclick="add()" class="layui-btn   layui-btn-small"><i class="layui-icon">&#xe654;</i>增加</button>
	    <button onclick="loadData()" class="layui-btn layui-btn-warm  layui-btn-small"><i class="layui-icon">&#x1002;</i>刷新</button>
 	 </div>
</div>
	
  <table class="layui-table">
    <colgroup>
      <col width="150"><!-- 任务名称 -->
      <col width="150"><!-- 变量名称 -->
      <col width="120"><!-- 变量值 -->
      <col> <!-- 操作 -->
    </colgroup>
    <thead>
      <tr>
        <th>任务名称</th>
        <th>变量名称</th>
        <th>变量值</th>
        <th>操作</th>
      </tr> 
    </thead>
    <tbody id="dg">
    
     </tbody>
  </table>
</div>
	
</div>
</body>
</html>