package com.prenosrobe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenosrobe.dao.DriverOfferDao;
import com.prenosrobe.data.DriverOffer;

@Service
public class DriverOfferService
{
	@Autowired
	private static DriverOfferDao driverOfferDao;

	private static DriverOfferService instance = new DriverOfferService();

	/**
	 * Instantiate a new DriverOfferService.
	 */
	private DriverOfferService()
	{
	}

	/**
	 * Get the single instance of DriverOfferService.
	 *
	 * @return single instance of DriverOfferService
	 */
	public static DriverOfferService getInstance()
	{
		driverOfferDao = DriverOfferDao.getInstance();
		return instance;
	}

	/**
	 * Send driver offer.
	 *
	 * @param driverOffer driver offer
	 */
	public void sendDriverOffer(final DriverOffer driverOffer)
	{
		try
		{
			if (validateDriverOffer(driverOffer))
			{
				driverOfferDao.openCurrentSessionWithTransaction();
				driverOfferDao.sendDriverOffer(driverOffer);
				driverOfferDao.closeCurrentSessionWithTransaction();
			}
			else
			{
				throw new Exception("Invalid data entered!");
			}
		} catch (Exception e)
		{
			if (driverOfferDao.getCurrentTransaction().isActive())
				driverOfferDao.getCurrentTransaction().rollback();
		} finally
		{
			if (driverOfferDao.getCurrentSession().isConnected())
				driverOfferDao.closeCurrentSession();
		}
	}

	/**
	 * Get all driver offers.
	 *
	 * @return all driver offers
	 */
	public List<DriverOffer> getDriverOffers()
	{
		driverOfferDao.openCurrentSession();
		List<DriverOffer> driverOffers = driverOfferDao.getDriverOffers();
		driverOfferDao.closeCurrentSession();

		return driverOffers;
	}

	/**
	 * Get the driver offer by driver offer id.
	 *
	 * @param id id
	 *
	 * @return driver offer by driver offer id
	 */
	public DriverOffer getDriverOfferById(final int driverOfferId)
	{
		driverOfferDao.openCurrentSession();
		DriverOffer driverOffer = driverOfferDao.getDriverOfferById(driverOfferId);
		driverOfferDao.closeCurrentSession();

		return driverOffer;
	}

	/**
	 * Get the driver offers by driver id.
	 *
	 * @param driverId  driver id
	 * @return driver offers by driver id
	 */
	public List<DriverOffer> getDriverOffersByDriverId(final int driverId)
	{
		driverOfferDao.openCurrentSession();
		List<DriverOffer> driverOffers = driverOfferDao.getDriverOffersByDriverId(driverId);
		driverOfferDao.closeCurrentSession();

		return driverOffers;
	}

	/**
	 * Get the driver offers by claimer id.
	 *
	 * @param claimerId claimer id
	 * @return driver offers by claimer user id
	 */
	public List<DriverOffer> getDriverOffersByClaimerId(final int claimerId)
	{
		driverOfferDao.openCurrentSession();
		List<DriverOffer> driverOffers = driverOfferDao.getDriverOffersByClaimerId(claimerId);
		driverOfferDao.closeCurrentSession();

		return driverOffers;
	}

	/**
	 * Delete driver offer by driver offer id.
	 *
	 * @param driverOfferId driver offer id
	 */
	public void deleteDriverOffer(final int driverOfferId)
	{
		try
		{
			driverOfferDao.openCurrentSessionWithTransaction();
			driverOfferDao.deleteDriverOffer(driverOfferId);
			driverOfferDao.closeCurrentSessionWithTransaction();
		} catch (Exception e)
		{
			if (driverOfferDao.getCurrentTransaction().isActive())
				driverOfferDao.getCurrentTransaction().rollback();
		} finally
		{
			if (driverOfferDao.getCurrentSession().isConnected())
				driverOfferDao.closeCurrentSession();
		}
	}

	/**
	 * Update driver offer.
	 *
	 * @param driverOffer driver offer
	 */
	public void updateDriverOffer(final DriverOffer driverOffer)
	{
		try
		{
			if (validateDriverOffer(driverOffer))
			{
				driverOfferDao.openCurrentSessionWithTransaction();
				driverOfferDao.updateDriverOffer(driverOffer);
				driverOfferDao.closeCurrentSessionWithTransaction();
			}
			else
			{
				throw new Exception("Invalid data entered!");
			}
		} catch (Exception e)
		{
			if (driverOfferDao.getCurrentTransaction().isActive())
				driverOfferDao.getCurrentTransaction().rollback();
		} finally
		{
			if (driverOfferDao.getCurrentSession().isConnected())
				driverOfferDao.closeCurrentSession();
		}
	}

	/**
	 * Update driver offer activity.
	 *
	 * @param driverOffer driver offer
	 * @param isActive is active
	 */
	public void updateDriverOfferActivity(final DriverOffer driverOffer, final boolean isActive)
	{
		try
		{
			if (validateDriverOffer(driverOffer))
			{
				driverOfferDao.openCurrentSessionWithTransaction();
				driverOfferDao.updateDriverOfferActivity(driverOffer, isActive);
				driverOfferDao.closeCurrentSessionWithTransaction();
			}
			else
			{
				throw new Exception("Invalid data entered!");
			}
		} catch (Exception e)
		{
			if (driverOfferDao.getCurrentTransaction().isActive())
				driverOfferDao.getCurrentTransaction().rollback();
		} finally
		{
			if (driverOfferDao.getCurrentSession().isConnected())
				driverOfferDao.closeCurrentSession();
		}
	}

	/**
	 * Validate driver offer.
	 *
	 * @param driverOffer driver offer
	 * @return true, if successful
	 */
	private boolean validateDriverOffer(final DriverOffer driverOffer)
	{
		// TODO: add logic!
		if (driverOffer == null)
			return false;

		return true;
	}
}
