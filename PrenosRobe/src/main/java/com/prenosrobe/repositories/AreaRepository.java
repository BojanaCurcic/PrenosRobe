package com.prenosrobe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.Area;

public interface AreaRepository extends CrudRepository<Area, Integer>
{
	List<Area> findAll();
}
