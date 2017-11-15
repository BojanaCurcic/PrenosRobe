package com.prenosrobe.deo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "claimer_offer")
public class ClaimerOffer
{
	@Id
	@Column(name = "claimer_offer_id")
	private int id;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "departure_location")
	private String departureLocation;

	@Column(name = "arrival_location")
	private String arrivalLocation;

	@Column(name = "date")
	private Date date;

	@Column(name = "photo")
	private String photo;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "driver_offer_id")
	private DriverOffer driverOffer;

	@ManyToOne
	@JoinColumn(name = "offer_status_id")
	private OfferStatus offerStatus;

	/**
	 * Get the id.
	 *
	 * @return id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Set the id.
	 *
	 * @param id new id
	 */
	public void setId(final int id)
	{
		this.id = id;
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

	/**
	 * Get the photo.
	 *
	 * @return photo
	 */
	public String getPhoto()
	{
		return photo;
	}

	/**
	 * Set the photo.
	 *
	 * @param photo new photo
	 */
	public void setPhoto(final String photo)
	{
		this.photo = photo;
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
	 * Get the user.
	 *
	 * @return user
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * Set the user.
	 *
	 * @param user new user
	 */
	public void setUser(final User user)
	{
		this.user = user;
	}

	/**
	 * Get the driver offer.
	 *
	 * @return driver offer
	 */
	public DriverOffer getDriverOffer()
	{
		return driverOffer;
	}

	/**
	 * Set the driver offer.
	 *
	 * @param driverOffer new driver offer
	 */
	public void setDriverOffer(final DriverOffer driverOffer)
	{
		this.driverOffer = driverOffer;
	}

	/**
	 * Get the offer status.
	 *
	 * @return offer status
	 */
	public OfferStatus getOfferStatus()
	{
		return offerStatus;
	}

	/**
	 * Set the offer status.
	 *
	 * @param offerStatus new offer status
	 */
	public void setOfferStatus(final OfferStatus offerStatus)
	{
		this.offerStatus = offerStatus;
	}
}
