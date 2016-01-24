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
function loadKeywords(){
	var result = confirm('确定要加载关键字？');  
	if(result){  
		$("#productsForm").attr("action", "/wms-web/prod/loadKeywords");
		$('#productsForm').submit();
	}  
}
function submitForm(preOrNext){
	$('#preOrNext').val(preOrNext);
	$('#productsForm').submit();
}
</script>
<title>AddPordgenericKeywords</title>
</head>
<body>
	<div>
		<ul class="nav nav-tabs">
		  <li ><a href="#" onclick="submitFormAndGoTo('addTitle')">产品基本信息 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPicture')">产品图片 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addBulletPoint')">产品特性描述 </a></li>
		  <li class="active"><a href="#">搜索关键字 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPrice')">价格和库存 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addOtherinfo')">其他信息  </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPurchaseUrl')">进货信息  </a></li>
		</ul>
	</div>
	
	<section class="container-fluid">
		<form:form modelAttribute="productsForm" enctype="multipart/form-data" action='/wms-web/prod/saveTab'>
			<input id="tabName" name="tabName" type="hidden" value ="addKeyword"/>
			<table id="myTable" class="table table-striped">
				<caption>
					<input type="button" id="btnAdd" class="btn btn-primary" value="保存"  onclick="submitFormAndGoTo()" />
					<input type="checkbox" id="synchronizeBox" >同步更新后续子产品</input>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" id="btnAdd" class="btn btn-primary" value="加载关键字"  onclick="loadKeywords()" />
					<c:if test="${keywordsExcelFilePath ==null}">
						<input type="text" name="keywordsExcelFilePath" size="100" value="C:\Users\owen\Desktop\keyword\"></input>
					</c:if>
					<c:if test="${keywordsExcelFilePath !=null}">
						<input type="text" name="keywordsExcelFilePath" size="80" value="${keywordsExcelFilePath}"></input>
					</c:if>
					&nbsp;&nbsp;&nbsp;
					起始行数
					<c:if test="${keywordsExcelStartIndex ==null}">
						<input type="text" name="keywordsExcelStartIndex" size="10" value="1"></input>
					</c:if>
					<c:if test="${keywordsExcelFilePath !=null}">
						<input type="text" name="keywordsExcelStartIndex" size="10" value="${keywordsExcelStartIndex}"></input>
					</c:if>
					<c:if test="${errorMsg !=null}">
						<span class="label label-danger">${errorMsg}</span>
					</c:if>
					
				</caption>
				<thead>
					<tr>
						<th>SKU</th> 
						<th>缩略图</th> 
						<th>关键字</th>
					</tr>
				</thead>
				<tbody id="tbody" align="left">
					<c:if test="${productsForm.list != null }">
						<c:forEach items="${productsForm.list}" var="prod" varStatus="status">
							<tr>
								<input type="hidden" id="list${status.index}.itemSku" name='list[${status.index}].itemSku'  value="${prod.itemSku}"/>
								<input type="hidden" id="list${status.index}.mainImageUrl" name='list[${status.index}].mainImageUrl'  value="${prod.mainImageUrl}"/>
								<td width="10%"><b>${prod.itemSku}</b></td>
								<td width="8%">
									<c:if test="${ prod.getLocalImagePath() !=null }">
										<img src="/wms-web/img${prod.getLocalImagePath()}"  height="100"  onclick='window.open("/wms-web/img${prod.getLocalImagePath()}")'> 
									</c:if>
								</td>
								<td width="80%">
									<input id="list${status.index}.genericKeywords1" name='list[${status.index}].genericKeywords1' type="text"  style="width:100%" type='text' value="${prod.genericKeywords1}" />
									<input id="list${status.index}.genericKeywords2" name='list[${status.index}].genericKeywords2' type="text"  style="width:100%" type='text' value="${prod.genericKeywords2}" />
									<input id="list${status.index}.genericKeywords3" name='list[${status.index}].genericKeywords3' type="text"  style="width:100%" type='text' value="${prod.genericKeywords3}" />
									<input id="list${status.index}.genericKeywords4" name='list[${status.index}].genericKeywords4' type="text"  style="width:100%" type='text' value="${prod.genericKeywords4}" />
									<input id="list${status.index}.genericKeywords5" name='list[${status.index}].genericKeywords5' type="text"  style="width:100%" type='text' value="${prod.genericKeywords5}" />
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