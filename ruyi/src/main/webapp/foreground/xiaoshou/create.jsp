<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<script>

function save(){
	
	//显示正在上传附件  进度条
	layer.open({
	  type: 2
	  ,content: '正在提交'
	  ,shadeClose:false
	});
	
	
	var clientName = $("#clientName").val();
	var clientPhone = $("#clientPhone").val();
	var clientAddress = $("#clientAddress").val();
	
	var jixingId = $("#jixingId").val();
	var shuiyuanId = $("#shuiyuanId").val();
	var installPosId = $("#installPosId").val();
	var shuiya = $("#shuiya").val();
	
	var install_ = $("#install_").val();
	var ding_jine =  $("#ding_jine").val();
	var yu_jine = $("#yu_jine").val();
	var remark = $("#remark").val();
	
	
	if(clientName.length<1){
		layer.closeAll();
		layer.open({
		    content: '请输入客户姓名!!!!!!'
		    ,btn: '我知道了'
		});
		return ; 
	}
	
	if(clientPhone.length<1){
		layer.closeAll();
		layer.open({
		    content: '请输入客户手机!!!!!!'
		    ,btn: '我知道了'
		});
		return ; 
	}
	
	if(shuiya.length<1){
		shuiya = "0" ; 
	}
	
	if(ding_jine.length<1){
		ding_jine = 0 ; 
	}
	
	if(yu_jine.length<1){
		yu_jine = 0 ; 
	}
	
	
	$.post("/admin/xiaoshou/add",{clientName:clientName,clientPhone:clientPhone,clientAddress:clientAddress
		,install_:install_,ding_jine:ding_jine,yu_jine:yu_jine,remark:remark,jixingId:jixingId
		,shuiyuanId:shuiyuanId,installPosId:installPosId,shuiya:shuiya,ticheng:'不提成',ticheng_jine:0},function(result){
			if(result.success){
				
				
				$("#save").attr("disabled",'disabled');
				layer.closeAll();
				 //信息框
				  layer.open({
				    content: '流程创建成功'
				    ,btn: '我知道了'
			    	,yes: function(index){
			    		layer.close(index);
			    		window.location.href="/wap_index"
					    }
				  });
				 
			}else{
				
				layer.closeAll();
				
				 layer.open({
					    content: '流程创建失败!!!!!!!!'
					    ,btn: '我知道了'
				    	,yes: function(index){
				    		layer.close(index);
				    		window.location.href="/wap_index"
						    }
					  });
				 
				 
			}
			
	},'json');
	
	
}
</script>

<style>
.mui-input-row label {
    font-size: 15px;
    font-family: "Helvitica Neue", Helvitica, Arial, "Hiragino Sans GB", "Microsoft YaHei", "Arial Regular", "Microsoft JhengHei", sans-serif;
}
</style>

<header class="mui-bar mui-bar-nav">
	<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"><span style="font-size: 16px; line-height: 20px; height: 20px;">返回</span></a>
	<h1 class="mui-title">${title}</h1>
</header>

<div class="mui-content">
		
		<div style="overflow: hidden; background-color: white;">
			
			<form class="mui-input-group" style="margin-top: 5px;">
			    <div class="mui-input-row">
			        <label>客户姓名</label> <input type="text" id="clientName" class="mui-input-clear"
			            placeholder="客户姓名-必填" />
			    </div>
			    
			    <div class="mui-input-row">
			        <label>客户手机</label> <input type="text" id="clientPhone"  class="mui-input-clear"
			            placeholder="客户手机-必填" />
			    </div>
			    
			    <div class="mui-input-row">
			        <label>客户地址</label> <input type="text" id="clientAddress"   class="mui-input-clear"
			            placeholder="客户地址-选填" />
			    </div>

		
			    <div class="mui-input-row">
			        <label>机型</label>
					<select id="jixingId">
						<c:forEach var="jixing" items="${jixings}">
							<option value="${jixing.id}">${jixing.name}</option>
						</c:forEach>
			        </select>
			    </div>
			    
			    
			    <div class="mui-input-row">
			        <label>水源</label> 
			        <select id="shuiyuanId">
			        	<c:forEach var="shuiyuan" items="${shuiyuans}">
							<option value="${shuiyuan.id}">${shuiyuan.name}</option>
						</c:forEach>
			        </select>
			    </div>
			    
			    
			    <div class="mui-input-row">
			        <label>水压</label> <input type="text" id="shuiya"   class="mui-input-clear"
			            placeholder="请输入水压" />
			    </div>
			    
			    <div class="mui-input-row">
			        <label>安装位置</label>
			        <select id="installPosId">
			        	<c:forEach var="pos" items="${inspos}">
							<option value="${pos.id}">${pos.name}</option>
						</c:forEach>
			        </select>
			        
			    </div>
			    
			    <div class="mui-input-row">
			        <label>是否安装</label>
			        <select id="install_">
			        	  <option value="安装">安装</option>
			        	   <option value="不安装">不安装</option>
			        </select>
			    </div>
			    
			    <div class="mui-input-row">
			        <label>定金</label> 
			        <input type="text"  id="ding_jine" class="mui-input-clear"
			            placeholder="定金-选填" />
			    </div>
			    
			    <div class="mui-input-row">
			        <label>余款</label> <input type="text"  id="yu_jine" class="mui-input-clear"
			            placeholder="余款-选填" />
			    </div>
			</form>
			
			<div class="mui-input-row" style="margin: 10px 5px;">
				<textarea id="remark" rows="5" placeholder="备注"></textarea>
			</div>
			
			
			<div style="margin-top: 50px; padding: 10px 10px 10px 10px; ">
				<button type="button" id="save"  onclick="save()" class="mui-btn mui-btn-success mui-btn-block" style="padding: 4px 0px;">提交销售单</button>
			</div>
		</div>
		
		<div style="overflow: hidden; height: 55px; width: 0px;">底部留个空白</div>

</div>