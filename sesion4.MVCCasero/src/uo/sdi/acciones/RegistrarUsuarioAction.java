package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

public class RegistrarUsuarioAction implements Accion
{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response)
	{

		String resultado = "EXITO";

		String login = request.getParameter("login");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");

		HttpSession session = request.getSession();

		if (session.getAttribute("user") == null)
		{

			UserDao dao = PersistenceFactory.newUserDao();
			User userWithSameLogin = dao.findByLogin(login);

			if (userWithSameLogin == null)// comprobar que el usuario no exista
			{
				if (password1.equals(password2)) // comprobar que las contraseñas sean iguales
				{
					User newUser = new User(login, password1, nombre,
							apellidos, email);
					dao.save(newUser);
					Log.info("Se ha registrado el usuario [%s]", login);
					session.setAttribute("user", newUser);
				} else
				{
					session.invalidate();
					Log.info(
							"Las contraseñas del registro del usuario [%s] no son iguales",
							login);
					resultado = "FRACASO";
				}
			} else
			{
				session.invalidate();
				Log.info("Ya existe un usuario con login [%s]", login);
				resultado = "FRACASO";
			}

		} else
		{
			Log.info("Se ha intentado regitrar teniendo sesion iniciada como [%s]",
					((User) session.getAttribute("user")).getLogin());
			//session.invalidate();
			resultado = "FRACASO";
		}
		return resultado;
	}

}
