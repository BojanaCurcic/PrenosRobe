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
@Table(name = "user_vehicle")
public class UserVehicle implements Serializable
{
	private static final long serialVersionUID = -7520418113558228713L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_vehicle_id")
	private Integer id;

	@Valid
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	@Valid
	@NotNull
	@ManyToOne
	@JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
	private Vehicle vehicle;

	/**
	 * Instantiate a new UserVehicle.
	 */
	public UserVehicle()
	{
	}

	/**
	 * Instantiate a new UserVehicle.
	 *
	 * @param user user
	 * @param vehicle vehicle
	 */
	public UserVehicle(final User user, final Vehicle vehicle)
	{
		this.user = user;
		this.vehicle = vehicle;
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
	 * @param userId new user
	 */
	public void setUser(final User user)
	{
		this.user = user;
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
	 * @param vehicleId new vehicle 
	 */
	public void setVehicle(final Vehicle vehicle)
	{
		this.vehicle = vehicle;
	}
}
