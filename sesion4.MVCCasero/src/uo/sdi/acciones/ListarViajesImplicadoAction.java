package uo.sdi.acciones;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class ListarViajesImplicadoAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		List<Trip> viajesPromotor;
		List<Trip> viajesParticipante;
		List<Trip> viajesInteresado;
		List<Trip> viajesInteresadoFiltrados;

		HttpSession session = request.getSession();
		User usuario = ((User) session.getAttribute("user"));
		
		String origen = request.getParameter("origen");
		String destino = request.getParameter("destino");

		try {
			// Obteniendo viajes que ha ofertado el usuario
			viajesPromotor = PersistenceFactory.newTripDao().findByPromoterId(
					usuario.getId());

			request.setAttribute("listaViajesPromotor", viajesPromotor);
			Log.debug("Obtenida lista de viajes del promotor,"
					+ " conteniendo [%d] viajes", viajesPromotor.size());

			// Obteniendo viajes en los que participa el usuario
			viajesParticipante = PersistenceFactory.newTripDao()
					.findByUserAndStatus(usuario.getId(), SeatStatus.ACCEPTED.ordinal());

			request.setAttribute("listaViajesParticipante", viajesParticipante);
			Log.debug("Obtenida lista de viajes en los que participa,"
					+ " conteniendo [%d] viajes", viajesPromotor.size());

			// Obteniendo viajes en los que el usuario se ha interesado
			
			viajesInteresado = PersistenceFactory.newTripDao()
					.findByUserInterested(usuario.getId());
			
			if (assertNotNull(origen) && assertNotNull(destino)) {
				viajesInteresadoFiltrados = viajesInteresado;
				
			}
			else if (!assertNotNull(origen) && assertNotNull(destino)){
				viajesInteresadoFiltrados = new ArrayList<Trip>();
				for (Trip t : viajesInteresado) {
					if (t.getDeparture().getCity().equals(origen)){
						viajesInteresadoFiltrados.add(t);
					}
				}
				
			}
			else if (assertNotNull(origen) && !assertNotNull(destino)){
				viajesInteresadoFiltrados = new ArrayList<Trip>();
				for (Trip t : viajesInteresado) {
					if (t.getDestination().getCity().equals(destino)){
						viajesInteresadoFiltrados.add(t);
					}
				}
			}
			else {
				viajesInteresadoFiltrados = new ArrayList<Trip>();
				for (Trip t : viajesInteresado) {
					if (t.getDeparture().getCity().equals(origen)
							&& t.getDestination().getCity().equals(destino) ){
						viajesInteresadoFiltrados.add(t);
					}
				}
			}
			
			
			

			request.setAttribute("listaViajesInteresado",
					viajesInteresadoFiltrados);
			Log.debug("Obtenida lista de viajes en los que el usuario "
					+ "está interesado, conteniendo [%d] viajes",
					viajesInteresadoFiltrados.size());

		} catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo las listas de viajes");
		}
		return "EXITO";
	}
	
	private boolean assertNotNull(String str) {
		return str == null || str.isEmpty();
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
