package uo.sdi.acciones;

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

		HttpSession session = request.getSession();
		User usuario = ((User) session.getAttribute("user"));

		try {
			// Obteniendo viajes que ha ofertado el usuario
			viajesPromotor = PersistenceFactory.newTripDao().findByPromoterId(
					usuario.getId());

			request.setAttribute("listaViajesPromotor", viajesPromotor);
			Log.debug("Obtenida lista de viajes del promotor,"
					+ " conteniendo [%d] viajes", viajesPromotor.size());

			// Obteniendo viajes en los que participa el usuario
			viajesParticipante = PersistenceFactory.newTripDao()
					.findByUserAndStatus(usuario.getId(), SeatStatus.ACCEPTED);

			request.setAttribute("listaViajesParticipante", viajesParticipante);
			Log.debug("Obtenida lista de viajes en los que participa,"
					+ " conteniendo [%d] viajes", viajesPromotor.size());

			// Obteniendo viajes en los que el usuario se ha interesado
			viajesInteresado = PersistenceFactory.newTripDao()
					.findByUserInterested(usuario.getId());

			request.setAttribute("listaViajesInteresado", viajesInteresado);
			Log.debug("Obtenida lista de viajes en los que el usuario "
					+ "está interesado, conteniendo [%d] viajes",
					viajesPromotor.size());

		} catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo las listas de viajes");
		}
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
