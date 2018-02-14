package com.prenosrobe.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "area")
@SuppressWarnings("serial")
public class Area implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "area_id")
	private Integer id;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@NotEmpty
	@Column(name = "name")
	private String name;

	/**
	 * Instantiate a new Area.
	 */
	public Area()
	{
	}

	/**
	 * Instantiate a new Area.
	 *
	 * @param name name
	 */
	public Area(final String name)
	{
		this.name = name;
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
	 * Get the name.
	 *
	 * @return name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the name.
	 *
	 * @param name new name
	 */
	public void setName(final String name)
	{
		this.name = name;
	}
}
