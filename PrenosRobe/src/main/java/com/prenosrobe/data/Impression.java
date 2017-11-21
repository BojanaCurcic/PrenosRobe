package com.prenosrobe.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "impression")
public class Impression
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "impression_id")
	private int id;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@Column(name = "comment")
	private String comment;

	@Column(name = "rating")
	private int rating;

	// @ManyToOne
	// @JoinColumn(name="user_id", nullable = false)
	@Transient
	private User user;

	@Column(name = "user_id")
	private int userId;

	/**
	 * Instantiate a new impression.
	 *
	 * @param comment comment
	 * @param rating rating
	 * @param user user
	 */
	public Impression(final String comment, final int rating, final User user)
	{
		this.comment = comment;
		this.rating = rating;
		this.user = user;
		this.userId = user.getId();
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
	 * Get the comment.
	 *
	 * @return comment
	 */
	public String getComment()
	{
		return comment;
	}

	/**
	 * Set the comment.
	 *
	 * @param comment new comment
	 */
	public void setComment(final String comment)
	{
		this.comment = comment;
	}

	/**
	 * Get the rating.
	 *
	 * @return rating
	 */
	public int getRating()
	{
		return rating;
	}

	/**
	 * Set the rating.
	 *
	 * @param rating new rating
	 */
	public void setRating(final int rating)
	{
		this.rating = rating;
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
}
