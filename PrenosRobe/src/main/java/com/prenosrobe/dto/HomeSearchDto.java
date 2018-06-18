package com.prenosrobe.dto;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.prenosrobe.util.SqlDateSerializer;

public class HomeSearchDto implements Serializable
{
	private static final long serialVersionUID = 5262031628551714399L;

	@NotEmpty
	private String departureLocation;
	
	@NotEmpty
	private String arrivalLocation;
	
	@JsonSerialize(using = SqlDateSerializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Europe/Budapest")
	private Date date;

	public HomeSearchDto()
	{}
	
	/**
	 * Instantiate a new HomeSearchDto.
	 *
	 * @param departureLocation departure location
	 * @param arrivalLocation arrival location
	 * @param date date
	 */
	public HomeSearchDto(final String departureLocation, final String arrivalLocation,
			final Date date)
	{
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.date = date;
	}

	/**
	 * Get the departure location.
	 *
	 * @return departure location
	 */
	public String getDepartureLocation()
	{
		return departureLocation;
	}

	/**
	 * Set the departure location.
	 *
	 * @param departureLocation new departure location
	 */
	public void setDepartureLocation(final String departureLocation)
	{
		this.departureLocation = departureLocation;
	}

	/**
	 * Get the arrival location.
	 *
	 * @return arrival location
	 */
	public String getArrivalLocation()
	{
		return arrivalLocation;
	}

	/**
	 * Set the arrival location.
	 *
	 * @param arrivalLocation new arrival location
	 */
	public void setArrivalLocation(final String arrivalLocation)
	{
		this.arrivalLocation = arrivalLocation;
	}

	/**
	 * Get the date.
	 *
	 * @return date
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * Set the date.
	 *
	 * @param date new date
	 */
	public void setDate(final Date date)
	{
		this.date = date;
	}
}
