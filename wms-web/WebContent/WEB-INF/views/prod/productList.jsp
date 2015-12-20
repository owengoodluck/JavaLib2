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
<script type="text/javascript">
function exportExcel(){
	$('#productsForm').submit();
}

function selectAll(){
	$('[name=itemSkuList]').each(function(){
		if($(this).is(':checked')){     
			this.checked=false;
		}else{     
			this.checked=true;
		}  
	});
}
</script>
<title>Products</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container-fluid">
				<a href='<c:url value="/" />' class="btn btn-success pull-right">首页</a>
				<h1>产品</h1>
				<p>产品列表</p>
				<a href='<c:url value="/prod/addTitle" />' class="btn btn-info pull-left">添加产品</a>
			</div>
		</div>
	</section>
	
	<section class="container-fluid ">
		<form id="productsForm" action="/wms-web/prod/exportExcel" method="post">
			<table class="table table-striped">
				<caption>
					<input type="checkbox" onchange="selectAll()"/>全选 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
					<input type="button" id="btnAdd" class="btn btn-primary" value="导出Excel" onclick="exportExcel()"/>
					<input id="exportFolder" name='exportFolder' type="text"  style="width:30%" type='text' value="C:/Users/owen/Desktop/tmp" />
				</caption>
				<thead>
					<tr>
						<th >SKU</th>
						<th >Edit</th>
						<th >parentSku</th>
						<th>title</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="order" >
						<tr>
							<td width="9%"><input name="itemSkuList" id="itemSkuList" type="checkbox" value="${order.itemSku}"/>${order.itemSku}</td>
							<td width="9%"><a href='<c:url value="/prod/edit/${order.itemSku}" />' class="btn">编辑产品</a></td>
							<td width="9%">${order.itemSku}</td>
							<td width="9%">${order.parentSku}</td>
							<td width="80%">${order.itemName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</section>
</body>
</html>