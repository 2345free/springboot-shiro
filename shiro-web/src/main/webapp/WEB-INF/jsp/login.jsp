<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@include file="common/base.jsp" %>
    <title>Login</title>
</head>

<body>
<h1>登录页面<c:if test="${!empty message }">----${message }</c:if></h1>
<img alt="" src="${path }/pic.jpg">
<form:form action="${path }/login" commandName="user" method="post">
    用户名：<form:input path="username" class="Checktip" nullmsg="用户名不能为空" dataType="*"/>
    <form:errors path="username" cssClass="error"/>
    <br/>
    密码：<form:password path="password"/>
    <form:errors path="password" cssClass="error"/>
    <br/>
    <form:button name="button">提交</form:button>
</form:form>
</body>
</html>