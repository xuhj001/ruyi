<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<!-- shiro标签 授权   -->
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!--  shiro标签 授权 -->


<script>

//完成任务
function done(state){
	
	layer.open({
	    type: 2
	    ,content: '正在处理'
	    ,shadeClose:false
    });
	
	
	var msg="";
	if(state==1){
		msg="通过";
	}
	if(state==2){
		msg="驳回";
	}
	if(state==3){
		msg="关闭";
	}
	var done_text = $("#done_text").val();
	
	if(done_text.length<1){
		done_text = "无";
	}
	
	
	var comment = ""+msg+"任务。[审批意见:"+done_text+"]";
	
	$.post("/admin/renwu/last_done",{taskId:${param.taskId},comment:comment,taskName:taskName,msg:msg},function(result){
		if(result.success){
			//提交成功 设置按钮不可用 disabled="disabled"
			
			layer.closeAll();
			
			//信息框
			  layer.open({
			    content: '办理成功'
			    ,btn: '我知道了'
		    	,yes: function(index){
		    		layer.close(index);
		    		window.location.href="/wap_index.html"
				    }
			  });
			
			
			
		}else{
			layer.closeAll();
			
			//信息框
			  layer.open({
			    content: result.msg
			    ,btn: '我知道了'
		    	,yes: function(index){
		    		layer.close(index);
		    		window.location.href="/wap_index.html"
				    }
			  });
			
			
		}
	},'json');
	
}
</script>


<div id="banli"  style="position: fixed; width: 100%; bottom: 0;">
	<div style="overflow: hidden; padding:10px 10px 10px 10px; background-color: white; ">
		<form class="mui-input-group">
			<div class="mui-input-row">
				<input id="done_text" type="text" class="mui-input-clear" placeholder="请输入审批意见">
			</div>
		</form>
	</div>
	
	<div style="overflow:hidden; width:100%; padding:0px 0px 30px 0px;   background-color: white; display: flex;display: -webkit-flex; ">
		<div style="overflow: hidden; flex:1;-webkit-flex:1; text-align: center;">
			<button onclick="done(1)" type="button" class="mui-btn mui-btn-success ">
			<span class="mui-icon mui-icon-checkmarkempty"></span>通过	
			</button>
		</div>
		<div style="overflow: hidden; flex:1;-webkit-flex:1; text-align: center;">
			<button onclick="done(2)"  type="button" class="mui-btn mui-btn-danger ">
			<span class="mui-icon mui-icon-closeempty"></span>驳回	
			</button>
		</div>
	</div>
	
	<shiro:hasPermission name="renwu:close">
	<div style="overflow: hidden; padding: 10px; background-color: white; ">
		<div style="width: 50%; margin: 0 auto; overflow: hidden;">
			<button type="button" onclick="done(3)" class="mui-btn mui-btn-danger mui-btn-block" style="padding: 4px 0px 4px 0px;">关闭任务</button>
		</div>
	</div>
	</shiro:hasPermission>
	
</div>
