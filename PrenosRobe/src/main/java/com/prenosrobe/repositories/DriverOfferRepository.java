package com.prenosrobe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.DriverOffer;

public interface DriverOfferRepository extends CrudRepository<DriverOffer, Integer>
{
	List<DriverOffer> findAll();

	List<DriverOffer> findByUserVehicleId(Integer userVehicleId);
}
