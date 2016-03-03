package uo.sdi.acciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.AddressPoint;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.Waypoint;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;
import alb.util.log.Log;

public class ModificarViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

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
		Long viajeId = null;
		User usuario = ((User) session.getAttribute("user"));
		List<String> logMessages = new ArrayList<String>();

		if (nuevaDireccionOrigen == null || nuevaCiudadOrigen == null
				|| nuevoEstadoOrigen == null || nuevoPaisOrigen == null
				|| nuevaPostalOrigen == null || nuevaFechaOrigen == null
				|| nuevaHoraOrigen == null || nuevaDireccionDestino == null
				|| nuevaCiudadDestino == null || nuevoEstadoDestino == null
				|| nuevoPaisDestino == null || nuevaPostalDestino == null
				|| nuevaFechaDestino == null || nuevaHoraDestino == null
				|| nuevaFechaLimite == null || nuevoPlazasMaximo == null
				|| nuevoPlazasLibres == null || nuevoCoste == null
				|| nuevaDescripcion == null) {
			return "FRACASO";
		}

		if (nuevaDireccionOrigen.isEmpty() || nuevaCiudadOrigen.isEmpty()
				|| nuevoEstadoOrigen.isEmpty() || nuevoPaisOrigen.isEmpty()
				|| nuevaPostalOrigen.isEmpty() || nuevaFechaOrigen.isEmpty()
				|| nuevaHoraOrigen.isEmpty() || nuevaDireccionDestino.isEmpty()
				|| nuevaCiudadDestino.isEmpty() || nuevoEstadoDestino.isEmpty()
				|| nuevoPaisDestino.isEmpty() || nuevaPostalDestino.isEmpty()
				|| nuevaFechaDestino.isEmpty() || nuevaHoraDestino.isEmpty()
				|| nuevaFechaLimite.isEmpty() || nuevoPlazasMaximo.isEmpty()
				|| nuevoPlazasLibres.isEmpty() || nuevoCoste.isEmpty()
				|| nuevaDescripcion.isEmpty()) {
			Log.info("Campo/s vacio/s al intentar modificar viaje por el"
					+ " usuario [%s]", usuario.getLogin());
			request.setAttribute("wrongRegistrarViaje", "Debe rellenar todos "
					+ "los campos (excepto latitud y longitud");
			result = "FRACASO";
		} else {
			Date dateOrigen;
			Date dateDestino;
			Date dateLimite;
			int intPlazasMaximo;
			int intPlazasLibres;
			double doubleCoste;

			try {
				dateOrigen = convertDate(nuevaFechaOrigen, nuevaHoraOrigen);
				dateDestino = convertDate(nuevaFechaDestino, nuevaHoraDestino);
				dateLimite = convertDate(nuevaFechaLimite, "23:59");
			} catch (Exception e) {
				request.setAttribute("wrongModificarViaje",
						"Error: formato de fecha y/o hora incorrecto)");
				Log.info("Error con el formato fecha registrar "
						+ "viaje del usuario [%s]", usuario.getLogin());
				return "FRACASO";
			}
			try {
				intPlazasMaximo = Integer.parseInt(nuevoPlazasMaximo);
				intPlazasLibres = Integer.parseInt(nuevoPlazasLibres);
				doubleCoste = Double.parseDouble(nuevoCoste);
			} catch (Exception e) {
				request.setAttribute("wrongModificarViaje",
						"Error: formato de plazas y/o coste");
				Log.info("Error con el formato plazas y/o coste al registrar"
						+ "viaje del usuario [%s]", usuario.getLogin());
				return "FRACASO";
			}
			if (dateOrigen.after(dateDestino) || dateLimite.after(dateOrigen)
					|| dateOrigen.before(new Date())
					|| dateDestino.before(new Date())
					|| dateLimite.before(new Date())) {
				request.setAttribute("wrongModificarViaje",
						"Error: la fecha de origen debe ser posterior a "
								+ "la fecha limite, la fecha de destino "
								+ "posterior a la fecha de origen y todas las "
								+ "fechas posteriores al dia de hoy.");
				Log.info("Fecha de destino o de origen posterior a "
						+ "fecha de origen o limite [%s]", usuario.getLogin());
				result = "FRACASO";
			} else if (intPlazasMaximo < intPlazasLibres
					|| intPlazasMaximo <= 0 || intPlazasLibres <= 0
					|| doubleCoste <= 0) {
				request.setAttribute("wrongModificarViaje",
						"Error: Numero de plazas y coste deben ser mayor que 0"
								+ " además numero de plazas maximo debe ser "
								+ "mayor que numero de plazas libres");
				Log.info("Numero de plazas y/o coste <0 o maximo de plazas "
						+ "menor que num plzas libres, por el usuario"
						+ " [%s]", usuario.getLogin());
				result = "FRACASO";
			} else {
				AddressPoint addressOrigen = new AddressPoint(
						nuevaDireccionOrigen, nuevaCiudadOrigen,
						nuevoEstadoOrigen, nuevoPaisOrigen, nuevaPostalOrigen);
				AddressPoint addressDestino = new AddressPoint(
						nuevaDireccionDestino, nuevaCiudadDestino,
						nuevoEstadoDestino, nuevoPaisDestino,
						nuevaPostalDestino);
				if (!(nuevaLatOrigen.isEmpty() || nuevaLongOrigen.isEmpty()
						|| nuevaLatDestino.isEmpty() || nuevaLongDestino
							.isEmpty())) {
					try {
						Double doubleLatOrigen = Double
								.parseDouble(nuevaLatOrigen);
						Double doubleLongOrigen = Double
								.parseDouble(nuevaLongOrigen);
						Double doubleLatDestino = Double
								.parseDouble(nuevaLatDestino);
						Double doubleLongDestino = Double
								.parseDouble(nuevaLongDestino);
						addressOrigen.setWaypoint(new Waypoint(doubleLatOrigen,
								doubleLongOrigen));
						addressDestino.setWaypoint(new Waypoint(
								doubleLatDestino, doubleLongDestino));
					} catch (Exception e) {
						request.setAttribute("wrongModificarViaje",
								"Error: formato de latitud y/o logintud");
						Log.info(
								"Error con el formato langitud y/o latitud al "
										+ "registrar viaje del usuario [%s]",
								usuario.getLogin());
						return "FRACASO";
					}
				} else {
					addressOrigen.setWaypoint(new Waypoint(0.0, 0.0));
					addressDestino.setWaypoint(new Waypoint(0.0, 0.0));
				}
				try {
					viajeId = Long.parseLong(request.getParameter("viajeId"));
					Trip trip = PersistenceFactory.newTripDao().findById(
							viajeId);
					//request.setAttribute("trip", trip);
					trip.setDeparture(addressOrigen);
					trip.setDestination(addressDestino);
					trip.setArrivalDate(dateDestino);
					trip.setDepartureDate(dateOrigen);
					trip.setClosingDate(dateLimite);
					trip.setAvailablePax(intPlazasLibres);
					trip.setMaxPax(intPlazasMaximo);
					trip.setEstimatedCost(doubleCoste);
					trip.setComments(nuevaDescripcion);
					TripDao dao = PersistenceFactory.newTripDao();
					dao.update(trip);
					for (String message : logMessages)
						Log.debug(message);
					request.setAttribute("corrrectDataUpdate",
							"Datos actualizados con éxito");
					result = "EXITO";
				} catch (Exception e) {
					Log.error(
							"Algo ha ocurrido actualizando"
							+ " la información de [%s]",
							viajeId.toString());
					request.setAttribute("wrongDatatUpdate",
							"Error actualizando los datos");
				}
			}
		}
		return result;
	}
	
	private Date convertDate(String fecha, String hora) throws ParseException
	{
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return formateador.parse(fecha + " " + hora);
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
