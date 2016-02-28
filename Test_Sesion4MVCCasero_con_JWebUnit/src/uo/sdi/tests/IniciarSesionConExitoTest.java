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

public class IniciarSesionConExitoTest {

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

}
