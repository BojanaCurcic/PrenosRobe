package com.prenosrobe.dao;

import org.springframework.stereotype.Repository;

import com.prenosrobe.data.OfferStatus;

@Repository
public class OfferStatusDao extends AbstractDao
{
	private static OfferStatusDao instance = new OfferStatusDao();

	/**
	 * Get the single instance of OfferStatusDao.
	 *
	 * @return single instance of OfferStatusDao
	 */
	public static OfferStatusDao getInstance()
	{
		return instance;
	}

	/**
	 * Send offer status.
	 *
	 * @param offerStatus offer status
	 */
	public void sendOfferStatus(final OfferStatus offerStatus)
	{
		getCurrentSession().save(offerStatus);
	}

	/**
	 * Get the offer status by id.
	 *
	 * @param id id
	 * @return offer status by id
	 */
	public OfferStatus getOfferStatusById(final int id)
	{
		return (OfferStatus) getCurrentSession().get(OfferStatus.class, id);
	}
}
