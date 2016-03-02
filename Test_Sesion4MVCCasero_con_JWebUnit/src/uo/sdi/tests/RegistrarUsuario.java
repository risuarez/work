package uo.sdi.tests;

import static net.sourceforge.jwebunit.junit.JWebUnit.assertTextPresent;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTextField;
import static net.sourceforge.jwebunit.junit.JWebUnit.submit;

import org.junit.Before;
import org.junit.Test;


public class RegistrarUsuario {

	@Before
	public void prepare() {
		setBaseUrl("http://localhost:8280/sesion4.MVCCasero");
	}

	@Test
	public void registrarConExito() {
		beginAt("/registrarUsuario.jsp");
		assertTitleEquals("ShareMyTrip - Registrar usuario");
		/**setTextField("login", "pruebaTest");
		setTextField("nombre", "pruebaTest");
		setTextField("apellidos", "pruebaTest");
		setTextField("email", "pruebaTest");
		setTextField("password1", "pruebaTest");
		setTextField("password2", "pruebaTest");
		submit();
		assertTitleEquals("ShareMyTrip - Inicie sesión");
		assertTextPresent("Se ha registrado con exito el usuario pruebaTest");**/
	}

	@Test
	public void registrarSinExitoCamposVacios() {
		beginAt("/registrarUsuario.jsp");
		setTextField("login", "pruebaTest2");
		//falta nombre
		setTextField("apellidos", "pruebaTest");
		setTextField("email", "pruebaTest");
		setTextField("password1", "pruebaTest");
		setTextField("password2", "pruebaTest");
		submit();
		assertTitleEquals("ShareMyTrip - Registrar usuario");
		assertTextPresent("Debe rellenar todos los campos");
	}
	@Test
	public void registrarSinExitoDiferentesPass() {
		beginAt("/registrarUsuario.jsp");
		setTextField("login", "pruebaTest3");
		setTextField("nombre", "pruebaTest");
		setTextField("apellidos", "pruebaTest");
		setTextField("email", "pruebaTest");
		setTextField("password1", "pruebaTest");
		setTextField("password2", "wrongPassword");
		submit();
		assertTitleEquals("ShareMyTrip - Registrar usuario");
		assertTextPresent("Error: Las contraseñas deben ser iguales");
	}

}
