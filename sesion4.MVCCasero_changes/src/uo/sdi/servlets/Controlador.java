package uo.sdi.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;
import uo.sdi.acciones.*;


public class Controlador extends javax.servlet.http.HttpServlet
{

	private static final long serialVersionUID = 1L;
	private Map<String, Map<String, Accion>> mapaDeAcciones; // <rol, <opcion,
																// objeto
																// Accion>>
	private Map<String, Map<String, Map<String, String>>> mapaDeNavegacion; // <rol,
																			// <opcion,
																			// <resultado,
																			// JSP>>>

	public void init() throws ServletException
	{
		crearMapaAcciones();
		crearMapaDeJSP();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException
	{

		String opcion, resultado, jspSiguiente;
		Accion accion;
		String rolAntes, rolDespues;


		try
		{
			opcion = req.getServletPath().replace("/", "");

			rolAntes = obtenerRolDeSesion(req);

			accion = buscarAccionParaOpcion(rolAntes, opcion);

			resultado = accion.execute(req, res);

			rolDespues = obtenerRolDeSesion(req);

			jspSiguiente = buscarJSPSegun(rolDespues, opcion, resultado);

			req.setAttribute("jspSiguiente", jspSiguiente);


		} catch (Exception e)
		{

			req.getSession().invalidate();

			Log.error("Se ha producido alguna excepción no manejada [%s]", e);

			jspSiguiente = "/login.jsp";
			//throw e;
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jspSiguiente);

		dispatcher.forward(req, res);
	}

	private String obtenerRolDeSesion(HttpServletRequest req)
	{
		HttpSession sesion = req.getSession();
		if (sesion.getAttribute("user") == null)
			return "PUBLICO";
		else
			return "REGISTRADO";
	}

	// Obtiene un objeto accion en funci�n de la opci�n
	// enviada desde el navegador
	private Accion buscarAccionParaOpcion(String rol, String opcion)
	{

		Accion accion = mapaDeAcciones.get(rol).get(opcion);
		Log.debug("Elegida acción [%s] para opción [%s] y rol [%s]", accion,
				opcion, rol);
		return accion;
	}

	// Obtiene la p�gina JSP a la que habr� que entregar el
	// control el funci�n de la opci�n enviada desde el navegador
	// y el resultado de la ejecuci�n de la acci�n asociada
	private String buscarJSPSegun(String rol, String opcion, String resultado)
	{

		String jspSiguiente = mapaDeNavegacion.get(rol).get(opcion)
				.get(resultado);
		Log.debug(
				"Elegida página siguiente [%s] para el resultado [%s] tras realizar [%s] con rol [%s]",
				jspSiguiente, resultado, opcion, rol);
		return jspSiguiente;
	}

	private void crearMapaAcciones()
	{

		mapaDeAcciones = new HashMap<String, Map<String, Accion>>();

		Map<String, Accion> mapaPublico = new HashMap<String, Accion>();
		mapaPublico.put("validarse", new ValidarseAction());
		mapaPublico.put("listarViajes", new ListarViajesAction());
		mapaPublico.put("registrarUsuario", new RegistrarUsuarioAction());
		mapaDeAcciones.put("PUBLICO", mapaPublico);

		Map<String, Accion> mapaRegistrado = new HashMap<String, Accion>();
		mapaRegistrado.put("modificarDatos", new ModificarDatosAction());
		mapaRegistrado.put("modificarPassword", new ModificarPasswordAction());
		mapaRegistrado.put("cerrarSesion", new CerrarSesionAction());
		mapaRegistrado.put("consultarViajes",
				new ConsultarRegistradoViajesAction());
		mapaRegistrado.put("registrarViaje", new RegistrarViajeAction());
		mapaRegistrado.put("verMisViajes", new ListarViajesImplicadoAction());
		mapaRegistrado.put("solicitarPlaza", new SolicitarPlazaAction());
		mapaRegistrado.put("verPerfilUsuario", new MostrarPerfilAction());
		mapaRegistrado.put("cargarPrincipal", new CargarPrincipalAction());
		mapaRegistrado.put("modificarViaje", new ModificarViajeAction());
		mapaRegistrado.put("cancelarViaje", new CancelarViajeAction());
		mapaRegistrado.put("verPasajerosInteresados", 
				new VerPasajerosInteresadosAction());
		mapaRegistrado.put("aceptarPasajero", 
				new AceptarPasajeroAction());
		mapaDeAcciones.put("REGISTRADO", mapaRegistrado);
	}

	private void crearMapaDeJSP()
	{
		mapaDeNavegacion = new HashMap<String, Map<String, Map<String, String>>>();

		// Crear mapas auxiliares vacíos
		Map<String, Map<String, String>> opcionResJSP = new HashMap<String, Map<String, String>>();
		Map<String, String> resJSP = new HashMap<String, String>();

		// Mapa de navegación del público
		resJSP.put("FRACASO", "/login.jsp");
		opcionResJSP.put("validarse", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/login.jsp");
		opcionResJSP.put("listarViajes", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/login.jsp");
		resJSP.put("FRACASO", "/registrarUsuario.jsp");
		opcionResJSP.put("registrarUsuario", resJSP);


		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/login.jsp");
		opcionResJSP.put("cerrarSesion", resJSP);

		mapaDeNavegacion.put("PUBLICO", opcionResJSP);

		// Crear mapas auxiliares vacíos
		opcionResJSP = new HashMap<String, Map<String, String>>();
		resJSP = new HashMap<String, String>();

		// Mapa de navegación de usuarios registrados
		resJSP.put("EXITO", "/principal.jsp");
		opcionResJSP.put("validarse", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/modificarUsuario.jsp");
		resJSP.put("FRACASO", "/modificarUsuario.jsp");
		opcionResJSP.put("modificarDatos", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/modificarUsuario.jsp");
		resJSP.put("FRACASO", "/modificarUsuario.jsp");
		opcionResJSP.put("modificarPassword", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/login.jsp");
		opcionResJSP.put("cerrarSesion", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/registrarViaje.jsp");
		resJSP.put("FRACASO", "/registrarViaje.jsp");
		opcionResJSP.put("registrarViaje", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/verMisViajes.jsp");
		opcionResJSP.put("verMisViajes", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/consultarViajes.jsp");
		opcionResJSP.put("consultarViajes", resJSP);
		
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/principal.jsp");
		resJSP.put("FRACASO", "/principal.jsp");
		opcionResJSP.put("solicitarPlaza", resJSP);
		
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/verPerfilUsuario.jsp");
		opcionResJSP.put("verPerfilUsuario", resJSP);
		
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/principal.jsp");
		opcionResJSP.put("cargarPrincipal", resJSP);
		
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/modificarViaje.jsp");
		resJSP.put("FRACASO", "/modificarViaje.jsp");
		opcionResJSP.put("modificarViaje", resJSP);
		
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/principal.jsp");
		resJSP.put("FRACASO", "/principal.jsp");
		opcionResJSP.put("cancelarViaje", resJSP);
		
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/verPasajerosInteresados.jsp");
		resJSP.put("FRACASO", "/principal.jsp");
		opcionResJSP.put("verPasajerosInteresados", resJSP);
		
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/principal.jsp");
		resJSP.put("FRACASO", "/principal.jsp");
		opcionResJSP.put("aceptarPasajero", resJSP);
		

		mapaDeNavegacion.put("REGISTRADO", opcionResJSP);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException
	{

		doGet(req, res);
	}

}