package com.prenosrobe.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "station")
public class Station
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "station_id")
	private int id;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@Column(name = "name")
	private String name;

	@Column(name = "serial_number")
	private int serialNumber;

	@Column(name = "active")
	private boolean active = true;

	@Transient
	private DriverOffer driverOffer;

	@Column(name = "driver_offer_id")
	private int driverOfferId;

	/**
	 * Instantiate a new station.
	 *
	 * @param name name
	 * @param serialNumber serial number
	 * @param driverOfferId driver offer id
	 */
	public Station(final String name, final int serialNumber, final int driverOfferId)
	{
		this.name = name;
		this.serialNumber = serialNumber;
		this.driverOfferId = driverOfferId;
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
	 * Get the name.
	 *
	 * @return name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the name.
	 *
	 * @param name new name
	 */
	public void setName(final String name)
	{
		this.name = name;
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
}
