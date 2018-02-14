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

@Entity
@Table(name = "user_language")
@SuppressWarnings("serial")
public class UserLanguage implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_language_id")
	private Integer id;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@NotNull
	@Column(name = "user_id")
	private Integer userId;

	@Valid
	@NotNull
	@ManyToOne
	@JoinColumn(name = "language_id", referencedColumnName = "language_id")
	private Language language;

	/**
	 * Instantiate a new UserLanguage.
	 */
	public UserLanguage()
	{
	}

	/**
	 * Instantiate a new UserLanguage.
	 *
	 * @param userId user id
	 * @param language language
	 */
	public UserLanguage(final Integer userId, final Language language)
	{
		this.userId = userId;
		this.language = language;
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
	 * Get the user id.
	 *
	 * @return user id
	 */
	public Integer getUserId()
	{
		return userId;
	}

	/**
	 * Set the user id.
	 *
	 * @param userId new user id
	 */
	public void setUserId(final Integer userId)
	{
		this.userId = userId;
	}

	/**
	 * Get the language.
	 *
	 * @return language
	 */
	public Language getLanguage()
	{
		return language;
	}

	/**
	 * Set the language.
	 *
	 * @param language new language
	 */
	public void setLanguage(final Language language)
	{
		this.language = language;
	}
}
