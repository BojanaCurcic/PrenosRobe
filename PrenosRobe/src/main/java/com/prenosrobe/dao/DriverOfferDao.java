package com.prenosrobe.dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.prenosrobe.data.ClaimerOffer;
import com.prenosrobe.data.DriverOffer;
import com.prenosrobe.data.Impression;
import com.prenosrobe.data.OfferStatus;
import com.prenosrobe.data.Station;
import com.prenosrobe.data.User;

@Repository
public class DriverOfferDao extends AbstractDao
{
	private static DriverOfferDao instance = new DriverOfferDao();

	/**
	 * Instantiate a new DriverOfferDao.
	 */
	private DriverOfferDao()
	{
	}

	/**
	 * Get the single instance of DriverOfferDao.
	 *
	 * @return single instance of DriverOfferDao
	 */
	public static DriverOfferDao getInstance()
	{
		return instance;
	}

	/**
	 * Send driver offer.
	 *
	 * @param driverOffer driver offer
	 */
	public void sendDriverOffer(final DriverOffer driverOffer)
	{
		getCurrentSession().save(driverOffer);
		driverOffer.setStationsDriverOfferId();
	}

	/**
	 * Get all driver offers.
	 *
	 * @return all driver offers
	 */
	public List<DriverOffer> getDriverOffers()
	{
		List<DriverOffer> driverOffers = new ArrayList<>();

		String sql = "SELECT * FROM driver_offer;";

		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		if (query == null)
			return driverOffers;

		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
		rows.forEach(row -> {
			DriverOffer driverOffer = new DriverOffer(row[2].toString(), row[3].toString(),
					(Date) row[4], Integer.valueOf(row[6].toString()));
			driverOffer.setId(Integer.valueOf(row[0].toString()));
			driverOffer.setCreatedAt((Date) row[1]);
			if (row[5] != null)
				driverOffer.setTime((Time) row[5]);
			driverOffer.setActive(Boolean.valueOf(row[7].toString()));

			setTransientDriverOfferFields(getUserById(driverOffer.getUserId()), driverOffer);

			driverOffers.add(driverOffer);
		});

		return driverOffers;
	}

	/**
	 * Get the driver offer by driver offer id.
	 *
	 * @param id id
	 *
	 * @return driver offer by driver offer id
	 */
	public DriverOffer getDriverOfferById(final int driverOfferId)
	{
		DriverOffer driverOffer = (DriverOffer) getCurrentSession().get(DriverOffer.class,
				driverOfferId);

		if (driverOffer != null)
			setTransientDriverOfferFields(getUserById(driverOffer.getUserId()), driverOffer);

		return driverOffer;
	}

	/**
	 * Get the driver offers by driver id.
	 *
	 * @param driverId  driver id
	 * @return driver offers by driver id
	 */
	public List<DriverOffer> getDriverOffersByDriverId(final int driverId)
	{
		List<DriverOffer> driverOffers = new ArrayList<>();

		User user = getUserById(driverId);
		if (user == null)
			return driverOffers;

		String sql = "SELECT * FROM driver_offer WHERE user_id = ?;";

		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		if (query == null)
			return driverOffers;

		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.setInteger(0, driverId).list();
		rows.forEach(row -> {
			DriverOffer driverOffer = new DriverOffer(row[2].toString(), row[3].toString(),
					(Date) row[4], driverId);
			driverOffer.setId(Integer.valueOf(row[0].toString()));
			driverOffer.setCreatedAt((Date) row[1]);
			if (row[5] != null)
				driverOffer.setTime((Time) row[5]);
			driverOffer.setActive(Boolean.valueOf(row[7].toString()));

			setTransientDriverOfferFields(user, driverOffer);

			driverOffers.add(driverOffer);
		});

		return driverOffers;
	}

	/**
	 * Get the driver offers by claimer id.
	 *
	 * @param claimerId claimer id
	 * @return driver offers by claimer user id
	 */
	public List<DriverOffer> getDriverOffersByClaimerId(final int claimerId)
	{
		List<DriverOffer> driverOffers = new ArrayList<>();

		String sql = "SELECT claimer_offer.driver_offer_id FROM claimer_offer "
				+ "WHERE user_id = ?;";

		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		if (query == null)
			return driverOffers;

		@SuppressWarnings("unchecked")
		List<Integer> rows = query.setInteger(0, claimerId).list();
		rows.forEach(row -> {
			DriverOffer driverOffer = getDriverOfferById(row);

			driverOffers.add(driverOffer);
		});

		return driverOffers;
	}

	/**
	 * Set the transient driver offer fields, which are user, stations and claimerOffers.
	 *
	 * @param user user
	 * @param driverOffer driver offer
	 */
	private void setTransientDriverOfferFields(final User user, final DriverOffer driverOffer)
	{
		driverOffer.setUser(user);
		driverOffer.setStations(getStationsByDriverOffer(driverOffer));
		driverOffer.setClaimerOffers(getClaimerOffersByDriverOffer(driverOffer));
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
	 * Get the claimer offers by driver offer.
	 *
	 * @param driverOffer driver offer
	 * @return claimer offers by driver offer
	 */
	private List<ClaimerOffer> getClaimerOffersByDriverOffer(final DriverOffer driverOffer)
	{
		List<ClaimerOffer> claimerOffers = new ArrayList<>();

		String sql = "SELECT * FROM claimer_offer WHERE driver_offer_id = ?;";

		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		if (query == null)
			return claimerOffers;

		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.setInteger(0, driverOffer.getId()).list();
		rows.forEach(row -> {
			ClaimerOffer claimerOffer = new ClaimerOffer(row[2].toString(), row[3].toString(),
					row[4].toString(), Integer.valueOf(row[6].toString()), driverOffer.getId(),
					Integer.valueOf(row[8].toString()));
			claimerOffer.setId(Integer.valueOf(row[0].toString()));
			claimerOffer.setCreatedAt((Date) row[1]);
			if (row[5] != null)
				claimerOffer.setPhoto(row[5].toString());

			OfferStatus offerStatus = (OfferStatus) getCurrentSession().get(OfferStatus.class,
					claimerOffer.getOfferStatusId());
			claimerOffer.setOfferStatus(offerStatus);
			claimerOffer.setUser(getUserById(claimerOffer.getUserId()));
			claimerOffer.setDriverOffer(driverOffer);

			claimerOffers.add(claimerOffer);
		});

		return claimerOffers;
	}

	/**
	 * Get stations by driver offer.
	 *
	 * @param driverOffer driver offer
	 * @return stations by driver offer
	 */
	private List<Station> getStationsByDriverOffer(final DriverOffer driverOffer)
	{
		List<Station> stations = new ArrayList<>();

		String sql = "SELECT * FROM station WHERE driver_offer_id = ?;";

		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		if (query == null)
			return stations;

		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.setInteger(0, driverOffer.getId()).list();
		rows.forEach(row -> {
			Station station = new Station(row[2].toString(), Integer.valueOf(row[3].toString()),
					driverOffer.getId());
			station.setId(Integer.valueOf(row[0].toString()));
			station.setCreatedAt((Date) row[1]);
			station.setActive(Boolean.valueOf(row[5].toString()));
			station.setDriverOffer(driverOffer);

			stations.add(station);
		});

		return stations;
	}

	/**
	 * Delete driver offer by driver offer id.
	 *
	 * @param driverOfferId driver offer id
	 */
	public void deleteDriverOffer(final int driverOfferId)
	{
		String sql = "DELETE FROM driver_offer WHERE driver_offer_id = ?";
		getCurrentSession().createSQLQuery(sql).setInteger(0, driverOfferId).executeUpdate();
	}

	/**
	 * Update driver offer.
	 *
	 * @param driverOffer driver offer
	 */
	public void updateDriverOffer(final DriverOffer driverOffer)
	{
		String sql = "UPDATE driver_offer SET departure_location = ? WHERE driver_offer_id = ?";
		getCurrentSession().createSQLQuery(sql).setString(0, driverOffer.getDepartureLocation())
				.setInteger(1, driverOffer.getId()).executeUpdate();
	}

	/**
	 * Update driver offer activity.
	 *
	 * @param driverOffer driver offer
	 * @param isActive is active
	 */
	public void updateDriverOfferActivity(final DriverOffer driverOffer, final boolean isActive)
	{
		String sql = "UPDATE driver_offer SET active = ? WHERE driver_offer_id = ?";
		getCurrentSession().createSQLQuery(sql).setBoolean(0, isActive)
				.setInteger(1, driverOffer.getId()).executeUpdate();
	}
}
