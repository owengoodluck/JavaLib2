<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<!Doctype html>
<html>
<link rel="stylesheet"
	href='<c:url value="/resource/css/bootstrap.min.css" />'
	type="text/css"
/>
<script src='<c:url value="/resource/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resource/js/bootstrap.min.js"/>'></script>
<script src='<c:url value="/resource/js/owen.js"/>'></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$("input").each(function(){
		$(this).keypress(function(){
			copyValue(this);
		});
		$(this).keyup(function(){
			copyValue(this);
		});
		$(this).change(function(){
			copyValue(this);
		});
	});
	
});

function submitForm(preOrNext){
	$('#preOrNext').val(preOrNext);
	$('#productsForm').submit();
}
</script>
<title>AddPordPrice</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container-fluid">
				<a href='<c:url value="/" />' class="btn btn-success pull-right">首页</a>
				<h1>产品</h1>
				<p>价格和库存</p>
			</div>
		</div>
	</section>
	
	<section class="container-fluid">
		<form:form modelAttribute="productsForm" enctype="multipart/form-data" action="addPrice">
			<table id="myTable" class="table table-striped">
				<caption>价格和库存
					<input type="button" id="btnAdd" class="btn btn-primary" value="前一页" onclick="submitForm('pre')"/>
					<input type="button" id="btnAdd" class="btn btn-primary" value="下一页"  onclick="submitForm('next')" />
					<input type="checkbox" id="synchronizeBox" >同步更新后续子产品</input>
				</caption>
				<tbody id="tbody">
					<c:if test="${productsForm.list != null }">
						<c:forEach items="${productsForm.list}" var="prod" varStatus="status">
							<tr>
								<td width="0.1%">
									<c:if test="${ prod.getLocalImagePath() !=null }">
										<img src="/wms-web/${prod.getLocalImagePath()}"  height="35"> 
									</c:if>
								</td>
								<td width="5%">SKU</td>
								<td width="10%"><input id="list${status.index}.itemSku" name='list[${status.index}].itemSku' type="text"  style="width:100%" type='text' value="${prod.itemSku}"/></td>
								<td width="5%">售价</td>
								<td width="20%">
									<input id="list${status.index}.standardPrice" name='list[${status.index}].standardPrice' type="text"  style="width:100%" type='text' value="${prod.standardPrice}" />
								</td>
								<td width="5%">原价</td>
								<td width="20%">
									<input id="list${status.index}.listPrice" name='list[${status.index}].listPrice' type="text"  style="width:100%" type='text' value="${prod.listPrice}" />
								</td>
								<td width="5%">折扣率</td>
								<td width="10%">
									<c:if test="${prod.standardPrice != null && prod.listPrice != null && prod.listPrice != 0}">
										<input disabled="true" type="text"  style="width:100%" type='text' value='<fmt:parseNumber value="${(100*prod.standardPrice/prod.listPrice)}" integerOnly="true"/>%' />
									</c:if>
								</td>
								<td width="5%">库存数量</td>
								<td width="20%">
									<input id="list${status.index}.quantity" name='list[${status.index}].quantity' type="text"  style="width:100%" type='text' value="${prod.quantity}" />
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<tr>
					<input id="preOrNext" name="preOrNext" type="hidden"/>
					<td><input type="button" id="btnAdd" class="btn btn-primary" value="前一页" onclick="submitForm('pre')"/></td>
					<td><input type="button" id="btnAdd" class="btn btn-primary" value="下一页"  onclick="submitForm('next')" /></td>
				</tr>
			</table>
		</form:form>
	</section>
</body>
</html>