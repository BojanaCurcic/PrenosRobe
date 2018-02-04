package com.prenosrobe.dto;

import com.prenosrobe.data.VehicleType;

public class VehicleTypeDto
{
	private Integer id;

	private String name;

	public VehicleTypeDto()
	{
	}

	public VehicleTypeDto(final VehicleType vehicleType)
	{
		this.id = vehicleType.getId();
		this.name = vehicleType.getName();
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(final Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}
}
