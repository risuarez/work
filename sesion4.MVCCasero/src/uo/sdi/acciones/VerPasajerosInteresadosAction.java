package uo.sdi.acciones;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class VerPasajerosInteresadosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		Long viajeId = null;
		User usuario = ((User) session.getAttribute("user"));
		List<String> logMessages = new ArrayList<String>();
		List<User> usuarios;

		
		viajeId = Long.parseLong(request.getParameter("viajeId"));
		Trip trip = PersistenceFactory.newTripDao().findById(
		viajeId);
		
		if (trip.getAvailablePax() <= 0){
			Log.debug("El número de plazas disponibles"
					+ " para este viaje es <= 0");
			return "FRACASO";
		} else {
			try {
				
				usuarios = PersistenceFactory.newUserDao().
						findByTripInterestedIn(viajeId);
				List<User> usuariosFiltrados = new ArrayList<User>();
				
				for (User usr : usuarios){
					if(findApplicantsAccepted(usr.getId(), viajeId) == null){
						usuariosFiltrados.add(usr);
					}
				}
				
				request.setAttribute("listaUsuarios", usuariosFiltrados);
				System.out.println(usuariosFiltrados.size());
				request.setAttribute("tripId", viajeId);
				for (String message : logMessages)
					Log.debug(message);
				
				return "EXITO";
			} catch (Exception e) {
				Log.error(
						"Algo ha ocurrido cancelando el viaje"
						+ " [%s] por el usuario [%s]",
						viajeId.toString(), usuario.getLogin());
			}
			
		}
		
//		try {
//			if (assertNotNull(origen) || assertNotNull(destino)) {
//				viajes=PersistenceFactory.newTripDao().findAll();
//			}
//			else if (!assertNotNull(origen) || assertNotNull(destino)){
//				viajes=PersistenceFactory.newTripDao().findByOrigen(origen);
//			}
//			else if (assertNotNull(origen) || !assertNotNull(destino)){
//				viajes=PersistenceFactory.newTripDao().findByDestino(destino);
//			}
//			else {
//				viajes=PersistenceFactory.newTripDao().findByOrigenAndDestino(origen, destino);
//			}
//			
//			request.setAttribute("listaViajes", viajes);
//			Log.debug("Obtenida lista de viajes conteniendo [%d] viajes", viajes.size());
//		}
//		catch (Exception e) {
//			Log.error("Algo ha ocurrido obteniendo lista de viajes");
//		}
		return "EXITO";
	}

	
	
	private User findApplicantsAccepted(Long id, Long viajeId) {
		
		return PersistenceFactory.newUserDao().findUserAccepted(id, viajeId);
		
	}



	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
