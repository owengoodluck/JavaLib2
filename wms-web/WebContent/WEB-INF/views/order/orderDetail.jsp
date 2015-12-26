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
<script src='<c:url value="/resource/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resource/js/bootstrap.min.js"/>'></script>
<script type="text/javascript">
function submitForm(){
}
</script>
<title>订单详情-${order.amazonOrderId}</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container-fluid">
				<a href='<c:url value="/" />' class="btn btn-success pull-right">首页</a>
				<h1>订单</h1>
				<p>订单详情</p>
			</div>
		</div>
	</section>
	
	<section class="container-fluid ">
		<table class="table table-striped">
			<caption>
				订单详情
				<b>${order.amazonOrderId}</b>
				<a href="/wms-web/yanwen/create?amazonOrderID=${order.amazonOrderId}" target="_blank">打印订单</a>
			</caption>
			<thead>
				<tr>
					<th>SKU</th>
					<th>价格</th>
					<th>数量</th>
					<th>Amazon链接</th>
					<th>标题</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${order.orderItemList}" var="item" >
					<tr>
						<td><img src="/wms-web/${order.getLocalImagePath()}"  height="30"> </td>
						<td>${item.sellerSKU}</td>
						<td>${item.itemPriceAmount}</td>
						<td>${item.quantityOrdered}</td>
						<td><a href='http://www.amazon.com/dp/${item.ASIN}' target="_blank"  class="btn">Amazon链接</a></td>
						<td>${item.title}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</body>
</html>