package com.prenosrobe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer>
{
	Vehicle findByRegistrationNumber(String registrationNumber);
}
