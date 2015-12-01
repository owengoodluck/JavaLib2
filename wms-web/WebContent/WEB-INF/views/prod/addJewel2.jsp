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
<title>AddJewelry</title>
</head>
<body>
	<section>
		<div class="container-fluid">
			<div class="container-fluid">
				<a href='<c:url value="/" />' class="btn btn-success pull-right">首页</a>
				<h1>产品</h1>
				<p>添加产品</p>
			</div>
		</div>
	</section>
	
	<section class="container-fluid">
		<table class="table table-striped">
			<caption>产品详细字段</caption>
			<tbody>
					<tr>
						<td width="9%">itemName(Title)</td>
						<td colspan="3"><form:input path="jewel.itemName" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">productDescription</td>
						<td colspan="3"><form:input path="jewel.productDescription" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">itemSku</td>
						<td><form:input path="jewel.itemSku" type="text" style="width:100%"/></td>
						<td width="9%">model</td>
						<td><form:input path="jewel.model" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">manufacturer</td>
						<td><form:input path="jewel.manufacturer" type="text" style="width:100%"/></td>
						<td width="9%">brandName</td>
						<td><form:input path="jewel.brandName" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">feedProductType</td>
						<td><form:input path="jewel.feedProductType" type="text" style="width:100%"/></td>
						<td width="9%">itemType</td>
						<td><form:input path="jewel.itemType" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">departmentName</td>
						<td><form:input path="jewel.departmentName" type="text" style="width:100%"/></td>
						<td width="9%">displayDimensionsUnitOfMeasure</td>
						<td><form:input path="jewel.displayDimensionsUnitOfMeasure" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">externalProductIdType</td>
						<td><form:input path="jewel.externalProductIdType" type="text" style="width:100%"/></td>
						<td width="9%">externalProductId</td>
						<td><form:input path="jewel.externalProductId" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">listPrice</td>
						<td><form:input path="jewel.listPrice" type="text" style="width:100%"/></td>
						<td width="9%">currency</td>
						<td><form:input path="jewel.currency" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">standardPrice</td>
						<td><form:input path="jewel.standardPrice" type="text" style="width:100%"/></td>
						<td width="9%">quantity</td>
						<td><form:input path="jewel.quantity" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">bulletPoint-1</td>
						<td colspan="3"><form:input path="jewel.bulletPoint1" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">bulletPoint-2</td>
						<td colspan="3"><form:input path="jewel.bulletPoint2" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">bulletPoint-3</td>
						<td colspan="3"><form:input path="jewel.bulletPoint3" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">bulletPoint-4</td>
						<td colspan="3"><form:input path="jewel.bulletPoint4" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">bulletPoint-5</td>
						<td colspan="3"><form:input path="jewel.bulletPoint5" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">genericKeywords-1</td>
						<td colspan="2"><form:input path="jewel.genericKeywords1" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">genericKeywords-2</td>
						<td colspan="2"><form:input path="jewel.genericKeywords2" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">genericKeywords-3</td>
						<td colspan="2"><form:input path="jewel.genericKeywords3" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">genericKeywords-4</td>
						<td colspan="2"><form:input path="jewel.genericKeywords4" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">genericKeywords-5</td>
						<td colspan="2"><form:input path="jewel.genericKeywords5" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">mainImageUrl</td>
						<td colspan="3"><form:input path="jewel.mainImageUrl" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">otherImageUrl-1</td>
						<td colspan="3"><form:input path="jewel.otherImageUrl1" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">otherImageUrl-2</td>
						<td colspan="3"><form:input path="jewel.otherImageUrl2" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">otherImageUrl-3</td>
						<td colspan="3"><form:input path="jewel.otherImageUrl3" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">otherImageUrl-4</td>
						<td colspan="3"><form:input path="jewel.otherImageUrl4" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">otherImageUrl-5</td>
						<td colspan="3"><form:input path="jewel.otherImageUrl5" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">otherImageUrl-6</td>
						<td colspan="3"><form:input path="jewel.otherImageUrl6" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">otherImageUrl-7</td>
						<td colspan="3"><form:input path="jewel.otherImageUrl7" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">otherImageUrl-8</td>
						<td colspan="3"><form:input path="jewel.otherImageUrl8" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">metalType</td>
						<td><form:input path="jewel.metalType" type="text" style="width:100%"/></td>
						<td width="9%">metalStamp</td>
						<td><form:input path="jewel.metalStamp" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">gemType1</td>
						<td><form:input path="jewel.gemType1" type="text" style="width:100%"/></td>
						<td width="9%">gemType2</td>
						<td><form:input path="jewel.gemType2" type="text" style="width:100%"/></td>
					</tr>
					<tr>
						<td width="9%">gemType3</td>
						<td><form:input path="jewel.gemType3" type="text" style="width:100%"/></td>
						<td width="9%">countryOfOrigin</td>
						<td><form:input path="jewel.countryOfOrigin" type="text" style="width:100%"/></td>
					</tr>
			</tbody>
		</table>
	</section>
</body>
</html>