<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<title>ShareMyTrip - Página principal del usuario</title>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="principal.jsp">ShareMyTrip</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="principal.jsp">Consultar viajes</a></li>
			<li><a href="modificarUsuario.jsp">Modificar datos usuario</a></li>
			<li><a href="principal.jsp">Ver mis viajes</a></li>
			<li><a href="registrarViaje.jsp">Registrar viaje</a></li>
			<li>
				<form class="form-horizontal" action="cerrarSesion" method="post">
					<input type="submit" class="btn btn-danger" value="Cerrar sesion">
				</form>
			</li>
		</ul>
	</div>
	</nav>
	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />
	<div class="col-md-4 col-md-offset-4">
		<div class="alert alert-success fade in">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<p>
				Bienvenido <strong><jsp:getProperty property="login"
						name="user" /></strong> <br />Es Vd el usuario número: ${contador}
			</p>
		</div>
		<h1>Próximos viajes</h1>
		<c:forEach var="entry" items="${listaViajes}" varStatus="i">
			<div class="panel panel-info">
				<div class="panel-heading">${entry.departure.city}->
					${entry.destination.city}</div>
				<div class="panel-body">
					<ul>
						<li>Id viaje: ${entry.id}</li>
						<li>Plazas libres: ${entry.availablePax}</li>
					</ul>
					<div class="panel panel-default">
						<strong>Salida</strong>
						<ul>
							<li>Direccion: ${entry.destination.address}
								${entry.destination.zipCode},
								${entry.destination.city}
								(${entry.destination.state} -
								${entry.destination.country})</li>
							<li>Coordenadas: ${entry.destination.waypoint}</li>
							<li>Fecha: ${entry.departure.departureDate}</li>
						</ul>
					</div>
					<div class="panel panel-default">
						<strong>Destino</strong>
						<ul>
							<li>Direccion: ${entry.destination.address}
								${entry.destination.zipCode},
								${entry.destination.city}
								(${entry.destination.state} -
								${entry.destination..country})</li>
							<li>Coordenadas: ${entry.destination.waypoint}</li>
							<li>Fecha: ${entry.destination.destinationDate}</li>
						</ul>
					</div>
					<!--  <li>Promotor: ${entry.promoterId}</li>
						<li>Plazas libres: ${entry.availablePax}</li>-->
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>
