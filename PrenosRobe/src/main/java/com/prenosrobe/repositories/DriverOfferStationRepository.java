package com.prenosrobe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.DriverOfferStation;

public interface DriverOfferStationRepository extends CrudRepository<DriverOfferStation, Integer>
{
	List<DriverOfferStation> findAll();
}
