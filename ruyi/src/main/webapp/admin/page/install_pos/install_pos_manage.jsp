<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>

<!-- 开启高速模式    -->
<meta name="renderer" content="webkit">
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

<!-- 引入manage 的基础css -->
<link href="${pageContext.request.contextPath}/static/common/manage/base.css" rel="stylesheet"/>
<!-- 引入manage 的基础css -->

</head>
<body>
<style>
body{
	padding-top: 10px;
}
</style>

<script>
var page = 1 ; 
var rows = 100;
var url = '/admin/installpos/list';

var total = 0 ;//总数据数
var result_json ;//拿到的 具体数据
var pageSize ;//一共几页

$(function(){
	layui.use(['form'], function(){
		  var form = layui.form();
		  form.render('select');//刷新下拉框
		  
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



//格式化str 如果是null则不显示。 起过虑作用
function format(str){
	if(str){
		return str;
	}else{
		return "";
	}
}


function format_isUse(str){
	if(str){
		if(str==1){
			return "<eg>使用中</eg>";
		}
		if(str==0){
			return "<er>未使用</er>";		
		}
	}else{
		return "<er>未使用</er>";
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
			        '<td>'+format(result_json[i].name)+'</td>'+
			        '<td>'+format_isUse(result_json[i].isUse)+'</td>'+
			        '<td>'+  
			        '<div class="layui-btn-group">'+
			        '<button onclick="open_edit(\''+result_json[i].id+'\')" class="layui-btn layui-btn-mini"><i class="layui-icon">&#xe642;</i>修改</button>'+
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


function add(){
	//iframe层
	layer.open({
	  type: 2,
	  offset: ['100px', '50px'],
	  title: '添加安装位置',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['700px', '600px'],
	  content: '/installpos/pc/add' //iframe的url
	});
}


//删除 确定窗口
function opend_del_dlg(id){
	
	//询问框
	layer.confirm('您是否要删除？', {
	  btn: ['确定删除','取消'] //按钮
	}, function(){
	  	del(id);
		//layer.msg('的确很重要', {icon: 1});//样式1是不是黑色的 是方框形态的
	}, function(){
		layer.msg('您选择了取消');
	});
}


function del(ids){
	var index = layer.load(1, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	$.post('/admin/link/delete',{ids:ids},function(result){
		if(result.success){
			layer.closeAll();
			layer.msg('删除成功');
			refresh_data();
		}
	},'json');
}


//打开编辑窗口
function open_edit(id){
	//iframe层
	layer.open({
	  type: 2,
	  title: '修改安装位置',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['700px', '600px'],
	  content: '/installpos/pc/edit?id='+id
	});
}



//子窗口调用 的  关闭窗口方法 
function closeDlg(msg){
	 layer.closeAll();
	 layer.msg(msg);
	 refresh_data();
}


function refresh_data(){
	 //刷新数据
	 loadData();
	 //重新分页
	 var t=setTimeout(fenye,500);
}


function del_select(){
	var checkBoxListSelected = $("#dg").find('input[type="checkbox"]:checked');
	//判断 checkBoxListSelected大小 是否大于0
	if(checkBoxListSelected.length<1){
		layer.msg('请选择要删除的内容!');
		return;
	}
	var ids='';
	for(var i=0;i<checkBoxListSelected.length;i++){
		ids+=checkBoxListSelected[i].name+',';
	}
	//去掉最后的逗号,
	ids = ids.substring(0,ids.length-1);
	
	//询问框
	layer.confirm('您是否要删除这'+checkBoxListSelected.length+'个吗？', {
	  btn: ['确定删除','取消'] //按钮
	}, function(){
	  	del(ids);
		//layer.msg('的确很重要', {icon: 1});//样式1是不是黑色的 是方框形态的
	}, function(){
		layer.msg('您选择了取消');
	});
	
}

</script>

<style>
.layui-table-toolbar{
	overflow: hidden;
	width: 100%;
}
</style>

<div class="layui-form" style="min-width:1000px;">

<div class="layui-table-toolbar">
	<div class="layui-btn-group">
	 	<button onclick="add()" class="layui-btn   layui-btn-small"><i class="layui-icon">&#xe654;</i>增加</button>
	    <button onclick="loadData()" class="layui-btn layui-btn-warm  layui-btn-small"><i class="layui-icon">&#x1002;</i>刷新</button>
 	 </div>
</div>

<!-- <col>  如果不设置宽 那么就是自适应 -->
  <table class="layui-table">
    <colgroup>
      <col width="50"><!-- cb -->
      <col width="200"><!-- 名称-->
      <col width="150"><!-- 是否使用-->
      <col>            <!-- 操作 -->
    </colgroup>
    
    <thead>
      <tr>
      	<th><input type="checkbox" id="allCheckBox" name="" lay-skin="primary" lay-filter="allChoose"></th>
        <th>位置名称</th>
        <th>是否使用</th>
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