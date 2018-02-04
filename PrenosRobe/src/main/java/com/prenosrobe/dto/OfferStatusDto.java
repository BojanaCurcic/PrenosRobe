package com.prenosrobe.dto;

import com.prenosrobe.data.OfferStatus;

public class OfferStatusDto
{
	private Integer id;

	private String name;

	public OfferStatusDto()
	{
	}

	public OfferStatusDto(final OfferStatus offerStatus)
	{
		this.id = offerStatus.getId();
		this.name = offerStatus.getName();
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
