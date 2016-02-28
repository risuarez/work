<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<title>ShareMyTrip - Modificar usuario</title>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="principal.jsp">ShareMyTrip</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="#">Ver viajes</a></li>
			<li class="active"><a href="principal.jsp">Modificar datos usuario</a></li>
			<li><form class="form-horizontal" action="cerrarSesion" method="post">
					<input type="submit" class="btn btn-danger" value="Cerrar sesion">
				</form>
			</li>
		</ul>
	</div>
	</nav>
	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />
	<div class="col-md-4 col-md-offset-4">
		<h1>Modificar datos de usuario</h1>
		<c:if test="${corrrectDataUpdate!=null}">
			<div class="alert alert-success">
				<p>${corrrectDataUpdate}</p>
			</div>
		</c:if>
		<c:if test="${wrongDatatUpdate!=null}">
			<div class="alert alert-danger">
				<p>${wrongDatatUpdate}</p>
			</div>
		</c:if>
		<form class="form-horizontal" action="modificarDatos" method="post">
			<div class="form-group">
				<label class="col-md-4 control-label" for="textinput">Login:
				</label>
				<div class="col-md-4">
					<input class="form-control" type="text" name="login"
						readonly value="<jsp:getProperty property="login" name="user"/>" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="textinput">Nombre:
				</label>
				<div class="col-md-4">
					<input class="form-control" type="text" name="nombre"
						value="<jsp:getProperty property="name" name="user"/>" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="textinput">Apellidos:
				</label>
				<div class="col-md-4">
					<input class="form-control" type="text" name="apellidos"
						value="<jsp:getProperty property="surname" name="user"/>" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="textinput">Email:
				</label>
				<div class="col-md-4">
					<input class="form-control" type="text" name="email"
						value="<jsp:getProperty property="email" name="user"/>" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-10">
					<input type="submit" class="btn btn-success pull-right"
						value="Modificar datos">

				</div>
			</div>
		</form>
		<h1>Modificar contraseña</h1>
		<c:if test="${correctUpdatePassword!=null}">
			<div class="alert alert-success">
				<p>${correctUpdatePassword}</p>
			</div>
		</c:if>
		<c:if test="${wrongUpdatePassword!=null}">
			<div class="alert alert-danger">
				<p>${wrongUpdatePassword}</p>
			</div>
		</c:if>
		<form class="form-horizontal" action="modificarPassword" method="post">
			<div class="form-group">
				<label class="col-md-4 control-label" for="textinput">Contraseña antigua:
				</label>
				<div class="col-md-4">
				<input class="form-control" type="password" name="oldPassword"
					placeholder="Contraseña antigua" />
					</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="textinput">Nueva contraseña:
				</label>
				<div class="col-md-4">
				<input class="form-control" type="password" name="newPassword1"
					placeholder="Nueva contraseña" />
					</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="textinput">Repita nueva contraseña:
				</label>
				<div class="col-md-4">
				<input class="form-control" type="password" name="newPassword2"
					placeholder="Nueva contraseña" />
					</div>
			</div>
			<div class="form-group">
				<div class="col-md-10">
					<input type="submit" class="btn btn-success pull-right"
						value="Modificar contraseña">

				</div>
			</div>
			</form>
	</div>
</body>
</html>
