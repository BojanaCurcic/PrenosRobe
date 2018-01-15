package com.prenosrobe.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "driver_offer_station")
@SuppressWarnings("serial")
public class DriverOfferStation implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "driver_offer_station_id")
	private Integer id;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@Transient
	private DriverOffer driverOffer;

	@Column(name = "driver_offer_id")
	private int driverOfferId;

	@Transient
	private Station station;

	@Column(name = "station_id")
	private int stationId;

	@Column(name = "serial_number")
	private int serialNumber;

	@Column(name = "active")
	private boolean active = true;

	/**
	 * Instantiate a new DriverOfferStation.
	 */
	public DriverOfferStation()
	{
	}

	/**
	 * Instantiate a new DriverOfferStation.
	 *
	 * @param driverOfferId driver offer id
	 * @param stationId station id
	 * @param serialNumber serial number
	 */
	public DriverOfferStation(final int driverOfferId, final int stationId, final int serialNumber)
	{
		this.driverOfferId = driverOfferId;
		this.stationId = stationId;
		this.serialNumber = serialNumber;
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
	 * Get the driver offer.
	 *
	 * @return driver offer
	 */
	public DriverOffer getDriverOffer()
	{
		return driverOffer;
	}

	/**
	 * Set the driver offer.
	 *
	 * @param driverOffer new driver offer
	 */
	public void setDriverOffer(final DriverOffer driverOffer)
	{
		if (driverOffer != null)
		{
			this.driverOffer = driverOffer;
			this.driverOfferId = driverOffer.getId();
		}
	}

	/**
	 * Get the driver offer id.
	 *
	 * @return driver offer id
	 */
	public int getDriverOfferId()
	{
		return driverOfferId;
	}

	/**
	 * Set the driver offer id.
	 *
	 * @param driverOfferId new driver offer id
	 */
	public void setDriverOfferId(final int driverOfferId)
	{
		this.driverOfferId = driverOfferId;
	}

	/**
	 * Get the station.
	 *
	 * @return station
	 */
	public Station getStation()
	{
		return station;
	}

	/**
	 * Set the station.
	 *
	 * @param station new station
	 */
	public void setStation(final Station station)
	{
		if (station != null)
		{
			this.station = station;
			this.stationId = station.getId();
		}
	}

	/**
	 * Get the station id.
	 *
	 * @return station id
	 */
	public int getStationId()
	{
		return stationId;
	}

	/**
	 * Set the station id.
	 *
	 * @param stationId new station id
	 */
	public void setStationId(final int stationId)
	{
		this.stationId = stationId;
	}

	/**
	 * Get the serial number.
	 *
	 * @return serial number
	 */
	public int getSerialNumber()
	{
		return serialNumber;
	}

	/**
	 * Set the serial number.
	 *
	 * @param serialNumber new serial number
	 */
	public void setSerialNumber(final int serialNumber)
	{
		this.serialNumber = serialNumber;
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
	public void setActive(final boolean active)
	{
		this.active = active;
	}
}
