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
@Table(name = "claimer_offer")
@SuppressWarnings("serial")
public class ClaimerOffer implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "claimer_offer_id")
	private Integer id;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@NotEmpty
	@Column(name = "departure_location")
	private String departureLocation;

	@NotEmpty
	@Column(name = "arrival_location")
	private String arrivalLocation;

	@NotEmpty
	@Column(name = "data")
	private String data;

	@Column(name = "photo")
	private String photo;

	@Valid
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "driver_offer_id", referencedColumnName = "driver_offer_id")
	private DriverOffer driverOffer;

	@ManyToOne
	@JoinColumn(name = "offer_status_id", referencedColumnName = "offer_status_id")
	private OfferStatus offerStatus;

	/**
	 * Instantiate a new ClaimerOffer.
	 */
	public ClaimerOffer()
	{
	}

	/**
	 * Instantiate a new ClaimerOffer.
	 *
	 * @param departureLocation departure location
	 * @param arrivalLocation arrival location
	 * @param data data
	 * @param user user
	 * @param driverOffer driver offer
	 * @param offerStatus offer status
	 */
	public ClaimerOffer(final String departureLocation, final String arrivalLocation,
			final String data, final User user, final DriverOffer driverOffer,
			final OfferStatus offerStatus)
	{
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.data = data;
		this.user = user;
		this.driverOffer = driverOffer;
		this.offerStatus = offerStatus;
	}

	/**
	 * Instantiate a new ClaimerOffer.
	 *
	 * @param departureLocation departure location
	 * @param arrivalLocation arrival location
	 * @param data data
	 * @param photo photo
	 * @param user user
	 * @param driverOffer driver offer
	 * @param offerStatus offer status
	 */
	public ClaimerOffer(final String departureLocation, final String arrivalLocation,
			final String data, final String photo, final User user, final DriverOffer driverOffer,
			final OfferStatus offerStatus)
	{
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.data = data;
		this.photo = photo;
		this.user = user;
		this.driverOffer = driverOffer;
		this.offerStatus = offerStatus;
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
	 * Get the data.
	 *
	 * @return data
	 */
	public String getDate()
	{
		return data;
	}

	/**
	 * Set the data.
	 *
	 * @param data new data
	 */
	public void setDate(final String data)
	{
		this.data = data;
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
	public DriverOffer getDriverOfferId()
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
