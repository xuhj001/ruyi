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
var url = '/admin/renwu/list';
var q ; //模糊查询
var rev_ = 2 ;//定义内部人2  客户1
var date1 ;//大于
var date2 ;//小于
var state ;//状态
var install_;//是否安装
var createUserId;//创建者

var fendianId ;
var jixingId;
var shuiyuanId;

var processDefinitionKey = "xiaoshou";

var last_change_xin_dataTime = '${last_change_xin_dataTime}';


var total = 0 ;//总数据数
var result_json ;//拿到的 具体数据
var pageSize ;//一共几页

$(function(){
	$("#xiaoshou").addClass("layui-this");
	
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
	
	
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
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

function format_email(str){
	if(str){
		if(str==1){
			return "支持";
		}
		return "不支持";
	}else{
		return "不支持";
	}
}

//1交往中   2转为流程   3已流失 关闭
function format_state(str){
	if(str){
		if(str==1){
			return "<eb>审批中</eb>";
		}
		if(str==2){
			return "<eg>已通过</eg>";		
		}
		if(str==3){
			return "<er>关闭</er>";
		}
	}else{
		return "";
	}
}

function format_install(str){
	if(str=='安装'){
		return "<eb>安装</eb>";
	}else{
		return "<er>不安装</er>";
	}
}


function loadData(){
	var index = layer.load(1, {
		shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	
	date1 = $("#date1").val();
	date2 = $("#date2").val();
	q = $("#q").val();
	install_ = $("#install_").val();
	state = $("#state").val();
	createUserId = $("#createUserId").val();
	
	fendianId = $("#fendianId").val();
	jixingId = $("#jixingId").val();
	shuiyuanId = $("#shuiyuanId").val();
	
	
	if(createUserId=='全部'){
		createUserId = "";
	}
	if(state=='全部'){
		state = "";
	}
	if(install_=='全部'){
		install_ = "";
	}
	if(fendianId=='全部'){//分店
		fendianId = "";
	}
	if(jixingId=='全部'){//机型
		jixingId = "";
	}
	if(shuiyuanId=='全部'){//水源
		shuiyuanId = "";
	}
	//判断是不是  选择  查该换芯的订单  last_change_xin_dataTime
	var last_change_xin_dataTime_cb_val = $('input[name="last_change_xin_dataTime"]:checked').val();
	
	var last_date = last_change_xin_dataTime;
	if(last_change_xin_dataTime_cb_val){
	}else{
		last_date = null ;
	}
	
	
	$.post("/admin/renwu/list",{page:page,rows:rows,q:q,processDefinitionKey:processDefinitionKey,date1:date1,date2:date2
		,install_:install_,state:state,createUserId:createUserId,fendianId:fendianId,jixingId:jixingId,shuiyuanId:shuiyuanId
		,last_change_xin_dataTime:last_date},function(result){
		total = result.total;
		result_json = result.rows;
		$("#dg").empty();
		for(var i=0;i<result_json.length;i++){
			$("#dg").append(
					'<tr>'+
					'<td><input type="checkbox" name="'+result_json[i].id+'" lay-skin="primary"></td>'+
					'<td>'+format(result_json[i].xiaoshou.clientName)+'</td>'+
					'<td>'+format(result_json[i].xiaoshou.clientPhone)+'</td>'+
			        '<td>'+format(result_json[i].xiaoshou.clientAddress)+'</td>'+
			        '<td>'+format_install(result_json[i].xiaoshou.install_)+'</td>'+
			        '<td>'+format(result_json[i].xiaoshou.ding_jine)+'</td>'+
			        '<td>'+format(result_json[i].xiaoshou.yu_jine)+'</td>'+
			        '<td>'+format(result_json[i].xiaoshou.remark)+'</td>'+
			        '<td>'+format(result_json[i].createDateTime)+'</td>'+
			        '<td>'+format_state(result_json[i].state)+'</td>'+
			        '<td><eg>'+result_json[i].fendian.name+'</eg></td>'+
			        '<td>'+
			        '<div class="layui-btn-group">'+ 
			        '<button onclick="view(\''+result_json[i].id+'\')" class="layui-btn layui-btn-normal layui-btn-mini">详情</button>'+
			        '<button onclick="view_win(\''+result_json[i].id+'\')" class="layui-btn layui-btn-mini">新窗口打开</button>'+
			        '<button onclick="edit(\''+result_json[i].xiaoshou.id+'\')" class="layui-btn layui-btn-danger layui-btn-mini">修改</button>'+
			        '<button onclick="open_map(\''+result_json[i].id+'\')" class="layui-btn  layui-btn-normal layui-btn-mini">打开地图</button>'+
			        '</div>'+
			        '</td>'+
			      '</tr>'
			);
		}
		
		refreshCheckBox();
		pageSize =Math.ceil(total/rows) ;
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


function del(ids){
	var index = layer.load(1, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	$.post('/admin/renwu/delete',{ids:ids},function(result){
		if(result.success){
			layer.closeAll();
			layer.msg(result.msg);
			refresh_data();
		}else{
			
			layer.closeAll();
			
			//询问框
			layer.confirm(result.msg, {
			  btn: ['确定','我知道了'] //按钮
			}, function(){
				refresh_data();
				//layer.msg('的确很重要', {icon: 1});//样式1是不是黑色的 是方框形态的
			}, function(){
				refresh_data();
			});
			
		}
	},'json');
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


function edit(id){
	layer.open({
		  type: 2,
		  title: '修改',
		  shadeClose: true,
		  shade: 0.8,
		  area: ['700px', '700px'],
		  content: '/xiaoshou/pc/edit?id='+id
	});
}


//renwuId
function view(id){
	layer.open({
	  type: 2,
	  title: '详情',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['700px', '700px'],
	  content: '/renwu/pc/'+id
	});
}


function view_win(id){
	window.open('/renwu/pc/'+id);
}

function open_map(id){
	window.open('/map/pc/open_map?renwuId='+id);
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
<div class="layui-form" style="min-width:1000px;">

<div class="layui-table-toolbar">
	<div class="layui-btn-group">
		<button onclick="del_select()" class="layui-btn layui-btn-danger   layui-btn-small"><i class="layui-icon">&#xe640;</i>删除选中</button>
	    <button onclick="loadData()" class="layui-btn layui-btn-normal  layui-btn-small"><i class="layui-icon">&#x1002;</i>刷新</button>
 	 </div>
</div>

<form class="layui-form layui-form-pane" >
  <div class="layui-form-item" style="   margin-bottom: -1px;">
    <label class="layui-form-label" style="width: 111px;">选择创建人</label>
    <div class="layui-input-inline" style="width: 124px; margin-right: -1px; ">
      <select id="createUserId">
        <option value="全部">全部</option>
        <c:forEach var="user" items="${userList}">
        	<option value="${user.id_ }">${user.first_ }</option>
        </c:forEach>
      </select>
    </div>
    
    <label class="layui-form-label" style="width: 63px;">大于</label>
    <div class="layui-input-inline" style="  width: 113px; margin-right: -1px; ">
      <input class="layui-input" id="date1" placeholder="大于几号" onclick="layui.laydate({elem: this, festival: true})">
    </div>
    
    <label class="layui-form-label" style="width: 63px;">小于</label>
    <div class="layui-input-inline" style="  width: 113px; margin-right: -1px; ">
      <input class="layui-input" id="date2" placeholder="小于几号" onclick="layui.laydate({elem: this, festival: true})">
    </div>
    
    <label class="layui-form-label" style="width: 97px;">模糊查询</label>
    <div class="layui-input-inline" style="  width: 200px;margin-right: -1px; ">
      <input class="layui-input" id="q"  onkeydown="if(event.keyCode==13) open_search()"   placeholder="模糊查询" ">
    </div>
    
    <div class="layui-input-inline" style="  width: 113px;">
    	<a class="layui-btn" onclick="open_search()">查询</a>
    </div>
</div>

<div class="layui-form-item" style="   margin-bottom: -1px;">
    <label class="layui-form-label" style="width: 111px;">是否安装</label>
    <div class="layui-input-inline" style="width: 124px; margin-right: -1px; ">
      <select id="install_">
        	<option value="全部">全部</option>
        	<option value="安装">安装</option>
        	<option value="不安装">不安装</option>
      </select>
    </div>
    
    <label class="layui-form-label" style="width: 63px;">状态</label>
    <div class="layui-input-inline" style="  width: 113px; margin-right: -1px; ">
	      <select id="state">
	        	<option value="全部">全部</option>
	        	<option value="1">审批中</option>
	        	<option value="2">全部通过</option>
	        	<option value="3">已关闭</option>
	      </select>
    </div>
    
    <label class="layui-form-label" style="width: 63px;">分店</label>
    <div class="layui-input-inline" style="  width: 113px; margin-right: -1px; ">
	      <select id="fendianId">
	      		
	      		<c:choose>
				    <c:when test="${fendian!=null}">
				    	<option value="${fendian.id}">${fendian.name}</option>
				    </c:when>
				    
				     <c:when test="${fendian==null }">
				     	<option value="全部">全部</option>
			        	<c:forEach var="fendian" items="${fendianList}">
			        		<option value="${fendian.id}">${fendian.name}</option>
			        	</c:forEach>
				     </c:when>
				 </c:choose>
	      </select>
	</div>
    
    <label class="layui-form-label" style="width: 63px;">机型</label>
    <div class="layui-input-inline" style="  width: 113px; margin-right: -1px; ">
	      <select id="jixingId">
	        	<option value="全部">全部</option>
	        	<c:forEach var="jixing" items="${jixingList}">
	        		<option value="${jixing.id}">${jixing.name}</option>
	        	</c:forEach>
	      </select>
    </div>
    
    <label class="layui-form-label" style="width: 63px;">水源</label>
    <div class="layui-input-inline" style="  width: 113px; margin-right: -1px; ">
	      <select id="shuiyuanId">
	        	<option value="全部">全部</option>
	        	<c:forEach var="shuiyuan" items="${shuiyuanList}">
	        		<option value="${shuiyuan.id}">${shuiyuan.name}</option>
	        	</c:forEach>
	      </select>
    </div>
</div>


<div class="layui-form-item" style="   margin-bottom: -1px;">
	<div class="layui-input-inline" style="  width: 111px; margin-right: -1px; ">
		<input type="checkbox" name="last_change_xin_dataTime" value="1"  title="显示待换滤芯用户">
	</div>
</div>
  
   

</form>




<!-- <col>  如果不设置宽 那么就是自适应 -->
  <table class="layui-table">
    <colgroup>
      <col width="50"><!-- cb -->
      <col width="120"><!-- 客户姓名-->
      <col width="120"><!-- 客户电话--> 
      <col width="200"><!-- 客户地址 -->
      <col width="90"><!-- 是否安装 -->
      <col width="60"><!-- 定金 -->
      <col width="60"><!-- 余款 -->
      <col width="200"><!-- 备注 -->
      <col width="120"><!-- 创建时间 -->
      <col width="60"><!-- 状态 -->
      <col width="60"><!-- 分店 -->
      <col>            <!-- 操作 -->
    </colgroup>
    
    
	<thead>
      <tr>
      	<th><input type="checkbox" id="allCheckBox" name="" lay-skin="primary" lay-filter="allChoose"></th>
        <th>客户姓名</th>
        <th>客户电话</th>
        <th>客户地址</th>
        <th>是否安装</th>
        <th>定金</th>
        <th>余款</th>
        <th>备注</th>
        <th>创建时间</th>
        <th>状态</th>
        <th>分店</th>
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
