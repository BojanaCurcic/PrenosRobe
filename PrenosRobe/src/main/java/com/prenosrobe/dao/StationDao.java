package com.prenosrobe.dao;

import org.springframework.stereotype.Repository;

import com.prenosrobe.data.Station;

@Repository
public class StationDao extends AbstractDao
{
	private static StationDao instance = new StationDao();

	/**
	 * Get the single instance of StationDao.
	 *
	 * @return single instance of StationDao
	 */
	public static StationDao getInstance()
	{
		return instance;
	}

	/**
	 * Send station.
	 *
	 * @param station station
	 */
	public void sendStation(final Station station)
	{
		getCurrentSession().save(station);
	}

	/**
	 * Get the station by id.
	 *
	 * @param id id
	 * @return station by id
	 */
	public Station getStationById(final int id)
	{
		return (Station) getCurrentSession().get(Station.class, id);
	}
}
