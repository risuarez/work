package uo.sdi.acciones;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;
import alb.util.log.Log;

public class ModificarViajeAction implements Accion
{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response)
	{

		String result = "FRACASO";

		String nuevaDireccionOrigen = request.getParameter("direccionOrigen");
		String nuevaCiudadOrigen = request.getParameter("ciudadOrigen");
		String nuevoEstadoOrigen = request.getParameter("estadoOrigen");
		String nuevoPaisOrigen = request.getParameter("paisOrigen");
		String nuevaPostalOrigen = request.getParameter("postalOrigen");
		String nuevaLatOrigen = request.getParameter("latOrigen");
		String nuevaLongOrigen = request.getParameter("longOrigen");
		String nuevaFechaOrigen = request.getParameter("fechaOrigen");
		String nuevaHoraOrigen = request.getParameter("horaOrigen");

		String nuevaDireccionDestino = request.getParameter("direccionDestino");
		String nuevaCiudadDestino = request.getParameter("ciudadDestino");
		String nuevoEstadoDestino = request.getParameter("estadoDestino");
		String nuevoPaisDestino = request.getParameter("paisDestino");
		String nuevaPostalDestino = request.getParameter("postalDestino");
		String nuevaLatDestino = request.getParameter("latDestino");
		String nuevaLongDestino = request.getParameter("longDestino");
		String nuevaFechaDestino = request.getParameter("fechaDestino");
		String nuevaHoraDestino = request.getParameter("horaDestino");

		String nuevaFechaLimite = request.getParameter("fechaLimite");
		String nuevoPlazasMaximo = request.getParameter("plazasMaximo");
		String nuevoPlazasLibres = request.getParameter("plazasLibres");
		String nuevoCoste = request.getParameter("coste");
		String nuevaDescripcion = request.getParameter("descripcion");

		HttpSession session = request.getSession();
		long viajeId = Long.parseLong(request.getParameter("viajeId"));
		User usuario = ((User) session.getAttribute("user"));
		List<String> logMessages = new ArrayList<String>();


		//borra si existen los mensajes
		session.removeAttribute("corrrectDataUpdate");
		session.removeAttribute("wrongDatatUpdate");
		
		if (nuevaDireccionOrigen.isEmpty() || nuevaCiudadOrigen.isEmpty()
				|| nuevoEstadoOrigen.isEmpty() || nuevoPaisOrigen.isEmpty()
				|| nuevaPostalOrigen.isEmpty() || nuevaFechaOrigen.isEmpty()
				|| nuevaHoraOrigen.isEmpty() || nuevaDireccionDestino.isEmpty()
				|| nuevaCiudadDestino.isEmpty() || nuevoEstadoDestino.isEmpty()
				|| nuevoPaisDestino.isEmpty() || nuevaPostalDestino.isEmpty()
				|| nuevaFechaDestino.isEmpty() || nuevaHoraDestino.isEmpty()
				|| nuevaFechaLimite.isEmpty() || nuevoPlazasMaximo.isEmpty()
				|| nuevoPlazasLibres.isEmpty() || nuevoCoste.isEmpty()
				|| nuevaDescripcion.isEmpty())
		{
			Log.info("Campo/s vacio/s al intentar modificar viaje por el"
					+ " usuario [%s]", usuario.getLogin());
			request.setAttribute("wrongRegistrarViaje", "Debe rellenar todos "
					+ "los campos (excepto latitud y longitud");
			result = "FRACASO";
		} else
		{
			Trip trip = PersistenceFactory.newTripDao().findById(viajeId);
//			if (!usuario.getEmail().equals(nuevoEmail))
//			{
//				usuario.setEmail(nuevoEmail);
//				logMessages.add("Modificado email de" + usuario.getLogin()
//						+ "con el valor" + nuevoEmail);
//			}
//			if (!usuario.getName().equals(nuevoNombre))
//			{
//				usuario.setName(nuevoNombre);
//				logMessages.add("Modificado nombre de" + usuario.getLogin()
//						+ "con el valor" + nuevoNombre);
//			}
//			if (!usuario.getSurname().equals(nuevoApellidos))
//			{
//				usuario.setSurname(nuevoApellidos);
//				logMessages.add("Modificado apellidos de" + usuario.getLogin()
//						+ "con el valor" + nuevoApellidos);
//			}
			try
			{
				TripDao dao = PersistenceFactory.newTripDao();
				dao.update(trip);
				for (String message : logMessages)
					Log.debug(message);
				session.setAttribute("corrrectDataUpdate",
						"Datos actualizados con éxito");
				result = "EXITO";
			} catch (Exception e)
			{
				Log.error(
						"Algo ha ocurrido actualizando la información de [%s]",
						usuario.getLogin());
				session.setAttribute("wrongDatatUpdate",
						"Error actualizando los datos");
			}
		}
		return result;
	}

	@Override
	public String toString()
	{
		return getClass().getName();
	}

}
