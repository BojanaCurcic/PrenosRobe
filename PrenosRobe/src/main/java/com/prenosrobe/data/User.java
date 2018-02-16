package com.prenosrobe.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user")
@SuppressWarnings("serial")
public class User implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer id;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@NotEmpty
	@Column(name = "name")
	private String name;

	@NotEmpty
	@Column(name = "surname")
	private String surname;

	@NotEmpty
	@Column(name = "username")
	private String username;

	@NotEmpty
	@Column(name = "password")
	private String password;

	@NotEmpty
	@Email
	@Column(name = "email")
	private String email;

	@NotEmpty
	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "photo")
	private String photo;

	// TODO: preimenuj token u neko smislenije ime
	@Column(name = "token")
	private String token;

	@Column(name = "active")
	private Boolean active;

	@Valid
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_id")
	private List<Impression> impressions = new ArrayList<>();

	@Valid
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_id")
	private List<UserLanguage> userLanguages = new ArrayList<>();

	/**
	 * Instantiate a new User.
	 */
	public User()
	{
	}

	/**
	 * Instantiate a new user according to the forwarded user.
	 *
	 * @param user user
	 */
	public User(final User user)
	{
		this.active = user.isActive();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.phoneNumber = user.getPhoneNumber();
		this.photo = user.getPhoto();
		this.token = user.getToken();
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
	 * Get the token.
	 *
	 * @return token
	 */
	public String getToken()
	{
		return token;
	}

	/**
	 * Set the token.
	 *
	 * @param token new token
	 */
	public void setToken(final String token)
	{
		this.token = token;
	}

	/**
	 * Check if is active.
	 *
	 * @return true, if is active
	 */
	public Boolean isActive()
	{
		return active;
	}

	/**
	 * Set the active.
	 *
	 * @param active new active
	 */
	public void setActive(final Boolean active)
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
	 * Get the user languages.
	 *
	 * @return user languages
	 */
	public List<UserLanguage> getUserLanguages()
	{
		return userLanguages;
	}

	/**
	 * Set the user languages.
	 *
	 * @param userLanguages new user languages
	 */
	public void setUserLanguages(final List<UserLanguage> userLanguages)
	{
		this.userLanguages = userLanguages;
	}
}
