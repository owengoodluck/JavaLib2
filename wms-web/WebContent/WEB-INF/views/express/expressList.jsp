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
    $( "#sendDateFrom" ).datepicker({"dateFormat":"yy-mm-dd"});
    $( "#sendDateTo" ).datepicker({"dateFormat":"yy-mm-dd"});
});
function submitForm(num){
	var currentPage = $('#currentPage').val();
	if(0==num){
		$('#currentPage').val(1);
	}else{
		$('#currentPage').val(Number(currentPage)+Number(num));
	}
	$('#expressQueryForm').submit();
}

function cleanForm(){
	$('#expressID').val(null);
	$('#orderID').val(null);
	$('#receiver').val(null);
	$('#sendDateFrom').val(null);
	$('#sendDateTo').val(null);
	$('#channel').val(null);
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
	
	<div class="container-fluid">
	   <div class="row">
	      	<form:form modelAttribute="expressQueryForm" enctype="multipart/form-data" action="/wms-web/yanwen/pageQuery">
		      	<div align="left">
			      	快递单号:<form:input path="expressID" size="10"/>
			      	订单号:<form:input path="orderID" size="10"/>
			      	收件人:<form:input path="receiver" size="10"/>
			      	发货日期 从:<form:input path="sendDateFrom" size="10"/>
			      	到:<form:input path="sendDateTo" size="10"/>
			      	发货渠道:
			      	<form:select path="channel">
			      		<form:option value="">全部</form:option>
			      		<form:option value="中邮北京E邮宝(线下)">中邮北京E邮宝(线下)</form:option>
			      		<form:option value="中邮北京平邮小包">中邮北京平邮小包</form:option>
			      		<form:option value="中邮上海E邮宝(线下)">中邮上海E邮宝(线下)</form:option>
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
				<c:forEach items="${page.list}" var="express" >
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