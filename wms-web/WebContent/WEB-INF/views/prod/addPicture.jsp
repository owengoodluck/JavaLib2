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

function oppenPicLink(picButton,surfix){
	var id = picButton.id;//pictureButton1
	var imgID='list'+id.substr(13,1)+'.'+surfix;
	var url = $( document.getElementById(imgID) ).val();
	if(url != null && url.length > 0){
		window.open(url)
	}
}
function submitForm(preOrNext){
	$('#preOrNext').val(preOrNext);
	$('#productsForm').submit();
}
</script>
<title>AddPordotherImageUrl</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container-fluid">
				<a href='<c:url value="/" />' class="btn btn-success pull-right">首页</a>
				<h1>产品</h1>
				<p>添加产品图片地址</p>
			</div>
		</div>
	</section>
	
	<section class="container-fluid">
		<form:form modelAttribute="productsForm" enctype="multipart/form-data" action="addPicture">
			<table id="myTable" class="table table-striped">
				<caption>产品图片信息 
					<input type="button" id="btnAdd" class="btn btn-primary" value="前一页" onclick="submitForm('pre')"/>
					<input type="button" id="btnAdd" class="btn btn-primary" value="下一页"  onclick="submitForm('next')" />
					<input type="checkbox" id="synchronizeBox" >同步更新后续子产品</input>
				</caption>
				<tbody id="tbody">
					<c:if test="${productsForm.list != null }">
						<c:forEach items="${productsForm.list}" var="prod" varStatus="status">
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