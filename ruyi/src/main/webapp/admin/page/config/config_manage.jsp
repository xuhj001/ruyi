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

var total = 0 ;//总数据数
var result_json ;//拿到的 具体数据
var pageSize ;//一共几页

$(function(){
	$("#config").addClass("layui-this");
	
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
	// $.post("/admin/config/findById",{id:1},function(result){
	$.post("${pageContext.request.contextPath}/admin/config/findById",{id:1},function(result){
		$("#dg").empty();
		 
			$("#dg").append(
					'<tr>'+
					'<td><input type="checkbox" name="'+result.id+'" lay-skin="primary"></td>'+
					'<td>'+format(result.da_ka_count)+'</td>'+
					'<td>'+format(result.web_site_url)+'</td>'+
					'<td>'+format(result.days)+'</td>'+
			        '<td>'+
			        '<div class="layui-btn-group">'+
			        	'<button onclick="open_edit(\''+result.id+'\')" class="layui-btn layui-btn-danger layui-btn-mini"><i class="layui-icon">&#xe642;</i>修改</button>'+
			        '</div>'+
			        '</td>'+
			      '</tr>'
			);
		
		refreshCheckBox();
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


//打开编辑窗口
function open_edit(id){
	//iframe层
	layer.open({
	  type: 2,
	  title: '修改配置',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['700px', '300px'],
	  // content: '/config/pc/edit?id='+id //iframe的url
	  content: '${pageContext.request.contextPath}/config/pc/edit?id='+id //iframe的url
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
	 //var t=setTimeout(fenye,500);
}



</script>

<style>
.layui-table-toolbar{
	overflow: hidden;
	width: 100%;
}
body{padding-top: 10px;}

</style>
<body>
web_url是负责android端手机上传图片用的，必须使用 http://www.baidu.com【换成自己的域名】
<div class="layui-form" style="min-width:1000px;">

<div class="layui-table-toolbar">
	<div class="layui-btn-group">
	    <button onclick="loadData()" class="layui-btn layui-btn-warm  layui-btn-small"><i class="layui-icon">&#x1002;</i>刷新</button>
 	 </div>
</div>


<!-- <col>  如果不设置宽 那么就是自适应 -->
  <table class="layui-table">
    <colgroup>
	      <col width="50">
	      <col width="120">
	      <col width="200">
	      <col width="120">
	      <col>
    </colgroup>
    
    <thead>
      <tr>
      	<th><input type="checkbox" id="allCheckBox" name="" lay-skin="primary" lay-filter="allChoose"></th>
        <th>打卡次数</th>
        <th>web_url</th>
        <th>换芯周期</th>
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
