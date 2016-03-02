<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<title>ShareMyTrip - Registrar viaje</title>
</head>
<body>
	<%@ include file="barraDeMenu.jsp"%>
	<form class="form-horizontal" action="registrarViaje" method="post">
		<div class="col-md-4 col-md-offset-4">
			<h1>Registrar viaje</h1>

			<c:if test="${correctRegistrarViaje!=null}">
				<div class="alert alert-success">
					<p>${correctRegistrarViaje}</p>
				</div>
			</c:if>
			<c:if test="${wrongRegistrarViaje!=null}">
				<div class="alert alert-danger">
					<p>${wrongRegistrarViaje}</p>
				</div>
			</c:if>
			
			<div class="col-md-5">
				<h2>Origen</h2>
				<div class="form-group">
					<input class="form-control" type="text" name="direccionOrigen"
						placeholder="Dirección" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="ciudadOrigen"
						placeholder="Ciudad" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="estadoOrigen"
						placeholder="Estado" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="paisOrigen"
						placeholder="Pais" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="postalOrigen"
						placeholder="Codigo postal" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="latOrigen"
						placeholder="Latitud (opcional)" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="longOrigen"
						placeholder="Longitud (opcional)" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="fechaOrigen"
						placeholder="Fecha (dd/MM/yyyy)" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="horaOrigen"
						placeholder="Hora (HH:mm)" />
				</div>
			</div>

			<div class="col-md-5"></div>
			<div class="col-md-5  col-md-offset-2">
				<h2>Destino</h2>
				<div class="form-group">
					<input class="form-control" type="text" name="direccionDestino"
						placeholder="Dirección" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="ciudadDestino"
						placeholder="Ciudad" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="estadoDestino"
						placeholder="Estado" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="paisDestino"
						placeholder="Pais" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="postalDestino"
						placeholder="Codigo postal" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="latDestino"
						placeholder="Latitud (opcional)" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="longDestino"
						placeholder="Longitud (opcional)" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="fechaDestino"
						placeholder="Fecha (dd/MM/yyyy)" />
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="horaDestino"
						placeholder="Hora (HH:mm)" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-5 control-label" for="textinput">Fecha
					limite inscribirse: </label>
				<div class="col-md-5">
					<input class="form-control" type="text" name="fechaLimite"
						placeholder="Fecha limite (dd/MM/yyyy)" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-5 control-label" for="textinput">Numero
					de plazas: </label>
				<div class="col-md-3">
					<input class="form-control" type="text" name="plazasMaximo"
						placeholder="Maximo plazas" />
				</div>
				<div class="col-md-3">
					<input class="form-control" type="text" name="plazasLibres"
						placeholder="Plazas libres" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-5 control-label" for="textinput">Coste
					estimado: </label>
				<div class="col-md-5">
					<input class="form-control" type="text" name="coste"
						placeholder="Coste (€)" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-5 control-label" for="textinput">Descripcion:
				</label>
				<div class="col-md-5">
					<input class="form-control" type="text" name="descripcion"
						placeholder="Descripcion/comentarios" />
				</div>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-success pull-right"
					value="Registrar">
			</div>
		</div>
	</form>
</body>
</html>
