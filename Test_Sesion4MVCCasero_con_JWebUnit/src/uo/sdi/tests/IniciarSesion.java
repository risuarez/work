package uo.sdi.tests;

import static net.sourceforge.jwebunit.junit.JWebUnit.assertTextInElement;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTextPresent;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTextField;
import static net.sourceforge.jwebunit.junit.JWebUnit.submit;

import org.junit.Before;
import org.junit.Test;

public class IniciarSesion {

	@Before
	public void prepare() {
		setBaseUrl("http://localhost:8280/sesion4.MVCCasero");
	}

	@Test
	public void testIniciarSesionConExito() {
		
		beginAt("/"); // Navegar a la URL
		setTextField("nombreUsuario", "user1"); 
		setTextField("password", "user1");
		submit(); // Enviar formulario
		assertTitleEquals("ShareMyTrip - Página principal del usuario"); 
		assertTextInElement("login", "user1"); 
		//assertTextInElement("name", "Fernando"); 
		assertTextPresent("Es Vd el usuario número:"); 
	}
	
	@Test
	public void testIniciarSesionSinExito() {
		// Rellenando el formulario HTML
		beginAt("/"); // Navegar a la URL
		setTextField("nombreUsuario", "yoNoExisto"); // Rellenar primer campo de
														// formulario
		submit(); // Enviar formulario
		assertTitleEquals("ShareMyTrip - Inicie sesión"); // Comprobar título de
															// la página
		assertTextPresent("Debe escribir usuario y contraseña"); 
	}
	
	@Test
	public void testIniciarSesionSinExitoWrongPassword() {
		// Rellenando el formulario HTML
		beginAt("/"); // Navegar a la URL
		setTextField("nombreUsuario", "user1");
		setTextField("password", "wrongPassword");
		
		submit(); // Enviar formulario
		assertTitleEquals("ShareMyTrip - Inicie sesión"); // Comprobar título de
															// la página
		assertTextPresent("Contraseña y/o usuario incorrecto"); 
	}


}
