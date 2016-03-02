package uo.sdi.acciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

/**
 * Este action devuelve la lista de los proximos viajes con plazas disponibles y
 * el plazo de inscripcion abierto. Es para los usuarios registrados (devuelve
 * mas informacion que el de para usuarios publicos. Añade dos atributos a la
 * request: la lista de viajes (listaViajes) y la lista de participantes de cada
 * viaje ()
 * 
 * @author Fabio
 * 
 */
public class ConsultarRegistradoViajesAction implements Accion
{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response)
	{

		List<Trip> viajes;
		Map<Long, List<User>> participantes = new HashMap<Long, List<User>>();
		HttpSession session = request.getSession();
		User usuario = ((User) session.getAttribute("user"));

		try
		{
			viajes = PersistenceFactory.newTripDao().findAll();
			request.setAttribute("listaViajes", viajes);
			Log.debug("Obtenida lista de viajes conteniendo [%d] viajes",
					viajes.size());
			for (Trip t : viajes)
			{
				participantes.put(t.getId(), PersistenceFactory.newUserDao()
						.findByTrip(t.getId()));
			}
			request.setAttribute("mapParticipantes", participantes);
			Log.debug("Obtenida listas de usuarios");

		} catch (Exception e)
		{
			Log.error("Error calculando los viajes y/o los participantes [%s]",
					usuario.getLogin());
//			throw e;
		}
		return "EXITO";
	}

	@Override
	public String toString()
	{
		return getClass().getName();
	}

}
