package com.prenosrobe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.ClaimerOffer;
import com.prenosrobe.data.DriverOffer;

public interface ClaimerOfferRepository extends CrudRepository<ClaimerOffer, Integer>
{
	List<ClaimerOffer> findByUserId(Integer userId);

	List<ClaimerOffer> findByDriverOffer(DriverOffer driverOffer);
}
