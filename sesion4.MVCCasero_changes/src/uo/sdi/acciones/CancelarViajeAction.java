package uo.sdi.acciones;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;
import alb.util.log.Log;

public class CancelarViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String result = "FRACASO";

		HttpSession session = request.getSession();
		Long viajeId = null;
		User usuario = ((User) session.getAttribute("user"));
		List<String> logMessages = new ArrayList<String>();

		
		viajeId = Long.parseLong(request.getParameter("viajeId"));
		Trip trip = PersistenceFactory.newTripDao().findById(
		viajeId);
		
		if (trip.getArrivalDate().before(new Date()) ||
			trip.getDepartureDate().before(new Date())){
			request.setAttribute("notCancelled",
					"No se ha podido cancelar el viaje [%s],"
					+ " su fecha ya ha pasado "
			+viajeId.toString());
			Log.debug(
					"El usuario [%s] ha intentado cancelar un viaje"
					+ " que ya ha pasado",
					usuario.getLogin());
			return "FRACASO";
			
			
		}
		else if (trip.getStatus().equals(TripStatus.DONE)
				|| trip.getStatus().equals(TripStatus.CLOSED)){
			
			request.setAttribute("notCancelled",
					"No se ha podido cancelar el viaje [%s], ya se ha efectuado"
					+ "o ya estaba cancelado previamente "
			+viajeId.toString());
			Log.debug(
					"El usuario [%s] ha intentado cancelar un viaje"
					+ " que ya ha pasado",
					usuario.getLogin());
			return "FRACASO";
		} else {
		try{
		trip.setStatus(TripStatus.CANCELLED);
					
		TripDao dao = PersistenceFactory.newTripDao();
		dao.update(trip);
		for (String message : logMessages)
			Log.debug(message);
		request.setAttribute("isCancelled",
							"Se ha cancelado el viaje correctamente");
					result = "EXITO";
				} catch (Exception e) {
					Log.error(
							"Algo ha ocurrido cancelando el viaje"
							+ " [%s]",
							viajeId.toString());
					request.setAttribute("notCancelled",
							"Error cancelando viaje");
				}
			}
		
		return result;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
