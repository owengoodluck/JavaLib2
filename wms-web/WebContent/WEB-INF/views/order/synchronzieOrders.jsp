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
    $( "#startDateStr" ).datepicker({"dateFormat":"yy-mm-dd"});
    $( "#endDateStr" ).datepicker({"dateFormat":"yy-mm-dd"});
});
    
function submitForm(){
	$('#synForm').submit();
}

</script>
<title>订单同步</title>
</head>
<body>
	<div class="container">
		<div align="left">
			<ol class="breadcrumb" >
			  <li ><a href="<spring:url value='/order/list' />">所有订单</a></li>
			  <li class="active">订单同步</li>
			</ol>
		</div>
		<div align="left">
		
	  	<form:form modelAttribute="synForm" enctype="multipart/form-data" action="/wms-web/order/synchronzieOrders">
				从<form:input path="startDateStr" size="10"/>
				到<form:input path="endDateStr" size="10"/>
				<input type="button" id="btnAdd" class="btn btn-primary" value="订单同步"  onclick="submitForm()" />
		</form:form>
		</div>
	</div>
</body>
</html>