<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<title>ShareMyTrip - Registrar usuario</title>
<body>
	<div class="col-md-4 col-md-offset-4">
		<h1>Registrar nuevo usuario</h1>
		<c:if test="${error!=null}">
			<div class="alert alert-danger">
				<p>${error}</p>
			</div>
		</c:if>
		<form class="form-horizontal" action="registrarUsuario" method="post">
			<div class="form-group">
				<input class="form-control" type="text" name="login"
					placeholder="Usuario" />
			</div>
			<div class="form-group">
				<input class="form-control" type="text" name="nombre"
					placeholder="Nombre" />
			</div>
			<div class="form-group">
				<input class="form-control" type="text" name="apellidos"
					placeholder="Apellidos" />
			</div>
			<div class="form-group">
				<input class="form-control" type="text" name="email"
					placeholder="Email" />
			</div>
			<div class="form-group">
				<input class="form-control" type="password" name="password1"
					placeholder="Contraseña" />
			</div>
			<div class="form-group">
				<input class="form-control" type="password" name="password2"
					placeholder="Repita la contraseña" />
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="btRegistrarse"></label>
				<div class="col-md-8">
					<a href="login.jsp" class="btn btn-danger" role="button">Cancelar</a>
					<input type="submit" class="btn btn-success" value="Registrar">
				</div>
			</div>
		</form>
	</div>
</body>
</html>