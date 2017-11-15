package com.prenosrobe.deo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "impression")
public class Impression
{
	@Id
	@Column(name = "impression_id")
	private int id;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "comment")
	private String comment;

	@Column(name = "rating")
	private int rating;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

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
}
