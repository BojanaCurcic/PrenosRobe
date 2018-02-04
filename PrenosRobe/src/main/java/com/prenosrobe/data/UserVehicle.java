package com.prenosrobe.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_vehicle")
@SuppressWarnings("serial")
public class UserVehicle implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_vehicle_id")
	private Integer id;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "vehicle_id")
	private Integer vehicleId;

	/**
	 * Instantiate a new UserVehicle.
	 */
	public UserVehicle()
	{
	}

	/**
	 * Instantiate a new UserVehicle.
	 *
	 * @param userId user id
	 * @param vehicleId vehicle id
	 */
	public UserVehicle(final Integer userId, final Integer vehicleId)
	{
		this.userId = userId;
		this.vehicleId = vehicleId;
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
	 * Get the user id.
	 *
	 * @return user id
	 */
	public Integer getUserId()
	{
		return userId;
	}

	/**
	 * Set the user id.
	 *
	 * @param userId new user id
	 */
	public void setUserId(final Integer userId)
	{
		this.userId = userId;
	}

	/**
	 * Get the vehicle id.
	 *
	 * @return vehicle id
	 */
	public Integer getVehicleId()
	{
		return vehicleId;
	}

	/**
	 * Set the vehicle id.
	 *
	 * @param vehicleId new vehicle id
	 */
	public void setVehicleId(final Integer vehicleId)
	{
		this.vehicleId = vehicleId;
	}
}
