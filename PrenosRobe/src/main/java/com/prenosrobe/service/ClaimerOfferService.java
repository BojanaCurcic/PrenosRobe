package com.prenosrobe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenosrobe.dao.ClaimerOfferDao;
import com.prenosrobe.data.ClaimerOffer;

@Service
public class ClaimerOfferService
{
	@Autowired
	private static ClaimerOfferDao claimerOfferDao;

	private static ClaimerOfferService instance = new ClaimerOfferService();

	/**
	 * Instantiate a new ClaimerOfferService.
	 */
	private ClaimerOfferService()
	{
	}

	/**
	 * Get the single instance of ClaimerOfferService.
	 *
	 * @return single instance of ClaimerOfferService
	 */
	public static ClaimerOfferService getInstance()
	{
		claimerOfferDao = ClaimerOfferDao.getInstance();
		return instance;
	}

	/**
	 * Send claimer offer.
	 *
	 * @param claimerOffer claimer offer
	 */
	public void sendClaimerOffer(final ClaimerOffer claimerOffer)
	{
		try
		{
			if (validateClaimerOffer(claimerOffer))
			{
				claimerOfferDao.openCurrentSessionWithTransaction();
				claimerOfferDao.sendClaimerOffer(claimerOffer);
				claimerOfferDao.closeCurrentSessionWithTransaction();
			}
			else
			{
				throw new Exception("Invalid data entered!");
			}
		} catch (Exception e)
		{
			if (claimerOfferDao.getCurrentTransaction().isActive())
				claimerOfferDao.getCurrentTransaction().rollback();
		} finally
		{
			if (claimerOfferDao.getCurrentSession().isConnected())
				claimerOfferDao.closeCurrentSession();
		}
	}

	/**
	 * Get the claimer offer by id.
	 *
	 * @param id id
	 * @return claimer offer by id
	 */
	public ClaimerOffer getClaimerOfferById(final int id)
	{
		claimerOfferDao.openCurrentSession();
		ClaimerOffer claimerOffer = claimerOfferDao.getClaimerOfferById(id);
		claimerOfferDao.closeCurrentSession();

		return claimerOffer;
	}

	/**
	 * Get the claimer offers by driver offer id.
	 *
	 * @param driverOfferId driver offer id
	 * @return claimer offers by driver offer id
	 */
	public List<ClaimerOffer> getClaimerOffersByDriverOfferId(final int driverOfferId)
	{
		claimerOfferDao.openCurrentSession();
		List<ClaimerOffer> claimerOffers = claimerOfferDao
				.getClaimerOffersByDriverOfferId(driverOfferId);
		claimerOfferDao.closeCurrentSession();

		return claimerOffers;
	}

	/**
	 * Get the claimer offers by claimer id.
	 *
	 * @param claimerId claimer id
	 * @return claimer offers by claimer id
	 */
	public List<ClaimerOffer> getClaimerOffersByClaimerId(final int claimerId)
	{
		claimerOfferDao.openCurrentSession();
		List<ClaimerOffer> claimerOffers = claimerOfferDao.getClaimerOffersByClaimerId(claimerId);
		claimerOfferDao.closeCurrentSession();

		return claimerOffers;
	}

	/**
	 * Delete claimer offer.
	 *
	 * @param claimerOfferId claimer offer id
	 */
	public void deleteClaimerOffer(final int claimerOfferId)
	{
		try
		{
			claimerOfferDao.openCurrentSessionWithTransaction();
			claimerOfferDao.deleteClaimerOffer(claimerOfferId);
			claimerOfferDao.closeCurrentSessionWithTransaction();
		} catch (Exception e)
		{
			if (claimerOfferDao.getCurrentTransaction().isActive())
				claimerOfferDao.getCurrentTransaction().rollback();
		} finally
		{
			if (claimerOfferDao.getCurrentSession().isConnected())
				claimerOfferDao.closeCurrentSession();
		}
	}

	/**
	 * Update claimer offer status.
	 *
	 * @param claimerOfferStatus claimer offer status
	 * @param offerStatusId offer status id
	 */
	public void updateClaimerOfferStatus(final int claimerOfferId, final int offerStatusId)
	{
		try
		{
			claimerOfferDao.openCurrentSessionWithTransaction();
			claimerOfferDao.updateClaimerOfferStatus(claimerOfferId, offerStatusId);
			claimerOfferDao.closeCurrentSessionWithTransaction();
		} catch (Exception e)
		{
			if (claimerOfferDao.getCurrentTransaction().isActive())
				claimerOfferDao.getCurrentTransaction().rollback();
		} finally
		{
			if (claimerOfferDao.getCurrentSession().isConnected())
				claimerOfferDao.closeCurrentSession();
		}
	}

	/**
	 * Validate claimer offer.
	 *
	 * @param claimerOffer the claimer offer
	 * @return true, if successful
	 */
	private boolean validateClaimerOffer(final ClaimerOffer claimerOffer)
	{
		// TODO: add logic!
		if (claimerOffer == null)
			return false;

		return true;
	}
}
