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
@Table(name = "vehicle")
@SuppressWarnings("serial")
public class Vehicle implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vehicle_id")
	private Integer id;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@Column(name = "registration_number")
	private String registrationNumber;

	@Transient
	private VehicleType vehicleType;

	@Column(name = "vehicle_type_id")
	private int vehicleTypeId;

	/**
	 * Instantiate a new Vehicle.
	 */
	public Vehicle()
	{
	}

	/**
	 * Instantiate a new Vehicle.
	 *
	 * @param registrationNumber registration number
	 * @param vehicleTypeId vehicle type id
	 */
	public Vehicle(final String registrationNumber, final int vehicleTypeId)
	{
		this.registrationNumber = registrationNumber;
		this.vehicleTypeId = vehicleTypeId;
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
	 * Get the registration number.
	 *
	 * @return registration number
	 */
	public String getRegistrationNumber()
	{
		return registrationNumber;
	}

	/**
	 * Set the registration number.
	 *
	 * @param registrationNumber new registration number
	 */
	public void setRegistrationNumber(final String registrationNumber)
	{
		this.registrationNumber = registrationNumber;
	}

	/**
	 * Get the vehicle type.
	 *
	 * @return vehicle type
	 */
	public VehicleType getVehicleType()
	{
		return vehicleType;
	}

	/**
	 * Set the vehicle type.
	 *
	 * @param vehicleType new vehicle type
	 */
	public void setVehicleType(final VehicleType vehicleType)
	{
		if (vehicleType != null)
		{
			this.vehicleType = vehicleType;
			this.vehicleTypeId = vehicleType.getId();
		}
	}

	/**
	 * Get the vehicle type id.
	 *
	 * @return vehicle type id
	 */
	public int getVehicleTypeId()
	{
		return vehicleTypeId;
	}

	/**
	 * Set the vehicle type id.
	 *
	 * @param vehicleTypeId new vehicle type id
	 */
	public void setVehicleTypeId(final int vehicleTypeId)
	{
		this.vehicleTypeId = vehicleTypeId;
	}
}
