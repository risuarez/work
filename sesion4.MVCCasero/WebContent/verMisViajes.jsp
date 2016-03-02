<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<!DOCTYPE>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<title>ShareMyTrip - Ver mis viajes</title>
</head>
<body>
	<%@ include file="barraDeMenu.jsp"%>
	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />
	<div class="col-md-6 col-md-offset-3">
		<h1>Viajes ofertados por mi.</h1>
		<c:forEach var="entry" items="${listaViajesPromotor}" varStatus="i">
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


					<a id="modificarViaje" href="modificarViaje?viajeId=${entry.id}"
						class="btn btn-success pull-right" role="button">Modificar
						viaje</a>


				</div>
			</div>
		</c:forEach>
	</div>
	<div class="col-md-4 col-md-offset-4">
		<h1>Viajes en los que he participado.</h1>
		<c:forEach var="entry" items="${listaViajesParticipante}"
			varStatus="i">
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

					<a id="comentarViaje" href="comentarViaje?viajeId=${entry.id}"
						class="btn btn-info pull-right" role="button">
						Comentar este viaje.</a>

				</div>
			</div>
		</c:forEach>
	</div>
	<div class="col-md-4 col-md-offset-4">
		<h1>Viajes que me han interesado.</h1>
		<c:forEach var="entry" items="${listaViajesInteresado}" varStatus="i">
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


					<a id="cancelarSolicitud" href="cancelarSolicitud?viajeId=
					${entry.id}"
						class="btn btn-danger pull-right" role="button">
						Cancelar solicitud</a>


				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>
