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
	$('#synForm').submit();
}
</script>
<title>所有订单</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container-fluid">
				<a href='<c:url value="/" />' class="btn btn-success pull-right">首页</a>
				<h1>订单</h1>
				<p>订单列表</p>
			</div>
		</div>
	</section>
	
	<section class="container-fluid ">
		<table class="table table-striped">
			<caption>
				<form:form modelAttribute="synForm" enctype="multipart/form-data" action="/wms-web/order/synchronzieOrders">
					<form:input path="startDateStr"/>
					<form:input path="endDateStr"/>
					<input type="button" id="btnAdd" class="btn btn-primary" value="订单同步"  onclick="submitForm()" />
				</form:form>
			</caption>
			<thead>
				<tr>
					<th >订单号</th>
					<th>购买日期</th>
					<th>最后更新时间</th>
					<th>订单状态</th>
					<th>发货数量</th>
					<th>未发货数量</th>
					<th>fulfillmentChannel</th>
					<!-- <th>isBusinessOrder</th>
					<th>isPremiumOrder</th>
					<th>isPrime</th> -->
					<!-- <th>marketplaceId</th> -->
					<!-- <th>orderType</th>
					<th>salesChannel</th> -->
					<th>shipServiceLevel</th>
					<!-- <th>shipmentServiceLevelCategory</th> -->
					<th>latestShipDate</th>
					<th>earliestShipDate</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orderList}" var="order" >
					<tr>
						<td width="9%">
							<a href='<c:url value="/order/detail/${order.getAmazonOrderId()}" />' target="_blank"  class="btn" >${order.getAmazonOrderId()}</a>
						</td>
						<td>${order.getPurchaseDate()}</td>
						<td>${order.getLastUpdateDate()}</td>
						<td>${order.getOrderStatus()}</td>
						<td>${order.getNumberOfItemsShipped()}</td>
						<td>${order.getNumberOfItemsUnshipped()}</td>	
						<td>${order.getFulfillmentChannel()}</td>
						<%-- <td>${order.getIsBusinessOrder()}</td>
						<td>${order.getIsPremiumOrder()}</td>
						<td>${order.getIsPrime()}</td> --%>
						<%-- <td>${order.getMarketplaceId()}</td> --%>
						<%-- <td>${order.getOrderType()}</td> --%>
						<%-- <td>${order.getSalesChannel()}</td> --%>
						<td>${order.getShipServiceLevel()}</td>
						<%-- <td>${order.getShipmentServiceLevelCategory()}</td> --%>
						<td>${order.getLatestShipDate()}</td>
						<td>${order.getEarliestShipDate()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</body>
</html>