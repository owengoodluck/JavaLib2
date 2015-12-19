<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value="/resource/css/styles.css" />" type="text/css">
<link rel="stylesheet" href="<c:url value="/resource/css/bootstrap.min.css" />" type="text/css">
<script src="<c:url value="/resource/js/scripts.js" />" type="text/javascript"></script>
<script src="<c:url value="/resource/js/jquery.min.js" />" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	setNameOfEnglish();
	$('#nameChinese').change(function(){
		setNameOfEnglish();
	});
});

function setNameOfEnglish(){
	var nameCh = $('#nameChinese').val();
	var nameEn = '';
	if ( nameCh == '不锈钢饰品配件-吊坠'){
		nameEn = 'Stainless Steel Accessories Necklace Pendant';
	}else if ( nameCh == '不锈钢饰品配件-手链'){
		nameEn = 'Stainless Steel Accessories Bracelet';
	}else if ( nameCh == '不锈钢饰品配件-指环'){
		nameEn = 'Stainless Steel Accessories Rings';
	}else if ( nameCh == '时尚饰品配件-吊坠'){
		nameEn = 'Fashion Accessories Necklace Pendant';
	}else if ( nameCh == '时尚饰品配件-手链'){
		nameEn = 'Fashion Accessories Bracelet';
	}else if ( nameCh == '时尚饰品配件-指环'){
		nameEn = 'Fashion Accessories Rings';
	}
	$('#nameEnglish').val(nameEn);
}

function submitForm(){
	var amazonOrderID = $('#amazonOrderID').val();
	var nameChinese = $('#nameChinese').val();
	var channel = $('#channel').val();
	
	if(amazonOrderID == null || amazonOrderID.length == 0){
		alert('亚马逊订单号不能为空');
		$('#amazonOrderID').focus();
		return;
	}
	if(channel == null || channel.length == 0){
		alert('请选择发货渠道');
		$('#channel').focus();
		return;
	}
	if(nameChinese == null || nameChinese.length == 0){
		alert('请选择中文品名');
		$('#nameChinese').focus();
		return;
	}
	$('#express').submit();
}
</script>
<title>创建快递单</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<a href='<c:url value="/" />'class="btn btn-success pull-right">首页</a>
				<h1>快递单</h1>
				<p>创建快递单</p>
			</div>
		</div>
	</section>
	<section class="container">
	<legend>创建快递单</legend>
		<c:if test='${ createSuccessIndicator == "快递单创建成功！" }'>
			<span class="label label-success">${createSuccessIndicator}</span>
		</c:if>
		<c:if test='${ createSuccessIndicator!=null && createSuccessIndicator != "快递单创建成功！" }'>
			<span class="label label-danger">${createSuccessIndicator}</span>
		</c:if>
		<form:form modelAttribute="express" enctype="multipart/form-data" >
			<table  border="1"  cellspacing="10">
				<tr>
					<td>亚马逊订单号</td> 
					<td width="90%"> 
						<form:input path="amazonOrderID" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>序列号（可选）</td> 
					<td width="90%"> 
						<form:input path="sequenceNo" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>发货方式</td> 
					<td> 
						<form:select path="channel">
								<form:option value=""></form:option>
								<form:option value="中邮北京平邮小包">中邮北京平邮小包</form:option>
								<form:option value="中邮北京E邮宝(线下)">中邮北京E邮宝(线下)</form:option>
								<form:option value="中邮北京挂号小包">中邮北京挂号小包</form:option>
								<form:option value="中邮上海E邮宝(线下)">中邮上海E邮宝(线下)</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>发货日期</td> 
					<td> 
						<form:input path="sendDate" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>发往国家</td> 
					<td> 
						<form:input path="country" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>中文品名</td> 
					<td> 
						<form:select path="nameChinese">
								<%-- <form:option value=""></form:option> --%>
								<form:option value="不锈钢饰品配件-吊坠">不锈钢饰品配件-吊坠</form:option>
								<form:option value="不锈钢饰品配件-手链">不锈钢饰品配件-手链</form:option>
								<form:option value="不锈钢饰品配件-指环">不锈钢饰品配件-指环</form:option>
								<form:option value="时尚饰品配件-吊坠">时尚饰品配件-吊坠</form:option>
								<form:option value="时尚饰品配件-手链">时尚饰品配件-手链</form:option>
								<form:option value="时尚饰品配件-指环">时尚饰品配件-指环</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>英文品名</td> 
					<td> 
						<form:input path="nameEnglish" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>货品数量</td> 
					<td> 
						<form:input path="quantity" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td> 总重量(g)</td> 
					<td> 
						<form:input path="weight" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>货币种类</td> 
					<td> 
						<form:input path="declaredCurrency" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>申报价值</td> 
					<td> 
						<form:input path="declaredValue" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>快递单保存地址</td> 
					<td width="90%"> 
						<form:input path="downloadPath" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"> 
						<c:if test="">
							
						</c:if>
						<input type="button" id="btnAdd" class="btn btn-primary" value="创建快递单"  onclick="submitForm()" />
					</td>
				</tr>
			</table>
		</form:form>
	</section>
</body>
</html>