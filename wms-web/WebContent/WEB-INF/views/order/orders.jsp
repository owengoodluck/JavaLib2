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

<link rel="stylesheet" href='<c:url value="/resource/css/jquery-ui.min.css" />' type="text/css" />
<script src='<c:url value="/resource/js/jquery-ui.min.js"/>'></script>

<script type="text/javascript">
$(function() {
    $( "#queryOrderDateFrom" ).datepicker({"dateFormat":"yy-mm-dd"});
    $( "#queryOrderDateTo" ).datepicker({"dateFormat":"yy-mm-dd"});
});
function submitForm(num){
	var currentPage = $('#currentPage').val();
	if(0==num){
		$('#currentPage').val(1);
	}else{
		$('#currentPage').val(Number(currentPage)+Number(num));
	}
	$('#orderQueryForm').submit();
}

function cleanForm(){
	$('#queryOrderID').val(null);
	$('#queryOrderDateFrom').val(null);
	$('#queryOrderDateTo').val(null);
	$('#queryOrderStatus').val(null);
}

function selectAll(isCheckeced){
	$('[name=amazonOrderIds]').each(function(){
		if($(this).is(':checked')){     
			this.checked=isCheckeced;
		}else{     
			this.checked=isCheckeced;
		}  
	});
}

function confirmShipment(){
	var isAnyChecked  = false;
	$('[name=amazonOrderIds]').each(function(){
		if($(this).is(':checked')){     
			isAnyChecked = true;
		}
	});
	if(isAnyChecked){
		$('#confirmShipmentForm').submit();
	}else{
		alert("请选择至少一条未发货记录。");	
	}
}
</script>
<title>订单列表</title>
</head>
<body>
	<div class="container-fluid">
	   <div class="row">
	      	<form:form modelAttribute="orderQueryForm" enctype="multipart/form-data" action="/wms-web/order/queryOrders">
	      		<div class="col-md-3" align="left">
		      		<input type="checkbox" onchange="selectAll(this.checked)"/>全选未发货
		      		<input type="button" value="确认发货" class="btn btn-primary" onclick="confirmShipment()"/>
				</div>
		      	<div class="col-md-9" align="right">
			      	订单号:<form:input path="queryOrderID" size="10"/>
			      	购买日期 从:<form:input path="queryOrderDateFrom" size="10"/>
			      	到:<form:input path="queryOrderDateTo" size="10"/>
			      	订单状态:
			      	<form:select path="queryOrderStatus">
			      		<form:option value="">全部订单</form:option>
			      		<form:option value="Unshipped">未发货</form:option>
			      		<form:option value="Shipped">已发货</form:option>
			      		<form:option value="Canceled">已取消</form:option>
			      		<form:option value="Pending">Pending</form:option>
			      	</form:select>
			      	<input type="button" value="清空条件" class="btn btn-primary" onclick="cleanForm()"/>
					<input type="submit" id="btnAdd" class="btn btn-primary" value="查询" onclick="submitForm(0)"/>
					每页显示：<form:input path="pageSize" size="3"/>
					总页数 <b>${page.totalPage }</b>： 总条数<b>${page.totalCount }</b>
		      		<input type="button" value="上一页" class="btn btn-primary" <c:if test='${!page.hasPrePage }'>disabled="disabled"</c:if> onclick="submitForm(-1)"/>
					当前页：<form:input path="currentPage" size="3"/>
					<input type="button" value="下一页" class="btn btn-primary" <c:if test='${!page.hasNextPage }'>disabled="disabled"</c:if> onclick="submitForm(1)"/>
		      	</div>
		      	     
			</form:form>
	   </div>
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
					<th>是否打印</th>
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
				<form id="confirmShipmentForm" enctype="multipart/form-data" action="/wms-web/order/confirmShipment" method="post">
				<c:forEach items="${page.list}" var="order" varStatus="status">
					<tr align="left">
						<td>
							${ status.index + 1}
							<c:if test="${order.getNumberOfItemsUnshipped() > 0 && order.getOrderStatus()!='Pending'}">
								<input name="amazonOrderIds" id="amazonOrderIds" type="checkbox" value="${order.amazonOrderId}"/>
							</c:if>
						</td>  
						<td width="9%">
							<c:forEach items="${order.orderItemList}" var="item" >
								<img src="/wms-web/img${item.getSellerSKU().getLocalImagePath()}"  height="40" onclick='window.open("/wms-web/img${item.getSellerSKU().getLocalImagePath()}")'>
							</c:forEach>
						</td>
						<td align="left"><a href='<c:url value="/order/detail/${order.getAmazonOrderId()}" />' target="_blank" >${order.getAmazonOrderId()}</a></td>
						<td align="left">${order.getPurchaseDate()}</td>
						<td>${order.getLastUpdateDate()}</td>
						<td>
							<c:if test="${order.getOrderStatus()== 'Unshipped' || order.getOrderStatus()== 'Shipped'}">
								${order.getOrderStatus()}
							</c:if>
							<c:if test="${ ! ( order.getOrderStatus() == 'Unshipped' || order.getOrderStatus()== 'Shipped') }">
								<span class="label label-warning">${order.getOrderStatus()} </span>
							</c:if>
						</td>
						<td>${order.getProfit()}</td>  
						<td>${order.getNumberOfItemsShipped()}</td>
						<td>
							<c:if test="${order.getNumberOfItemsUnshipped() == 0}">
								${order.getNumberOfItemsUnshipped()}
							</c:if>
							<c:if test="${order.getNumberOfItemsUnshipped() > 0 }">
								<span class="label label-warning">&nbsp;&nbsp;  ${order.getNumberOfItemsUnshipped()} &nbsp;&nbsp;</span>
							</c:if>
						</td>	
						<td>
							<c:if test="${order.isPrinted}">
								已打印
							</c:if>
							<c:if test="${!order.isPrinted }">
								<span class="label label-warning">未打印</span>
							</c:if>
						</td>
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
				</form>
			</tbody>
		</table>
	
	</section>
	<div align="left">
		
	</div>
</body>
</html>