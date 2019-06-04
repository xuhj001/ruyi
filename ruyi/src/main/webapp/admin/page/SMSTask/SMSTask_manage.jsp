<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- 开启高速模式    -->
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<!-- 开启高速模式-->

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


<!--添加 管理页面 基础css-->
<link href="${pageContext.request.contextPath}/static/common/manage/base.css" rel="stylesheet"/>
<!--添加 管理页面 基础css-->

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
var type = '${type}';
var page =1 ; 
var rows = 100;
var url = '/admin/smsTask/list';
var result_json ;//拿到的 具体数据


layui.use(['form'], function(){
  var form = layui.form();
});

layui.use('layer', function(){ //独立版的layer无需执行这一句
	  var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
});

//smstask.add.html传入  procdefId;//流程定义id 需要 根据这个id查节点，
function addSMSTask(){
	layer.open({
	  type: 2,
	  title: '增加短信内容',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['800px', '520px'],
	  content: '/smsTask/pc/add?procdefId=${procdefId}&type=${type}'
	}); 
}



function del(id){
	
	$.post('/admin/smsTask/delete.do',{id:id},function(result){
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

function format_groupToName(obj){
	if(obj){
		return obj.name_;
	}else{
		return "";
	}
}


function formatCreateUser(str){
	if(str){
		if(str==1){
			return "<eg>发送</eg>";
		}else{
			return "<er>不发送</er>";
		}
	}else{
		return "";
	}
}


function format_phone_sms(str){
	if(str){
		if(str==1){
			return "<eg>发送</eg>";
		}else{
			return "<er>不发送</er>";
		}
	}else{
		"<er>不发送</er>";
	}
}


//重新加载数据
//加载数据
function loadData(){
	$.post(url,{page:page,rows:rows,procdefId:procdefId,type:type},function(result){
		result_json = result.rows;
		$("#dg").empty();
		for(var i=0;i<result_json.length;i++){
			$("#dg").append(
					'<tr>'+
			        '<td>'+format(result_json[i].taskName)+'</td>'+
			        '<td>'+format(result_json[i].msg)+'</td>'+
			        '<td>'+format(result_json[i].smsModel)+'</td>'+
			        '<td>'+format_groupToName(result_json[i].group)+'</td>'+
			        '<td>'+formatCreateUser(result_json[i].client)+'</td>'+
			        '<td>'+format_phone_sms(result_json[i].phone_sms)+'</td>'+
			        '<td>'+format_phone_sms(result_json[i].wx_sms)+'</td>'+
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
})
</script>
</head>


<body>
<div style="padding: 10px; overflow: hidden;">

<div class="layui-form">

<div class="layui-table-toolbar">
	<div class="layui-btn-group">
	    <button onclick="addSMSTask()" class="layui-btn   layui-btn-small"><i class="layui-icon">&#xe654;</i>增加</button>
	    <button onclick="loadData()" class="layui-btn layui-btn-warm  layui-btn-small"><i class="layui-icon">&#x1002;</i>刷新</button>
 	 </div>
</div>

	
  <table class="layui-table">
    <colgroup>
      <col width="120"><!-- 任务名称 -->
      <col width="100"><!-- 任务状态 -->
      <col width="300"><!-- 短信模版 -->
      <col width="120"><!-- 发送的组 -->
      <col width="100"><!-- 发送的客户 -->
      <col width="120"><!-- 发送的客户 -->
      <col width="120"><!-- 发送的客户 -->
      <col>   <!-- 操作 -->
    </colgroup>
    <thead>
      <tr>
        <th>任务名称</th>
        <th>任务状态</th>
        <th>短信模版</th>
        <th>发送的部门</th>
        <th>客户</th>
        <th>手机短信</th>
        <th>微信消息</th>
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