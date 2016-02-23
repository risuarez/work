<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head> <title>ShareMyTrip - Registrar usuario</title>
<body>
  <form action="registrarUsuario" method="post">

 	<center><h1>Registrar nuevo usuario</h1></center>
 	<hr><br>
 	<table align="center">
    	<tr> 
    		<td align="right">Identificador de usuario</td>
	    	<td><input type="text" name="login" align="left" size="15"></td>
      	</tr>
    	<tr> 
    		<td align="right">Nombre</td>
	    	<td><input type="text" name="nombre" align="left" size="15"></td>
      	</tr>
    	<tr> 
    		<td align="right">Apellidos</td>
	    	<td><input type="text" name="apellidos" align="left" size="15"></td>
      	</tr>
    	<tr> 
    		<td align="right">Email</td>
	    	<td><input type="text" name="email" align="left" size="15"></td>
      	</tr>
    	<tr> 
    		<td align="right">Contraseña</td>
	    	<td><input type="text" name="password1" align="left" size="15"></td>
      	</tr>
    	<tr> 
    		<td align="right">Repita contraseña</td>
	    	<td><input type="text" name="password2" align="left" size="15"></td>
      	</tr>
      	<tr>
    	    <td><input type="submit" value="Enviar"/></td>
      	</tr>
      </table>
   </form>
</body>
</html>