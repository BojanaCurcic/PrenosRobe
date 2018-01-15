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
@Table(name = "impression")
@SuppressWarnings("serial")
public class Impression implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "impression_id")
	private Integer id;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@Column(name = "is_driver")
	private boolean isDriver;

	@Transient
	private User user;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "picked_on_time")
	private int pickedOnTime;

	@Column(name = "delivered_on_time")
	private int deliveredOnTime;

	@Column(name = "delivered_undamaged")
	private int deliveredUndamaged;

	@Column(name = "delivered")
	private int delivered;

	@Column(name = "correctly_paid")
	private int correctlyPaid;

	@Column(name = "comment")
	private String comment;

	/**
	 * Instantiate a new Impression.
	 */
	public Impression()
	{
	}

	/**
	 * Instantiate a new Impression if isDriver is true.
	 *
	 * @param isDriver is driver
	 * @param userId user id
	 * @param pickedOnTime picked on time
	 * @param deliveredOnTime delivered on time
	 * @param deliveredUndamaged delivered undamaged
	 * @param delivered delivered
	 * @param comment comment
	 */
	public Impression(final boolean isDriver, final int userId, final int pickedOnTime,
			final int deliveredOnTime, final int deliveredUndamaged, final int delivered,
			final String comment)
	{
		this.isDriver = isDriver;
		this.userId = userId;
		this.pickedOnTime = pickedOnTime;
		this.deliveredOnTime = deliveredOnTime;
		this.deliveredUndamaged = deliveredUndamaged;
		this.delivered = delivered;
		this.comment = comment;
	}

	/**
	 * Instantiate a new Impression if isDriver is false.
	 *
	 * @param isDriver is driver
	 * @param userId user id
	 * @param pickedOnTime picked on time
	 * @param deliveredOnTime delivered on time
	 * @param correctlyPaid correctly paid
	 * @param comment comment
	 */
	public Impression(final boolean isDriver, final int userId, final int pickedOnTime,
			final int deliveredOnTime, final int correctlyPaid, final String comment)
	{
		this.isDriver = isDriver;
		this.userId = userId;
		this.pickedOnTime = pickedOnTime;
		this.deliveredOnTime = deliveredOnTime;
		this.correctlyPaid = correctlyPaid;
		this.comment = comment;
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
	 * Check if is driver.
	 *
	 * @return true, if is driver
	 */
	public boolean isDriver()
	{
		return isDriver;
	}

	/**
	 * Set the driver.
	 *
	 * @param isDriver new driver
	 */
	public void setDriver(final boolean isDriver)
	{
		this.isDriver = isDriver;
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
	 * Get the picked on time.
	 *
	 * @return picked on time
	 */
	public int getPickedOnTime()
	{
		return pickedOnTime;
	}

	/**
	 * Set the picked on time.
	 *
	 * @param pickedOnTime new picked on time
	 */
	public void setPickedOnTime(final int pickedOnTime)
	{
		this.pickedOnTime = pickedOnTime;
	}

	/**
	 * Get the delivered on time.
	 *
	 * @return delivered on time
	 */
	public int getDeliveredOnTime()
	{
		return deliveredOnTime;
	}

	/**
	 * Set the delivered on time.
	 *
	 * @param deliveredOnTime new delivered on time
	 */
	public void setDeliveredOnTime(final int deliveredOnTime)
	{
		this.deliveredOnTime = deliveredOnTime;
	}

	/**
	 * Get the delivered undamaged.
	 *
	 * @return delivered undamaged
	 */
	public int getDeliveredUndamaged()
	{
		return deliveredUndamaged;
	}

	/**
	 * Set the delivered undamaged.
	 *
	 * @param deliveredUndamaged new delivered undamaged
	 */
	public void setDeliveredUndamaged(final int deliveredUndamaged)
	{
		this.deliveredUndamaged = deliveredUndamaged;
	}

	/**
	 * Get the delivered.
	 *
	 * @return delivered
	 */
	public int getDelivered()
	{
		return delivered;
	}

	/**
	 * Set the delivered.
	 *
	 * @param delivered new delivered
	 */
	public void setDelivered(final int delivered)
	{
		this.delivered = delivered;
	}

	/**
	 * Get the correctly paid.
	 *
	 * @return correctly paid
	 */
	public int getCorrectlyPaid()
	{
		return correctlyPaid;
	}

	/**
	 * Set the correctly paid.
	 *
	 * @param correctlyPaid new correctly paid
	 */
	public void setCorrectlyPaid(int correctlyPaid)
	{
		this.correctlyPaid = correctlyPaid;
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
}
