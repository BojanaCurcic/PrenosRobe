package com.prenosrobe.data;

import java.io.Serializable;
import java.util.Date;

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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "station")
@SuppressWarnings("serial")
public class Station implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "station_id")
	private Integer id;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@NotEmpty
	@Column(name = "name")
	private String name;

	@Column(name = "x_coordinate")
	private double xCoordinate;

	@Column(name = "y_coordinate")
	private double yCoordinate;

	@Valid
	@NotNull
	@ManyToOne
	@JoinColumn(name = "area_id", referencedColumnName = "area_id")
	private Area area;

	/**
	 * Instantiates a new Station.
	 */
	public Station()
	{
	}

	/**
	 * Instantiate a new Station.
	 *
	 * @param name the name
	 */
	public Station(final String name)
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

	/**
	 * Get the x coordinate.
	 *
	 * @return x coordinate
	 */
	public double getXCoordinate()
	{
		return xCoordinate;
	}

	/**
	 * Set the x coordinate.
	 *
	 * @param xCoordinate new x coordinate
	 */
	public void setXCoordinate(final double xCoordinate)
	{
		this.xCoordinate = xCoordinate;
	}

	/**
	 * Get the y coordinate.
	 *
	 * @return y coordinate
	 */
	public double getYCoordinate()
	{
		return yCoordinate;
	}

	/**
	 * Set the y coordinate.
	 *
	 * @param yCoordinate new y coordinate
	 */
	public void setYCoordinate(final double yCoordinate)
	{
		this.yCoordinate = yCoordinate;
	}

	/**
	 * Get the area.
	 *
	 * @return area
	 */
	public Area getArea()
	{
		return area;
	}

	/**
	 * Set the area.
	 *
	 * @param area new area
	 */
	public void setArea(final Area area)
	{
		this.area = area;
	}
}
