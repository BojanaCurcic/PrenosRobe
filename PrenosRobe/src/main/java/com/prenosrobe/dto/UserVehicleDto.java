package com.prenosrobe.dto;

public class UserVehicleDto
{
	private Integer id;

	private UserDto user;

	private VehicleDto vehicle;

	public UserVehicleDto()
	{
	}

	public UserVehicleDto(final Integer id, final UserDto user, final VehicleDto vehicle)
	{
		this.id = id;
		this.user = user;
		this.vehicle = vehicle;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(final Integer id)
	{
		this.id = id;
	}

	public UserDto getUser()
	{
		return user;
	}

	public void setUser(final UserDto user)
	{
		this.user = user;
	}

	public VehicleDto getVehicle()
	{
		return vehicle;
	}

	public void setVehicle(final VehicleDto vehicle)
	{
		this.vehicle = vehicle;
	}
}
