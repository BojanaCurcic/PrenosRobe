package com.prenosrobe.data;

import java.io.Serializable;
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

	@Transient
	private OfferStatus offerStatus;

	@Column(name = "offer_status_id")
	private int offerStatusId;

	@Transient
	private UserVehicle userVehicle;

	@Column(name = "user_vehhicle_id")
	private int userVehicleId;

	@Transient
	private List<String> stationNames = new ArrayList<>();

	@Transient
	private List<DriverOfferStation> driverOfferStations = new ArrayList<>();

	@Transient
	private List<ClaimerOffer> claimerOffers = new ArrayList<>();

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
			final Date date, final int offerStatusId, final int userVehicleId)
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
			final Date date, final Time time, final int offerStatusId, final int userVehicleId)
	{
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.date = date;
		this.time = time;
		this.offerStatusId = offerStatusId;
		this.userVehicleId = userVehicleId;
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
	public void setOfferStatus(final OfferStatus offerStatus)
	{
		if (offerStatus != null)
		{
			this.offerStatus = offerStatus;
			this.offerStatusId = offerStatus.getId();
		}
	}

	/**
	 * Get the offer status id.
	 *
	 * @return offer status id
	 */
	public int getOfferStatusId()
	{
		return offerStatusId;
	}

	/**
	 * Set the offer status id.
	 *
	 * @param offerStatusId new offer status id
	 */
	public void setOfferStatusId(int offerStatusId)
	{
		this.offerStatusId = offerStatusId;
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
		if (userVehicle != null)
		{
			this.userVehicle = userVehicle;
			this.userVehicleId = userVehicle.getId();
		}
	}

	/**
	 * Get the user vehicle id.
	 *
	 * @return user vehicle id
	 */
	public int getUserVehicleId()
	{
		return userVehicleId;
	}

	/**
	 * Set the user vehicle id.
	 *
	 * @param userVehicleId new user vehicle id
	 */
	public void setUserVehicleId(final int userVehicleId)
	{
		this.userVehicleId = userVehicleId;
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
	public void setDriverOfferStations(List<DriverOfferStation> driverOfferStations)
	{
		this.driverOfferStations = driverOfferStations;

		for (DriverOfferStation driverOfferStation : driverOfferStations)
		{
			this.stationNames.add(driverOfferStation.getStation().getName());
		}
	}

	/**
	 * Add the driver offer station.
	 *
	 * @param driverOfferStation driver offer station
	 */
	public void addDriverOfferStation(final DriverOfferStation driverOfferStation)
	{
		this.driverOfferStations.add(driverOfferStation);
		this.stationNames.add(driverOfferStation.getStation().getName());
	}

	/**
	 * Remove the driver offer station.
	 *
	 * @param driverOfferStation driver offer station
	 */
	public void removeDriverOfferStation(final DriverOfferStation driverOfferStation)
	{
		this.driverOfferStations.remove(driverOfferStation);
		this.stationNames.remove(driverOfferStation.getStation().getName());
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
		this.stationNames = stationNames;
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
}
