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
<title>Summarize</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container-fluid">
				<a href='<c:url value="/" />' class="btn btn-success pull-right">首页</a>
				<h1>产品</h1>
				<p>产品预览</p>
			</div>
		</div>
	</section>
	
	<section class="container-fluid">
		<form:form modelAttribute="productsForm" enctype="multipart/form-data" action='/wms-web/prod/addTitle'>
		<c:if test="${productsForm.list != null }">
			<c:forEach items="${productsForm.list}" var="prod" varStatus="status">
			<table id="myTable" class="table table-striped">
			
				<caption>
					产品预览
				</caption>
				
				<tbody id="tbody">
					<c:if test="${productsForm.list != null }">
						<c:forEach items="${productsForm.list}" var="prod" varStatus="status">
							<!-- 基本信息 -->
							<tr>
								<td width="1%">SKU</td>
								<td width="15%"><input class="itemSkuClass" id="list${status.index}.itemSku" name='list[${status.index}].itemSku' type="text"  style="width:100%" type='text' value="${prod.itemSku}"/></td>
								<td width="1%">UPC</td>
								<td width="8%"><input id="list${status.index}.externalProductId"  name='list[${status.index}].externalProductId'  type="text"  style="width:100%" type='text' value="${prod.externalProductId}"/></td>
								<td width="3%">子类分类依据</td> 
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
								<td width="3.5%">颜色</td>
								<td width="8%"><input class="colorNameClass" id="list${status.index}.colorName"  name='list[${status.index}].colorName'  type="text"  style="width:100%" type='text' value="${prod.colorName}"/></td>
								<td width="5%">标题</td>
								<td width="80%">
									<input class="titleClass" id="list${status.index}.itemName" name='list[${status.index}].itemName' type="text"  style="width:100%" type='text' value='${prod.itemName}' onchange="checkTitleLength(this)" onkeypress="checkTitleLength(this)"/>
									<span  id="list${status.index}_warnLable" class="label label-danger"></span>
								</td>
							</tr>
						
							<tr>
								<td width="5%">itemSku</td>
								<td width="10%"><input id="list${status.index}.itemSku" name='list[${status.index}].itemSku' type="text"  style="width:100%" type='text' value="${prod.itemSku}"/></td>
								<td width="5%">产品图片</td>
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
				
				
				<tbody id="tbody">
							<!-- 基本信息 -->
							<tr>
								<td width="1%">SKU</td>
								<td width="15%"><input class="itemSkuClass" id="list${status.index}.itemSku" name='list[${status.index}].itemSku' type="text"  style="width:100%" type='text' value="${prod.itemSku}"/></td>
								<td width="1%">UPC</td>
								<td width="8%"><input id="list${status.index}.externalProductId"  name='list[${status.index}].externalProductId'  type="text"  style="width:100%" type='text' value="${prod.externalProductId}"/></td>
								<td width="3%">子类分类依据</td>
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
								<td width="3.5%">颜色</td>
								<td width="8%"><input class="colorNameClass" id="list${status.index}.colorName"  name='list[${status.index}].colorName'  type="text"  style="width:100%" type='text' value="${prod.colorName}"/></td>
								<td width="5%">标题</td>
								<td width="80%">
									<input class="titleClass" id="list${status.index}.itemName" name='list[${status.index}].itemName' type="text"  style="width:100%" type='text' value='${prod.itemName}' onchange="checkTitleLength(this)" onkeypress="checkTitleLength(this)"/>
									<span  id="list${status.index}_warnLable" class="label label-danger"></span>
								</td>
							</tr>
						</table>
						<table class="table table-striped">	
							<!-- 特性描述 -->
							<tr>
								<td width="5%">itemSku</td>
								<td width="10%"><input id="list${status.index}.itemSku" name='list[${status.index}].itemSku' type="text"  style="width:100%" type='text' value="${prod.itemSku}"/></td>
								<td width="5%">特性描述</td>
								<td width="80%">
									<input id="list${status.index}.bulletPoint1" name='list[${status.index}].bulletPoint1' type="text"  style="width:100%" type='text' value="${prod.bulletPoint1}" />
									<input id="list${status.index}.bulletPoint2" name='list[${status.index}].bulletPoint2' type="text"  style="width:100%" type='text' value="${prod.bulletPoint2}" />
									<input id="list${status.index}.bulletPoint3" name='list[${status.index}].bulletPoint3' type="text"  style="width:100%" type='text' value="${prod.bulletPoint3}" />
									<input id="list${status.index}.bulletPoint4" name='list[${status.index}].bulletPoint4' type="text"  style="width:100%" type='text' value="${prod.bulletPoint4}" />
									<input id="list${status.index}.bulletPoint5" name='list[${status.index}].bulletPoint5' type="text"  style="width:100%" type='text' value="${prod.bulletPoint5}" />
								</td>
							</tr>
				</tbody>
						</table>
						<table class="table table-striped">	
							
							<!-- 图片 -->
							<tr>
								<td width="5%">itemSku</td>
								<td width="10%"><input id="list${status.index}.itemSku" name='list[${status.index}].itemSku' type="text"  style="width:100%" type='text' value="${prod.itemSku}"/></td>
								<td width="5%">产品图片</td>
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
						</table>
						<table class="table table-striped">	
							
							<!-- 关键字 -->
							<tr>
								<td width="5%">itemSku</td>
								<td width="10%"><input id="list${status.index}.itemSku" name='list[${status.index}].itemSku' type="text"  style="width:100%" type='text' value="${prod.itemSku}"/></td>
								<td width="5%">关键字</td>
								<td width="80%">
									<input id="list${status.index}.genericKeywords1" name='list[${status.index}].genericKeywords1' type="text"  style="width:100%" type='text' value="${prod.genericKeywords1}" />
									<input id="list${status.index}.genericKeywords2" name='list[${status.index}].genericKeywords2' type="text"  style="width:100%" type='text' value="${prod.genericKeywords2}" />
									<input id="list${status.index}.genericKeywords3" name='list[${status.index}].genericKeywords3' type="text"  style="width:100%" type='text' value="${prod.genericKeywords3}" />
									<input id="list${status.index}.genericKeywords4" name='list[${status.index}].genericKeywords4' type="text"  style="width:100%" type='text' value="${prod.genericKeywords4}" />
									<input id="list${status.index}.genericKeywords5" name='list[${status.index}].genericKeywords5' type="text"  style="width:100%" type='text' value="${prod.genericKeywords5}" />
								</td>
							</tr>
						</table>
						<table class="table table-striped">	
							
							<!--价格信息  -->
							<tr>
								<td width="5%">itemSku</td>
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
						</table>
						<table class="table table-striped">	
							
							<!-- 其他信息 -->
							<tr>
								<td width="5%">itemSku</td>
								<td width="10%"><input id="list${status.index}.itemSku" name='list[${status.index}].itemSku' type="text"  style="width:100%" type='text' value="${prod.itemSku}"/></td>
								<td width="5%">metalType</td>
								<td width="10%">
									<input id="list${status.index}.metalType" name='list[${status.index}].metalType' type="text"  style="width:100%" type='text' value="${prod.metalType}" />
								</td>
								<td width="5%">metalStamp</td>
								<td width="10%">
									<input id="list${status.index}.metalStamp" name='list[${status.index}].metalStamp' type="text"  style="width:100%" type='text' value="${prod.metalStamp}" />
								</td>
								<td width="5%">gemType1</td>
								<td width="10%">
									<input id="list${status.index}.gemType1" name='list[${status.index}].gemType1' type="text"  style="width:100%" type='text' value="${prod.gemType1}" />
								</td>
								<td width="5%">gemType2</td>
								<td width="10%">
									<input id="list${status.index}.gemType2" name='list[${status.index}].gemType2' type="text"  style="width:100%" type='text' value="${prod.gemType2}" />
								</td>
								<td width="5%">gemType3</td>
								<td width="10%">
									<input id="list${status.index}.gemType3" name='list[${status.index}].gemType3' type="text"  style="width:100%" type='text' value="${prod.gemType3}" />
								</td>
								<td width="5%">countryOfOrigin</td>
								<td width="10%">
									<input id="list${status.index}.countryOfOrigin" name='list[${status.index}].countryOfOrigin' type="text"  style="width:100%" type='text' value="${prod.countryOfOrigin}" />
								</td>
							</tr>
						</table>
						<table class="table table-striped">	
			</c:forEach>
		</c:if>
		</form:form>
	</section>
</body>
</html>