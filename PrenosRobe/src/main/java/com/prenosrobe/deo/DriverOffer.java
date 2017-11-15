package com.prenosrobe.deo;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "driver_offer")
public class DriverOffer
{
	@Id
	@Column(name = "driver_offer_id")
	private int id;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "departure_location")
	private String departureLocation;

	@Column(name = "arrival_location")
	private String arrivalLocation;

	@Column(name = "date")
	private Date date;

	@Column(name = "time")
	private Time time;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany
	private List<Station> stations = new ArrayList<Station>();

	@OneToMany
	private List<ClaimerOffer> claimerOffers = new ArrayList<ClaimerOffer>();

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
		this.user = user;
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
		this.stations = stations;
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
}
