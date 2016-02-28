package uo.sdi.acciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.AddressPoint;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.model.Waypoint;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;
import alb.util.log.Log;

public class RegistrarViajeAction implements Accion
{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response)
	{
		String resultado = "EXITO";

		String direccionOrigen = request.getParameter("direccionOrigen");
		String ciudadOrigen = request.getParameter("ciudadOrigen");
		String estadoOrigen = request.getParameter("estadoOrigen");
		String paisOrigen = request.getParameter("paisOrigen");
		String postalOrigen = request.getParameter("postalOrigen");
		String latOrigen = request.getParameter("latOrigen");
		String longOrigen = request.getParameter("longOrigen");
		String fechaOrigen = request.getParameter("fechaOrigen");
		String horaOrigen = request.getParameter("horaOrigen");

		String direccionDestino = request.getParameter("direccionDestino");
		String ciudadDestino = request.getParameter("ciudadDestino");
		String estadoDestino = request.getParameter("estadoDestino");
		String paisDestino = request.getParameter("paisDestino");
		String postalDestino = request.getParameter("postalDestino");
		String latDestino = request.getParameter("latDestino");
		String longDestino = request.getParameter("longDestino");
		String fechaDestino = request.getParameter("fechaDestino");
		String horaDestino = request.getParameter("horaDestino");

		String fechaLimite = request.getParameter("fechaLimite");
		String plazasMaximo = request.getParameter("plazasMaximo");
		String plazasLibres = request.getParameter("plazasLibres");
		String coste = request.getParameter("coste");
		String descripcion = request.getParameter("descripcion");

		HttpSession session = request.getSession();
		User usuario = ((User) session.getAttribute("user"));

		if (direccionOrigen.isEmpty() || ciudadOrigen.isEmpty()
				|| estadoOrigen.isEmpty() || paisOrigen.isEmpty()
				|| postalOrigen.isEmpty() || fechaOrigen.isEmpty()
				|| horaOrigen.isEmpty() || direccionDestino.isEmpty()
				|| ciudadDestino.isEmpty() || estadoDestino.isEmpty()
				|| paisDestino.isEmpty() || postalDestino.isEmpty()
				|| fechaDestino.isEmpty() || horaDestino.isEmpty()
				|| fechaLimite.isEmpty() || plazasMaximo.isEmpty()
				|| plazasLibres.isEmpty() || coste.isEmpty()
				|| descripcion.isEmpty())
		{
			Log.info("Campo/s vacio/s al intentar registrar viaje por el"
					+ " usuario [%s]", usuario.getLogin());
			request.setAttribute("wrongRegistrarViaje", "Debe rellenar todos "
					+ "los campos (excepto latitud y longitud");
			resultado = "FRACASO";
		} else
		{
			Date dateOrigen;
			Date dateDestino;
			Date dateLimite;
			int intPlazasMaximo;
			int intPlazasLibres;
			double doubleCoste;

			try
			{
				dateOrigen = convertDate(fechaOrigen, horaOrigen);
				dateDestino = convertDate(fechaDestino, horaDestino);
				dateLimite = convertDate(fechaLimite, "23:59");
			} catch (Exception e)
			{
				request.setAttribute("wrongRegistrarViaje",
						"Error: formato de fecha y/o hora incorrecto)");
				Log.info("Error con el formato fecha registrar "
						+ "viaje del usuario [%s]", usuario.getLogin());
				return "FRACASO";
			}

			try
			{
				intPlazasMaximo = Integer.parseInt(plazasMaximo);
				intPlazasLibres = Integer.parseInt(plazasLibres);
				doubleCoste = Double.parseDouble(coste);
			} catch (Exception e)
			{
				request.setAttribute("wrongRegistrarViaje",
						"Error: formato de plazas y/o coste");
				Log.info("Error con el formato plazas y/o coste al registrar"
						+ "viaje del usuario [%s]", usuario.getLogin());
				return "FRACASO";
			}

			if (dateOrigen.after(dateDestino) || dateLimite.after(dateOrigen)
					|| dateOrigen.before(new Date())
					|| dateDestino.before(new Date())
					|| dateLimite.before(new Date()))
			{
				request.setAttribute("wrongRegistrarViaje",
						"Error: la fecha de origen debe ser posterior a "
								+ "la fecha limite, la fecha de destino "
								+ "posterior a la fecha de origen y todas las "
								+ "fechas posteriores al dia de hoy.");
				Log.info("Fecha de destino o de origen posterior a "
						+ "fecha de origen o limite [%s]", usuario.getLogin());
				resultado = "FRACASO";
			} else if (intPlazasMaximo <= intPlazasLibres
					|| intPlazasMaximo <= 0 || intPlazasLibres <= 0
					|| doubleCoste <= 0)
			{
				request.setAttribute("wrongRegistrarViaje",
						"Error: Numero de plazas y coste deben ser mayor que 0"
								+ " además numero de plazas maximo debe ser "
								+ "mayor que numero de plazas libres");
				Log.info("Numero de plazas y/o coste <0 o maximo de plazas "
						+ "menor que num plzas libres, por el usuario"
						+ " [%s]", usuario.getLogin());
				resultado = "FRACASO";
			} else
			{
				AddressPoint addressOrigen = new AddressPoint(direccionOrigen,
						ciudadOrigen, estadoOrigen, paisOrigen, postalOrigen);
				AddressPoint addressDestino = new AddressPoint(
						direccionDestino, ciudadDestino, estadoDestino,
						paisDestino, postalDestino);
				if (!(latOrigen.isEmpty() || longOrigen.isEmpty()
						|| latDestino.isEmpty() || longDestino.isEmpty()))
				{
					try
					{
						Double doubleLatOrigen = Double.parseDouble(latOrigen);
						Double doubleLongOrigen = Double
								.parseDouble(longOrigen);
						Double doubleLatDestino = Double
								.parseDouble(latDestino);
						Double doubleLongDestino = Double
								.parseDouble(longDestino);
						addressOrigen.setWaypoint(new Waypoint(doubleLatOrigen,
								doubleLongOrigen));
						addressDestino.setWaypoint(new Waypoint(
								doubleLatDestino, doubleLongDestino));
					} catch (Exception e)
					{
						request.setAttribute("wrongRegistrarViaje",
								"Error: formato de latitud y/o logintud");
						Log.info(
								"Error con el formato langitud y/o latitud al "
										+ "registrar viaje del usuario [%s]",
								usuario.getLogin());
						return "FRACASO";
					}
				} else
				{
					addressOrigen.setWaypoint(new Waypoint(0.0, 0.0));
					addressDestino.setWaypoint(new Waypoint(0.0, 0.0));
				}
				
				Trip newTrip = new Trip(addressOrigen, addressDestino,
						dateDestino, dateOrigen, dateLimite, intPlazasLibres,
						intPlazasMaximo, doubleCoste, descripcion,
						TripStatus.OPEN, usuario.getId());
				TripDao dao = PersistenceFactory.newTripDao();
				dao.save(newTrip);
				Log.info("Se ha registrado un viaje por el usuario [%s]",
						usuario.getLogin());
				request.setAttribute("correctRegistrarViaje",
						"Viaje registrado con éxito");
			}

		}
		return resultado;
	}

	private Date convertDate(String fecha, String hora) throws ParseException
	{
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return formateador.parse(fecha + " " + hora);
	}

	@Override
	public String toString()
	{
		return getClass().getName();
	}
}
