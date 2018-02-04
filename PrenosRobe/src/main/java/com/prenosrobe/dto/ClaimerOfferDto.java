package com.prenosrobe.dto;

import com.prenosrobe.data.ClaimerOffer;

public class ClaimerOfferDto
{
	private Integer id;

	private String departureLocation;

	private String arrivalLocation;

	private String data;

	private String photo;

	private UserDto user;

	private DriverOfferDto driverOffer;

	private OfferStatusDto offerStatus;

	public ClaimerOfferDto()
	{
	}

	public ClaimerOfferDto(final ClaimerOffer claimerOffer)
	{
		this.id = claimerOffer.getId();
		this.departureLocation = claimerOffer.getDepartureLocation();
		this.arrivalLocation = claimerOffer.getArrivalLocation();
		this.data = claimerOffer.getDate();
		this.photo = claimerOffer.getPhoto();
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(final Integer id)
	{
		this.id = id;
	}

	public String getDepartureLocation()
	{
		return departureLocation;
	}

	public void setDepartureLocation(final String departureLocation)
	{
		this.departureLocation = departureLocation;
	}

	public String getArrivalLocation()
	{
		return arrivalLocation;
	}

	public void setArrivalLocation(final String arrivalLocation)
	{
		this.arrivalLocation = arrivalLocation;
	}

	public String getData()
	{
		return data;
	}

	public void setData(final String data)
	{
		this.data = data;
	}

	public String getPhoto()
	{
		return photo;
	}

	public void setPhoto(final String photo)
	{
		this.photo = photo;
	}

	public UserDto getUser()
	{
		return user;
	}

	public void setUser(final UserDto user)
	{
		this.user = user;
	}

	public DriverOfferDto getDriverOffer()
	{
		return driverOffer;
	}

	public void setDriverOffer(final DriverOfferDto driverOffer)
	{
		this.driverOffer = driverOffer;
	}

	public OfferStatusDto getOfferStatus()
	{
		return offerStatus;
	}

	public void setOfferStatus(final OfferStatusDto offerStatus)
	{
		this.offerStatus = offerStatus;
	}
}
