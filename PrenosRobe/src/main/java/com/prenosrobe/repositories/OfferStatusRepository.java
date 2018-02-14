package com.prenosrobe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.OfferStatus;

public interface OfferStatusRepository extends CrudRepository<OfferStatus, Integer>
{
	List<OfferStatus> findAll();

	OfferStatus findByName(String name);
}
