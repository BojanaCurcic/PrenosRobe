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
@Table(name = "offer_status")
public class OfferStatus
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "offer_status_id")
	private int id;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@Column(name = "name")
	private String name;

	@Transient
	private List<ClaimerOffer> claimerOffers = new ArrayList<ClaimerOffer>();

	/**
	 * Instantiate a new offer status.
	 *
	 * @param name name
	 */
	public OfferStatus(String name)
	{
		this.name = name;
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
}
