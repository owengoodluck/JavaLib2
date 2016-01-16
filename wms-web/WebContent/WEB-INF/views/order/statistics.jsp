<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	$('#statisticsForm').submit();
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
<title>销售统计</title>
</head>
<body>
	
	<div class="container">
	<div>
		<ol class="breadcrumb" align="left">
		  <li class="active">销售统计</li>
		</ol>
	</div>
	   <div class="row">
	      	<form  id="statisticsForm" action="/wms-web/order/statistics">
	      		<div class="col-md-3" align="left">
		      		<select id ="statisticType" name = "statisticType" onchange="submitForm()">
		      			<option value="latestDate" <c:if test="${ statisticType == 'latestDate' }"> selected="selected"</c:if> >按销售日期排序</option>
		      			<option value="sellCount"  <c:if test="${ statisticType == 'sellCount' }"> selected="selected"</c:if>>按销售数量排序</option>
		      		</select>
				</div>
			</form>
	   </div>
	</div>
	
	<section class="container">
		<table class="table table-hover" >
			<thead>
				<tr >
					<th >序号</th>
					<th >图片</th>
					<th >SKU</th>
					<th>销售数量</th>
					<th>最近销售日期</th>
					<th>库存数量</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="item" varStatus="status">
					<tr align="left">
						<td width="3%">
							${ status.index + 1}
						</td>  
						<td width="9%">
							<img src="/wms-web/img${item.getLocalImagePath()}"  height="40" onclick='window.open("/wms-web/img${item.getLocalImagePath()}")'>
						</td>
						<td align="left" width="9%">${item.itemSku}</td>
						<td align="left" width="9%">${item.sellCount}</td>
						<td align="left" width="9%">
							<c:if test="${ statisticType == 'latestDate' }"> 
								<fmt:formatDate value="${item.latestDate}" pattern="yyyy-MM-dd"/>
							</c:if>
						</td>
						<td align="left"  width="9%">${item.stockQuantity}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	</section>
	<div align="left">
		
	</div>
</body>
</html>