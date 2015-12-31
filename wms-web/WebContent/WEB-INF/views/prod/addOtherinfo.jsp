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
<title>AddPordgenericKeywords</title>
</head>
<body>
	<div>
		<ol class="breadcrumb" align="left">
		  <li >产品基本信息  </li>
		  <li >产品图片  </li>
		  <li >产品特性描述  </li>
		  <li >搜索关键字  </li>
		  <li >价格和库存  </li>
		  <li class="active">其他信息   </li>
		</ol>
	</div>
	
	<section class="container-fluid">
		<form:form modelAttribute="productsForm" enctype="multipart/form-data" action="addOtherinfo">
			<table id="myTable" class="table table-striped">
				<caption>
					<input type="button" id="btnAdd" class="btn btn-primary" value="前一页" onclick="submitForm('pre')"/>
					<input type="button" id="btnAdd" class="btn btn-primary" value="下一页"  onclick="submitForm('next')" />
					<input type="checkbox" id="synchronizeBox" >同步更新后续子产品</input>
				</caption>
				<thead>
					<tr>
						<th>SKU</th> 
						<th>主缩略图</th> 
						<th>metalType</th> 
						<th>metalStamp</th> 
						<th>gemType1</th> 
						<th>gemType2</th> 
						<th>gemType3</th> 
						<th>countryOfOrigin</th> 
					</tr>
				</thead>
				<tbody id="tbody">
					<c:if test="${productsForm.list != null }">
						<c:forEach items="${productsForm.list}" var="prod" varStatus="status">
							<tr>
								<td width="8%"><b>${prod.itemSku}</b></td>
								<td width="1%">
									<c:if test="${ prod.getLocalImagePath() !=null }">
										<img src="/wms-web/img${prod.getLocalImagePath()}"  height="35"  onclick='window.open("/wms-web/img${prod.getLocalImagePath()}")'> 
									</c:if>
								</td>
								<td width="10%">
									<input id="list${status.index}.metalType" name='list[${status.index}].metalType' type="text"  style="width:100%" type='text' value="${prod.metalType}" />
								</td>
								<td width="10%">
									<input id="list${status.index}.metalStamp" name='list[${status.index}].metalStamp' type="text"  style="width:100%" type='text' value="${prod.metalStamp}" />
								</td>
								<td width="10%">
									<input id="list${status.index}.gemType1" name='list[${status.index}].gemType1' type="text"  style="width:100%" type='text' value="${prod.gemType1}" />
								</td>
								<td width="10%">
									<input id="list${status.index}.gemType2" name='list[${status.index}].gemType2' type="text"  style="width:100%" type='text' value="${prod.gemType2}" />
								</td>
								<td width="10%">
									<input id="list${status.index}.gemType3" name='list[${status.index}].gemType3' type="text"  style="width:100%" type='text' value="${prod.gemType3}" />
								</td>
								<td width="10%">
									<input id="list${status.index}.countryOfOrigin" name='list[${status.index}].countryOfOrigin' type="text"  style="width:100%" type='text' value="${prod.countryOfOrigin}" />
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<div align="left">
				<input id="preOrNext" name="preOrNext" type="hidden"/>
				<input type="button" id="btnAdd" class="btn btn-primary" value="前一页" onclick="submitForm('pre')"/>
				<input type="button" id="btnAdd" class="btn btn-primary" value="下一页"  onclick="submitForm('next')" />
			</div>
		</form:form>
	</section>
</body>
</html>