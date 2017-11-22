package com.prenosrobe.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.prenosrobe.data.Impression;
import com.prenosrobe.data.User;

@Repository
public class ImpressionDao extends AbstractDao
{
	private static ImpressionDao instance = new ImpressionDao();

	/**
	 * Instantiate a new ImpressionDao.
	 */
	private ImpressionDao()
	{
	}

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
	 * Get the impression by impression id.
	 *
	 * @param id id
	 * @return impression by impression id
	 */
	public Impression getImpressionByImpressionId(final int impressionId)
	{
		return (Impression) getCurrentSession().get(Impression.class, impressionId);
	}

	/**
	 * Get the impressions by user id.
	 *
	 * @param userId user id
	 * @return impressions by user id
	 */
	public List<Impression> getImpressionsByUserId(final int userId)
	{
		List<Impression> impressions = new ArrayList<>();

		User user = (User) getCurrentSession().get(User.class, userId);
		if (user == null)
			return impressions;

		String sql = "SELECT * FROM impression WHERE user_id = ?;";

		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		if (query == null)
			return impressions;

		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.setInteger(0, userId).list();
		rows.forEach(row -> {
			Impression impression = new Impression(row[2].toString(),
					Integer.valueOf(row[3].toString()), userId);
			impression.setId(Integer.valueOf(row[0].toString()));
			impression.setCreatedAt((Date) row[1]);
			impression.setUser(user);

			impressions.add(impression);
		});

		user.setImpressions(impressions);

		return impressions;
	}
}
