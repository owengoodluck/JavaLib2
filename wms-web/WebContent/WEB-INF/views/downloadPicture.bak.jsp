<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
>
<link rel="stylesheet" href="<c:url value="/resource/css/styles.css" />"
	type="text/css"
>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script> -->
<script src="<c:url value="/resource/js/scripts.js" />"
	type="text/javascript"
></script>
<title>Products</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<a href="<spring:url value="/j_spring_security_logout " />"
					class="btn btn-danger btn-mini pull-right"
				>logout</a> <a href='<c:url value="/products" />'
					class="btn btn-success pull-right"
				>products</a>
				<h1>Picture</h1>
				<p>Download</p>
			</div>
		</div>
	</section>
	<section class="container">
		<form:form modelAttribute="picPackage" enctype="multipart/form-data" class="form-horizontal" >
			<fieldset>
				<legend>Download Pictures</legend>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="productId">
						Picture Source </label>
					<div class="col-lg-10">
						<form:select id="picSource" path="picSource" type="text"
							class="form:input-large"
						>
							<form:option value="Alibaba">Alibaba</form:option>
							<form:option value="Amazon">Amazon</form:option>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary"
							value="Download Picture"
						/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="name">URL1</label>
					<div id="urlList" class="col-lg-10">
						<form:input id="url1" type="text" path="urlList[0].url" class="form:input-large" width="80%"/>
						<br />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="name">URL2</label>
					<div class="col-lg-10">
						<form:input id="url2" type="text" path="urlList[1].url"
							class="form:input-large"
						/>
						<br />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="name">URL3</label>
					<div class="col-lg-10">
						<form:input id="url3" type="text" path="urlList[2].url"
							class="form:input-large"
						/>
						<br />
					</div>
				</div>
				
			</fieldset>
		</form:form>
	</section>
</body>
</html>