package uo.sdi.acciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class ListarViajesAction implements Accion
{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response)
	{

		List<Trip> viajes;
<<<<<<< HEAD
		List<Trip> viajesProximos = new ArrayList<Trip>();
		
		
		try {
			
				viajes=PersistenceFactory.newTripDao().
						findNextOpenAndFreeSeats();
				
				for (Trip t: viajes){
					if (t.getClosingDate().after(new Date())){
						viajesProximos.add(t);
					}
				}
			
			
			request.setAttribute("listaViajes", viajesProximos);
			Log.debug("Obtenida lista de viajes conteniendo [%d] viajes", viajes.size());
		}
		catch (Exception e) {
=======

		String origen = request.getParameter("origen");
		String destino = request.getParameter("destino");

		try
		{
			if (assertNotNull(origen) || assertNotNull(destino))
			{
				viajes = PersistenceFactory.newTripDao().findAll();
			} else if (!assertNotNull(origen) || assertNotNull(destino))
			{
				viajes = PersistenceFactory.newTripDao().findByOrigen(origen);
			} else if (assertNotNull(origen) || !assertNotNull(destino))
			{
				viajes = PersistenceFactory.newTripDao().findByDestino(destino);
			} else
			{
				viajes = PersistenceFactory.newTripDao()
						.findByOrigenAndDestino(origen, destino);
			}

			request.setAttribute("listaViajes", viajes);
			Log.debug("Obtenida lista de viajes conteniendo [%d] viajes",
					viajes.size());
		} catch (Exception e)
		{
>>>>>>> origin/master
			Log.error("Algo ha ocurrido obteniendo lista de viajes");
		}
		return "EXITO";
	}

<<<<<<< HEAD
	
	
=======
	private boolean assertNotNull(String str)
	{
		return str == null || str.trim().length() == 0;
	}

>>>>>>> origin/master
	@Override
	public String toString()
	{
		return getClass().getName();
	}

}
