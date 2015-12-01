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
$(document).ready(function(){
	$("#addChildBtn").click(function(){
		var value = $("#firstItem").html();
		//alert(value);
		$("#tbody").append("<tr>"+update(value)+"</tr>");
		//alert($("#tbody").html());
	});
	
});

function getPrfix(str){
	//list1.itemName -> return list1
	var index= str.indexOf('.');
	if(index!=-1){
		return str.substr(0,index)
	}else{
		return str;
	}
	
}
//update str to replace list0 or list[0] to  listX or list[X]
function update(str){
	var index = $("#myTable tr").length-1;
    //alert(index);
	while(str.indexOf("list0") !=-1){
		str = str.replace('list0',"list"+index);
	}
	while(str.indexOf("list[0]") !=-1){
		str = str.replace('list[0]','list['+index+']');
	}
	return str;
}

function checkTitleLength(currentInput){
	var thisID = currentInput.id;//list${status.index}.itemName
	var text = currentInput.value;
	
	var prefix = getPrfix(thisID);
	var warnLabel = $("#"+prefix+'_warnLable');
	if(text.length > 105){
		warnLabel.html('当前长度超过为'+text.length+',超过105');
		warnLabel.show();
		//alert(text+1);
	}else{
		warnLabel.hide();
		//alert(text+2);
	}
}
</script>
<title>AddJewelry</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container-fluid">
				<a href='<c:url value="/" />' class="btn btn-success pull-right">首页</a>
				<h1>产品</h1>
				<p>添加产品</p>
			</div>
		</div>
	</section>
	
	<section class="container-fluid">
		<form:form modelAttribute="productsForm" enctype="multipart/form-data" >
			<table id="myTable" class="table table-striped">
				<caption>产品详细字段 <button id="addChildBtn" type="button" class="btn btn-primary">添加子产品</button></caption>
				<tbody id="tbody">
					<c:if test="${productsForm.list != null }">
						<c:forEach items="${productsForm.list}" var="prod" varStatus="status">
							<c:if test="${ status.index == 0}">
								<tr id="firstItem">
									<td width="5%">itemSku</td>
									<td width="10%"><input id="list${status.index}.itemSku"  name='list[${status.index}].itemSku'  type="text"  style="width:100%" type='text' value="${prod.itemSku}"/></td>
									<td width="5%">itemName(Title)</td>
									<td width="80%">
										<input id="list${status.index}.itemName" name='list[${status.index}].itemName' type="text"  style="width:100%" type='text' value="${prod.itemName}" onchange="checkTitleLength(this)" onkeypress="checkTitleLength(this)"/>
										<span  id="list${status.index}_warnLable" class="label label-danger"></span>
									</td>
								</tr>
							</c:if>
							<c:if test="${ status.index > 0}">
								<tr>
									<td width="5%">itemSku</td>
									<td width="10%"><input id="list${status.index}.itemSku" name='list[${status.index}].itemSku' type="text"  style="width:100%" type='text' value="${prod.itemSku}"/></td>
									<td width="5%">itemName(Title)</td>
									<td width="80%">
										<input id="list${status.index}.itemName" name='list[${status.index}].itemName' type="text"  style="width:100%" type='text' value="${prod.itemName}" onchange="checkTitleLength(this)" onkeypress="checkTitleLength(this)"/>
										<span  id="list${status.index}_warnLable" class="label label-danger"></span>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
				</tbody>
				<tr>
					<td><input type="submit" id="btnAdd" class="btn btn-primary" value="提交" /></td>
				</tr>
			</table>
		</form:form>
	</section>
</body>
</html>