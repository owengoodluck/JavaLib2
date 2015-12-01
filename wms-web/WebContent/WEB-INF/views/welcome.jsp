<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
>
<title>Welcome</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>${greeting}</h1>
				<p>${tagline}</p>
			</div>
		</div>
	</section>
	<div class="container">
		<a href="<spring:url value="picture/download" />" class="btn btn-success btn-mini pull-left" >图片下载</a> 
		<br/><hr/>
		<a href="<spring:url value="yanwen/create" />" class="btn btn-success btn-mini pull-left" >快递管理</a> 
		<br/><hr/>
		<a href="<spring:url value="order/list" />" class="btn btn-success btn-mini pull-left" >订单管理</a> 
		<br/><hr/>
		<a href="<spring:url value="prod/listAll" />" class="btn btn-success btn-mini pull-left" >产品列表</a> 
	</div>
	<div class="container">
	</div>
</body>
</html>