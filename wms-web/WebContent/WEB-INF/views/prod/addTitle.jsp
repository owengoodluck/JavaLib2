<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!Doctype html>
<html>
<head>
<link rel="stylesheet"
	href='<c:url value="/resource/css/bootstrap.min.css" />'
	type="text/css"
/>
<script src='<c:url value="/resource/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resource/js/bootstrap.min.js"/>'></script>
<script src='<c:url value="/resource/js/owen.js"/>'></script>

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
			var result = confirm('确定要删除？');  
			if(result){  
				$("#tbody tr:last").remove();
			}  
		}
	});
	
	$("input.titleClass").each(function(){
		checkTitleLength(this);
	});
	
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
	$("select").each(function(){
		$(this).change(function(){
			copyValue(this);
		});
	});
});

//update str to replace list0 or list[0] to  listX or list[X]
function update(str){
	var index = $("#myTable tr").length-2;
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
	var text =   currentInput.value;
	
	var prefix = getPrefix(thisID)+getIndex(thisID);
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

function submitForm(preOrNext){
	if(nullCheck('.itemSkuClass') ){
		return;
	}
	if(nullCheck('.notNullClass') ){
		return;
	}
	
	if(uniqueValueCheck('.itemSkuClass') ){
		if(uniqueValueCheck('.colorNameClass') ){
			$('#productsForm').submit();
		}else{
			alert('colorNameClass不可重复 ！');
		}
	}else {
		alert('SKU不可重复 ！');
	}
}
</script>
<title>AddPordTitle</title>
</head>
<body>
	<div>
		<ol class="breadcrumb" align="left">
		  <li class="active">产品基本信息 </li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPicture')">产品图片 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addBulletPoint')">产品特性描述 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addKeyword')">搜索关键字 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPrice')">价格和库存 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addOtherinfo')">其他信息  </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPurchaseUrl')">进货信息 </a> </li>
		</ol>
	</div>
	
	<section class="container-fluid">
		<form:form modelAttribute="productsForm" enctype="multipart/form-data" action='/wms-web/prod/saveTab'>
			<input id="tabName" name="tabName" type="hidden" value ="addTitle"/>
			<table id="myTable" class="table table-striped table-bordered table-hover">
				<caption>
					<input type="button" id="btnAdd" class="btn btn-primary" value="保存"  onclick="submitFormAndGoTo()" />
					<button id="addChildBtn"    type="button" class="btn btn-primary">添加子产品</button>
					<button id="deleteChildBtn" type="button" class="btn btn-primary">删除最后一个子产品</button>
					<input type="checkbox" id="synchronizeBox" >同步更新后续子产品</input>
				</caption>
				<thead>
					<tr>
						<th >缩略图</th>
						<th >SKU</th>
						<th >父SKU</th>
						<th >类别</th>
						<th >对象</th>
						<th >子分类依据</th>
						<th >颜色</th>
						<th>实际库存</th>
						<th>进货价</th>
						<th>标题</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:if test="${productsForm.list != null }">
						<c:forEach items="${productsForm.list}" var="prod" varStatus="status">
							<c:if test="${ status.index == 0}">
								<tr id="firstItem">
									<td width="4%">
										<c:if test="${ prod.getLocalImagePath() !=null }">
											<img src="/wms-web/img${prod.getLocalImagePath()}"  height="50" onclick='window.open("/wms-web/img${prod.getLocalImagePath()}")'/> 
										</c:if>
									</td>
									<td width="10%">
										<input class="itemSkuClass" id="list${status.index}.itemSku" name='list[${status.index}].itemSku' type="text"  style="width:100%" type='text' value="${prod.itemSku}"/>
									</td>
									<td width="10%">
										<input id="list${status.index}.parentSku" name='list[${status.index}].parentSku' type="text"  style="width:100%" type='text' value="${prod.parentSku}"/>
									</td>
									<td width="7%">
										<select class="notNullClass" id="list${status.index}.itemType"  name='list[${status.index}].itemType'>
												<option value="" <c:if test="${ prod.itemType == '' || prod.itemType == null}" >selected="true"</c:if> ></option>
												<option value ="pendant-necklaces" <c:if test="${ prod.itemType == 'pendant-necklaces' }" >selected="true"</c:if> >项链/吊坠</option>
												<option value ="link-bracelets" <c:if test="${ prod.itemType == 'link-bracelets' }" >selected="true"</c:if> >手链</option>
												<option value="rings" <c:if test="${ prod.itemType == 'rings' }" >selected="true"</c:if> >戒指</option>
										</select>
									</td>
									<td width="7%">
										<select class="notNullClass" id="list${status.index}.departmentName"  name='list[${status.index}].departmentName'>
												<option value="" <c:if test="${ prod.departmentName == '' || prod.departmentName == null}" >selected="true"</c:if> ></option>
												<option value="womens" <c:if test="${ prod.departmentName == 'womens' }" >selected="true"</c:if> >女士</option>
												<option value="mens" <c:if test="${ prod.departmentName == 'mens' }" >selected="true"</c:if> >男士</option>
												<option value ="girls" <c:if test="${ prod.departmentName == 'girls' }" >selected="true"</c:if> >女孩</option>
												<option value ="boys" <c:if test="${ prod.departmentName == 'boys' }" >selected="true"</c:if> >男孩</option>
												<option value="unisex-adult" <c:if test="${ prod.departmentName == 'unisex-adult' }" >selected="true"</c:if> >成人(男女通用)</option>
												<option value="unisex-child" <c:if test="${ prod.departmentName == 'unisex-child' }" >selected="true"</c:if> >儿童(男女通用)</option>
										</select>
									</td>
									<td width="8%">
										<select id="list${status.index}.variationTheme"  name='list[${status.index}].variationTheme'>
											<option value ="" <c:if test="${ prod.variationTheme == '' || prod.variationTheme == null}" >selected="true"</c:if> ></option>
											<option value ="MetalType" <c:if test="${ prod.variationTheme == 'MetalType' }" >selected="true"</c:if> >MetalType</option>
											<option value="StyleName" <c:if test="${ prod.variationTheme == 'StyleName' }" >selected="true"</c:if> >StyleName</option>
											<option value ="ColorName" <c:if test="${ prod.variationTheme == 'ColorName' }" >selected="true"</c:if> >ColorName</option>
											<option value="RingSize" <c:if test="${ prod.variationTheme == 'RingSize' }" >selected="true"</c:if> >RingSize</option>
											<%-- <option value="MetalType-RingSize" <c:if test="${ prod.variationTheme == 'MetalType-RingSize' }" >selected="true"</c:if> >FashionRing-MetalType-RingSize</option>
											<option value="Length" <c:if test="${ prod.variationTheme == 'Length' }" >selected="true"</c:if> >FashionNecklaceBraceletAnklet-Length</option>
											<option value="Length-MetalType" <c:if test="${ prod.variationTheme == 'Length-MetalType' }" >selected="true"</c:if> >FashionNecklaceBraceletAnklet-Length-MetalType</option> --%>
										</select>
									</td>
									<td width="4%"><input id="list${status.index}.colorName"  name='list[${status.index}].colorName'  type="text"  style="width:100%" type='text' value="${prod.colorName}"/></td>
									<td width="5%">
										<b><input id="list${status.index}.stockQuantity" name='list[${status.index}].stockQuantity' type="text"  style="width:100%" type='text' value="${prod.stockQuantity}" /></b>
									</td>
									<td width="4%">
										<b><input id="list${status.index}.purchasePrice" name='list[${status.index}].purchasePrice' type="text"  style="width:100%" type='text' value="${prod.purchasePrice}" /></b>
									</td>
									<td width="70%">
										<input class="titleClass notNullClass" id="list${status.index}.itemName" name='list[${status.index}].itemName' type="text"  style="width:100%" type='text' value="${fn:replace(prod.itemName,'"','&quot;')}" onchange="checkTitleLength(this)" onkeypress="checkTitleLength(this)"/>
										<span  id="list${status.index}_warnLable" class="label label-danger"></span>
									</td>
								</tr>
							</c:if>
							<c:if test="${ status.index > 0}">
								<tr>
									<td width="4%">
										<c:if test="${ prod.getLocalImagePath() !=null }">
											<img src="/wms-web/img${prod.getLocalImagePath()}"  height="50"  onclick='window.open("/wms-web/img${prod.getLocalImagePath()}")'> 
										</c:if>
									</td>
									<td width="10%">
										<input class="itemSkuClass" id="list${status.index}.itemSku" name='list[${status.index}].itemSku' type="text"  style="width:100%" type='text' value="${prod.itemSku}"/>
									</td>
									<td width="10%">
										<input id="list${status.index}.parentSku" name='list[${status.index}].parentSku' type="text"  style="width:100%" type='text' value="${prod.parentSku}"/>
									</td>
									<td width="7%">
										<select id="list${status.index}.itemType"  name='list[${status.index}].itemType'>
												<option value="" <c:if test="${ prod.itemType == '' || prod.itemType == null}" >selected="true"</c:if> ></option>
												<option value ="pendant-necklaces" <c:if test="${ prod.itemType == 'pendant-necklaces' }" >selected="true"</c:if> >项链/吊坠</option>
												<option value ="link-bracelets" <c:if test="${ prod.itemType == 'link-bracelets' }" >selected="true"</c:if> >手链</option>
												<option value="rings" <c:if test="${ prod.itemType == 'rings' }" >selected="true"</c:if> >戒指</option>
										</select>
									</td>
									<td width="7%">
										<select class="notNullClass" id="list${status.index}.departmentName"  name='list[${status.index}].departmentName'>
												<option value="" <c:if test="${ prod.departmentName == '' || prod.departmentName == null}" >selected="true"</c:if> ></option>
												<option value="womens" <c:if test="${ prod.departmentName == 'womens' }" >selected="true"</c:if> >女士</option>
												<option value="mens" <c:if test="${ prod.departmentName == 'mens' }" >selected="true"</c:if> >男士</option>
												<option value ="girls" <c:if test="${ prod.departmentName == 'girls' }" >selected="true"</c:if> >女孩</option>
												<option value ="boys" <c:if test="${ prod.departmentName == 'boys' }" >selected="true"</c:if> >男孩</option>
												<option value="unisex-adult" <c:if test="${ prod.departmentName == 'unisex-adult' }" >selected="true"</c:if> >成人(男女通用)</option>
												<option value="unisex-child" <c:if test="${ prod.departmentName == 'unisex-child' }" >selected="true"</c:if> >儿童(男女通用)</option>
										</select>
									</td>
									<td width="8%">
										<select id="list${status.index}.variationTheme"  name='list[${status.index}].variationTheme'>
											<option value ="MetalType" <c:if test="${ prod.variationTheme == 'MetalType' }" >selected="true"</c:if> >MetalType</option>
											<option value="StyleName" <c:if test="${ prod.variationTheme == 'StyleName' }" >selected="true"</c:if> >StyleName</option>
											<option value ="ColorName" <c:if test="${ prod.variationTheme == 'ColorName' }" >selected="true"</c:if> >ColorName</option>
											<option value="RingSize" <c:if test="${ prod.variationTheme == 'RingSize' }" >selected="true"</c:if> >RingSize</option>
											<%-- <option value="MetalType-RingSize" <c:if test="${ prod.variationTheme == 'MetalType-RingSize' }" >selected="true"</c:if> >FashionRing-MetalType-RingSize</option>
											<option value="Length" <c:if test="${ prod.variationTheme == 'Length' }" >selected="true"</c:if> >FashionNecklaceBraceletAnklet-Length</option>
											<option value="Length-MetalType" <c:if test="${ prod.variationTheme == 'Length-MetalType' }" >selected="true"</c:if> >FashionNecklaceBraceletAnklet-Length-MetalType</option> --%>
										</select>
									</td>
									<td width="4%"><input class="colorNameClass" id="list${status.index}.colorName"  name='list[${status.index}].colorName'  type="text"  style="width:100%" type='text' value="${prod.colorName}"/></td>
									
									<td width="5%">
										<b><input id="list${status.index}.stockQuantity" name='list[${status.index}].stockQuantity' type="text"  style="width:100%" type='text' value="${prod.stockQuantity}" /></b>
									</td>
									<td width="4%">
										<b><input id="list${status.index}.purchasePrice" name='list[${status.index}].purchasePrice' type="text"  style="width:100%" type='text' value="${prod.purchasePrice}" /></b>
									</td>
									<td width="70%">
										<input class="titleClass" id="list${status.index}.itemName" name='list[${status.index}].itemName' type="text"  style="width:100%" type='text' value="${fn:replace(prod.itemName,'"','&quot;')}" onchange="checkTitleLength(this)" onkeypress="checkTitleLength(this)"/>
										<span  id="list${status.index}_warnLable" class="label label-danger"></span>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
				</tbody>
				<tr>
				</tr>
			</table>
		</form:form>
	</section>
</body>
</html>