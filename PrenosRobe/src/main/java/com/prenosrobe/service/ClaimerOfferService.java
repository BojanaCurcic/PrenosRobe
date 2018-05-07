package com.prenosrobe.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenosrobe.data.ClaimerOffer;
import com.prenosrobe.data.DriverOffer;
import com.prenosrobe.data.OfferStatus;
import com.prenosrobe.data.User;
import com.prenosrobe.data.UserVehicle;
import com.prenosrobe.data.Vehicle;
import com.prenosrobe.exception.DataNotSavedException;
import com.prenosrobe.exception.Messages;
import com.prenosrobe.repositories.ClaimerOfferRepository;
import com.prenosrobe.repositories.DriverOfferRepository;
import com.prenosrobe.repositories.OfferStatusRepository;
import com.prenosrobe.repositories.UserRepository;
import com.prenosrobe.repositories.UserVehicleRepository;
import com.prenosrobe.repositories.VehicleRepository;

@Service
public class ClaimerOfferService
{
	@Autowired
	private ClaimerOfferRepository claimerOfferRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OfferStatusRepository offersStatusRepository;

	@Autowired
	private DriverOfferRepository driverOfferRepository;

	@Autowired
	private UserVehicleRepository userVehicleRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * Adds the claimer offer. Parameter claimerOffer should have set fields 'departureLocation',
	 * 'arrivalLocation', 'data', 'photo', 'user', 'driverOffer' and 'offerStatus'. 'user', 'driverOffer' 
	 * and 'offerStatus' should have set all fields.
	 *
	 * @param claimerOffer claimer offer
	 * @return claimerOffer claimer offer with all its information
	 */
	public List<String> add(ClaimerOffer claimerOffer)
	{
		List<String> errorList = validateClaimerOffer(claimerOffer);
		if (errorList.isEmpty())
		{
			try
			{
				claimerOfferRepository.save(claimerOffer);
			} catch (Exception e)
			{
				throw new DataNotSavedException(e.getMessage(), e);
			}
		}

		return errorList;
	}

	/**
	 * Get the claimer offer by claimer offer id.
	 *
	 * @param id claimer offer id
	 * @return claimerOffer claimer offer with all its information
	 */
	public ClaimerOffer getClaimerOfferById(final Integer id)
	{
		return claimerOfferRepository.findOne(id);
	}

	/**
	 * Get my claimer offers.
	 *
	 * @param token token used for user identification
	 * @return my claimer offers
	 */
	public List<ClaimerOffer> myClaimerOffers(final String token)
	{
		User user = userRepository.findByToken(token);
		List<ClaimerOffer> claimerOffers = claimerOfferRepository.findByUserId(user.getId());
		claimerOffers.sort(new SortByID());
		
		return claimerOffers;
	}

	/**
	 * Get the claimer offers given for driver offer.
	 *
	 * @param driverOfferId driver offer id
	 * @return claimer offers given for driver offer
	 */
	public List<ClaimerOffer> getClaimerOffersByDriverOfferId(final int driverOfferId)
	{
		if (driverOfferRepository.findOne(driverOfferId) != null)
		{
			List<ClaimerOffer> claimerOffers = claimerOfferRepository.findByDriverOfferId(driverOfferId);
			claimerOffers.sort(new SortByID());
			
			return claimerOffers;
		}

		return null;
	}
	
	/**
	 * Comparator for sorting claimer offers
	 * by theirs IDs. Offers with bigger IDs will be first ones. 
	 */
	public class SortByID implements Comparator<ClaimerOffer>
	{
	    public int compare(ClaimerOffer a, ClaimerOffer b)
	    { 
	    	return b.getId() - a.getId();
	    }
	}

	/**
	 * Update claimer offer.
	 * 
	 * @param updatedClaimerOffer updated claimer offer
	 * @return error list
	 */
	public List<String> updateClaimerOffer(ClaimerOffer updatedClaimerOffer)
	{
		List<String> errorList = new ArrayList<>();
		errorList.addAll(validateClaimerOffer(updatedClaimerOffer));

		if (errorList.isEmpty())
		{
			try
			{
				claimerOfferRepository.save(updatedClaimerOffer);
			} catch (Exception e)
			{
				throw new DataNotSavedException(e.getMessage(), e);
			}
		}

		return errorList;
	}

	/**
	 * Validate claimer offer.
	 *
	 * @param claimerOffer the claimer offer
	 * @return error list
	 */
	private List<String> validateClaimerOffer(ClaimerOffer claimerOffer)
	{
		List<String> errorList = new ArrayList<>();

		Set<ConstraintViolation<ClaimerOffer>> constraintViolations = validator
				.validate(claimerOffer);
		constraintViolations.iterator().forEachRemaining(constrain -> errorList.add(
				"\"" + constrain.getPropertyPath() + "\" " + constrain.getMessage() + ". "));
		if (errorList.isEmpty())
		{
			// Validate offer status
			OfferStatus foundOfferStatus = offersStatusRepository
					.findByName(claimerOffer.getOfferStatus().getName());
			if (foundOfferStatus == null)
				errorList.add(Messages.UNKNOWN_OFFER_STATUS);
			else
				claimerOffer.setOfferStatus(foundOfferStatus);

			// Validate user
			User foundUser = userRepository.findByEmail(claimerOffer.getUser().getEmail());
			if (foundUser == null)
				errorList.add(Messages.UNKNOWN_USER);
			else
				claimerOffer.setUser(foundUser);

			// Find user vehicle
			UserVehicle foundUserVehicle = findUserVehicle(
					claimerOffer.getDriverOffer().getUserVehicle(), errorList);
			if (foundUserVehicle != null)
			{
				// Validate driver offer
				DriverOffer foundDriverOffer = driverOfferRepository.findByUserIdDateAndLocations(
						foundUserVehicle.getId(), claimerOffer.getDriverOffer().getDate(),
						claimerOffer.getDriverOffer().getDepartureLocation(),
						claimerOffer.getDriverOffer().getArrivalLocation());
				if (foundDriverOffer == null)
					errorList.add(Messages.UNKNOWN_DRIVER_OFFER);
				else
					claimerOffer.setDriverOffer(foundDriverOffer);
			}
			else
				errorList.add(Messages.UNKNOWN_DRIVER_OFFER_USER_VEHICLE);
		}

		return errorList;
	}

	/**
	 * Find user vehicle.
	 *
	 * @param userVehicle target user Vehicle
	 * @param erroList error list
	 * @return found user vehicle
	 */
	private UserVehicle findUserVehicle(UserVehicle userVehicle, List<String> errorList)
	{
		UserVehicle foundUserVehicle = null;

		User foundUser = userRepository.findByEmail(userVehicle.getUser().getEmail());
		if (foundUser == null)
			errorList.add(Messages.UNKNOWN_DRIVER_OFFER_USER_VEHICLE_USER);

		Vehicle foundVehicle = vehicleRepository
				.findByRegistrationNumber(userVehicle.getVehicle().getRegistrationNumber());
		if (foundVehicle == null)
			errorList.add(Messages.UNKNOWN_DRIVER_OFFER_USER_VEHICLE_VEHICLE);

		if (foundUser != null && foundVehicle != null)
			foundUserVehicle = userVehicleRepository.findByUserIdAndVehicleId(foundUser.getId(),
					foundVehicle.getId());

		return foundUserVehicle;
	}
}
