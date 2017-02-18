<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="common/base.jsp" %>
    <title>用户列表</title>
  </head>
  <body>
    <h1>user page</h1>
    <h1>用户列表--<a href="${path }/logout">退出登录</a>    </h1>
    <h2>权限列表</h2>
    <shiro:authenticated>用户已经登录显示此内容<br/></shiro:authenticated><br/>
    <shiro:hasRole name="manager">manager角色登录显示此内容<br/></shiro:hasRole>
    <shiro:hasRole name="admin">admin角色登录显示此内容<br/></shiro:hasRole>
    <shiro:hasRole name="normal">normal角色登录显示此内容<br/></shiro:hasRole><br/>

    <shiro:hasAnyRoles name="manager,admin">manager or admin 角色用户登录显示此内容<br/></shiro:hasAnyRoles><br/>
    <shiro:principal/>-显示当前登录用户名<br/><br/>
    <shiro:hasPermission name="add">add权限用户显示此内容<br/></shiro:hasPermission>
    <shiro:hasPermission name="user:query">user:query权限用户显示此内容<br/></shiro:hasPermission>
    <shiro:lacksPermission name="user:query">不具有user:query权限的用户显示此内容 <br/></shiro:lacksPermission>

    <br/>所有用户列表：<br/>
    <ul>
        <c:forEach items="${userList }" var="user">
            <li>用户名：${user.username }----密码：${user.password }----<a href="${path }/user/edit/${user.id}">修改用户（测试根据不同用户可访问权限不同，本例tom无权限，jack有权限）</a></li>
        </c:forEach>
    </ul>
    <img alt="" src="${path }/pic.jpg">
    
    <!-- Web前端资源打包成Java的Jar包，然后借助Maven这些依赖库的管理  -->
    <script type="text/javascript" src="/webjars-locator/jquery/jquery.js"></script>
	<%--
	<!-- spring boot 默认的静态资源映射规则  -->
    <script type="text/javascript" src="js/jquery.js"></script>
    <!-- jstl 标签设置固定的cdn路径  -->
	<script type="text/javascript" src="${jqueryCdnUrl }"></script>
	 --%>
  </body>
</html>