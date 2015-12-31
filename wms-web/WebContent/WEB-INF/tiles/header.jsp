<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
function clickMe(str){
	//alert( $(str).html() );
}
</script>
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
  
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
    </div>
    
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li <c:if test="${currentMenu == 'order' }">class="active"</c:if> > <a href="<spring:url value="/order/list" />" onclick="clickMe('A1')">订单管理</a> </li>
        <li <c:if test="${currentMenu == 'prod' }">class="active"</c:if> ><a href="<spring:url value="/prod/listAll" />">产品列表</a></li>
        <li <c:if test="${currentMenu == 'express' }">class="active"</c:if> ><a href="<spring:url value="/yanwen/create" />" >快递管理</a> </li>
        <li <c:if test="${currentMenu == 'pic' }">class="active"</c:if> ><a href="<spring:url value="/picture/download" />">图片下载</a> </li>
        <%-- <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">其他 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="<spring:url value="/picture/download" />">图片下载</a> </li>
            <li><a href="<spring:url value="/picture/download" />">图片下载</a> </li>
          </ul>
        </li> --%>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
    
    
  </div>
</nav>