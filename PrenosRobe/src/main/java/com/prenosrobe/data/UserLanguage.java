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

	@Transient
	private User user;

	@Column(name = "user_id")
	private int userId;

	@Transient
	private Language language;

	@Column(name = "language_id")
	private int languageId;

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
	 * @param languageId language id
	 */
	public UserLanguage(final int userId, final int languageId)
	{
		this.userId = userId;
		this.languageId = languageId;
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

	/**
	 * Get the language id.
	 *
	 * @return language id
	 */
	public int getLanguageId()
	{
		return languageId;
	}

	/**
	 * Set the language id.
	 *
	 * @param languageId new language id
	 */
	public void setLanguageId(final int languageId)
	{
		this.languageId = languageId;
	}
}
