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
<title>订单列表</title>
</head>
<body>
	<div>
		<ol class="breadcrumb" align="left">
		  <!-- <li><a href="#">Home</a></li> -->
		  <li class="active">所有订单</li>
		</ol>
	</div>
	<div align="right">
		<form:form modelAttribute="synForm" enctype="multipart/form-data" action="/wms-web/order/synchronzieOrders">
			<form:input path="startDateStr"/>
			<form:input path="endDateStr"/>
			<input type="button" id="btnAdd" class="btn btn-primary" value="订单同步"  onclick="submitForm()" />
		</form:form>
	</div>
	
	<section class="container-fluid ">
		<table class="table table-hover" >
			<thead>
				<tr >
					<th >序号</th>
					<th >图片</th>
					<th >订单号</th>
					<th>购买日期</th>
					<th>最后更新时间</th>
					<th>订单状态</th>
					<th>利润概览</th>
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
				<c:forEach items="${orderList}" var="order" varStatus="status">
					<tr align="left">
						<td>${ status.index + 1}</td>  
						<td width="9%">
							<c:forEach items="${order.orderItemList}" var="item" >
								<img src="/wms-web/img${item.getSellerSKU().getLocalImagePath()}"  height="40" onclick='window.open("/wms-web/img${item.getSellerSKU().getLocalImagePath()}")'>
							</c:forEach>
						</td>
						<td align="left"><a href='<c:url value="/order/detail/${order.getAmazonOrderId()}" />' target="_blank"  class="btn" >${order.getAmazonOrderId()}</a></td>
						<td align="left">${order.getPurchaseDate()}</td>
						<td>${order.getLastUpdateDate()}</td>
						<td>${order.getOrderStatus()}</td>
						<td>${order.getProfit()}</td>  
						<td>${order.getNumberOfItemsShipped()}</td>
						<td>
							<c:if test="${order.getNumberOfItemsUnshipped() == 0}">
								${order.getNumberOfItemsUnshipped()}
							</c:if>
							<c:if test="${order.getNumberOfItemsUnshipped() > 0 }">
								<span class="label label-warning">&nbsp&nbsp  ${order.getNumberOfItemsUnshipped()} &nbsp&nbsp</span>
							</c:if>
						</td>	
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