package com.prenosrobe.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.prenosrobe.persistence.HibernateUtil;

public class AbstractDao
{
	private Session currentSession;
	private Transaction currentTransaction;

	/**
	 * Open current session.
	 *
	 * @return current session
	 */
	public Session openCurrentSession()
	{
		currentSession = HibernateUtil.getSessionFactory().openSession();
		return currentSession;
	}

	/**
	 * Open current sessionWith transaction.
	 *
	 * @return current session
	 */
	public Session openCurrentSessionWithTransaction()
	{
		currentSession = HibernateUtil.getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	/**
	 * Close current session.
	 */
	public void closeCurrentSession()
	{
		currentSession.close();
	}

	/**
	 * Close current session with transaction.
	 */
	public void closeCurrentSessionWithTransaction()
	{
		currentTransaction.commit();
		currentSession.close();
	}

	/**
	 * Get the current session.
	 *
	 * @return current session
	 */
	public Session getCurrentSession()
	{
		return currentSession;
	}

	/**
	 * Set the current session.
	 *
	 * @param currentSession new current session
	 */
	public void setCurrentSession(final Session currentSession)
	{
		this.currentSession = currentSession;
	}

	/**
	 * Get the current transaction.
	 *
	 * @return current transaction
	 */
	public Transaction getCurrentTransaction()
	{
		return currentTransaction;
	}

	/**
	 * Set the current transaction.
	 *
	 * @param currentTransaction new current transaction
	 */
	public void setCurrentTransaction(final Transaction currentTransaction)
	{
		this.currentTransaction = currentTransaction;
	}
}
