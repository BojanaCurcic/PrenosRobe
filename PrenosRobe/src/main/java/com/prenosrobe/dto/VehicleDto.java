package com.prenosrobe.dto;

import com.prenosrobe.data.Vehicle;

public class VehicleDto
{
	private Integer id;

	private String registrationNumber;

	private VehicleTypeDto vehicleType;

	public VehicleDto()
	{
	}

	public VehicleDto(final Vehicle vehicle)
	{
		this.id = vehicle.getId();
		this.registrationNumber = vehicle.getRegistrationNumber();
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(final Integer id)
	{
		this.id = id;
	}

	public String getRegistrationNumber()
	{
		return registrationNumber;
	}

	public void setRegistrationNumber(final String registrationNumber)
	{
		this.registrationNumber = registrationNumber;
	}

	public VehicleTypeDto getVehicleType()
	{
		return vehicleType;
	}

	public void setVehicleType(final VehicleTypeDto vehicleType)
	{
		this.vehicleType = vehicleType;
	}
}
