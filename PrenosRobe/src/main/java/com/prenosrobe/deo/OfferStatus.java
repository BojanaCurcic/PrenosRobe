package com.prenosrobe.deo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "offer_status")
public class OfferStatus
{
	@Id
	@Column(name = "offer_status_id")
	private int id;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "name")
	private String name;

	@OneToMany
	private List<ClaimerOffer> claimerOffer = new ArrayList<ClaimerOffer>();

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
	 * Get the claimer offer.
	 *
	 * @return claimer offer
	 */
	public List<ClaimerOffer> getClaimerOffer()
	{
		return claimerOffer;
	}

	/**
	 * Set the claimer offer.
	 *
	 * @param claimerOffer new claimer offer
	 */
	public void setClaimerOffer(final List<ClaimerOffer> claimerOffer)
	{
		this.claimerOffer = claimerOffer;
	}
}
