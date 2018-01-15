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

	@Transient
	private User user;

	@Column(name = "user_id")
	private int userId;

	@Transient
	private Vehicle vehicle;

	@Column(name = "vehicle_id")
	private int vehicleId;

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
	public UserVehicle(final int userId, final int vehicleId)
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
		if (user != null)
		{
			this.user = user;
			this.userId = user.getId();
		}
	}

	/**
	 * Get the user id.
	 *
	 * @return user id
	 */
	public int getUserId()
	{
		return userId;
	}

	/**
	 * Set the user id.
	 *
	 * @param userId new user id
	 */
	public void setUserId(final int userId)
	{
		this.userId = userId;
	}

	/**
	 * Get the vehicle.
	 *
	 * @return vehicle
	 */
	public Vehicle getVehicle()
	{
		return vehicle;
	}

	/**
	 * Set the vehicle.
	 *
	 * @param vehicle new vehicle
	 */
	public void setVehicle(final Vehicle vehicle)
	{
		if (vehicle != null)
		{
			this.vehicle = vehicle;
			this.vehicleId = vehicle.getId();
		}
	}

	/**
	 * Get the vehicle id.
	 *
	 * @return vehicle id
	 */
	public int getVehicleId()
	{
		return vehicleId;
	}

	/**
	 * Set the vehicle id.
	 *
	 * @param vehicleId new vehicle id
	 */
	public void setVehicleId(final int vehicleId)
	{
		this.vehicleId = vehicleId;
	}
}
