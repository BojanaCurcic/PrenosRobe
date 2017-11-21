package com.prenosrobe.dao;

import org.springframework.stereotype.Repository;

import com.prenosrobe.data.Impression;

@Repository
public class ImpressionDao extends AbstractDao
{
	private static ImpressionDao instance = new ImpressionDao();

	/**
	 * Get the single instance of ImpressionDao.
	 *
	 * @return single instance of ImpressionDao
	 */
	public static ImpressionDao getInstance()
	{
		return instance;
	}

	/**
	 * Send impression.
	 *
	 * @param impression impression
	 */
	public void sendImpression(final Impression impression)
	{
		getCurrentSession().save(impression);
	}

	/**
	 * Get the impression by id.
	 *
	 * @param id id
	 * @return impression by id
	 */
	public Impression getImpressionById(final int id)
	{
		return (Impression) getCurrentSession().get(Impression.class, id);
	}
}
