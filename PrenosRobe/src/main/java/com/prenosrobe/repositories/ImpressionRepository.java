package com.prenosrobe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.Impression;

public interface ImpressionRepository extends CrudRepository<Impression, Integer>
{
	List<Impression> findByUserId(Integer userId);
}
