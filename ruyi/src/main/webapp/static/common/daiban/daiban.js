$(function(){
	loading('ul_weifenpei');
	loading('ul_yifenpei');
});


//格式化str 如果是null则不显示。 起过虑作用
function format(str){
	if(str){
		return str;
	}else{
		return "";
	}
}



function loading(tab){
	layer.open({
	    type: 2
	    ,content: '正在加载'
	    ,shadeClose:false
	});
	
	var page = $("#"+tab+"").attr("page");
	var rows = $("#"+tab+"").attr("rows");
	var url = $("#"+tab+"").attr("url");
	var count_span_id = $("#"+tab+"").attr("count_span_id");
	
	
	$.post(url,{page:page,rows:rows,prodefkey:prodefkey},function(result){
		//总记录 +1
		count_add(count_span_id,result.total);
		
		for(var i=0;i<result.rows.length;i++){
			
			//销售任务
			if(result.rows[i].renwu.xiaoshouId){
						$("#"+tab+"").find("#loading").before(
						'<a style="display: block; z-index:100; overflow: hidden;"  onclick="banli('+result.rows[i].id+',\''+result.rows[i].taskName+'\')" >'+
						'<div class="item">'+
						'<div class="entity_item">'+
							'<div class="div_left">流程名称：</div><div class="div_right renwu_fenlei">'+result.rows[i].renwu.processDefinitionName+'</div>'+
						'</div>'+
						'<div class="entity_item">'+
							'<div class="div_left">任务名称：</div><div class="div_right renwu_red">'+result.rows[i].taskName+'处理</div>'+
						'</div>'+
						'<div class="entity_item">'+
							'<div class="div_left">任务分类：</div><div class="div_right renwu_blue">'+result.rows[i].renwuFenlei+'</div>'+
						'</div>'+
						'<div class="entity_item">'+
							'<div class="div_left">客户姓名：</div><div class="div_right renwu_fenlei">'+result.rows[i].renwu.xiaoshou.clientName+'</div>'+
						'</div>'+
						'<div class="entity_item">'+
							'<div class="div_left">客户电话：</div><div class="div_right renwu_fenlei">'+result.rows[i].renwu.xiaoshou.clientPhone+'</div>'+
						'</div>'+
						'<div class="entity_item">'+
							'<div class="div_left">客户地址：</div><div class="div_right renwu_fenlei">'+format(result.rows[i].renwu.xiaoshou.clientAddress)+'</div>'+
						'</div>'+
						'<div class="entity_item">'+
							'<div class="div_left">创建时间：</div><div class="div_right renwu_fenlei">'+result.rows[i].taskCreateTime+'</div>'+
						'</div>'+
						'<div class="entity_item">'+
							'<div class="div_left">办理人：</div><div class="div_right renwu_fenlei">'+result.rows[i].banliren+'</div>'+
						'</div>'+
						'</div>'+
						'</a>'
						);
			}
			
			
			
			
			if(result.rows[i].renwu.leaveId){
						$("#"+tab+"").find("#loading").before(
						'<a style="display: block; z-index:100; overflow: hidden;"  onclick="banli('+result.rows[i].id+',\''+result.rows[i].taskName+'\')" >'+
						'<div class="item">'+
						'<div class="entity_item">'+
							'<div class="div_left">流程类型：</div><div class="div_right">'+result.rows[i].processDefinitionName+'</div>'+
						'</div>'+
						'<div class="entity_item">'+
							'<div class="div_left">任务名称：</div><div class="div_right">'+result.rows[i].name+'处理</div>'+
						'</div>'+
						'<div class="entity_item">'+
							'<div class="div_left">任务分类：</div><div class="div_right renwu_fenlei">'+result.rows[i].renwuFenlei+'</div>'+
						'</div>'+
						'<div class="entity_item">'+
							'<div class="div_left">创建时间：</div><div class="div_right">'+result.rows[i].taskCreateTime+'</div>'+
						'</div>'+
						'<div class="entity_item">'+
							'<div class="div_left">请假人：</div><div class="div_right">'+result.rows[i].renwu.leave.createUser.first_+'</div>'+
						'</div>'+
						'<div class="entity_item">'+
							'<div class="div_left">请假天数：</div><div class="div_right">'+result.rows[i].renwu.leave.leaveDay+'</div>'+
						'</div>'+
						'<div class="entity_item">'+
							'<div class="div_left">办理人：</div><div class="div_right">'+result.rows[i].banliren+'</div>'+
						'</div>'+
						'</div>'+
						'</a>'
						);
			}
			
			
		}
		//page+1
		$("#"+tab+"").attr("page",parseInt(page)+1);
		
	},'json');
	
	
	layer.closeAll();
	
	
	//重新初始化选项卡
	init_cart();
	
}

//初始化选项卡
function init_cart(){
	mui.init();
	(function($) {
	    //阻尼系数
	    var deceleration = mui.os.ios?0.003:0.0009;
	    $('.mui-scroll-wrapper').scroll({
	        bounce: false,
	        indicators: true, //是否显示滚动条
	        deceleration:deceleration
	    });  
	})(mui);
}

function count_add(span_id,count){
	var curr_count = $("#"+span_id+"").html();
	curr_count = parseInt(curr_count)+parseInt(count); 
	$("#"+span_id+"").html(curr_count);
}



//格式化str 如果是null则不显示。 起过虑作用
function format(str){
	if(str){
		return str;
	}else{
		return "";
	}
}


function banli(taskId,taskName){
	//点击后 按钮失效
	console.log(taskId);
	
	  layer.open({
		    type: 2
		    ,content: '正在打开'
		    ,shadeClose:false
	  });
	  
	  
	//根据taskId 拿到 formDate的url
	$.post("/task/wap/getFormDataByTaskId",{taskId:taskId},function(result){
		//恢复按钮
		layer.closeAll();
		
		//拿到url  然后跳转
		window.location.href=result.url+"?taskId="+taskId+"&taskName="+taskName;
		
	},'json');
}







