<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@ include file="comprobarNavegacion.jsp"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<title>ShareMyTrip - Ver perfil de usuario</title>
</head>
<body>
	<%@ include file="barraDeMenu.jsp"%>
	<div class="col-md-4 col-md-offset-4">
		<c:if test="${wrongResult!=null}">
			<div class="alert alert-danger">
				<p>${wrongResult}</p>
			</div>
		</c:if>
		<h1>Perfil de usuario</h1>
		<div class="panel panel-info">
			<div class="panel-heading">
				<strong>${userInfo.login}</strong>
			</div>
			<div class="panel-body">
				<ul>
					<li><strong>Nombre:</strong> ${userInfo.name}</li>
					<li><strong>Apellidos:</strong> ${userInfo.surname}</li>
					<li><strong>Email:</strong> ${userInfo.email}</li>
				</ul>
				<c:choose>
					<c:when test="${userRatings!=null && userRatings.size()>0}">
						<c:forEach var="rating" items="${userRatings}" varStatus="i">
							<div class="panel panel-default">
								<strong> Comentario sobre este usuario</strong>
								<ul>
									<li><strong>Puntuacion:</strong> ${rating.value}</li>
									<li><strong>Comentario:</strong> ${rating.comment}</li>
								</ul>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<strong> No hay comentarios sobre este usuario</strong>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>
