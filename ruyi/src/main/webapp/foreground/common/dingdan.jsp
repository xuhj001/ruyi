<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<script>

var page =1;
var rows = 20;
var currentUserId = '${currentUserId}';
var url = '/admin/rwidtt/list';
var processDefinitionKey = '${processDefinitionKey}';
var fendianId = '${fendianId}';



//格式化str 如果是null则不显示。 起过虑作用
function format(str){
	if(str){
		return str;
	}else{
		return "";
	}
}


function formatStateText(str){
	if(str){
		if(str==1){
			return "审批中";
		}
		if(str==2){
			return "已通过";
		}
		if(str==3){
			return "已关闭";
		}
	}else{
		return "";
	}
}

function formatStateClass(str){
	if(str){
		if(str==1){
			return "state1";
		}
		if(str==2){
			return "state2";
		}
		if(str==3){
			return "state3";
		}
	}else{
		return "";
	}
}

function formatJine(str){
	if(str){
		return str.toFixed(2);
	}else{
		return "";
	}
}

$(function(){
	loadData();
});

function loadData(){
	layer.open({
	    type: 2
	    ,content: '正在加载'
	    ,shadeClose:false
    });
	
	
	$.post(url,{page:page,rows:rows,createUserId:currentUserId,processDefinitionKey:processDefinitionKey,fendianId:fendianId},function(result){
		if(result.length>0){
			page = page +1;
		}else{
			layer.closeAll();
			 //提示
			  layer.open({
			    content: '没有更多了'
			    ,skin: 'msg'
			    ,time: 2 //2秒后自动关闭
			  });
			return;
		}
		var state_text ;
		var state_class_name;
		for(var i=0;i<result.length;i++){
			state_text = formatStateText(result[i].renwu.state);
			state_class_name = formatStateClass(result[i].renwu.state);
			
			$("#dg").find("#loading").before(
					'<a style="display: block; z-index:100; overflow: hidden;"  href="/renwu/wap/'+result[i].renwu.id+'">'+
					'<div class="item">'+
					'<div style="overflow: hidden; margin-bottom: 5px;">'+
						'<span style="overflow: hidden; display: inline-block; float: left; font-size: 14px; font-weight: bold; ">'+format(result[i].renwu.xiaoshou.clientName)+
						    '<span class="'+state_class_name+'">'+state_text+'</span></span>'+
					'</div>'+
					'<div class="entity_item">'+
						'<div class="div_left">客户姓名：</div><div class="div_right">'+result[i].renwu.xiaoshou.clientName+'</div>'+
					'</div>'+
					'<div class="entity_item">'+
						'<div class="div_left">客户电话：</div><div class="div_right">'+result[i].renwu.xiaoshou.clientPhone+'</div>'+
					'</div>'+
					'<div class="entity_item">'+
						'<div class="div_left">客户地址：</div><div class="div_right">'+format(result[i].renwu.xiaoshou.clientAddress)+'</div>'+
					'</div>'+
					'<div class="entity_item">'+
						'<div class="div_left">流程类型：</div><div class="div_right">'+result[i].renwu.processDefinitionName+'</div>'+
					'</div>'+
					'<div class="entity_item">'+
						'<div class="div_left">发起时间：</div><div class="div_right">'+result[i].renwu.createDateTime+'</div>'+
					'</div>'+
					'<div class="entity_item">'+
						'<div class="div_left">定金：</div><div class="div_right">'+formatJine(result[i].renwu.xiaoshou.ding_jine)+'</div>'+
					'</div>'+
					
					'</div>'+
					'</a>'
			);
		}
		
	},'json');
	layer.closeAll();
}

</script>

<style>
.entity_item {
	display: flex;
	display: -webkit-flex;
	margin-bottom: 5px;
	color: #777;
}

.entity_item .div_left {
	overflow: hidden;
	font-size: 14px;
	font-family: "Helvitica Neue", Helvitica, Arial, "Hiragino Sans GB",
		"Microsoft YaHei", "Arial Regular", "Microsoft JhengHei", sans-serif;
	text-align: left;
}
.entity_item .div_right {
	overflow: hidden;
	flex: 1;
	-webkit-flex: 1;
	font-size: 14px;
	font-family: "Helvitica Neue", Helvitica, Arial, "Hiragino Sans GB",
		"Microsoft YaHei", "Arial Regular", "Microsoft JhengHei", sans-serif;
}

.state1{
font-weight: normal; 
padding-left:5px; 
padding-right:5px;   
border: 1px solid; 
color: #8a6de9; 
border-radius:5px;
}
.state2{
font-weight: normal; 
padding-left:5px; 
padding-right:5px;   
border: 1px solid; 
color: #4cd964; 
border-radius:5px;
}
.state3{
font-weight: normal; 
padding-left:5px; 
padding-right:5px;   
border: 1px solid; 
color:#dd524d; 
border-radius:5px;
}
.item{
overflow: hidden;
border-bottom: 1px solid #777;
padding: 10px 0px 10px 0px;
}


</style>
<header class="mui-bar mui-bar-nav">
		    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left "><span style="font-size: 16px; line-height: 20px; height: 20px;">返回</span></a>
		    <h1 class="mui-title">${title}</h1>
		    <a href="javascript:location.replace(location.href);" style="display:inline-block;  height: 45px; right:11px; line-height:45px; position: absolute;">刷新</a>
</header>


<div class="mui-content">
	<div style="overflow: hidden; background-color: white; ">
		<div id="dg"  style="overflow: hidden; padding: 10px 10px 10px 10px;">
			<!--  
			<c:forEach var="rwidtt" items="${rwidttList }">
			<a style="display: block; z-index:100; overflow: hidden;"  href="/renwu/${rwidtt.renwu.id}.html">
			<div class="item">
			<div style="overflow: hidden; margin-bottom: 5px;">
				<span style="overflow: hidden; display: inline-block; float: left; font-size: 14px; font-weight: bold; ">
				${rwidtt.renwu.title }
				<c:choose>
				    <c:when test="${rwidtt.renwu.state==1}">
				    <span class="state1">审批中</span></span>
				    </c:when>
				     <c:when test="${rwidtt.renwu.state==2}">
				   	 <span class="state2">已通过</span></span>
				     </c:when>
				     <c:when test="${rwidtt.renwu.state==3}">
				     <span class="state3">已关闭</span></span>
				     </c:when>
				</c:choose>
			</div>
			
			
			<div class="entity_item">
				<div class="div_left">任务类型：</div><div class="div_right">${rwidtt.renwu.processDefinitionName }</div>
			</div>
			<div class="entity_item">
				<div class="div_left">发起时间：</div><div class="div_right"><fmt:formatDate value="${rwidtt.renwu.createDateTime}" pattern="yyyy-MM-dd HH:mm"/></div>
			</div>
			<div class="entity_item">
				<div class="div_left">任务金额：</div><div class="div_right"><fmt:formatNumber value="${rwidtt.renwu.jine}" pattern="#0.00" /></div>
			</div>
			
			</div>
			</a>
			</c:forEach>
			-->
			
			
			<button type="button" id="loading"  onclick="loadData()" class="mui-btn mui-btn-success mui-btn-block" style=" padding: 4px 0; margin-top: 5px;" >加载更多</button>
		</div>
	</div>
	
	
	<div style="overflow: hidden; height: 100px; width: 0px;">留下个空</div>
	
</div>



