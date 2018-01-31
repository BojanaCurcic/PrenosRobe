package com.prenosrobe.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.prenosrobe.dto.UserDto;

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

	@Column(name = "token")
	private String token;

	@Column(name = "active")
	private boolean active = true;

	/**
	 * Instantiate a new User.
	 */
	public User()
	{
	}

	/**
	 * Instantiate a new User.
	 *
	 * @param name name
	 * @param surname surname
	 * @param username username
	 * @param password password
	 * @param email email
	 * @param phoneNumber phone number
	 * @param token token
	 */
	public User(final String name, final String surname, final String username,
			final String password, final String email, final String phoneNumber, final String token)
	{
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.token = token;
	}

	/**
	 * Instantiate a new User.
	 *
	 * @param name name
	 * @param surname surname
	 * @param username username
	 * @param password password
	 * @param email email
	 * @param phoneNumber phone number
	 * @param photo photo
	 * @param token token
	 */
	public User(final String name, final String surname, final String username,
			final String password, final String email, final String phoneNumber, final String photo,
			final String token)
	{
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.photo = photo;
		this.token = token;
	}

	/**
	 * Instantiate a new user.
	 *
	 * @param userDto user dto
	 */
	public User(final UserDto userDto)
	{
		this.name = userDto.getName();
		this.surname = userDto.getSurname();
		this.username = userDto.getUsername();
		this.password = userDto.getPassword();
		this.email = userDto.getEmail();
		this.phoneNumber = userDto.getPhoneNumber();
		this.photo = userDto.getPhoto();
		this.token = userDto.getToken();
		this.active = userDto.isActive();
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
}
