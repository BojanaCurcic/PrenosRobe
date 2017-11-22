package com.prenosrobe.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.prenosrobe.data.ClaimerOffer;
import com.prenosrobe.data.DriverOffer;
import com.prenosrobe.data.Impression;
import com.prenosrobe.data.OfferStatus;
import com.prenosrobe.data.Station;
import com.prenosrobe.data.User;

public class HibernateUtil
{
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	/**
	 * Instantiate a new HibernateUtil.
	 */
	private HibernateUtil() {}

	/**
	 * Build the session factory.
	 *
	 * @return session factory
	 */
	private static SessionFactory buildSessionFactory()
	{
		try
		{
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration config = new Configuration().configure()
					.addAnnotatedClass(OfferStatus.class).addAnnotatedClass(User.class)
					.addAnnotatedClass(Impression.class).addAnnotatedClass(DriverOffer.class)
					.addAnnotatedClass(Station.class).addAnnotatedClass(ClaimerOffer.class);

			ServiceRegistry registry = new ServiceRegistryBuilder()
					.applySettings(config.getProperties()).buildServiceRegistry();

			return config.buildSessionFactory(registry);
		} catch (Throwable ex)
		{
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Get the session factory.
	 *
	 * @return session factory
	 */
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	/**
	 * Shutdown.
	 */
	public static void shutdown()
	{
		// Close caches and connection pools
		getSessionFactory().close();
	}
}
