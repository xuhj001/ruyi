<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->


<style>
.entity_item {
	overflow: hidden;
	padding: 6px 10px 0px 10px;
}

.entity_item .first_div {
	overflow: hidden;
	padding: 0px 0px 6px 0px;
	border-bottom: 1px solid #bbb;
	display: flex;
	display: -webkit-flex;
}

.entity_item .div_left {
	overflow: hidden;
	width: 115px;
	font-size: 14px;
	font-family: "Helvitica Neue", Helvitica, Arial, "Hiragino Sans GB",
		"Microsoft YaHei", "Arial Regular", "Microsoft JhengHei", sans-serif;
	text-align: right;
    padding-right: 23px;
}

.entity_item .div_right {
	overflow: hidden;
	flex: 1;
	-webkit-flex: 1;
	font-size: 14px;
	font-family: "Helvitica Neue", Helvitica, Arial, "Hiragino Sans GB",
		"Microsoft YaHei", "Arial Regular", "Microsoft JhengHei", sans-serif;
}
</style>


<div style="overflow: hidden; background-color: white; margin-top: 10px; margin-bottom:10px; ">
	
	<div class="entity_item">
		<div class="first_div">
			<div class="div_left">订单编号：</div>
			<div class="div_right">${renwu.dingdan_num}</div>
		</div>
	</div>
	
	<div class="entity_item">
		<div class="first_div">
			<div class="div_left">客户姓名：</div>
			<div class="div_right">${renwu.xiaoshou.clientName}</div>
		</div>
	</div>
	
	<div class="entity_item">
		<div class="first_div">
			<div class="div_left">客户电话：</div>
			<div class="div_right"><a href="tel:${renwu.xiaoshou.clientPhone}">${renwu.xiaoshou.clientPhone}</a></div>
		</div>
	</div>
	
	<div class="entity_item">
		<div class="first_div">
			<div class="div_left">客户地址：</div>
			<div class="div_right">${renwu.xiaoshou.clientAddress}</div>
		</div>
	</div>
	
	<div class="entity_item">
		<div class="first_div">
			<div class="div_left">是否安装：</div>
			<div class="div_right">${renwu.xiaoshou.install_}</div>
		</div>
	</div>
	
	<div class="entity_item">
		<div class="first_div">
			<div class="div_left">机型：</div>
			<div class="div_right">${renwu.xiaoshou.jixing.name}</div>
		</div>
	</div>
	
	<div class="entity_item">
		<div class="first_div">
			<div class="div_left">安装位置：</div>
			<div class="div_right">${renwu.xiaoshou.installPos.name}</div>
		</div>
	</div>
	<div class="entity_item">
		<div class="first_div">
			<div class="div_left">水源：</div>
			<div class="div_right">${renwu.xiaoshou.shuiyuan.name}</div>
		</div>
	</div>
	
	<div class="entity_item">
		<div class="first_div">
			<div class="div_left">水压：</div>
			<div class="div_right">${renwu.xiaoshou.shuiya}</div>
		</div>
	</div>
	
	
	<div class="entity_item">
		<div class="first_div">
			<div class="div_left">定金：</div>
			<div class="div_right">${renwu.xiaoshou.ding_jine}</div>
		</div>
	</div>
	
	<div class="entity_item">
		<div class="first_div">
			<div class="div_left">余款：</div>
			<div class="div_right">${renwu.xiaoshou.yu_jine}</div>
		</div>
	</div>
	
	
	<div class="entity_item">
		<div class="first_div">
			<div class="div_left">备注：</div>
			<div class="div_right">${renwu.xiaoshou.remark}</div>
		</div>
	</div>
	
	
	<div class="entity_item">
		<div class="first_div">
			<div class="div_left">创建人员：</div>
			<div class="div_right">${renwu.createUser.first_ }(<a href="tel:${renwu.createUser.last_}">${renwu.createUser.last_}</a>)</div>
		</div>
	</div>
	
	
	<div class="entity_item">
		<div class="first_div">
			<div  class="div_left">创建时间：</div>
			<div class="div_right"><fmt:formatDate value="${renwu.createDateTime}" pattern="yyyy-MM-dd HH:mm"/></div>
		</div>
	</div>
	
	
	<c:if test="${(renwu.state == 1)}">
		<div class="entity_item">
			<div class="first_div">
				<div class="div_left">正在办理：</div>
				<div class="div_right">${renwu.acceptUser.first_}(<a href="tel:${renwu.acceptUser.last_}">${renwu.acceptUser.last_}</a>)</div>
			</div>
		</div>
	</c:if>
</div>





				
				


