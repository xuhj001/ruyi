<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<script>
function select_point(){
	window.location.href="/map/wap/select_point?xiaoshouId="+${renwu.xiaoshou.id};
}
</script>

<div style="text-align: center;overflow: hidden;">
	<a onclick="select_point()">地图选点</a>
</div>


