package com.prenosrobe.dto;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.prenosrobe.data.DriverOffer;
import com.prenosrobe.data.DriverOfferStation;

public class DriverOfferDto
{
	private Integer id;

	private String departureLocation;

	private String arrivalLocation;

	private Date date;

	private Time time;

	private OfferStatusDto offerStatus;

	private UserVehicleDto userVehicle;

	private List<String> stationNames = new ArrayList<>();

	private List<DriverOfferStation> driverOfferStations = new ArrayList<>();

	private List<ClaimerOfferDto> claimerOffers = new ArrayList<>();

	public DriverOfferDto()
	{
	}

	public DriverOfferDto(final DriverOffer driverOffer)
	{
		this.id = driverOffer.getId();
		this.departureLocation = driverOffer.getDepartureLocation();
		this.arrivalLocation = driverOffer.getArrivalLocation();
		this.date = driverOffer.getDate();
		this.time = driverOffer.getTime();
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

	public Date getDate()
	{
		return date;
	}

	public void setDate(final Date date)
	{
		this.date = date;
	}

	public Time getTime()
	{
		return time;
	}

	public void setTime(final Time time)
	{
		this.time = time;
	}

	public OfferStatusDto getOfferStatus()
	{
		return offerStatus;
	}

	public void setOfferStatus(final OfferStatusDto offerStatus)
	{
		this.offerStatus = offerStatus;
	}

	public UserVehicleDto getUserVehicle()
	{
		return userVehicle;
	}

	public void setUserVehicle(final UserVehicleDto userVehicle)
	{
		this.userVehicle = userVehicle;
	}

	public List<ClaimerOfferDto> getClaimerOffers()
	{
		return claimerOffers;
	}

	public void setClaimerOffers(final List<ClaimerOfferDto> claimerOffers)
	{
		this.claimerOffers = claimerOffers;
	}

	public void addClaimerOffer(final ClaimerOfferDto claimerOffer)
	{
		this.claimerOffers.add(claimerOffer);
	}

	public void removeClaimerOffer(final ClaimerOfferDto claimerOffer)
	{
		this.claimerOffers.remove(claimerOffer);
	}
}
