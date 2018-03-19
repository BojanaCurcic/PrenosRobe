package com.prenosrobe.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "language")
public class Language implements Serializable
{
	private static final long serialVersionUID = 4145308471230297139L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "language_id")
	private Integer id;

	@NotEmpty
	@Column(name = "name")
	private String name;

	/**
	 * Instantiate a new Language.
	 */
	public Language()
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
	 * @param name name
	 */
	public void setName(final String name)
	{
		this.name = name;
	}
}
