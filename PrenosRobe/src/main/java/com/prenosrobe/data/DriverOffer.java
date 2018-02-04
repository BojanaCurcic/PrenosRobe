package com.prenosrobe.data;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.prenosrobe.dto.DriverOfferDto;

@Entity
@Table(name = "driver_offer")
@SuppressWarnings("serial")
public class DriverOffer implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "driver_offer_id")
	private Integer id;

	@Column(name = "created_at")
	private Date createdAt = new Date();

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

	@NotNull
	@Column(name = "offer_status_id")
	private Integer offerStatusId = null;

	@NotNull
	@Column(name = "user_vehicle_id")
	private Integer userVehicleId = null;

	/**
	 * Instantiate a new DriverOffer.
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
	 * @param offerStatusId offer status id
	 * @param userVehicleId user vehicle id
	 */
	public DriverOffer(final String departureLocation, final String arrivalLocation,
			final Date date, final Integer offerStatusId, final Integer userVehicleId)
	{
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.date = date;
		this.offerStatusId = offerStatusId;
		this.userVehicleId = userVehicleId;
	}

	/**
	 * Instantiate a new driver offer.
	 *
	 * @param departureLocation departure location
	 * @param arrivalLocation arrival location
	 * @param date date
	 * @param time time
	 * @param offerStatusId offer status id
	 * @param userVehicleId user vehicle id
	 */
	public DriverOffer(final String departureLocation, final String arrivalLocation,
			final Date date, final Time time, final Integer offerStatusId,
			final Integer userVehicleId)
	{
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.date = date;
		this.time = time;
		this.offerStatusId = offerStatusId;
		this.userVehicleId = userVehicleId;
	}

	/**
	 * Instantiate a new driver offer.
	 *
	 * @param driverOfferDto driver offer dto
	 */
	public DriverOffer(DriverOfferDto driverOfferDto)
	{
		this.departureLocation = driverOfferDto.getDepartureLocation();
		this.arrivalLocation = driverOfferDto.getArrivalLocation();
		this.date = driverOfferDto.getDate();
		this.time = driverOfferDto.getTime();
		if (driverOfferDto.getOfferStatus() != null
				&& driverOfferDto.getOfferStatus().getId() != null)
			this.offerStatusId = driverOfferDto.getOfferStatus().getId();
		if (driverOfferDto.getUserVehicle() != null
				&& driverOfferDto.getUserVehicle().getId() != null)
			this.userVehicleId = driverOfferDto.getUserVehicle().getId();
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
	 * Get the offer status id.
	 *
	 * @return offer status id
	 */
	public Integer getOfferStatusId()
	{
		return offerStatusId;
	}

	/**
	 * Set the offer status id.
	 *
	 * @param offerStatusId new offer status id
	 */
	public void setOfferStatusId(Integer offerStatusId)
	{
		this.offerStatusId = offerStatusId;
	}

	/**
	 * Get the user vehicle id.
	 *
	 * @return user vehicle id
	 */
	public Integer getUserVehicleId()
	{
		return userVehicleId;
	}

	/**
	 * Set the user vehicle id.
	 *
	 * @param userVehicleId new user vehicle id
	 */
	public void setUserVehicleId(final Integer userVehicleId)
	{
		this.userVehicleId = userVehicleId;
	}
}
