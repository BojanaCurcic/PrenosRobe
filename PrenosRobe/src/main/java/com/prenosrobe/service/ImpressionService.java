package com.prenosrobe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenosrobe.dao.ImpressionDao;
import com.prenosrobe.data.Impression;

@Service
public class ImpressionService
{
	@Autowired
	private static ImpressionDao impressionDao;

	private static ImpressionService instance = new ImpressionService();

	/**
	 * Instantiate a new ImpressionService.
	 */
	private ImpressionService()
	{
	}

	/**
	 * Get the single instance of ImpressionService.
	 *
	 * @return single instance of ImpressionService
	 */
	public static ImpressionService getInstance()
	{
		impressionDao = ImpressionDao.getInstance();
		return instance;
	}

	/**
	 * Send impression.
	 *
	 * @param impression impression
	 */
	public void sendImpression(final Impression impression)
	{
		try
		{
			if (validateImpression(impression))
			{
				impressionDao.openCurrentSessionWithTransaction();
				impressionDao.sendImpression(impression);
				impressionDao.closeCurrentSessionWithTransaction();
			}
			else
			{
				throw new Exception("Invalid data entered!");
			}
		} catch (Exception e)
		{
			if (impressionDao.getCurrentTransaction().isActive())
				impressionDao.getCurrentTransaction().rollback();
		} finally
		{
			if (impressionDao.getCurrentSession().isConnected())
				impressionDao.closeCurrentSession();
		}
	}

	/**
	 * Get the impression by impression id.
	 *
	 * @param impressionId impression id
	 * @return impression by impression id
	 */
	public Impression getImpressionByImpressionId(final int impressionId)
	{
		impressionDao.openCurrentSession();
		Impression impression = impressionDao.getImpressionByImpressionId(impressionId);
		impressionDao.closeCurrentSession();

		return impression;
	}

	/**
	 * Get the impressions by user id.
	 *
	 * @param userId user id
	 * @return impressions by user id
	 */
	public List<Impression> getImpressionsByUserId(final int userId)
	{
		impressionDao.openCurrentSession();
		List<Impression> impressions = impressionDao.getImpressionsByUserId(userId);
		impressionDao.closeCurrentSession();

		return impressions;
	}

	/**
	 * Validate impression.
	 *
	 * @param impression impression
	 * @return true, if successful
	 */
	private boolean validateImpression(final Impression impression)
	{
		// TODO: add logic!
		if (impression == null)
			return false;

		return true;
	}
}
