package com.prenosrobe.dao;

import org.springframework.stereotype.Repository;

import com.prenosrobe.data.User;

@Repository
public class UserDao extends AbstractDao
{
	private static UserDao instance = new UserDao();

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
	 * Get the user by id.
	 *
	 * @param id id
	 * @return user by id
	 */
	public User getUserById(final int id)
	{
		return (User) getCurrentSession().get(User.class, id);
	}
}
