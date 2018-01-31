package com.prenosrobe.dto;

import com.prenosrobe.data.Language;

public class LanguageDto
{
	private Integer id;

	private String name;

	public LanguageDto()
	{
	}

	public LanguageDto(final Language language)
	{
		this.id = language.getId();
		this.name = language.getName();
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
