package com.prenosrobe.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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

	@Transient
	private List<Station> stations = new ArrayList<>();

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

	/**
	 * Get the stations.
	 *
	 * @return stations
	 */
	public List<Station> getStations()
	{
		return stations;
	}

	/**
	 * Set the stations.
	 *
	 * @param stations new stations
	 */
	public void setStations(final List<Station> stations)
	{
		this.stations = stations;
	}

	/**
	 * Add the new station.
	 *
	 * @param station new station
	 */
	public void addStation(final Station station)
	{
		this.stations.add(station);
	}

	/**
	 * Remove the station.
	 *
	 * @param station station
	 */
	public void removeStation(final Station station)
	{
		this.stations.remove(station);
	}
}
