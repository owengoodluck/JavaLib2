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
	<div>
		<ol class="breadcrumb" align="left">
		  <li ><a href="<spring:url value='/order/list' />">所有订单</a></li>
		  <li class="active">订单详情   ${order.amazonOrderId}</li>
		</ol>
	</div>
	<div align="left">
		<a class="btn btn-primary" href="<spring:url value='/yanwen/create?amazonOrderID=${order.amazonOrderId}' />" role="button" target="_blank">打印订单</a>
	</div>
	<section class="container-fluid " align="left">
		<table class="table table-striped">
			<thead>
				<tr align="center">
					<th>图片</th>
					<th>SKU</th>
					<th>订购价-USD</th>
					<th>进货价-RMB</th>
					<th>订购量</th>
					<th>库存量</th>
					<th>Amazon链接</th>
					<th>标题</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${order.orderItemList}" var="item" >
					<tr>
						<td><img src="/wms-web/img${item.getSellerSKU().getLocalImagePath()}"  height="60" onclick='window.open("/wms-web/img${item.getSellerSKU().getLocalImagePath()}")'></td>
						<td>
							<a href='<c:url value="/prod/edit/${item.sellerSKU.itemSku}" />' class="btn" target="_blank">${item.sellerSKU.itemSku}</a>
						</td>
						<td>${item.itemPriceAmount}</td>
						<td>${item.sellerSKU.purchasePrice}</td>
						<td>${item.quantityOrdered}</td>
						<td> 
							<c:if test="${item.quantityOrdered < item.sellerSKU.stockQuantity}">
								<span class="label label-success">${item.sellerSKU.stockQuantity}</span>
							</c:if>
							<c:if test="${item.quantityOrdered == item.sellerSKU.stockQuantity }">
								<span class="label-warning">${item.sellerSKU.stockQuantity}</span> 
							</c:if>
							<c:if test="${item.quantityOrdered > item.sellerSKU.stockQuantity }">
								<span class="label label-danger">${item.sellerSKU.stockQuantity}</span> 
							</c:if>
						</td>
						<td><a href='http://www.amazon.com/dp/${item.ASIN}' target="_blank"  class="btn">Amazon链接</a></td>
						<td>${item.title}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</body>
</html>