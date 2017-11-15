package com.prenosrobe.deo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "station")
public class Station
{
	@Id
	@Column(name = "station_id")
	private int id;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "name")
	private String name;

	@Column(name = "serial_number")
	private int serialNumber;

	@Column(name = "active")
	private boolean active;

	@ManyToOne
	@JoinColumn(name = "driver_offer_id")
	private DriverOffer driverOffer;

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
		this.driverOffer = driverOffer;
	}
}
