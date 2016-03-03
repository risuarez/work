package uo.sdi.persistence;

import java.util.List;

import uo.sdi.model.User;
import uo.sdi.persistence.util.GenericDao;

public interface UserDao extends GenericDao<User, Long>{

	User findByLogin(String login);

	List<User> findByTrip(Long id);

	List<User> findByTripInterestedIn(Long viajeId);

}
