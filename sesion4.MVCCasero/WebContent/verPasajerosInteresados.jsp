<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<title>ShareMyTrip - Aceptar pasajeros</title>
</head>
<body>
	<%@ include file="barraDeMenu.jsp"%>
	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />
	<div class="col-md-4 col-md-offset-4">
		<h1>Pasajeros interesados en tu viaje.</h1>
			
		<c:forEach var="entry" items="${listaUsuarios}" varStatus="i">
								
		<h2>Perfil de usuario</h2>
		<div class="panel panel-info">
			<div class="panel-heading">
				<strong>${entry.login}</strong>
			</div>
			<div class="panel-body">
				<ul>
					<li><strong>Nombre:</strong> ${entry.name}</li>
					<li><strong>Apellidos:</strong> ${entry.surname}</li>
					<li><strong>Email:</strong> ${entry.email}</li>
				</ul>
				<a id="aceptarPasajero" 
				href="aceptarPasajero?userId=${entry.id}&&viajeId=${tripId}"
						class="btn btn-info pull-right" role="button">
						¡Aceptame!</a>
			</div>
		</div>
		</c:forEach>
	</div>
</body>
</html>
