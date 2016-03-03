<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<title>ShareMyTrip - Inicie sesión</title>
<body>
	<div class="col-md-4 col-md-offset-4">
		<h1>Inicie sesión</h1>
		<c:if test="${correctData!=null}">
			<div class="alert alert-success">
				<p>${correctData}</p>
			</div>
		</c:if>
		<c:if test="${error!=null}">
			<div class="alert alert-danger">
				<p>${error}</p>
			</div>
		</c:if>
		<form class="form-horizontal" action="validarse" method="post">
			<div class="form-group">
				<input class="form-control" type="text" name="nombreUsuario"
					placeholder="Usuario" />
			</div>
			<div class="form-group">
				<input class="form-control" type="password" name="password"
					placeholder="Contraseña" />
			</div>

			<div class="form-group">
					<a id="listarViajes" href="listarViajes" class="btn btn-warning"
						>Lista de viajes</a> 
					<a id="registrarUsuario"
						href="registrarUsuario.jsp" class="btn btn-info">Registrar
						usuario</a> 
					<input type="submit" class="btn btn-success pull-right"
						value="Iniciar sesión">
			</div>
		</form>
	</div>
	<c:if test="${listaViajes!=null}">
		<div class="col-md-4 col-md-offset-4">
		<h1>Próximos viajes</h1>
				<c:forEach var="entry" items="${listaViajes}" varStatus="i">
					<div class="panel panel-info">
						<div class="panel-heading"> ${entry.departure.city} -> ${entry.destination.city}</div>
						<div class="panel-body">
						<ul>
							<li>Id viaje: ${entry.id}</li>
							<li>Origen: ${entry.departure.city}</li>
							<li>Destino: ${entry.destination.city}</li>
							<li>Plazas libres: ${entry.availablePax}</li>
						</ul>
						</div>
					</div>
				</c:forEach>
		</div>
	</c:if>

</body>
</html>