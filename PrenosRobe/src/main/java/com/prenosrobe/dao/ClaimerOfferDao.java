package com.prenosrobe.dao;

import org.springframework.stereotype.Repository;

import com.prenosrobe.data.ClaimerOffer;

@Repository
public class ClaimerOfferDao extends AbstractDao
{
	private static ClaimerOfferDao instance = new ClaimerOfferDao();

	/**
	 * Get the single instance of ClaimerOfferDao.
	 *
	 * @return single instance of ClaimerOfferDao
	 */
	public static ClaimerOfferDao getInstance()
	{
		return instance;
	}

	/**
	 * Send claimer offer.
	 *
	 * @param claimerOffer claimer offer
	 */
	public void sendClaimerOffer(final ClaimerOffer claimerOffer)
	{
		getCurrentSession().save(claimerOffer);
	}

	/**
	 * Get the claimer offer by id.
	 *
	 * @param id id
	 * @return claimer offer by id
	 */
	public ClaimerOffer getClaimerOfferById(final int id)
	{
		return (ClaimerOffer) getCurrentSession().get(ClaimerOffer.class, id);
	}
}
