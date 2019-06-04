<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<!-- 文件上传 组件 -->
<script src="${pageContext.request.contextPath}/static/common/uploader/uploader.js"></script>
<link href="${pageContext.request.contextPath}/static/common/uploader/uploader.css" rel="stylesheet"/>
<!-- 文件上传 组件 -->

<script>
$(function(){
	var textarea = $("textarea");
	
	textarea.focus(function() {
		//失去焦点  隐藏 下面的菜单
		$("#banli").hide();
	});
	textarea.blur(function() {
		//失去焦点  显示下面的菜单
		$("#banli").show();
	});
})
</script>


<script>
//把这个地址定义在外面。  在js文件中直接取
var task_upload_url = '${config.web_site_url}/admin/renwu/uploader';

function addComment(){
	  //显示添加  批注
	  layer.open({
	    type: 2
	    ,content: '添加批注'
	    ,shadeClose:false
	  });
	  
	  
	//添加批注
	var comment = $("#comment").val();
	var annexStr = "";
	
	//拿出来图片src  imgList
	var update_img_item = $("#imgList").find(".item_img_div");
	for(var i=0;i<update_img_item.length-1;i++){
		var img = update_img_item.eq(i).find('img');
		img = img[0];
		var src = $(img).attr("origin");
		annexStr = annexStr+ '<img  src="'+src+'" origin="'+src+'" />';
	}
	//拼接
	comment = comment + annexStr;
	if(comment.length<1){
		layer.closeAll();
		//信息框
		layer.open({
		    content: '请输入【执行过程明细】内容'
		    ,btn: '我知道了'
	    	,yes: function(index){
	    		layer.close(index);
	    		location.replace(location.href);
			    }
		  });
		return ;
	}
	
	$.post("/admin/renwu/addComment.do",{taskId:${param.taskId},comment:comment,taskName:taskName},function(result){
		if(result.success){
			//提交成功 设置按钮不可用 disabled="disabled"
			layer.closeAll();
			
			//信息框
			  layer.open({
			    content: '添加【执行过程明细】成功'
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
			  });
			
		}
	},'json');
}
</script>


<div style=" overflow:hidden; margin-top: 10px; margin-bottom:10px; border: 1px solid #D0CBCB; background-color: white;">
<div class="mui-input-row">
	<textarea  rows="1" placeholder="添加执行明细" id="comment" style="margin-bottom: 0px; border: 0px;"></textarea>
</div>

<div style="padding: 20px 0px 5px 0px;; overflow: hidden;" id="imgList">
		<c:choose>
	        <c:when test="${bj!='android'}">
            	<div id="annex" class="item_img_div">
				 <img  src="/static/images/base/annex.png"  />
				 <input type="file" id="file" name="file" onchange ="uploadFile(this)"  class="update_input"   accept="image/*">
				</div>
            </c:when>
        	<c:when test="${bj=='android'}">
            	<div id="annex" class="item_img_div">
				   <img  src="/static/images/base/annex.png" onclick="showDlg()"  />
				</div>
            </c:when>
        </c:choose>
</div>
</div>



<button type="button" onclick="addComment()" class="mui-btn mui-btn-warning mui-btn-block" style="padding: 4px 0px 4px 0px;border: 1px solid #1E9FFF;background-color: #1E9FFF;">提交</button>	

<div id="picture" class="mui-popover mui-popover-action mui-popover-bottom">
	<ul class="mui-table-view">
		<li class="mui-table-view-cell">
			<a onclick="makePhone()">拍照</a>
		</li>
		<li class="mui-table-view-cell">
			<a onclick="selectPhone()">相册</a>
		</li>
	</ul>
	<ul class="mui-table-view">
		<li class="mui-table-view-cell">
			<a href="#picture"><b>取消</b></a>
		</li>
	</ul>
</div>
					
					