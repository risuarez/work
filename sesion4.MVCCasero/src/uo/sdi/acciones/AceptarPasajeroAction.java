package uo.sdi.acciones;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
import alb.util.log.Log;

public class AceptarPasajeroAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String result = "FRACASO";

		HttpSession session = request.getSession();
		User usuario = ((User) session.getAttribute("user"));
		List<String> logMessages = new ArrayList<String>();
		Long viajeId = Long.parseLong(request.getParameter("viajeId"));
		Long userId = Long.parseLong(request.getParameter("userId"));
		Trip trip = PersistenceFactory.newTripDao().findById(viajeId);
		
		if (usuario.getId().equals(userId)){
		
			Log.debug(
					"El usuario [%s] no puede aceptarse a sí mismo"
					+ "en un viaje",
					usuario.getLogin());
			return "FRACASO";
			
			
		}
		 else {
		try{
		
		Seat seat = new Seat(userId, viajeId, "", SeatStatus.ACCEPTED);
		SeatDao dao = PersistenceFactory.newSeatDao();
		dao.save(seat);
		trip.setAvailablePax(trip.getAvailablePax() - 1);
		
		for (String message : logMessages)
			Log.debug(message);
		request.setAttribute("isAccepted",
							"Se ha cancelado el viaje correctamente");
					result = "EXITO";
				} catch (Exception e) {
					Log.error(
							"Algo ha ocurrido confirmando la solicitud"
							+ " del usuario [%s]",
							userId.toString());
					System.out.println(viajeId);
					request.setAttribute("notAccepted",
							"Error aceptando solicitud");
				}
			}
		
		return result;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
