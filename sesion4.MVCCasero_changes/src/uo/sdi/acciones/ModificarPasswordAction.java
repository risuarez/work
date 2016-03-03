package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

public class ModificarPasswordAction implements Accion
{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response)
	{
		String result = "FRACASO";

		String oldPassword = request.getParameter("oldPassword");
		String newPassword1 = request.getParameter("newPassword1");
		String newPassword2 = request.getParameter("newPassword2");

		HttpSession session = request.getSession();
		User usuario = ((User) session.getAttribute("user"));

		if (oldPassword.isEmpty() || newPassword1.isEmpty()
				|| newPassword2.isEmpty())
		{
			request.setAttribute("wrongUpdatePassword",
					"Debe rellenar los 3 campos");
			Log.debug(
					"El usuario [%s] no ha rellando los 3 campos al actualizar contraseña",
					usuario.getLogin());
		} else
		{
			if (newPassword1.equals(newPassword2)) // comprobar que las
													// contraseñas sean iguales
			{
				if (usuario.getPassword().equals(oldPassword))
				{
					usuario.setPassword(newPassword1);

					try
					{
						UserDao dao = PersistenceFactory.newUserDao();
						dao.update(usuario);
						Log.debug("Modificada contraseña de [%s]",
								usuario.getLogin());
						request.setAttribute("correctUpdatePassword",
								"Contraseña actualizada con exito");
						result = "EXITO";
					} catch (Exception e)
					{
						Log.error(
								"Algo ha ocurrido actualizando la contraseña de [%s]",
								usuario.getLogin());
						request.setAttribute("wrongUpdatePassword",
								"Error actualizando la contraseña");
					}
				} else
					request.setAttribute("wrongUpdatePassword",
							"Contraseña antigua incorrecta");
				Log.debug(
						"El usuario [%s] ha insertado mal la contraseña al intentar actualizar",
						usuario.getLogin());
			} else
				request.setAttribute("wrongUpdatePassword",
						"Las nuevas contraseñas deben ser iguales");
			Log.debug(
					"El usuario [%s] no ha escrito dos contraseñas iguales para actualizar contraseña",
					usuario.getLogin());
		}

		return result;
	}

	@Override
	public String toString()
	{
		return getClass().getName();
	}

}
