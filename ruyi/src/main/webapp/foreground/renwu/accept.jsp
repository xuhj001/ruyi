<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->



<script>
function accept(taskId){
	
	  layer.open({
		    type: 2
		    ,content: '正在处理'
		    ,shadeClose:false
	  });
	  
	//向后台发送个数据  这个单 当前登陆的人接受了
	console.log(taskId);
	$.post("/admin/renwu/accept.do",{taskId:${param.taskId},taskName:taskName},function(result){
		if(result.success){
			//提交成功 设置按钮不可用 disabled="disabled"
			layer.closeAll();
			
			  //信息框
			  layer.open({
			    content: '接受成功'
			    ,btn: '我知道了'
		    	,yes: function(index){
		    		layer.close(index);
		    		location.replace(location.href);
				    }
			  });
				
		}else{
			
			  //信息框
			  layer.open({
			    content: result.msg
			    ,btn: '我知道了'
		    	,yes: function(index){
		    		layer.close(index);
		    		location.replace(location.href);
				    }
			  });
			
		}
	},'json');
}
</script>


<c:choose>
    <c:when test="${renwu.state==1&&renwu.isLock==1}">
    	<div style=" overflow: hidden; margin-bottom: 10px; text-align: center; ">
			<button type="button" onclick="accept(${param.taskId})" class="mui-btn mui-btn-royal">我要办理</button>
		</div>
    </c:when>
</c:choose>
				
				


