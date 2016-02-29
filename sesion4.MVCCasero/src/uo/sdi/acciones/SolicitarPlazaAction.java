package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Application;
import uo.sdi.model.User;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class SolicitarPlazaAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		

		HttpSession session = request.getSession();
		User usuario = ((User) session.getAttribute("user"));
		
		try {
			Long idViaje = Long.parseLong(request.getParameter("viajeId"));
			Application newApplication = new Application(usuario.getId(),idViaje);
			ApplicationDao dao = PersistenceFactory.newApplicationDao();
			dao.save(newApplication);
			Log.debug("Apuntado el usuario [%s] al viaje [%l]", usuario.getLogin(),idViaje);
		}
		catch (Exception e) {
			Log.error("Error solicitando plaza de viaje");
			return "FRACASO";
		}
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
