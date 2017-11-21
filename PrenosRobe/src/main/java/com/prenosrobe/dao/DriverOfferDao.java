package com.prenosrobe.dao;

import org.springframework.stereotype.Repository;

import com.prenosrobe.data.DriverOffer;

@Repository
public class DriverOfferDao extends AbstractDao
{
	private static DriverOfferDao instance = new DriverOfferDao();

	/**
	 * Get the single instance of DriverOfferDao.
	 *
	 * @return single instance of DriverOfferDao
	 */
	public static DriverOfferDao getInstance()
	{
		return instance;
	}

	/**
	 * Send driver offer.
	 *
	 * @param driverOffer driver offer
	 */
	public void sendDriverOffer(final DriverOffer driverOffer)
	{
		getCurrentSession().save(driverOffer);
		driverOffer.setStationsDriverOfferId();
	}

	/**
	 * Get the driver offer by id.
	 *
	 * @param id id
	 * @return driver offer by id
	 */
	public DriverOffer getDriverOfferById(final int id)
	{
		return (DriverOffer) getCurrentSession().get(DriverOffer.class, id);
	}
}
