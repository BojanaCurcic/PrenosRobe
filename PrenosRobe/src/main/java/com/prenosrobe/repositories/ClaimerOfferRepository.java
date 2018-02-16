package com.prenosrobe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.ClaimerOffer;

public interface ClaimerOfferRepository extends CrudRepository<ClaimerOffer, Integer>
{
	List<ClaimerOffer> findByUserId(Integer userId);

	List<ClaimerOffer> findByDriverOfferId(Integer driverOfferId);
}
