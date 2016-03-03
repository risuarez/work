package uo.sdi.acciones;

import java.util.ArrayList;
import java.util.Date;
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
		List<Trip> viajesProximos = new ArrayList<Trip>();
		Map<Long, List<User>> participantes = new HashMap<Long, List<User>>();
		HttpSession session = request.getSession();
		User usuario = ((User) session.getAttribute("user"));
		
		String origen = request.getParameter("origen");
		String destino = request.getParameter("destino");

		try
		{
			if (assertNotNull(origen) && assertNotNull(destino)) {
				viajes=PersistenceFactory.newTripDao().
						findNextOpenAndFreeSeats();
				for (Trip t: viajes){
					if (t.getClosingDate().after(new Date())){
						viajesProximos.add(t);
					}
				}
			}
			else if (!assertNotNull(origen) && assertNotNull(destino)){
				viajes=PersistenceFactory.newTripDao().findByOrigen(origen);
				for (Trip t: viajes){
					if (t.getClosingDate().after(new Date())){
						viajesProximos.add(t);
					}
				}
			}
			else if (assertNotNull(origen) && !assertNotNull(destino)){
				
				viajes=PersistenceFactory.newTripDao().findByDestino(destino);
				for (Trip t: viajes){
					if (t.getClosingDate().after(new Date())){
						viajesProximos.add(t);
					}
				}
			}
			else {
				viajes=PersistenceFactory.newTripDao().findByOrigenAndDestino(origen, destino);
				
				for (Trip t: viajes){
					if (t.getClosingDate().after(new Date())){
						viajesProximos.add(t);
					}
				}
			}
			
			for (Trip t : viajesProximos)
			{
				participantes.put(t.getId(), PersistenceFactory.newUserDao()
						.findByTrip(t.getId()));
			}
			
			request.setAttribute("listaViajes", viajesProximos);
			Log.debug("Obtenida lista de viajes conteniendo [%d] viajes",
					viajes.size());
			
			
			request.setAttribute("mapParticipantes", participantes);
			Log.debug("Obtenida listas de usuarios");//

		} catch (Exception e)
		{
			Log.error("Error calculando los viajes y/o los participantes [%s]",
					usuario.getLogin());
//			throw e;
		}
		return "EXITO";
	}
	
	private boolean assertNotNull(String str) {
		return str == null || str.isEmpty();
	}

	@Override
	public String toString()
	{
		return getClass().getName();
	}

}
