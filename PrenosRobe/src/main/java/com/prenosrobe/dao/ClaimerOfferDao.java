package com.prenosrobe.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.prenosrobe.data.ClaimerOffer;
import com.prenosrobe.data.DriverOffer;
import com.prenosrobe.data.Impression;
import com.prenosrobe.data.OfferStatus;
import com.prenosrobe.data.User;

@Repository
public class ClaimerOfferDao extends AbstractDao
{
	private static ClaimerOfferDao instance = new ClaimerOfferDao();

	/**
	 * Instantiate a new ClaimerOfferDao.
	 */
	private ClaimerOfferDao()
	{
	}

	/**
	 * Get the single instance of ClaimerOfferDao.
	 *
	 * @return single instance of ClaimerOfferDao
	 */
	public static ClaimerOfferDao getInstance()
	{
		return instance;
	}

	/**
	 * Send claimer offer.
	 *
	 * @param claimerOffer claimer offer
	 */
	public void sendClaimerOffer(final ClaimerOffer claimerOffer)
	{
		getCurrentSession().save(claimerOffer);
	}

	/**
	 * Get the claimer offer by id.
	 *
	 * @param id id
	 * @return claimer offer by id
	 */
	public ClaimerOffer getClaimerOfferById(final int id)
	{
		ClaimerOffer claimerOffer = (ClaimerOffer) getCurrentSession().get(ClaimerOffer.class, id);

		if (claimerOffer != null)
			setTransientClaimerOfferFields(claimerOffer);

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
		List<ClaimerOffer> claimerOffers = new ArrayList<>();

		DriverOffer driverOffer = getDriverOfferById(driverOfferId);
		if (driverOffer == null)
			return claimerOffers;

		String sql = "SELECT * FROM claimer_offer WHERE driver_offer_id = ?;";

		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		if (query == null)
			return claimerOffers;

		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.setInteger(0, driverOfferId).list();
		rows.forEach(row -> {
			ClaimerOffer claimerOffer = new ClaimerOffer(row[2].toString(), row[3].toString(),
					row[4].toString(), Integer.valueOf(row[6].toString()), driverOfferId,
					Integer.valueOf(row[8].toString()));
			claimerOffer.setId(Integer.valueOf(row[0].toString()));
			claimerOffer.setCreatedAt((Date) row[1]);
			if (row[5] != null)
				claimerOffer.setPhoto(row[5].toString());

			setTransientClaimerOfferFields(claimerOffer);

			claimerOffers.add(claimerOffer);
		});

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
		List<ClaimerOffer> claimerOffers = new ArrayList<>();

		User user = getUserById(claimerId);
		if (user == null)
			return claimerOffers;

		String sql = "SELECT * FROM claimer_offer WHERE user_id = ?;";

		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		if (query == null)
			return claimerOffers;

		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.setInteger(0, claimerId).list();
		rows.forEach(row -> {
			ClaimerOffer claimerOffer = new ClaimerOffer(row[2].toString(), row[3].toString(),
					row[4].toString(), claimerId, Integer.valueOf(row[7].toString()),
					Integer.valueOf(row[8].toString()));
			claimerOffer.setId(Integer.valueOf(row[0].toString()));
			claimerOffer.setCreatedAt((Date) row[1]);
			if (row[5] != null)
				claimerOffer.setPhoto(row[5].toString());

			setTransientClaimerOfferFields(claimerOffer);

			claimerOffers.add(claimerOffer);
		});

		return claimerOffers;
	}

	/**
	 * Set the transient claimer offer fields, which are user, driverOffer and offerStatus.
	 *
	 * @param claimerOffer claimer offer
	 */
	private void setTransientClaimerOfferFields(final ClaimerOffer claimerOffer)
	{
		claimerOffer.setUser(getUserById(claimerOffer.getUserId()));
		claimerOffer.setOfferStatus(getOfferStatusById(claimerOffer.getOfferStatusId()));
		claimerOffer.setDriverOffer(getDriverOfferById(claimerOffer.getDriverOfferId()));
	}

	/**
	 * Get the user by user id.
	 *
	 * @param userId user id
	 * @return user by user id
	 */
	private User getUserById(final int userId)
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
	 * Get the offer status by id.
	 *
	 * @param offerStatusId offer status id
	 * @return offer status by id
	 */
	private OfferStatus getOfferStatusById(final int offerStatusId)
	{
		return (OfferStatus) getCurrentSession().get(OfferStatus.class, offerStatusId);
	}

	/**
	 * Get the driver offer by id.
	 *
	 * @param driverOfferId driver offer id
	 * @return driver offer by id
	 */
	private DriverOffer getDriverOfferById(final int driverOfferId)
	{
		return (DriverOffer) getCurrentSession().get(DriverOffer.class, driverOfferId);
	}

	/**
	 * Delete claimer offer.
	 *
	 * @param claimerOfferId claimer offer id
	 */
	public void deleteClaimerOffer(final int claimerOfferId)
	{
		String sql = "DELETE FROM claimer_offer WHERE claimer_offer_id = ?";
		getCurrentSession().createSQLQuery(sql).setInteger(0, claimerOfferId).executeUpdate();
	}

	/**
	 * Update claimer offer status.
	 *
	 * @param claimerOfferStatus claimer offer status
	 * @param offerStatusId offer status id
	 */
	public void updateClaimerOfferStatus(final int claimerOfferId, final int offerStatusId)
	{
		String sql = "UPDATE claimer_offer SET offer_status_id = ? WHERE claimer_offer_id = ?";
		getCurrentSession().createSQLQuery(sql).setInteger(0, offerStatusId)
				.setInteger(1, claimerOfferId).executeUpdate();
	}
}
