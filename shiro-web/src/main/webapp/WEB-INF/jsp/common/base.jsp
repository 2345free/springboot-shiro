<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(80 == request.getServerPort()?"":":"+request.getServerPort())+path+"/";
%>
<c:set var="jqueryCdnUrl" value="//cdn.bootcss.com/jquery/3.1.1/jquery.js"></c:set>

<!-- head part -->
<meta charset="utf-8" />
 <meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="renderer" content="webkit" />
<meta name="keywords" content="492222986" />
<meta name="description" content="492222986" />
<link type="image/x-icon" href="favorite.ico" rel="shortcut icon" />