package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class ListarViajesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Trip> viajes;
		
		String origen = request.getParameter("origen");
		String destino = request.getParameter("destino");
		
		try {
			if (assertNotNull(origen) || assertNotNull(destino)) {
				viajes=PersistenceFactory.newTripDao().findAll();
			}
			else if (!assertNotNull(origen) || assertNotNull(destino)){
				viajes=PersistenceFactory.newTripDao().findByOrigen(origen);
			}
			else if (assertNotNull(origen) || !assertNotNull(destino)){
				viajes=PersistenceFactory.newTripDao().findByDestino(destino);
			}
			else {
				viajes=PersistenceFactory.newTripDao().findByOrigenAndDestino(origen, destino);
			}
			
			request.setAttribute("listaViajes", viajes);
			Log.debug("Obtenida lista de viajes conteniendo [%d] viajes", viajes.size());
		}
		catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo lista de viajes");
		}
		return "EXITO";
	}

	private boolean assertNotNull(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
