package com.prenosrobe.service;

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
	public String add(ClaimerOffer claimerOffer)
	{
		String errors = validateClaimerOffer(claimerOffer);
		if (errors.isEmpty())
		{
			try
			{
				claimerOfferRepository.save(claimerOffer);
			} catch (Exception e)
			{
				throw new DataNotSavedException(e.getMessage(), e);
			}
		}

		return errors;
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

		return claimerOfferRepository.findByUserId(user.getId());
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
			return claimerOfferRepository.findByDriverOfferId(driverOfferId);

		return null;
	}

	/**
	 * Update claimer offer.
	 * 
	 * @param updatedClaimerOffer updated claimer offer
	 * @return errors
	 */
	public String updateClaimerOffer(ClaimerOffer updatedClaimerOffer)
	{
		StringBuilder errors = new StringBuilder();
		errors.append(validateClaimerOffer(updatedClaimerOffer));

		if (errors.toString().isEmpty())
		{
			try
			{
				claimerOfferRepository.save(updatedClaimerOffer);
			} catch (Exception e)
			{
				throw new DataNotSavedException(e.getMessage(), e);
			}
		}

		return errors.toString();
	}

	/**
	 * Validate claimer offer.
	 *
	 * @param claimerOffer the claimer offer
	 * @return errors
	 */
	private String validateClaimerOffer(ClaimerOffer claimerOffer)
	{
		StringBuilder errors = new StringBuilder();

		Set<ConstraintViolation<ClaimerOffer>> constraintViolations = validator
				.validate(claimerOffer);
		constraintViolations.iterator().forEachRemaining(constrain -> errors.append(
				"\"" + constrain.getPropertyPath() + "\" " + constrain.getMessage() + ". "));
		if (errors.toString().isEmpty())
		{
			// Validate offer status
			OfferStatus foundOfferStatus = offersStatusRepository
					.findByName(claimerOffer.getOfferStatus().getName());
			if (foundOfferStatus == null)
				errors.append(Messages.UNKNOWN_OFFER_STATUS);
			else
				claimerOffer.setOfferStatus(foundOfferStatus);

			// Validate user
			User foundUser = userRepository.findByEmail(claimerOffer.getUser().getEmail());
			if (foundUser == null)
				errors.append(Messages.UNKNOWN_USER);
			else
				claimerOffer.setUser(foundUser);

			// Find user vehicle
			UserVehicle foundUserVehicle = findUserVehicle(
					claimerOffer.getDriverOffer().getUserVehicle(), errors);
			if (foundUserVehicle != null)
			{
				// Validate driver offer
				DriverOffer foundDriverOffer = driverOfferRepository.findByUserIdDateAndLocations(
						foundUserVehicle.getId(), claimerOffer.getDriverOffer().getDate(),
						claimerOffer.getDriverOffer().getDepartureLocation(),
						claimerOffer.getDriverOffer().getArrivalLocation());
				if (foundDriverOffer == null)
					errors.append(Messages.UNKNOWN_DRIVER_OFFER);
				else
					claimerOffer.setDriverOffer(foundDriverOffer);
			}
			else
				errors.append(Messages.UNKNOWN_DRIVER_OFFER_USER_VEHICLE);
		}

		return errors.toString();
	}

	/**
	 * Find user vehicle.
	 *
	 * @param userVehicle target user Vehicle
	 * @param errors errors
	 * @return found user vehicle
	 */
	private UserVehicle findUserVehicle(UserVehicle userVehicle, StringBuilder errors)
	{
		UserVehicle foundUserVehicle = null;

		User foundUser = userRepository.findByEmail(userVehicle.getUser().getEmail());
		if (foundUser == null)
			errors.append(Messages.UNKNOWN_DRIVER_OFFER_USER_VEHICLE_USER);

		Vehicle foundVehicle = vehicleRepository
				.findByRegistrationNumber(userVehicle.getVehicle().getRegistrationNumber());
		if (foundVehicle == null)
			errors.append(Messages.UNKNOWN_DRIVER_OFFER_USER_VEHICLE_VEHICLE);

		if (foundUser != null && foundVehicle != null)
			foundUserVehicle = userVehicleRepository.findByUserIdAndVehicleId(foundUser.getId(),
					foundVehicle.getId());

		return foundUserVehicle;
	}
}
