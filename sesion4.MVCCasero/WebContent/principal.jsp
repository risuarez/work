<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<title>ShareMyTrip - Página principal del usuario</title>
</head>
<body>
	<%@ include file="barraDeMenu.jsp"%>
	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />
	<div class="col-md-4 col-md-offset-4">
		<div class="alert alert-info">
			<p id="login">
				Bienvenido <strong><jsp:getProperty property="login"
						name="user" /></strong> <br />Es Vd el usuario número: ${contador}
			</p>
		</div>
		<c:if test="${corrrectResult!=null}">
			<div class="alert alert-success">
				<p>${corrrectResult}</p>
			</div>
		</c:if>
		<c:if test="${wrongResult!=null}">
			<div class="alert alert-danger">
				<p>${wrongResult}</p>
			</div>
		</c:if>
	</div>
</body>
</html>
