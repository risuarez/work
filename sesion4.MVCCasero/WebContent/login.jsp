<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<title>ShareMyTrip - Inicie sesión</title>
<body>
	<div class="col-md-4 col-md-offset-4">
		<h1>Inicie sesión</h1>
		<!--  <c:if test="${requestScope.jspSiguiente==null}">
    	<div class="alert alert-danger">
        	<p>Usuario y/o contraseña incorrecto</p>
    	</div>
    </c:if>-->
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
				<label class="col-md-4 control-label" for="btRegistrarse"></label>
				<div class="col-md-8">
					<a id="registrarUsuario" href="registrarUsuario.jsp" class="btn btn-info" role="button">Registrar usuario</a>
					<input type="submit" class="btn btn-success" value="Iniciar sesión">
				</div>
			</div>
		</form>
	</div>
	<a id="listarViajes" href="listarViajes" class="btn btn-warning" role="button">Lista de viajes</a>
</body>
</html>