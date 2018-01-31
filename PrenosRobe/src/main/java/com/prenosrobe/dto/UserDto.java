package com.prenosrobe.dto;

import java.util.ArrayList;
import java.util.List;

import com.prenosrobe.data.ClaimerOffer;
import com.prenosrobe.data.DriverOffer;
import com.prenosrobe.data.Impression;
import com.prenosrobe.data.Language;
import com.prenosrobe.data.User;
import com.prenosrobe.data.Vehicle;

public class UserDto
{
	private Integer id;

	private String name;

	private String surname;

	private String username;

	private String password;

	private String email;

	private String phoneNumber;

	private String photo;

	private String token;

	private boolean active;

	private List<Impression> impressions = new ArrayList<>();

	private List<LanguageDto> languages = new ArrayList<>();

	private List<Vehicle> vehicles = new ArrayList<>();

	private List<DriverOffer> driverOffers = new ArrayList<>();

	private List<ClaimerOffer> claimerOffers = new ArrayList<>();

	public UserDto()
	{
	}

	public UserDto(final User user)
	{
		this.id = user.getId();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.phoneNumber = user.getPhoneNumber();
		this.photo = user.getPhoto();
		this.token = user.getToken();
		this.active = user.isActive();
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(final Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(final String surname)
	{
		this.surname = surname;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(final String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(final String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getPhoto()
	{
		return photo;
	}

	public void setPhoto(final String photo)
	{
		this.photo = photo;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(final String token)
	{
		this.token = token;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(final boolean active)
	{
		this.active = active;
	}

	public List<Impression> getImpressions()
	{
		return impressions;
	}

	public void setImpressions(final List<Impression> impressions)
	{
		this.impressions = impressions;
	}

	public void addImpression(final Impression impression)
	{
		this.impressions.add(impression);
	}

	public void removeImpression(final Impression impression)
	{
		this.impressions.remove(impression);
	}

	public List<Vehicle> getVehicles()
	{
		return vehicles;
	}

	public void setVehicles(final List<Vehicle> vehicles)
	{
		this.vehicles = vehicles;
	}

	public void addVehicle(final Vehicle vehicle)
	{
		this.vehicles.add(vehicle);
	}

	public void removeVehicle(final Vehicle vehicle)
	{
		this.vehicles.remove(vehicle);
	}

	public List<LanguageDto> getLanguages()
	{
		return languages;
	}

	public void setLanguages(final List<LanguageDto> languages)
	{
		this.languages = languages;
	}

	public void addLanguage(final LanguageDto language)
	{
		this.languages.add(language);
	}

	public void removeLanguage(final Language language)
	{
		this.languages.remove(language);
	}

	public List<DriverOffer> getDriverOffers()
	{
		return driverOffers;
	}

	public void setDriverOffers(final List<DriverOffer> driverOffers)
	{
		this.driverOffers = driverOffers;
	}

	public void addDriverOffer(final DriverOffer driverOffer)
	{
		this.driverOffers.add(driverOffer);
	}

	public void removeDriverOffer(final DriverOffer driverOffer)
	{
		this.driverOffers.remove(driverOffer);
	}

	public List<ClaimerOffer> getClaimerOffers()
	{
		return claimerOffers;
	}

	public void setClaimerOffers(final List<ClaimerOffer> claimerOffers)
	{
		this.claimerOffers = claimerOffers;
	}

	public void addClaimerOffer(final ClaimerOffer claimerOffer)
	{
		this.claimerOffers.add(claimerOffer);
	}

	public void removeClaimerOffer(final ClaimerOffer claimerOffer)
	{
		this.claimerOffers.remove(claimerOffer);
	}
}
