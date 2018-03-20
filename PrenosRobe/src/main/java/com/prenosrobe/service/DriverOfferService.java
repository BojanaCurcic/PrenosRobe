package com.prenosrobe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenosrobe.data.Area;
import com.prenosrobe.data.DriverOffer;
import com.prenosrobe.data.OfferStatus;
import com.prenosrobe.data.Station;
import com.prenosrobe.data.User;
import com.prenosrobe.data.UserVehicle;
import com.prenosrobe.data.Vehicle;
import com.prenosrobe.data.VehicleType;
import com.prenosrobe.exception.DataNotSavedException;
import com.prenosrobe.exception.Messages;
import com.prenosrobe.repositories.AreaRepository;
import com.prenosrobe.repositories.DriverOfferRepository;
import com.prenosrobe.repositories.OfferStatusRepository;
import com.prenosrobe.repositories.StationRepository;
import com.prenosrobe.repositories.UserRepository;
import com.prenosrobe.repositories.UserVehicleRepository;
import com.prenosrobe.repositories.VehicleRepository;
import com.prenosrobe.repositories.VehicleTypeRepository;

@Service
public class DriverOfferService
{
	@Autowired
	private DriverOfferRepository driverOfferRepository;

	@Autowired
	private OfferStatusRepository offersStatusRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserVehicleRepository userVehicleRepository;

	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;

	@Autowired
	private StationRepository stationRepository;

	@Autowired
	private AreaRepository areaRepository;

	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * Add the driver offer. Parameter driverOffer should have set fields 'departureLocation', 
	 * 'arrivalLocation', 'date', 'time', 'offerStatus' and 'userVehicle'. 'offerStatus' should 
	 * have set field 'name' and 'userVehicle' should have set all fields.
	 *
	 * @param driverOffer driver offer
	 * @return driverOffer driver offer with all its information
	 */
	public List<String> add(DriverOffer driverOffer)
	{
		List<String> errorList = validateDriverOffer(driverOffer);

		if (errorList.isEmpty())
		{
			try
			{
				DriverOffer savedDriverOffer = new DriverOffer(driverOffer);
				driverOfferRepository.save(savedDriverOffer);
				driverOffer.setId(savedDriverOffer.getId());

				addValidStationsIntoBase(driverOffer);
			} catch (Exception e)
			{
				throw new DataNotSavedException(e.getMessage(), e);
			}
		}
		return errorList;
	}

	/**
	 * Get the driver offer by driver offer id.
	 *
	 * @param id driver offer id
	 * @return driverOffer driver offer with all its information
	 */
	public DriverOffer getDriverOfferById(final Integer id)
	{
		return driverOfferRepository.findOne(id);
	}

	/**
	 * Get my driver offers.
	 *
	 * @return my driver offers
	 * @param token token used for user identification
	 */
	public List<DriverOffer> getMyDriverOffers(final String token)
	{
		List<DriverOffer> myDriverOffer = new ArrayList<>();

		User user = userRepository.findByToken(token);
		List<UserVehicle> userVehicles = userVehicleRepository.findByUserId(user.getId());
		for (UserVehicle userVehicle : userVehicles)
		{
			List<DriverOffer> driverOffers = driverOfferRepository.findByUserVehicle(userVehicle);

			driverOffers.iterator().forEachRemaining(driverOffer -> myDriverOffer.add(driverOffer));
		}

		return myDriverOffer;
	}

	/**
	 * Update driver offer.
	 *
	 * @param updatedDriverOffer updated driver offer
	 * @return error list
	 */
	public List<String> updateDriverOffer(DriverOffer updatedDriverOffer)
	{
		List<String> errorList = new ArrayList<>();
		errorList.addAll(validateDriverOffer(updatedDriverOffer));

		if (errorList.isEmpty())
		{
			try
			{
				DriverOffer driverOffer = new DriverOffer(updatedDriverOffer);
				driverOffer.setId(updatedDriverOffer.getId());
				driverOfferRepository.save(driverOffer);
			} catch (Exception e)
			{
				throw new DataNotSavedException(e.getMessage(), e);
			}
		}

		return errorList;
	}

	/**
	 * Get the all driver offers.
	 *
	 * @return driver offers list of all driver offers
	 */
	public List<DriverOffer> getAll()
	{
		return driverOfferRepository.findAll();
	}

	/**
	 * Validate driver offer.
	 *
	 * @param driverOffer driver offer
	 * @return error list
	 */
	private List<String> validateDriverOffer(DriverOffer driverOffer)
	{
		List<String> errorList = new ArrayList<>();

		Set<ConstraintViolation<DriverOffer>> constraintViolations = validator
				.validate(driverOffer);
		constraintViolations.iterator().forEachRemaining(constrain -> errorList
				.add("\"" + constrain.getPropertyPath() + "\" " + constrain.getMessage() + ". "));

		if (errorList.isEmpty())
		{
			// Validate offer status
			OfferStatus foundOfferStatus = offersStatusRepository
					.findByName(driverOffer.getOfferStatus().getName());
			if (foundOfferStatus == null)
				errorList.add(Messages.UNKNOWN_OFFER_STATUS);
			else
				driverOffer.setOfferStatus(foundOfferStatus);

			// Find user
			User foundUser = findUser(driverOffer.getUserVehicle().getUser().getEmail(), errorList);

			// Find vehicle
			Vehicle foundVehicle = findVehicle(
					driverOffer.getUserVehicle().getVehicle().getRegistrationNumber(),
					driverOffer.getUserVehicle().getVehicle().getVehicleType().getName(),
					errorList);

			// Validate user vehicle
			if (foundUser != null && foundVehicle != null)
			{
				UserVehicle foundUserVehicle = userVehicleRepository
						.findByUserIdAndVehicleId(foundUser.getId(), foundVehicle.getId());
				if (foundUserVehicle == null)
				{
					try
					{
						foundUserVehicle = new UserVehicle(foundUser, foundVehicle);
						userVehicleRepository.save(foundUserVehicle);
					} catch (Exception e)
					{
						throw new DataNotSavedException(e.getMessage(), e);
					}
				}
				driverOffer.setUserVehicle(foundUserVehicle);
			}
		}

		return errorList;
	}

	/**
	 * Find user.
	 *
	 * @param email email
	 * @param errorList error list
	 * @return found user
	 */
	private User findUser(final String email, List<String> errorList)
	{
		User foundUser = userRepository.findByEmail(email);
		if (foundUser == null)
			errorList.add(Messages.UNKNOWN_USER_VEHICLE_USER);

		return foundUser;
	}

	/**
	 * Find vehicle.
	 *
	 * @param registrationNumber registration number
	 * @param vehicleTypeName vehicle type name
	 * @param errorList error list
	 * @return found vehicle
	 */
	private Vehicle findVehicle(final String registrationNumber, final String vehicleTypeName,
			List<String> errorList)
	{
		Vehicle foundVehicle = vehicleRepository.findByRegistrationNumber(registrationNumber);
		if (foundVehicle == null)
		{
			VehicleType foundVehicleType = vehicleTypeRepository.findByName(vehicleTypeName);
			if (foundVehicleType == null)
				errorList.add(Messages.UNKNOWN_USER_VEHICLE_VEHICLE_VEHICLE_TYPE);
			else
			{
				try
				{
					foundVehicle = new Vehicle(registrationNumber, foundVehicleType);
					vehicleRepository.save(foundVehicle);
				} catch (Exception e)
				{
					throw new DataNotSavedException(e.getMessage(), e);
				}
			}
		}

		return foundVehicle;
	}

	/**
	 * Get the all offer statuses.
	 *
	 * @return offerStatuses list of all supported offer statuses
	 */
	public List<OfferStatus> getAllOfferStatuses()
	{
		return offersStatusRepository.findAll();
	}

	/**
	 * Get the vehicle by registration number.
	 *
	 * @param registrationNumber registration number
	 * @return the vehicle by registration number
	 */
	public UserVehicle getUserVehicleByRegistrationNumber(final String registrationNumber,
			String token)
	{
		UserVehicle foundUserVehicle = null;
		Vehicle foundVehicle = vehicleRepository.findByRegistrationNumber(registrationNumber);
		User foundUser = userRepository.findByToken(token);
		if (foundVehicle != null && foundUser != null)
		{
			foundUserVehicle = userVehicleRepository.findByUserIdAndVehicleId(foundUser.getId(),
					foundVehicle.getId());
			if (foundUserVehicle == null)
			{
				try
				{
					foundUserVehicle = userVehicleRepository
							.save(new UserVehicle(foundUser, foundVehicle));
				} catch (Exception e)
				{
					throw new DataNotSavedException(e.getMessage(), e);
				}
			}
		}

		return foundUserVehicle;
	}

	/**
	 * Get the all vehicle types.
	 *
	 * @return vehicleTypes list of all supported vehicle types
	 */
	public List<VehicleType> getAllVehicleTypes()
	{
		return vehicleTypeRepository.findAll();
	}

	/**
	 * Get the all stations.
	 *
	 * @return stations list of all currently supported stations
	 */
	public List<Station> getAllStations()
	{
		return stationRepository.findAll();
	}

	/**
	 * Get the all areas.
	 *
	 * @return areas list of all currently supported areas
	 */
	public List<Area> getAllAreas()
	{
		return areaRepository.findAll();
	}

	/**
	 * Add the valid stations into base and remove invalid from passed driverOffer.
	 *
	 * @param driverOffer the driver offer
	 */
	private void addValidStationsIntoBase(DriverOffer driverOffer)
	{
		// TODO: implementirati logiku za dodavanje stanica u bazu!!!!!!!!!!!!!
	}
}
