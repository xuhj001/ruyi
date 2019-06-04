<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>

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

<!--添加  jq  支持加载-->
<script	src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<!--添加 jq 支持加载-->

<!--添加 layui  支持加载-->
<link href="${pageContext.request.contextPath}/static/layui_1.0.9/css/layui.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/static/layui_1.0.9/layui.js"></script>
<!--添加 layui  支持加载-->

<!--手动加载这个css  使用layui加载的话会报错，不知原因-->
<link href="${pageContext.request.contextPath}/static/layui_1.0.9/css/modules/layer/default/layer.css?v=3.0.3303" rel="stylesheet"/>
<!--手动加载这个css  使用layui加载的话会报错，不知原因-->



<!--添加 layer 弹出 窗口  支持加载-->
<script src="${pageContext.request.contextPath}/static/layer-v3.0.3/layer/layer.js"></script>
<!--添加 layer 弹出 窗口  支持加载-->


<!--添加 管理页面 基础css-->
<link href="${pageContext.request.contextPath}/static/common/manage/base.css" rel="stylesheet"/>
<!--添加 管理页面 基础css-->
</head>

<script>
var page = 1 ; 
var rows = 100 ;
var url = '/admin/procedef/list';

var total = 0 ;//总数据数
var result_json ;//拿到的 具体数据
var pageSize ;//一共几页

$(function(){
	$("#procedef").addClass("layui-this");
	
	layui.use(['form'], function(){
		  var form = layui.form();
		  form.render('select'); 
		  //全选
		  form.on('checkbox(allChoose)', function(data){
		    var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
		    child.each(function(index, item){
		      item.checked = data.elem.checked;
		    });
		    form.render('checkbox');
		  });
	});
	
	refresh_data();
	
});


function open_search(){
	page = 1 ;//搜索的时候强制  从1页
	refresh_data();
}



//格式化str 如果是null则不显示。 起过虑作用
function format(str){
	if(str){
		return str;
	}else{
		return "";
	}
}

//1交往中   2转为流程   3已流失 关闭
function format_state(str){
	if(str){
		if(str==1){
			return "交往中";
		}
		if(str==2){
			return "已转为流程";		
		}
		if(str==3){
			return "已流失&关闭";
		}
	}else{
		return "";
	}
}



function loadData(){
	var index = layer.load(1, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	$.post(url,{page:page,rows:rows},function(result){
		total = result.total;
		result_json = result.rows;
		$("#dg").empty();
		for(var i=0;i<result_json.length;i++){
			$("#dg").append(
					
					'<tr>'+
					'<td><input type="checkbox" name="'+result_json[i].id+'" lay-skin="primary"></td>'+
					'<td>'+format(result_json[i].id)+'</td>'+
					'<td>'+format(result_json[i].name)+'</td>'+
					'<td>'+format(result_json[i].key)+'</td>'+
					'<td>'+format(result_json[i].version)+'</td>'+
					'<td>'+format(result_json[i].resourceName)+'</td>'+
					'<td>'+format(result_json[i].diagramResourceName)+'</td>'+
					'<td>'+format(result_json[i].deploymentId)+'</td>'+
			        '<td>'+
			        '<div class="layui-btn-group">'+
			        	'<button onclick="see_proce_img(\''+result_json[i].deploymentId+'\',\''+result_json[i].diagramResourceName+'\')" class="layui-btn layui-btn-danger layui-btn-mini">查看流程图</button>'+
			        	'<button onclick="open_sms_manage(\''+result_json[i].id+'\')" class="layui-btn layui-btn-mini">审批任务短信管理</button>'+
			        	'<button onclick="accept_sms_manage(\''+result_json[i].id+'\')" class="layui-btn layui-btn-mini">接受任务短信管理</button>'+
			        	'<button onclick="open_show_phone(\''+result_json[i].id+'\')" class="layui-btn layui-btn-mini">显示号码管理</button>'+
			        	'<button onclick="opend_provar_manage(\''+result_json[i].id+'\')" class="layui-btn layui-btn-mini">流程变量设置</button>'+
			        '</div>'+
			        '</td>'+
			      '</tr>'
			      
			);
		}
		refreshCheckBox();
		pageSize =Math.ceil(total/rows);
	},'json');
	layer.closeAll();
}

//刷新筛选框 
function refreshCheckBox(){
	layui.use('form', function(){
		var $ = layui.jquery, form = layui.form();
		form.render(); //更新全部
		
		
		//取消allCheckBox  
		$("#allCheckBox").attr("checked",false);
		
		form.render('checkbox'); //刷新select选择框渲染
		
	});
}


//分页
function fenye(){
	layui.use(['laypage'], function(){
		  var laypage = layui.laypage;
		//调用分页
		  laypage({
		    cont: 'demo8'
		    ,curr: page
		    ,pages: pageSize  //得到总页数
		    ,groups: 12//连续显示分页数
		    ,jump: function(obj){
		      	//console.log(obj.curr);
		      	page = obj.curr;
		      	loadData();
		    }
		  });
	});
}



//流程变量设置
function opend_provar_manage(procdefId){
	
	//iframe层
	layer.open({
	  type: 2,
	  title: '流程变量管理',
	  shadeClose: false,
	  shade: 0.8,
	  area: ['800px', '600px'],
	  content: '/procevar/pc/manage?procdefId='+procdefId
	});
}


//显示号码管理
function open_show_phone(procdefId){
	//iframe层
	layer.open({
	  type: 2,
	  title: '显示号码管理',
	  shadeClose: false,
	  shade: 0.8,
	  area: ['1000px', '720px'],
	  content: '/showPhone/pc/manage?procdefId='+procdefId
	});
}

//审批短信管理
function open_sms_manage(procdefId){
		//iframe层
		layer.open({
		  type: 2,
		  title: '发送短信管理',
		  shadeClose: false,
		  shade: 0.8,
		  area: ['1000px', '720px'],
		  content: '/smsTask/pc/manage?procdefId='+procdefId+'&type=1'
		});
}

//接受任务短信管理
function accept_sms_manage(procdefId){
	//iframe层
	layer.open({
	  type: 2,
	  title: '接受任务短信管理',
	  shadeClose: false,
	  shade: 0.8,
	  area: ['1000px', '720px'],
	  content: '/smsTask/pc/manage?procdefId='+procdefId+'&type=2'
	});
}


//子窗口调用 的  关闭窗口方法 
function closeDlg(msg){
	 layer.closeAll();
	 layer.msg(msg);
	 //刷新数据
	 refresh_data();
}


function refresh_data(){
	 //刷新数据
	 loadData();
	 //重新分页
	 var t=setTimeout(fenye,500);
}



function see_proce_img(deploymentId,diagramResourceName){
	window.open("/admin/procedef/showView?deploymentId="+deploymentId+"&diagramResourceName="+diagramResourceName);
}


</script>

<style>
.layui-table-toolbar{
	overflow: hidden;
	width: 100%;
}
body{
	padding-top: 10px;
}
</style>

<body>
<div class="layui-form" style="min-width:1000px;">

<div class="layui-table-toolbar">
	<div class="layui-btn-group">
	    <button onclick="loadData()" class="layui-btn layui-btn-warm  layui-btn-small"><i class="layui-icon">&#x1002;</i>刷新</button>
 	 </div>
</div>


<!-- <col>  如果不设置宽 那么就是自适应 -->
  <table class="layui-table">
    <colgroup>
      <col width="50"><!-- cb -->
      <col width="100"><!--流程定义id -->
      <col width="150"><!--流程名称 -->
      <col width="150"><!--key -->
      <col width="60"><!--版本 -->
      <col width="200"><!--规则文件名称 -->
      <col width="200"><!--图片名称 -->
      <col width="100"><!--流程部署Id -->
      <col><!--操作 -->
    </colgroup>
    
    <thead>
      <tr>
      	<th><input type="checkbox" id="allCheckBox" name="" lay-skin="primary" lay-filter="allChoose"></th>
        <th>流程定义id</th>
        <th>流程名称</th>
        <th>流程定义的key</th>
        <th>版本</th>
        <th>流程定义的规则文件名称</th>
        <th>流程定义的规则图片名称</th>
        <th>流程部署Id</th>
        <th>操作</th>
      </tr>
    </thead>
    
    <tbody id="dg">
		
    </tbody>
  </table>
</div>


<div style="overflow: hidden; ">
	<div id="demo8"></div>
</div>

</body>
</html>
