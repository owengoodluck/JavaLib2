<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href='<c:url value="/resource/css/bootstrap.min.css" />' type="text/css"/>
<script src='<c:url value="/resource/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resource/js/bootstrap.min.js"/>'></script>
<script src='<c:url value="/resource/js/owen.js"/>'></script>
<head>
</head>

<body>
	<nav class="navbar navbar-inverse">
		<tiles:insertAttribute name="header" />
	</nav>
	
	<div class="container-fluid text-center">    
		<tiles:insertAttribute name="main" />
	</div>
	
	<footer class="container-fluid text-center">
	  <!-- <p>Designed By Owen</p> -->
	</footer>

</body>
</html>
