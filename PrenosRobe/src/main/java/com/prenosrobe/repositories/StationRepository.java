package com.prenosrobe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.Station;

public interface StationRepository extends CrudRepository<Station, Integer>
{
	List<Station> findAll();
	
	Station findByName(String name);
}
