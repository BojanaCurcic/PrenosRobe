package com.prenosrobe.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "driver_offer_station")
public class DriverOfferStation implements Serializable
{
	private static final long serialVersionUID = 1947604447550803643L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "driver_offer_station_id")
	private Integer id;

	@Column(name = "driver_offer_id")
	private Integer driverOfferId;

	@Valid
	@NotNull
	@ManyToOne
	@JoinColumn(name = "station_id", referencedColumnName = "station_id")
	private Station station;

	@NotNull
	@Column(name = "serial_number")
	private Integer serialNumber;

	@Column(name = "active")
	private Boolean active = true;

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
	 * @param station station
	 * @param serialNumber serial number
	 */
	public DriverOfferStation(final Integer driverOfferId, final Station station,
			final Integer serialNumber)
	{
		this.driverOfferId = driverOfferId;
		this.station = station;
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
	 * Get the driver offer id.
	 *
	 * @return driver offer id
	 */
	public Integer getDriverOfferId()
	{
		return driverOfferId;
	}

	/**
	 * Set the driver offer id.
	 *
	 * @param driverOfferId new driver offer id
	 */
	public void setDriverOfferId(final Integer driverOfferId)
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
		this.station = station;
	}

	/**
	 * Get the serial number.
	 *
	 * @return serial number
	 */
	public Integer getSerialNumber()
	{
		return serialNumber;
	}

	/**
	 * Set the serial number.
	 *
	 * @param serialNumber new serial number
	 */
	public void setSerialNumber(final Integer serialNumber)
	{
		this.serialNumber = serialNumber;
	}

	/**
	 * Check if is active.
	 *
	 * @return true, if is active
	 */
	public Boolean isActive()
	{
		return active;
	}

	/**
	 * Set the active.
	 *
	 * @param active new active
	 */
	public void setActive(final Boolean active)
	{
		this.active = active;
	}
}
