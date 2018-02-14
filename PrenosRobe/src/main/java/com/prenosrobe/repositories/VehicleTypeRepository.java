package com.prenosrobe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.VehicleType;

public interface VehicleTypeRepository extends CrudRepository<VehicleType, Integer>
{
	List<VehicleType> findAll();
	
	VehicleType findByName(String name);
}
