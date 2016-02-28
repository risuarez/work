package uo.sdi.tests;

import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTextField;
import static net.sourceforge.jwebunit.junit.JWebUnit.submit;

import org.junit.Before;
import org.junit.Test;

public class IniciarSesionSinExitoTest {

	@Before
	public void prepare() {
		setBaseUrl("http://localhost:8280/sesion4.MVCCasero");
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
	}

}
