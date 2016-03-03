<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<title>ShareMyTrip - Consultar viajes</title>
</head>
<body>
	<%@ include file="barraDeMenu.jsp"%>
	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />
	<div class="col-md-6 col-md-offset-3">
<<<<<<< HEAD
	<div class="container">
		<form action="consultarViajes">
		
			<label for="origen">Origen:</label>
			<input type="text" id="origen"name="origen" />
			
			<label for="destino">Destino:</label>
			<input type="text" id="destino" name="destino" />
			
			<input type="submit" value="Buscar" />
		</form>
		<!--<form>

  			<input type="radio" name="orderBy" value="origen"> Origen<br>
  			<input type="radio" name="orderBy" value="destino"> Destino<br>
  			<input type="radio" name="orderBy" value="fecha"> Fecha
  			
  		</form>	-->
	</div>
=======
		<div class="container">
			<form>
				<label for="origen">Origen:</label> <input type="text" id="origen"
					name="origen" /> <label for="destino">Destino:</label> <input
					type="text" id="destino" name="destino" /> <input type="submit"
					value="Buscar" />
			</form>
			<form>

				<input type="radio" name="orderBy" value="origen"> Origen<br>
				<input type="radio" name="orderBy" value="destino"> Destino<br>
				<input type="radio" name="orderBy" value="fecha"> Fecha

			</form>


		</div>
>>>>>>> origin/master
		<h1>Próximos viajes</h1>
		<c:forEach var="entry" items="${listaViajes}" varStatus="i">
			<div class="panel panel-info">
				<div class="panel-heading">${entry.departure.city}->
					${entry.destination.city} (${entry.status})</div>
				<div class="panel-body">
					<ul>
						<li><strong>Id viaje:</strong> ${entry.id}</li>
						<li><strong>Plazas libres:</strong> ${entry.availablePax}</li>
						<li><strong>Plazas totales:</strong> ${entry.maxPax}</li>
						<li><strong>Coste (a repartir):</strong>
							${entry.estimatedCost}€</li>
						<li><strong>Fecha limite para apuntarse:</strong>
							${entry.closingDate.toLocaleString()}</li>
						<li><strong>Descripcion:</strong> ${entry.comments}</li>
					</ul>
					<div class="panel panel-default">
						<strong> Salida</strong>
						<ul>
							<li>Direccion: ${entry.departure.address}
								${entry.departure.zipCode}, ${entry.departure.city}
								(${entry.departure.state} - ${entry.departure.country})</li>
							<li>Coordenadas: ${entry.departure.waypoint.lat},
								${entry.departure.waypoint.lon}</li>
							<li>Fecha: ${entry.departureDate.toLocaleString()}</li>
						</ul>
					</div>
					<div class="panel panel-default">
						<strong> Destino</strong>
						<ul>
							<li>Direccion: ${entry.destination.address}
								${entry.destination.zipCode}, ${entry.destination.city}
								(${entry.destination.state} - ${entry.destination.country})</li>
							<li>Coordenadas: ${entry.destination.waypoint.lat},
								${entry.destination.waypoint.lon}</li>
							<li>Fecha: ${entry.arrivalDate.toLocaleString()}</li>
						</ul>
					</div>
					<div class="panel panel-default">
						<strong> Participantes</strong>
						<div class="form-group">
							<a id="verPromotor"
								href="verPerfilUsuario?idUsuario=${entry.promoterId}"
								class="btn btn-default">Promotor</a>
							<c:forEach var="participante"
								items="${mapParticipantes.get(entry.id)}" varStatus="i">
								<a id="verUsuario${participante.id}"
									href="verPerfilUsuario?idUsuario=${participante.id}"
									class="btn btn-default">${participante.login}</a>
							</c:forEach>
						</div>
					</div>
<<<<<<< HEAD
					<c:if test="${entry.availablePax<=entry.maxPax && 
=======
					<c:if
						test="${entry.availablePax<entry.maxPax && 
>>>>>>> origin/master
					entry.promoterId!=user.id && 
					!mapParticipantes.get(entry.id).contains(user)}">
						<a id="solicitarPlaza" href="solicitarPlaza?viajeId=${entry.id}"
							class="btn btn-success pull-right">Solicitar plaza</a>
					</c:if>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>
