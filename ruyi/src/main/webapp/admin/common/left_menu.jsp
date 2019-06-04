<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->


<!-- left 导航  --> 
<div class="layui-side layui-bg-black" style="top: 60px; ">
<div class="layui-side-scroll">
<ul class="layui-nav layui-nav-tree" lay-filter="demo">
  
  
   
  <c:forEach var="tree" items="${treeList }">
  <li class="layui-nav-item layui-nav-itemed">
    <a href="javascript:;">${tree.text}</a>
    <dl class="layui-nav-child">
    	<c:forEach var="child" items="${tree.children}">
    		<dd id="${child.dd_id}" url="${child.url}" text="${child.text}"><a style="cursor:pointer;"><i class="layui-icon">${child.iconCls }</i>&nbsp; ${child.text}</a></dd>
    	</c:forEach>
    </dl>
  </li>
  </c:forEach>
 
  
  
  
  
 <!--  
  <li class="layui-nav-item layui-nav-itemed">
    <a href="javascript:;">微信用户管理</a>
    <dl class="layui-nav-child">
      <dd id="wxuser"><a href="/redirect.html?url=/admin/page/wxuser/userManage.jsp&title=微信用户管理"><i class="layui-icon">&#xe63a;</i>&nbsp; 微信用户管理</a></dd>
    </dl>
  </li>
  
  
   <li class="layui-nav-item layui-nav-itemed">
    <a href="javascript:;">流程管理</a>
    <dl class="layui-nav-child">
      <dd id="deployment"><a href="/redirect.html?url=/admin/page/deploy/deployManage.jsp&title=流程部署"><i class="layui-icon">&#xe609;</i>&nbsp; 流程部署</a></dd>
      <dd id="processDefinition"><a href="/redirect.html?url=/admin/page/processDefinition/processDefinitionManage.jsp&title=流程定义管理"><i class="layui-icon">&#xe63c;</i>&nbsp; 流程定义管理</a></dd>
    </dl>
  </li>
  
  
   <li class="layui-nav-item layui-nav-itemed">
    <a href="javascript:;">辅助功能</a>
    <dl class="layui-nav-child">
      <dd id="note"><a href="/note/pc/manage"><i class="layui-icon">&#xe63c;</i>&nbsp; 备忘录</a></dd>
      <dd id="user"><a href="/user/pc/manage"><i class="layui-icon">&#xe63c;</i>&nbsp; 用户管理</a></dd>
      <dd id="group"><a href="/group/pc/manage"><i class="layui-icon">&#xe63c;</i>&nbsp; 部门&组 管理</a></dd>
      <dd id="test_load"><a href="/test"><i class="layui-icon">&#xe63c;</i>&nbsp; 测试懒加载图片</a></dd>
    </dl>
  </li>
  
  
  <li class="layui-nav-item layui-nav-itemed">
    <a href="javascript:;">其它</a>
    <dl class="layui-nav-child">
      <dd id="logout"><a href="/user/logout.html"><i class="layui-icon">&#xe63c;</i>&nbsp; 注销登陆</a></dd>
    </dl>
  </li>
 -->
 
  
</ul>
</div>

</div>
<!-- left 导航 结束 div -->

