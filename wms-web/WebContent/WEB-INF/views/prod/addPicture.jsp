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
function oppenPicLink(picButton,surfix){
	var id = picButton.id;//pictureButton1
	var imgID='list'+id.substr(13,1)+'.'+surfix;
	var url = $( document.getElementById(imgID) ).val();
	if(url != null && url.length > 0){
		window.open(url)
	}
}
</script>
<title>AddPordotherImageUrl</title>
</head>
<body>
	<div>
		<ol class="breadcrumb" align="left">
		  <li ><a href="#" onclick="submitFormAndGoTo('addTitle')">产品基本信息 </a></li>
		  <li class="active">产品图片 </li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addBulletPoint')">产品特性描述 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addKeyword')">搜索关键字 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPrice')">价格和库存 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addOtherinfo')">其他信息  </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPurchaseUrl')">进货信息  </a></li>
		</ol>
	</div>
	
	<section class="container-fluid">
		<form:form modelAttribute="productsForm" enctype="multipart/form-data" action='/wms-web/prod/saveTab'>
			<input id="tabName" name="tabName" type="hidden" value ="addPrice"/>
			<table id="myTable" class="table table-striped">
				<caption>
					<input type="button" id="btnAdd" class="btn btn-primary" value="保存"  onclick="submitFormAndGoTo()" />
					<input type="checkbox" id="synchronizeBox" >同步更新后续子产品</input>
				</caption>
				<thead>
					<tr>
						<th>SKU</th> 
						<th>缩略图</th> 
						<th>产品图片上传地址</th>
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
										<input id="pictureButton${status.index}" type="button" value="打开图片链接" onclick="oppenPicLink(this,'mainImageUrl')"/>
										<input id="list${status.index}.mainImageUrl" name='list[${status.index}].mainImageUrl' type="text"  style="width:100%" type='text' value="${prod.mainImageUrl}" />
									</nobr>
									<nobr>
										<input id="pictureButton${status.index}" type="button" value="打开图片链接" onclick="oppenPicLink(this,'otherImageUrl1')"/>
										<input id="list${status.index}.otherImageUrl1" name='list[${status.index}].otherImageUrl1' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl1}" />
									</nobr>
									<nobr>
										<input id="pictureButton${status.index}" type="button" value="打开图片链接" onclick="oppenPicLink(this,'otherImageUrl2')"/>
										<input id="list${status.index}.otherImageUrl2" name='list[${status.index}].otherImageUrl2' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl2}" />
									</nobr>
									<nobr>
										<input id="pictureButton${status.index}" type="button" value="打开图片链接" onclick="oppenPicLink(this,'otherImageUrl3')"/>
										<input id="list${status.index}.otherImageUrl3" name='list[${status.index}].otherImageUrl3' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl3}" />
									</nobr>
									<nobr>
										<input id="pictureButton${status.index}" type="button" value="打开图片链接" onclick="oppenPicLink(this,'otherImageUrl4')"/>
										<input id="list${status.index}.otherImageUrl4" name='list[${status.index}].otherImageUrl4' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl4}" />
									</nobr>
									<nobr>
										<input id="pictureButton${status.index}" type="button" value="打开图片链接" onclick="oppenPicLink(this,'otherImageUrl5')"/>
										<input id="list${status.index}.otherImageUrl5" name='list[${status.index}].otherImageUrl5' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl5}" />
									</nobr>
									<%-- <nobr>
										<input id="pictureButton${status.index}" type="button" value="打开图片链接" onclick="oppenPicLink(this,'otherImageUrl6')"/>
										<input id="list${status.index}.otherImageUrl6" name='list[${status.index}].otherImageUrl6' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl6}" />
									</nobr>
									<nobr>
										<input id="pictureButton${status.index}" type="button" value="打开图片链接" onclick="oppenPicLink(this,'otherImageUrl7')"/>
										<input id="list${status.index}.otherImageUrl7" name='list[${status.index}].otherImageUrl7' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl7}" />
									</nobr>
									<nobr>
										<input id="pictureButton${status.index}" type="button" value="打开图片链接" onclick="oppenPicLink(this,'otherImageUrl8')"/>
										<input id="list${status.index}.otherImageUrl8" name='list[${status.index}].otherImageUrl8' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl8}" />
									</nobr> --%>
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