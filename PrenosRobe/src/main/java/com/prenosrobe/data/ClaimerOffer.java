package com.prenosrobe.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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

	@Transient
	private User user;

	@NotNull
	@Column(name = "user_id")
	private int userId;

	@Transient
	private DriverOffer driverOffer;

	@NotNull
	@Column(name = "driver_offer_id")
	private int driverOfferId;

	@Transient
	private OfferStatus offerStatus;

	@NotNull
	@Column(name = "offer_status_id")
	private int offerStatusId;

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
	 * @param userId user id
	 * @param driverOfferId driver offer id
	 * @param offerStatusId offer status id
	 */
	public ClaimerOffer(final String departureLocation, final String arrivalLocation,
			final String data, final int userId, final int driverOfferId, final int offerStatusId)
	{
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.data = data;
		this.userId = userId;
		this.driverOfferId = driverOfferId;
		this.offerStatusId = offerStatusId;
	}

	/**
	 * Instantiate a new ClaimerOffer.
	 *
	 * @param departureLocation departure location
	 * @param arrivalLocation arrival location
	 * @param data data
	 * @param photo photo
	 * @param userId user id
	 * @param driverOfferId driver offer id
	 * @param offerStatusId offer status id
	 */
	public ClaimerOffer(final String departureLocation, final String arrivalLocation,
			final String data, final String photo, final int userId, final int driverOfferId,
			final int offerStatusId)
	{
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.data = data;
		this.photo = photo;
		this.userId = userId;
		this.driverOfferId = driverOfferId;
		this.offerStatusId = offerStatusId;
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
		if (user != null)
		{
			this.user = user;
			this.userId = user.getId();
		}
	}

	/**
	 * Get the user id.
	 *
	 * @return user id
	 */
	public int getUserId()
	{
		return userId;
	}

	/**
	 * Set the user id.
	 *
	 * @param userId new user id
	 */
	public void setUserId(final int userId)
	{
		this.userId = userId;
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
		if (driverOffer != null)
		{
			this.driverOffer = driverOffer;
			this.driverOfferId = driverOffer.getId();
		}
	}

	/**
	 * Get the driver offer id.
	 *
	 * @return driver offer id
	 */
	public int getDriverOfferId()
	{
		return driverOfferId;
	}

	/**
	 * Set the driver offer id.
	 *
	 * @param driverOfferId new driver offer id
	 */
	public void setDriverOfferId(final int driverOfferId)
	{
		this.driverOfferId = driverOfferId;
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
		if (offerStatus != null)
		{
			this.offerStatus = offerStatus;
			this.offerStatusId = offerStatus.getId();
		}
	}

	/**
	 * Get the offer status id.
	 *
	 * @return offer status id
	 */
	public int getOfferStatusId()
	{
		return offerStatusId;
	}

	/**
	 * Set the offer status id.
	 *
	 * @param offerStatusId new offer status id
	 */
	public void setOfferStatusId(final int offerStatusId)
	{
		this.offerStatusId = offerStatusId;
	}
}
