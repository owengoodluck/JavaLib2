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
		  <li ><a href="#" onclick="submitFormAndGoTo('addTitle')">产品基本信息 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPicture')">产品图片 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addBulletPoint')">产品特性描述 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addKeyword')">搜索关键字 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPrice')">价格和库存 </a></li>
		  <li class="active">其他信息  </li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPurchaseUrl')">进货信息  </a></li>
		</ol>
	</div>
	
	<section class="container-fluid">
		<form:form modelAttribute="productsForm" enctype="multipart/form-data" action='/wms-web/prod/saveTab'>
			<input id="tabName" name="tabName" type="hidden" value ="addOtherinfo"/>
			<table id="myTable" class="table table-striped">
				<caption>
					<input type="button" id="btnAdd" class="btn btn-primary" value="保存"  onclick="submitFormAndGoTo()" />
					<input type="checkbox" id="synchronizeBox" >同步更新后续子产品</input>
				</caption>
				<thead>
					<tr>
						<th>SKU</th> 
						<th>缩略图</th> 
						<th>manufacturer</th> 
						<th>model</th> 
						<th>feed_product_type</th> 
						<!-- <th>item_type</th> 
						<th>metalType</th>  -->
						<th>metalStamp</th> 
						<th>gemType1</th> 
						<th>gemType2</th> 
						<th>gemType3</th> 
						<th>countryOfOrigin</th> 
					</tr>
				</thead>
				<tbody id="tbody" align="left">
					<c:if test="${productsForm.list != null }">
						<c:forEach items="${productsForm.list}" var="prod" varStatus="status">
							<tr>
								<input type="hidden" id="list${status.index}.itemSku" name='list[${status.index}].itemSku'  value="${prod.itemSku}"/>
								<td width="7%"><b>${prod.itemSku}</b></td>
								<td width="4%">
									<c:if test="${ prod.getLocalImagePath() !=null }">
										<img src="/wms-web/img${prod.getLocalImagePath()}"  height="35"  onclick='window.open("/wms-web/img${prod.getLocalImagePath()}")'> 
									</c:if>
								</td>
								<td width="7%">
									<input id="list${status.index}.manufacturer" name='list[${status.index}].manufacturer' type="text"  style="width:100%" type='text' value="${prod.manufacturer}" />
								</td>
								<td width="7%">
									<input id="list${status.index}.model" name='list[${status.index}].model' type="text"  style="width:100%" type='text' value="${prod.model}" />
								</td>
								<td width="7%">
									<input id="list${status.index}.metalType" name='list[${status.index}].metalType' type="text"  style="width:100%" type='text' value="${prod.metalType}" />
								</td>
								<td width="7%">
									<input id="list${status.index}.metalStamp" name='list[${status.index}].metalStamp' type="text"  style="width:100%" type='text' value="${prod.metalStamp}" />
								</td>
								<td width="7%">
									<input id="list${status.index}.gemType1" name='list[${status.index}].gemType1' type="text"  style="width:100%" type='text' value="${prod.gemType1}" />
								</td>
								<td width="7%">
									<input id="list${status.index}.gemType2" name='list[${status.index}].gemType2' type="text"  style="width:100%" type='text' value="${prod.gemType2}" />
								</td>
								<td width="7%">
									<input id="list${status.index}.gemType3" name='list[${status.index}].gemType3' type="text"  style="width:100%" type='text' value="${prod.gemType3}" />
								</td>
								<td width="7%">
									<input id="list${status.index}.countryOfOrigin" name='list[${status.index}].countryOfOrigin' type="text"  style="width:100%" type='text' value="${prod.countryOfOrigin}" />
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</form:form>
	</section>
</body>
</html>