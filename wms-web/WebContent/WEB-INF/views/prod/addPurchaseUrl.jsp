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

function submitForm(action){
	if('exportExcel' == action ){
		$("#productsForm").attr("action", "export2Excel");
	}else{
		$('#preOrNext').val(action);
	}
	$('#productsForm').submit();
}

function oppenLink(button,suffix){
	var id = button.id;//urlButtonX
	var index = id.substr(9,1);
	var imgID='list'+index+'.'+suffix;//listX.purchaseUrlX
	var url = $( document.getElementById(imgID) ).val();
	if(url != null && url.length > 0){
		window.open(url)
	}
}
</script>
<title>AddPurchaseUrl</title>
</head>
<body>
	<div>
		<ul class="nav nav-tabs">
		  <li ><a href="#" onclick="submitFormAndGoTo('addTitle')">产品基本信息 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPicture')">产品图片 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addBulletPoint')">产品特性描述 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addKeyword')">搜索关键字 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPrice')">价格和库存 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addOtherinfo')">其他信息  </a></li>
		  <li class="active"><a href="#">进货信息  </a></li>
		</ul>
	</div>
	
	<section class="container-fluid">
		<form:form modelAttribute="productsForm" enctype="multipart/form-data" action='/wms-web/prod/saveTab'>
			<input id="tabName" name="tabName" type="hidden" value ="addPurchaseUrl"/>
			<table id="myTable" class="table table-striped">
				<caption>
					<input type="button" id="btnAdd" class="btn btn-primary" value="保存"  onclick="submitFormAndGoTo()" />
					<input type="button" id="btnAdd" class="btn btn-primary" value="导出Exce"  onclick="submitForm('exportExcel')" />
					<input type="checkbox" id="synchronizeBox" >同步更新后续子产品</input>
				</caption>
				<thead>
					<tr>
						<th>SKU</th> 
						<th>缩略图</th> 
						<th>进货渠道</th>
					</tr>
				</thead>
				<tbody id="tbody" align="left">
					<c:if test="${productsForm.list != null }">
						<c:forEach items="${productsForm.list}" var="prod" varStatus="status">
							<tr>
								<input type="hidden" id="list${status.index}.itemSku" name='list[${status.index}].itemSku'  value="${prod.itemSku}"/>
								<td width="6%"><b>${prod.itemSku}</b></td>
								<td width="5%">
									<c:if test="${ prod.getLocalImagePath() !=null }">
										<img src="/wms-web/img${prod.getLocalImagePath()}"  height="100"  onclick='window.open("/wms-web/img${prod.getLocalImagePath()}")'> 
									</c:if>
								</td>
								<td width="51%">
									<nobr>
										<input id="urlButton${status.index}" type="button" value="打开链接" onclick="oppenLink(this,'purchaseUrl1')"/>
										<input id="list${status.index}.purchaseUrl1" name='list[${status.index}].purchaseUrl1' type="text"  style="width:100%" type='text' value="${prod.purchaseUrl1}" />
									</nobr>
									<nobr>
										<input id="urlButton${status.index}" type="button" value="打开链接" onclick="oppenLink(this,'purchaseUrl2')"/>
										<input id="list${status.index}.purchaseUrl2" name='list[${status.index}].purchaseUrl2' type="text"  style="width:100%" type='text' value="${prod.purchaseUrl2}" />
									</nobr>
									<nobr>
										<input id="urlButton${status.index}" type="button" value="打开链接" onclick="oppenLink(this,'purchaseUrl3')"/>
										<input id="list${status.index}.purchaseUrl3" name='list[${status.index}].purchaseUrl3' type="text"  style="width:100%" type='text' value="${prod.purchaseUrl3}" />
									</nobr>
									<nobr>
										<input id="urlButton${status.index}" type="button" value="打开链接" onclick="oppenLink(this,'purchaseUrl4')"/>
										<input id="list${status.index}.purchaseUrl4" name='list[${status.index}].purchaseUrl4' type="text"  style="width:100%" type='text' value="${prod.purchaseUrl4}" />
									</nobr>
									<%-- <input id="list${status.index}.purchaseUrl5" name='list[${status.index}].purchaseUrl5' type="text"  style="width:100%" type='text' value="${prod.purchaseUrl5}" /> --%>
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