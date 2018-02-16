/*
 * 
 */
package com.prenosrobe.data;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "driver_offer")
@SuppressWarnings("serial")
public class DriverOffer implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "driver_offer_id")
	private Integer id;

	@NotEmpty
	@Column(name = "departure_location")
	private String departureLocation;

	@NotEmpty
	@Column(name = "arrival_location")
	private String arrivalLocation;

	@NotNull
	@Column(name = "date")
	private Date date;

	@Column(name = "time")
	private Time time;

	@Valid
	@NotNull
	@ManyToOne
	@JoinColumn(name = "offer_status_id", referencedColumnName = "offer_status_id")
	private OfferStatus offerStatus;

	@Valid
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_vehicle_id", referencedColumnName = "user_vehicle_id")
	private UserVehicle userVehicle;

	@Valid
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "driver_offer_id")
	private List<DriverOfferStation> driverOfferStations = new ArrayList<>();

	/**
	 * Instantiate a new DriverOffer.
	 */
	public DriverOffer()
	{
	}

	/**
	 * Instantiate a new driver offer according to the forwarded driver offer.
	 *
	 * @param driverOffer driver offer
	 */
	public DriverOffer(final DriverOffer driverOffer)
	{
		this.departureLocation = driverOffer.getDepartureLocation();
		this.arrivalLocation = driverOffer.getArrivalLocation();
		this.date = driverOffer.getDate();
		this.time = driverOffer.getTime();
		this.offerStatus = driverOffer.getOfferStatus();
		this.userVehicle = driverOffer.getUserVehicle();
	}

	/**
	 * Get the id.
	 *
	 * @return id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the id.
	 *
	 * @param id new id
	 */
	public void setId(final Integer id)
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
	 * Get the offer status.
	 *
	 * @return offer status 
	 */
	public OfferStatus getOfferStatus()
	{
		return offerStatus;
	}

	/**
	 * Set the offer status.
	 *
	 * @param offerStatus new offer status 
	 */
	public void setOfferStatus(OfferStatus offerStatus)
	{
		this.offerStatus = offerStatus;
	}

	/**
	 * Get the user vehicle.
	 *
	 * @return user vehicle
	 */
	public UserVehicle getUserVehicle()
	{
		return userVehicle;
	}

	/**
	 * Set the user vehicle.
	 *
	 * @param userVehicle new user vehicle
	 */
	public void setUserVehicle(final UserVehicle userVehicle)
	{
		this.userVehicle = userVehicle;
	}

	/**
	 * Get the driver offer stations.
	 *
	 * @return driver offer stations
	 */
	public List<DriverOfferStation> getDriverOfferStations()
	{
		return driverOfferStations;
	}

	/**
	 * Set the driver offer stations.
	 *
	 * @param driverOfferStations new driver offer stations
	 */
	public void setDriverOfferStations(final List<DriverOfferStation> driverOfferStations)
	{
		this.driverOfferStations = driverOfferStations;
	}
}
