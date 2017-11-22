package com.prenosrobe.data;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "driver_offer")
public class DriverOffer
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "driver_offer_id")
	private int id;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@Column(name = "departure_location")
	private String departureLocation;

	@Column(name = "arrival_location")
	private String arrivalLocation;

	@Column(name = "date")
	private Date date;

	@Column(name = "time")
	private Time time;

	@Transient
	private User user;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "active")
	private boolean active = true;

	@Transient
	private List<Station> stations = new ArrayList<>();

	@Transient
	private List<String> stationNames = new ArrayList<>();

	@Transient
	private List<ClaimerOffer> claimerOffers = new ArrayList<>();

	/**
	 * Instantiate a new driver offer.
	 */
	public DriverOffer()
	{
	}

	/**
	 * Instantiate a new driver offer.
	 *
	 * @param departureLocation departure location
	 * @param arrivalLocation arrival location
	 * @param date date
	 * @param userId user id
	 */
	public DriverOffer(final String departureLocation, final String arrivalLocation,
			final Date date, final int userId)
	{
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.date = date;
		this.userId = userId;
	}

	/**
	 * Instantiate a new driver offer.
	 *
	 * @param departureLocation departure location
	 * @param arrivalLocation arrival location
	 * @param date date
	 * @param time time
	 * @param userId user id
	 */
	public DriverOffer(final String departureLocation, final String arrivalLocation,
			final Date date, final Time time, final int userId)
	{
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.date = date;
		this.time = time;
		this.userId = userId;
	}

	/**
	 * Get the id.
	 *
	 * @return id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Set the id.
	 *
	 * @param id new id
	 */
	public void setId(final int id)
	{
		this.id = id;
	}

	/**
	 * Get the departure location.
	 *
	 * @return departure location
	 */
	public String getDepartureLocation()
	{
		return departureLocation;
	}

	/**
	 * Set the departure location.
	 *
	 * @param departureLocation new departure location
	 */
	public void setDepartureLocation(final String departureLocation)
	{
		this.departureLocation = departureLocation;
	}

	/**
	 * Get the arrival location.
	 *
	 * @return arrival location
	 */
	public String getArrivalLocation()
	{
		return arrivalLocation;
	}

	/**
	 * Set the arrival location.
	 *
	 * @param arrivalLocation new arrival location
	 */
	public void setArrivalLocation(final String arrivalLocation)
	{
		this.arrivalLocation = arrivalLocation;
	}

	/**
	 * Get the date.
	 *
	 * @return date
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * Set the date.
	 *
	 * @param date new date
	 */
	public void setDate(final Date date)
	{
		this.date = date;
	}

	/**
	 * Get the time.
	 *
	 * @return time
	 */
	public Time getTime()
	{
		return time;
	}

	/**
	 * Set the time.
	 *
	 * @param time new time
	 */
	public void setTime(final Time time)
	{
		this.time = time;
	}

	/**
	 * Get the created at.
	 *
	 * @return created at
	 */
	public Date getCreatedAt()
	{
		return createdAt;
	}

	/**
	 * Set the created at.
	 *
	 * @param createdAt new created at
	 */
	public void setCreatedAt(final Date createdAt)
	{
		this.createdAt = createdAt;
	}

	/**
	 * Get the user.
	 *
	 * @return user
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * Set the user.
	 *
	 * @param user new user
	 */
	public void setUser(final User user)
	{
		if (user != null)
		{
			this.user = user;
			this.userId = user.getId();
		}
	}

	/**
	 * Get the user id.
	 *
	 * @return user id
	 */
	public int getUserId()
	{
		return userId;
	}

	/**
	 * Set the user id.
	 *
	 * @param userId new user id
	 */
	public void setUserId(final int userId)
	{
		this.userId = userId;
	}

	/**
	 * Check if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive()
	{
		return active;
	}

	/**
	 * Set the active.
	 *
	 * @param active new active
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}

	/**
	 * Get the stations.
	 *
	 * @return stations
	 */
	public List<Station> getStations()
	{
		return stations;
	}

	/**
	 * Set the stations.
	 *
	 * @param stations new stations
	 */
	public void setStations(final List<Station> stations)
	{
		this.stations.clear();
		this.stations = stations;

		this.stationNames.clear();
		for (Station station : stations)
		{
			this.stationNames.add(station.getName());
		}
	}

	/**
	 * Add the new station.
	 *
	 * @param stationNames station new station
	 */
	public void addStation(final Station station)
	{
		this.stations.add(station);
		this.stationNames.add(station.getName());
	}

	/**
	 * Remove the station.
	 *
	 * @param station station
	 */
	public void removeStation(final Station station)
	{
		this.stations.remove(station);
		this.stationNames.remove(station.getName());
	}

	/**
	 * Get the claimer offers.
	 *
	 * @return claimer offers
	 */
	public List<ClaimerOffer> getClaimerOffers()
	{
		return claimerOffers;
	}

	/**
	 * Set the claimer offers.
	 *
	 * @param claimerOffers new claimer offers
	 */
	public void setClaimerOffers(final List<ClaimerOffer> claimerOffers)
	{
		this.claimerOffers = claimerOffers;
	}

	/**
	 * Get the station names.
	 *
	 * @return station names
	 */
	public List<String> getStationNames()
	{
		return stationNames;
	}

	/**
	 * Set the station names.
	 *
	 * @param stationNames new station names
	 */
	public void setStationNames(List<String> stationNames)
	{
		this.stationNames.clear();
		this.stationNames = stationNames;

		populateStations();
	}

	/**
	 * Add the new claimer offer.
	 *
	 * @param claimerOffer new claimer offer
	 */
	public void addClaimerOffer(final ClaimerOffer claimerOffer)
	{
		this.claimerOffers.add(claimerOffer);
	}

	/**
	 * Remove the claimer offer.
	 *
	 * @param claimerOffer claimer offer
	 */
	public void removeClaimerOffer(final ClaimerOffer claimerOffer)
	{
		this.claimerOffers.remove(claimerOffer);
	}

	/**
	 * Populate stations according to stationNames list.
	 */
	private void populateStations()
	{
		stations.clear();

		Station departureStation = new Station(departureLocation, 0, getId());
		departureStation.setDriverOffer(this);
		stations.add(departureStation);

		int serialNumber = 1;
		for (String stationName : stationNames)
		{
			Station station = new Station(stationName, serialNumber, getId());
			station.setDriverOffer(this);
			stations.add(station);
			serialNumber++;
		}

		Station arrivalStation = new Station(arrivalLocation, serialNumber, getId());
		arrivalStation.setDriverOffer(this);
		stations.add(arrivalStation);
	}

	/**
	 * Set the stations driver offer id. This method must be called adding this object into database.
	 */
	public void setStationsDriverOfferId()
	{
		for (Station station : stations)
		{
			station.getDriverOffer().setId(this.id);
			station.setDriverOfferId(this.id);
		}
	}
}
