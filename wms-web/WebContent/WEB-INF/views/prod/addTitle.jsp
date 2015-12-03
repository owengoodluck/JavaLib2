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
	
	$("#deleteChildBtn").click(function(){
		if($("#tbody tr").length>1){
			$("#tbody tr:last").remove();
		}
	});
	
	$("input.titleClass").each(function(){
		checkTitleLength(this);
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
		warnLabel.html('当前长度为'+text.length+',超过105，请删除不必要的字段');
		//warnLabel.show();
		warnLabel.removeClass('label-warn');
		warnLabel.addClass('label-danger');
		//alert(text+1);
	}else{
		warnLabel.html('当前长度为'+text.length);
		warnLabel.addClass('label-success');
		warnLabel.removeClass('label-danger');
		//warnLabel.hide();
		//alert(text+2);
	}
}
</script>
<title>AddPordTitle</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container-fluid">
				<a href='<c:url value="/" />' class="btn btn-success pull-right">首页</a>
				<h1>产品</h1>
				<p>添加产品标题，SKU</p>
			</div>
		</div>
	</section>
	
	<section class="container-fluid">
		<form:form modelAttribute="productsForm" enctype="multipart/form-data" action="addTitle">
			<table id="myTable" class="table table-striped">
				<caption>
					产品详细字段 
					<button id="addChildBtn"    type="button" class="btn btn-primary">添加子产品</button>
					<button id="deleteChildBtn" type="button" class="btn btn-primary">删除最后一个子产品</button>
				</caption>
				<tbody id="tbody">
					<c:if test="${productsForm.list != null }">
						<c:forEach items="${productsForm.list}" var="prod" varStatus="status">
							<c:if test="${ status.index == 0}">
								<tr id="firstItem">
									<td width="0.1%">SKU</td>
									<td width="15%"><input id="list${status.index}.itemSku"  name='list[${status.index}].itemSku'  type="text"  style="width:100%" type='text' value="${prod.itemSku}"/></td>
									<td width="1%">UPC</td>
									<td width="8%"><input id="list${status.index}.externalProductId"  name='list[${status.index}].externalProductId'  type="text"  style="width:100%" type='text' value="${prod.externalProductId}"/></td>
									<td width="3.5%">子分类依据</td>
									<td width="8%"><input id="list${status.index}.variationTheme"  name='list[${status.index}].variationTheme'  type="text"  style="width:100%" type='text' value="${prod.variationTheme}"/></td>
									<td width="5%">标题</td>
									<td width="80%">
										<input class="titleClass" id="list${status.index}.itemName" name='list[${status.index}].itemName' type="text"  style="width:100%" type='text' value="${prod.itemName}" onchange="checkTitleLength(this)" onkeypress="checkTitleLength(this)"/>
										<span  id="list${status.index}_warnLable" class="label label-danger"></span>
									</td>
								</tr>
							</c:if>
							<c:if test="${ status.index > 0}">
								<tr>
									<td width="1%">SKU</td>
									<td width="15%"><input id="list${status.index}.itemSku" name='list[${status.index}].itemSku' type="text"  style="width:100%" type='text' value="${prod.itemSku}"/></td>
									<td width="1%">UPC</td>
									<td width="8%"><input id="list${status.index}.externalProductId"  name='list[${status.index}].externalProductId'  type="text"  style="width:100%" type='text' value="${prod.externalProductId}"/></td>
									<td width="3%">子类分类依据</td>
									<td width="8%"><input id="list${status.index}.variationTheme"  name='list[${status.index}].variationTheme'  type="text"  style="width:100%" type='text' value="${prod.variationTheme}"/></td>
									<td width="5%">标题</td>
									<td width="80%">
										<input class="titleClass" id="list${status.index}.itemName" name='list[${status.index}].itemName' type="text"  style="width:100%" type='text' value="${prod.itemName}" onchange="checkTitleLength(this)" onkeypress="checkTitleLength(this)"/>
										<span  id="list${status.index}_warnLable" class="label label-danger"></span>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
				</tbody>
				<tr>
					<td><input type="button" id="btnAdd" class="btn btn-primary" value="提交"  onclick="this.form.submit()" /></td>
				</tr>
			</table>
		</form:form>
	</section>
</body>
</html>