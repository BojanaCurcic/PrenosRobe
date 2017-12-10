package com.prenosrobe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenosrobe.dao.UserDao;
import com.prenosrobe.data.User;

@Service
public class UserService
{
	@Autowired
	private static UserDao userDao;

	private static UserService instance = new UserService();

	/**
	 * Instantiate a new UserService.
	 */
	private UserService()
	{
	}

	/**
	 * Get the single instance of UserService.
	 *
	 * @return single instance of UserService
	 */
	public static UserService getInstance()
	{
		userDao = UserDao.getInstance();
		return instance;
	}

	/**
	 * Send user.
	 *
	 * @param user user
	 */
	public void sendUser(final User user)
	{
		try
		{
			if (validateUser(user))
			{
				userDao.openCurrentSessionWithTransaction();
				userDao.sendUser(user);
				userDao.closeCurrentSessionWithTransaction();
			}
			else
			{
				throw new Exception("Invalid data entered!");
			}
		} catch (Exception e)
		{
			if (userDao.getCurrentTransaction().isActive())
				userDao.getCurrentTransaction().rollback();
		} finally
		{
			if (userDao.getCurrentSession().isConnected())
				userDao.closeCurrentSession();
		}
	}

	/**
	 * Get the user by user id.
	 *
	 * @param userId user id
	 * @return user by user id
	 */
	public User getUserById(final int userId)
	{
		userDao.openCurrentSession();
		User user = userDao.getUserById(userId);
		userDao.closeCurrentSession();

		return user;
	}

	/**
	 * Update user.
	 *
	 * @param user user
	 */
	public void updateUser(final User user)
	{
		try
		{
			if (validateUser(user))
			{
				userDao.openCurrentSessionWithTransaction();
				userDao.updateUser(user);
				userDao.closeCurrentSessionWithTransaction();
			}
			else
			{
				throw new Exception("Invalid data entered!");
			}
		} catch (Exception e)
		{
			if (userDao.getCurrentTransaction().isActive())
				userDao.getCurrentTransaction().rollback();
		} finally
		{
			if (userDao.getCurrentSession().isConnected())
				userDao.closeCurrentSession();
		}
	}

	/**
	 * Update user activity.
	 *
	 * @param userId user id
	 * @param isActive is active
	 */
	public void updateUserActivity(final int userId, final boolean isActive)
	{
		try
		{
			userDao.openCurrentSessionWithTransaction();
			userDao.updateUserActivity(userId, isActive);
			userDao.closeCurrentSessionWithTransaction();
		} catch (Exception e)
		{
			if (userDao.getCurrentTransaction().isActive())
				userDao.getCurrentTransaction().rollback();
		} finally
		{
			if (userDao.getCurrentSession().isConnected())
				userDao.closeCurrentSession();
		}
	}

	/**
	 * Validate user.
	 *
	 * @param user user
	 * @return true, if successful
	 */
	private boolean validateUser(final User user)
	{
		// TODO: add logic!
		if (user == null)
			return false;

		return true;
	}
}
