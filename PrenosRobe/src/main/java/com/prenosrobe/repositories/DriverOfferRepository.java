package com.prenosrobe.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.DriverOffer;
import com.prenosrobe.data.UserVehicle;

public interface DriverOfferRepository extends CrudRepository<DriverOffer, Integer>
{
	List<DriverOffer> findAll();

	List<DriverOffer> findByUserVehicle(UserVehicle userVehicle);

	@Query(value = "SELECT * FROM driver_offer WHERE user_vehicle_id = ? AND date = ? AND departure_location = ? AND arrival_location = ?;", nativeQuery = true)
	DriverOffer findByUserIdDateAndLocations(Integer userVehicleId, Date date,
			String departureLocation, String arrivalLocation);
}
