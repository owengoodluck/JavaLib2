<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!Doctype html>
<html>
<link rel="stylesheet"
	href='<c:url value="/resource/css/bootstrap.min.css" />'
	type="text/css"
/>
<script src='<c:url value="/resource/js/jquery.min.js"/>'>
	
</script>
<script src='<c:url value="/resource/js/bootstrap.min.js"/>'>
	
</script>
<title>Products</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container-fluid">
				<h1>产品</h1>
				<p>产品列表</p>
				<a href='<c:url value="/" />' class="btn btn-success pull-left">首页</a>
				<a href='<c:url value="/prod/add" />' class="btn btn-info pull-left">添加产品</a>
			</div>
		</div>
	</section>
	
	<section class="container-fluid ">
		<table class="table table-striped">
			<caption>产品列表</caption>
			<thead>
				<tr>
					<th >SKU</th>
					<th>title</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="order" >
					<tr>
						<td width="9%">${order.itemSku}</td>
						<td width="9%">${order.itemName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</body>
</html>