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
<script src='<c:url value="/resource/js/jquery.min.js"/>'>
</script>
<script src='<c:url value="/resource/js/bootstrap.min.js"/>'>
</script>
<script type="text/javascript">
function exportExcel(){
	$('#exportFolder').val($('#exportFolderInput').val());
	$('#exportProductsForm').submit();
}

function selectAll(){
	$('[name=itemSkuList]').each(function(){
		if($(this).is(':checked')){     
			this.checked=false;
		}else{     
			this.checked=true;
		}  
	});
}
function submitForm(num){
	var currentPage = $('#currentPage').val();
	if(0==num){
		$('#currentPage').val(1);
	}else{
		$('#currentPage').val(Number(currentPage)+Number(num));
	}
	$('#prodQueryForm').submit();
}

function cleanForm(){
	$('#itemSKU').val(null);
	$('#itemName').val(null);
	$('#itemType').val(null);
}
</script>
<title>产品列表</title>
</head>
<body>

	<div class="container-fluid">
	   <div class="row">
	      		<div class="col-md-4" align="left">
		      		<input type="checkbox" onchange="selectAll()"/>全选 &nbsp;&nbsp;
					导出到：<input id="exportFolderInput" name='exportFolderInput' type="text" type='text' value="C:/Users/owen/Desktop/tmp" size="40"/>
					<input type="button" id="btnAdd" class="btn btn-primary" value="导出Excel" onclick="exportExcel()"/>
				</div>
		      	<div class="col-md-8" align="right">
		      		<form:form modelAttribute="prodQueryForm" enctype="multipart/form-data" action="/wms-web/prod/queryProd">
		      			<form:select path="itemType" onchange="submitForm(0)">
		      				<form:option value="">全部分类</form:option>
		      				<form:option value="pendant-necklaces">项链/吊坠</form:option>
		      				<form:option value="rings">戒指</form:option>
		      				<form:option value="link-bracelets">手链-1</form:option>
		      				<form:option value="cuff-bracelets">手链-2</form:option>
		      			</form:select>
				      	SKU:<form:input path="itemSKU" size="15"/>
				      	标题:<form:input path="itemName" size="15"/>
				      	父子关系
				      	<form:select path="parentChild" onchange="submitForm(0)">
				      		<form:option value="parent">只显示父类</form:option>
				      		<form:option value="">显示所有</form:option>
				      	</form:select>
				      	<input type="button" value="清空条件" class="btn btn-primary" onclick="cleanForm()"/>
						<input type="submit" id="btnAdd" class="btn btn-primary" value="查询" onclick="submitForm(0)"/>
						每页显示：<form:input path="pageSize" size="2"/>
						总页数 <b>${page.totalPage }</b>： 总条数<b>${page.totalCount }</b>
			      		<input type="button" value="上一页" class="btn btn-primary" <c:if test='${!page.hasPrePage }'>disabled="disabled"</c:if> onclick="submitForm(-1)"/>
						当前页：<form:input path="currentPage" size="2"/>
						<input type="button" value="下一页" class="btn btn-primary" <c:if test='${!page.hasNextPage }'>disabled="disabled"</c:if> onclick="submitForm(1)"/>
					</form:form>
		      	</div>
	   </div>
	</div>
	
	<section class="container-fluid ">
		<table class="table table-hover" >
			<thead>
				<tr>
					<th width="3%">序号</th>
					<th width="9%">SKU</th>
					<th width="5%">Pic</th>
					<th width="9%">Edit</th>
					<th width="9%">parentSku</th>
					<th width="80%">title</th>
				</tr>
			</thead>
			<form id="exportProductsForm" action="/wms-web/prod/exportExcel" method="post">
				<input id="exportFolder" name='exportFolder' type="hidden"/>
				<tbody align="left">
					<c:forEach items="${page.list}" var="order" varStatus="status">
						<tr>
							<td>${ status.index + 1}</td>  
							<td width="9%"><input name="itemSkuList" id="itemSkuList" type="checkbox" value="${order.itemSku}"/>${order.itemSku}</td>
							<td width="5%"> 
								<c:if test="${ order.getLocalImagePath() !=null }">
									<img src="/wms-web/img${order.getLocalImagePath()}"  height="40" onclick='window.open("/wms-web/img${order.getLocalImagePath()}")'/> 
								</c:if>
							</td>
							<td width="9%"><a href='<c:url value="/prod/edit/${order.itemSku}" />' class="btn">编辑产品</a></td>
							<td width="9%">${order.parentSku}</td>
							<td width="80%">${order.itemName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</form>
		</table>
		<div align="right">
			<input type="button" value="上一页" class="btn btn-primary" <c:if test='${!page.hasPrePage }'>disabled="disabled"</c:if> onclick="submitForm(-1)"/>
			<input type="button" value="下一页" class="btn btn-primary" <c:if test='${!page.hasNextPage }'>disabled="disabled"</c:if> onclick="submitForm(1)"/>
		</div>
	</section>
</body>
</html>