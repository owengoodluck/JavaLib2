<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resource/css/styles.css" />" type="text/css">
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script> -->
<script src="<c:url value="/resource/js/scripts.js" />"type="text/javascript"></script>
<script type="text/javascript">

function submitForm(){
	$('#submitButton').val("正在下载...");
	$('#submitButton').attr('disabled',"true");
	$('#cleanButton').attr('disabled',"true");
	$('#picPackage').submit();
}
function clearForm(){
	var result = confirm('确定要清空？');  
	if(!result){  
		return;
	}
	$(":text").each(function(){
		var idStr = this.id;
		if(idStr.indexOf('urlList')>-1){
			$( document.getElementById(idStr) ).val(null);
		}
	});
}

</script>
<title>图片管理</title>
</head>
<body>
	<section class="container">
	<legend align="left">图片下载</legend>
		<form:form modelAttribute="picPackage" enctype="multipart/form-data" >
			<table  border="1"  cellspacing="10">
				
				<tr>
					<td>图片保存地址</td> 
					<td> 
						<form:input path="downloadPath" type="text" style="width:100%"/>
					</td>
				</tr>
				
				<tr>
					<td>下载链接1</td> 
					<td width="90%"> 
						<form:input path="urlList[0].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接2</td> 
					<td> 
						<form:input path="urlList[1].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接3</td> 
					<td> 
						<form:input path="urlList[2].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接4</td> 
					<td> 
						<form:input path="urlList[3].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接5</td> 
					<td> 
						<form:input path="urlList[4].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接6</td> 
					<td> 
						<form:input path="urlList[5].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接7</td> 
					<td> 
						<form:input path="urlList[6].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接8</td> 
					<td> 
						<form:input path="urlList[7].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接9</td> 
					<td> 
						<form:input path="urlList[8].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接10</td> 
					<td> 
						<form:input path="urlList[9].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"> 
						<c:if test="${msg !=null }">
							<span class="label label-success">${msg}</span>
						</c:if>
						<input id="cleanButton" type="button" value="清空" class="btn btn-primary" onclick="clearForm()"/>
						<input id="submitButton" type="button" value="下载" class="btn btn-primary" onclick="submitForm()"/>
					</td>
				</tr>
			</table>
		</form:form>
	</section>
</body>
</html>