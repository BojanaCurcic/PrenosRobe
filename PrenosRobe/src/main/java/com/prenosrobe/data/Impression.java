package com.prenosrobe.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "impression")
public class Impression implements Serializable
{
	private static final long serialVersionUID = 2258394587369467912L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "impression_id")
	private Integer id;

	@NotNull
	@Column(name = "is_driver")
	private Boolean driver;

	@NotNull
	@Column(name = "user_id")
	private Integer userId;

	@NotNull
	@Min(1)
	@Max(10)
	@Column(name = "picked_on_time")
	private Integer pickedOnTime;

	@NotNull
	@Min(1)
	@Max(10)
	@Column(name = "delivered_on_time")
	private Integer deliveredOnTime;

	@Min(1)
	@Max(10)
	@Column(name = "delivered_undamaged")
	private Integer deliveredUndamaged;

	@Min(1)
	@Max(10)
	@Column(name = "delivered")
	private Integer delivered;

	@Min(1)
	@Max(10)
	@Column(name = "correctly_paid")
	private Integer correctlyPaid;

	@NotEmpty
	@Column(name = "comment")
	private String comment;

	/**
	 * Instantiate a new Impression.
	 */
	public Impression()
	{
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
	 * Check if is driver.
	 *
	 * @return true, if is driver
	 */
	public Boolean isDriver()
	{
		return driver;
	}

	/**
	 * Set the driver.
	 *
	 * @param driver new driver
	 */
	public void setDriver(final Boolean driver)
	{
		this.driver = driver;
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
	public void setUserId(final Integer userId)
	{
		this.userId = userId;
	}

	/**
	 * Get the picked on time.
	 *
	 * @return picked on time
	 */
	public Integer getPickedOnTime()
	{
		return pickedOnTime;
	}

	/**
	 * Set the picked on time.
	 *
	 * @param pickedOnTime new picked on time
	 */
	public void setPickedOnTime(final Integer pickedOnTime)
	{
		this.pickedOnTime = pickedOnTime;
	}

	/**
	 * Get the delivered on time.
	 *
	 * @return delivered on time
	 */
	public Integer getDeliveredOnTime()
	{
		return deliveredOnTime;
	}

	/**
	 * Set the delivered on time.
	 *
	 * @param deliveredOnTime new delivered on time
	 */
	public void setDeliveredOnTime(final Integer deliveredOnTime)
	{
		this.deliveredOnTime = deliveredOnTime;
	}

	/**
	 * Get the delivered undamaged.
	 *
	 * @return delivered undamaged
	 */
	public Integer getDeliveredUndamaged()
	{
		return deliveredUndamaged;
	}

	/**
	 * Set the delivered undamaged.
	 *
	 * @param deliveredUndamaged new delivered undamaged
	 */
	public void setDeliveredUndamaged(final Integer deliveredUndamaged)
	{
		this.deliveredUndamaged = deliveredUndamaged;
	}

	/**
	 * Get the delivered.
	 *
	 * @return delivered
	 */
	public Integer getDelivered()
	{
		return delivered;
	}

	/**
	 * Set the delivered.
	 *
	 * @param delivered new delivered
	 */
	public void setDelivered(final Integer delivered)
	{
		this.delivered = delivered;
	}

	/**
	 * Get the correctly paid.
	 *
	 * @return correctly paid
	 */
	public Integer getCorrectlyPaid()
	{
		return correctlyPaid;
	}

	/**
	 * Set the correctly paid.
	 *
	 * @param correctlyPaid new correctly paid
	 */
	public void setCorrectlyPaid(final Integer correctlyPaid)
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
