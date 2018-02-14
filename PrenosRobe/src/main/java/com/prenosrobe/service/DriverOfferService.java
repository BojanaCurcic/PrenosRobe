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
	public String add(DriverOffer driverOffer)
	{
		String errors = validateDriverOffer(driverOffer);

		if (errors.isEmpty())
		{
			DriverOffer savedDriverOffer = new DriverOffer(driverOffer);
			driverOfferRepository.save(savedDriverOffer);
			driverOffer.setId(savedDriverOffer.getId());

			addValidStationsIntoBase(driverOffer);
		}
		return errors;
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
	 * @param driverOffer the driver offer
	 */
	public boolean updateDriverOffer(DriverOffer driverOffer)
	{
		// TODO: imprelent logic for updating driver offer
		return true;
	}

	/**
	 * Update driver offer status.
	 *
	 * @param driverOffer driver offer
	 * @param offerStatusName offer status name
	 * @return errors
	 */
	public String updateDriverOfferStatus(DriverOffer driverOffer, final String offerStatus)
	{
		StringBuilder errors = new StringBuilder();
		errors.append(validateDriverOffer(driverOffer));

		OfferStatus foundOfferStatus = offersStatusRepository.findByName(offerStatus);
		if (errors.toString().isEmpty() && foundOfferStatus != null)
		{
			driverOffer.setOfferStatus(foundOfferStatus);
			driverOfferRepository.save(driverOffer);
		}
		else
			errors.append("Unknown offer status. ");

		return errors.toString();
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
	 * @return errors
	 */
	private String validateDriverOffer(DriverOffer driverOffer)
	{
		StringBuilder errors = new StringBuilder();

		Set<ConstraintViolation<DriverOffer>> constraintViolations = validator
				.validate(driverOffer);
		constraintViolations.iterator().forEachRemaining(constrain -> errors.append(
				"\"" + constrain.getPropertyPath() + "\" " + constrain.getMessage() + ". "));

		if (errors.toString().isEmpty())
		{
			// Validate offer status
			OfferStatus foundOfferStatus = offersStatusRepository
					.findByName(driverOffer.getOfferStatus().getName());
			if (foundOfferStatus == null)
				errors.append("\"offerStatus\" is unknown. ");
			else
				driverOffer.setOfferStatus(foundOfferStatus);

			// Find user
			User foundUser = findUser(driverOffer.getUserVehicle().getUser().getEmail(), errors);

			// Find vehicle
			Vehicle foundVehicle = findVehicle(
					driverOffer.getUserVehicle().getVehicle().getRegistrationNumber(),
					driverOffer.getUserVehicle().getVehicle().getVehicleType().getName(), errors);

			// Validate user vehicle
			if (foundUser != null && foundVehicle != null)
			{
				UserVehicle foundUserVehicle = userVehicleRepository
						.findByUserIdAndVehicleId(foundUser.getId(), foundVehicle.getId());
				if (foundUserVehicle == null)
				{
					foundUserVehicle = new UserVehicle(foundUser, foundVehicle);
					userVehicleRepository.save(foundUserVehicle);
				}
				driverOffer.setUserVehicle(foundUserVehicle);
			}
		}

		return errors.toString();
	}

	/**
	 * Find user.
	 *
	 * @param email email
	 * @param errors errors
	 * @return found user
	 */
	private User findUser(final String email, StringBuilder errors)
	{
		User foundUser = userRepository.findByEmail(email);
		if (foundUser == null)
			errors.append("\"userVehicle.user\" is unknown. ");

		return foundUser;
	}

	/**
	 * Find vehicle.
	 *
	 * @param registrationNumber registration number
	 * @param vehicleTypeName vehicle type name
	 * @param errors errors
	 * @return found vehicle
	 */
	private Vehicle findVehicle(final String registrationNumber, final String vehicleTypeName,
			StringBuilder errors)
	{
		Vehicle foundVehicle = vehicleRepository.findByRegistrationNumber(registrationNumber);
		if (foundVehicle == null)
		{
			VehicleType foundVehicleType = vehicleTypeRepository.findByName(vehicleTypeName);
			if (foundVehicleType == null)
				errors.append("\"userVehicle.vehicle.vehicleType\" is unknown. ");
			else
			{
				foundVehicle = new Vehicle(registrationNumber, foundVehicleType);
				vehicleRepository.save(foundVehicle);
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
				foundUserVehicle = userVehicleRepository
						.save(new UserVehicle(foundUser, foundVehicle));
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
		// TODO: implement logic for adding stations into data base!!!!!!!!!!!!!
	}
}
