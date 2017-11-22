package com.prenosrobe.data;

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

@Entity
@Table(name = "user")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "photo")
	private String photo;

	@Column(name = "active")
	private boolean active = true;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	// @OneToMany(mappedBy = "user")
	@Transient
	private List<Impression> impressions = new ArrayList<>();

	@Transient
	private List<DriverOffer> driverOffers = new ArrayList<>();

	@Transient
	private List<ClaimerOffer> claimerOffers = new ArrayList<>();

	/**
	 * Instantiate a new user.
	 */
	public User()
	{
	}

	/**
	 * Instantiate a new user.
	 *
	 * @param name name
	 * @param surname surname
	 * @param username username
	 * @param password password
	 * @param email email
	 * @param phoneNumber phone number
	 */
	public User(final String name, final String surname, final String username,
			final String password, final String email, final String phoneNumber)
	{
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Instantiate a new user.
	 *
	 * @param name name
	 * @param surname surname
	 * @param username username
	 * @param password password
	 * @param email email
	 * @param phoneNumber phone number
	 * @param photo photo
	 */
	public User(final String name, final String surname, final String username,
			final String password, final String email, final String phoneNumber, final String photo)
	{
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.photo = photo;
	}

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
	 * Get the surname.
	 *
	 * @return surname
	 */
	public String getSurname()
	{
		return surname;
	}

	/**
	 * Set the surname.
	 *
	 * @param surname new surname
	 */
	public void setSurname(final String surname)
	{
		this.surname = surname;
	}

	/**
	 * Get the username.
	 *
	 * @return username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * Set the username.
	 *
	 * @param username new username
	 */
	public void setUsername(final String username)
	{
		this.username = username;
	}

	/**
	 * Get the password.
	 *
	 * @return password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * Set the password.
	 *
	 * @param password new password
	 */
	public void setPassword(final String password)
	{
		this.password = password;
	}

	/**
	 * Get the email.
	 *
	 * @return email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Set the email.
	 *
	 * @param email new email
	 */
	public void setEmail(final String email)
	{
		this.email = email;
	}

	/**
	 * Get the phone number.
	 *
	 * @return phone number
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	/**
	 * Set the phone number.
	 *
	 * @param phoneNumber new phone number
	 */
	public void setPhoneNumber(final String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
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
	 * Check if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive()
	{
		return active;
	}

	/**
	 * Set the active.
	 *
	 * @param active new active
	 */
	public void setActive(final boolean active)
	{
		this.active = active;
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
	 * Get the impressions.
	 *
	 * @return impressions
	 */
	public List<Impression> getImpressions()
	{
		return impressions;
	}

	/**
	 * Set the impressions.
	 *
	 * @param impressions new impressions
	 */
	public void setImpressions(final List<Impression> impressions)
	{
		this.impressions = impressions;
	}

	/**
	 * Add the new impression.
	 *
	 * @param impression new impression
	 */
	public void addImpression(final Impression impression)
	{
		this.impressions.add(impression);
	}

	/**
	 * Remove the impression.
	 *
	 * @param impression impression
	 */
	public void removeImpression(final Impression impression)
	{
		this.impressions.remove(impression);
	}

	/**
	 * Get the driver offers.
	 *
	 * @return driver offers
	 */
	public List<DriverOffer> getDriverOffers()
	{
		return driverOffers;
	}

	/**
	 * Set the driver offers.
	 *
	 * @param driverOffers new driver offers
	 */
	public void setDriverOffers(final List<DriverOffer> driverOffers)
	{
		this.driverOffers = driverOffers;
	}

	/**
	 * Add the new driver offer.
	 *
	 * @param driverOffer new driver offer
	 */
	public void addDriverOffer(final DriverOffer driverOffer)
	{
		this.driverOffers.add(driverOffer);
	}

	/**
	 * Remove the driver offer.
	 *
	 * @param driverOffer driver offer
	 */
	public void removeDriverOffer(final DriverOffer driverOffer)
	{
		this.driverOffers.remove(driverOffer);
	}

	/**
	 * Get the claimer offers.
	 *
	 * @return claimer offers
	 */
	public List<ClaimerOffer> getClaimerOffers()
	{
		return claimerOffers;
	}

	/**
	 * Set the claimer offers.
	 *
	 * @param claimerOffers new claimer offers
	 */
	public void setClaimerOffers(final List<ClaimerOffer> claimerOffers)
	{
		this.claimerOffers = claimerOffers;
	}

	/**
	 * Add the new claimer offer.
	 *
	 * @param claimerOffer new claimer offer
	 */
	public void addClaimerOffer(final ClaimerOffer claimerOffer)
	{
		this.claimerOffers.add(claimerOffer);
	}

	/**
	 * Remove the claimer offer.
	 *
	 * @param claimerOffer claimer offer
	 */
	public void removeClaimerOffer(final ClaimerOffer claimerOffer)
	{
		this.claimerOffers.remove(claimerOffer);
	}

	/**
	 * Get the average rating.
	 *
	 * @return the average rating or 0 if there is no impressions for this user
	 */
	public double getAverageRating()
	{
		if (impressions.isEmpty())
			return 0;

		double ratings = impressions.stream().mapToDouble(impression -> impression.getRating())
				.sum();

		return ratings / impressions.size();
	}
}
