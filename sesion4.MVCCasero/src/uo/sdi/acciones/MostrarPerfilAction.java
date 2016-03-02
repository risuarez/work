package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uo.sdi.model.Rating;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class MostrarPerfilAction implements Accion
{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response)
	{

		Long idUsuario = null;
		try
		{
			idUsuario = Long.parseLong(request.getParameter("idUsuario"));

			User userInfo = PersistenceFactory.newUserDao().findById(idUsuario);
			request.setAttribute("userInfo", userInfo);
			Log.info("Obtenidos datos del usuario [%s]", userInfo.getLogin());

			List<Rating> listaRatings = PersistenceFactory.newRatingDao()
					.findByAboutUser(idUsuario);
			request.setAttribute("userRatings", listaRatings);
			Log.info("Obtenidas [%d] opiniones del usuario [%s]",
					listaRatings.size(), userInfo.getLogin());
		} catch (Exception e)
		{
			request.setAttribute("wrongResult",
					"No es posible mostrar los datos y/o opiniones del usuario"
							+ idUsuario.toString());
			Log.error("Error obteniendo datos de " + idUsuario);
			throw e;
		}

		return "EXITO";
	}

	@Override
	public String toString()
	{
		return getClass().getName();
	}

}
