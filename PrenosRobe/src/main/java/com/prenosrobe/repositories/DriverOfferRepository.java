package com.prenosrobe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.DriverOffer;
import com.prenosrobe.data.UserVehicle;

public interface DriverOfferRepository extends CrudRepository<DriverOffer, Integer>
{
	List<DriverOffer> findAll();

	List<DriverOffer> findByUserVehicle(UserVehicle userVehicle);
}
