package uo.sdi.persistence;

import java.util.Date;
import java.util.List;

import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.persistence.util.GenericDao;

public interface TripDao extends GenericDao<Trip, Long> {

	Trip findByPromoterIdAndArrivalDate(Long id, Date arrivalDate);
	List<Trip> findByPromoterId(Long id);
	List<Trip> findByUserAndStatus(Long id, int status);
	List<Trip> findByUserInterested(Long userId);
	List<Trip> findByOrigen(String origen);
	List<Trip> findByDestino(String destino);
	List<Trip> findByOrigenAndDestino(String origen, String destino);

}
