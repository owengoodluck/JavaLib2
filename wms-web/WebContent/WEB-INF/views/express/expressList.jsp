<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!Doctype html>
<html>
<head>
<link rel="stylesheet"
	href='<c:url value="/resource/css/bootstrap.min.css" />'
	type="text/css"
/>
<script src='<c:url value="/resource/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resource/js/bootstrap.min.js"/>'></script>
<script type="text/javascript">
function submitForm(){
	$('#synForm').submit();
}
</script>
<title>快递列表</title>
</head>
<body>
	<div>
		<ol class="breadcrumb" align="left">
		  <!-- <li><a href="#">Home</a></li> -->
		  <li class="active">所有快递</li>
		</ol>
	</div>
	<section class="container-fluid ">
		<table class="table table-hover" >
			<thead>
				<tr >
					<th>快递单号</th>
					<th>订单号</th>
					<th>发货渠道</th>
					<th>收件人</th>
					<th>电话</th>
					<th>州</th>
					<th>城市</th>
					<th>创建日期</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${expressList}" var="express" >
					<tr align="left">
						<td >${express.epcode}</td>
						<td>${express.userOrderNumber}</td>
						<td>${express.channel}</td>
						<td>${express.name}</td>
						<td>${express.phone}</td>
						<td>${express.state}</td>	
						<td>${express.city}</td>
						<td>${express.sendDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</body>
</html>