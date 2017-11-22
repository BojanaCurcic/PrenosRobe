package com.prenosrobe.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.prenosrobe.data.Impression;
import com.prenosrobe.data.User;

@Repository
public class UserDao extends AbstractDao
{
	private static UserDao instance = new UserDao();

	/**
	 * Instantiate a new UserDao.
	 */
	private UserDao()
	{
	}

	/**
	 * Get the single instance of UserDao.
	 *
	 * @return single instance of UserDao
	 */
	public static UserDao getInstance()
	{
		return instance;
	}

	/**
	 * Send user.
	 *
	 * @param user user
	 */
	public void sendUser(final User user)
	{
		getCurrentSession().save(user);
	}

	/**
	 * Get the user by user id.
	 *
	 * @param userId user id
	 * @return user by user id
	 */
	public User getUserById(final int userId)
	{
		User user = (User) getCurrentSession().get(User.class, userId);

		if (user != null)
		{
			List<Impression> impressions = new ArrayList<>();

			String sql = "SELECT * FROM impression WHERE user_id = ?;";

			SQLQuery query = getCurrentSession().createSQLQuery(sql);
			if (query == null)
				return user;

			@SuppressWarnings("unchecked")
			List<Object[]> rows = query.setInteger(0, userId).list();
			rows.forEach(row -> {
				Impression impression = new Impression(row[2].toString(),
						Integer.valueOf(row[3].toString()), user.getId());
				impression.setId(Integer.valueOf(row[0].toString()));
				impression.setCreatedAt((Date) row[1]);
				impression.setUser(user);

				impressions.add(impression);
			});

			user.setImpressions(impressions);
		}

		return user;
	}

	/**
	 * Update user.
	 *
	 * @param user user
	 */
	public void updateUser(final User user)
	{
		String sql = "UPDATE user SET name = ? WHERE user_id = ?";
		getCurrentSession().createSQLQuery(sql).setString(0, user.getName())
				.setInteger(1, user.getId()).executeUpdate();
	}

	/**
	 * Update user activity.
	 *
	 * @param userId user id
	 * @param isActive is active
	 */
	public void updateUserActivity(final int userId, final boolean isActive)
	{
		String sql = "UPDATE user SET active = ? WHERE user_id = ?";
		getCurrentSession().createSQLQuery(sql).setBoolean(0, isActive).setInteger(1, userId)
				.executeUpdate();
	}
}
