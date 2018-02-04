package com.prenosrobe.service;

import java.util.ArrayList;
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
import com.prenosrobe.data.VehicleType;
import com.prenosrobe.dto.ClaimerOfferDto;
import com.prenosrobe.dto.DriverOfferDto;
import com.prenosrobe.dto.OfferStatusDto;
import com.prenosrobe.dto.UserDto;
import com.prenosrobe.dto.UserVehicleDto;
import com.prenosrobe.dto.VehicleDto;
import com.prenosrobe.dto.VehicleTypeDto;
import com.prenosrobe.repositories.ClaimerOfferRepository;
import com.prenosrobe.repositories.DriverOfferRepository;
import com.prenosrobe.repositories.OfferStatusRepository;
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
	private ClaimerOfferRepository claimerOfferRepository;

	@Autowired
	private UserService userService;

	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * Add the driver offer. Parameter driverOfferDto should have set fields 'departureLocation', 
	 * 'arrivalLocation', 'date', 'time', 'offerStatus' and 'userVehicle'. 'offerStatus' and 
	 * 'userVehicle' should have set fields 'id'.
	 *
	 * @param driverOfferDto driver offer
	 * @return driverOfferDto driver offer with all its information
	 */
	public String add(DriverOfferDto driverOfferDto)
	{
		String errors = validateDriverOffer(driverOfferDto);

		if (errors.isEmpty())
		{
			DriverOffer newDriverOffer = driverOfferRepository
					.save(new DriverOffer(driverOfferDto));
			driverOfferDto.setId(newDriverOffer.getId());

			// TODO: STATIONS!!!!!!!!!!!!!!!!
			addValidStationsIntoBase(driverOfferDto);
		}
		return errors;
	}

	/**
	 * Get the driver offer by driver offer id.
	 *
	 * @param id driver offer id
	 * @return driverOfferDto driver offer with all its information
	 */
	public DriverOfferDto getDriverOfferById(final int id)
	{
		DriverOffer driverOffer = driverOfferRepository.findOne(id);
		if (driverOffer != null)
		{
			return getFullFilledDriverOfferDto(driverOffer);
		}

		return null;
	}

	/**
	 * Get my driver offers.
	 *
	 * @return my driver offers
	 * @param token token used for user identification
	 */
	public List<DriverOfferDto> getMyDriverOffers(final String token)
	{
		List<DriverOfferDto> myDriverOfferDtos = new ArrayList<>();

		User user = userRepository.findByToken(token);
		List<UserVehicle> userVehicles = userVehicleRepository.findByUserId(user.getId());
		for (UserVehicle userVehicle : userVehicles)
		{
			List<DriverOffer> driverOffers = driverOfferRepository
					.findByUserVehicleId(userVehicle.getId());
			for (DriverOffer driverOffer : driverOffers)
			{
				DriverOfferDto myDriverOfferDto = new DriverOfferDto(driverOffer);
				myDriverOfferDto.setOfferStatus(new OfferStatusDto(
						offersStatusRepository.findOne(driverOffer.getOfferStatusId())));

				myDriverOfferDtos.add(myDriverOfferDto);
			}
		}

		return myDriverOfferDtos;
	}

	public boolean updateDriverOffer(DriverOfferDto driverOfferDto)
	{
		// TODO: add logic
		return true;
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
	 * @param driverOfferDto the driver offer
	 * @return errors
	 */
	public String validateDriverOffer(DriverOfferDto driverOfferDto)
	{
		StringBuilder errors = new StringBuilder();

		Set<ConstraintViolation<DriverOffer>> constraintViolations = validator
				.validate(new DriverOffer(driverOfferDto));
		constraintViolations.iterator().forEachRemaining(constrain -> errors.append(
				"\"" + constrain.getPropertyPath() + "\" " + constrain.getMessage() + ". "));

		if (driverOfferDto.getOfferStatus() != null
				&& driverOfferDto.getOfferStatus().getId() != null
				&& offersStatusRepository.findOne(driverOfferDto.getOfferStatus().getId()) == null)
			errors.append("\"offerStatus\" is unknown. ");

		if (driverOfferDto.getUserVehicle() != null
				&& driverOfferDto.getUserVehicle().getId() != null
				&& userVehicleRepository.findOne(driverOfferDto.getUserVehicle().getId()) == null)
			errors.append("\"userVehicle\" is unknown. ");

		return errors.toString();
	}

	/**
	 * Get the all offer statuses.
	 *
	 * @return offerStatuses list of all supported offer statuses
	 */
	public List<OfferStatusDto> getAllOfferStatuses()
	{
		List<OfferStatus> offerStatuses = offersStatusRepository.findAll();
		List<OfferStatusDto> offerStatusesDtos = new ArrayList<>();
		offerStatuses.iterator().forEachRemaining(
				offerStatus -> offerStatusesDtos.add(new OfferStatusDto(offerStatus)));

		return offerStatusesDtos;
	}

	/**
	 * Get the vehicle by registration number.
	 *
	 * @param registrationNumber registration number
	 * @return the vehicle by registration number
	 */
	public UserVehicleDto getUserVehicleByRegistrationNumber(final String registrationNumber,
			String token)
	{
		UserVehicleDto userVehicleDto = null;
		Vehicle foundVehicle = vehicleRepository.findByRegistrationNumber(registrationNumber);
		User foundUser = userRepository.findByToken(token);
		if (foundVehicle != null && foundUser != null)
		{
			UserVehicle foundUserVehicle = userVehicleRepository
					.findByUserIdAndVehicleId(foundUser.getId(), foundVehicle.getId());
			if (foundUserVehicle == null)
				foundUserVehicle = userVehicleRepository
						.save(new UserVehicle(foundUser.getId(), foundVehicle.getId()));

			VehicleDto vehicleDto = new VehicleDto(foundVehicle);
			vehicleDto.setVehicleType(new VehicleTypeDto(
					vehicleTypeRepository.findOne(foundVehicle.getVehicleTypeId())));

			userVehicleDto = new UserVehicleDto(foundUserVehicle.getId(), new UserDto(foundUser),
					vehicleDto);
		}

		return userVehicleDto;
	}

	/**
	 * Get the all vehicle types.
	 *
	 * @return vehicleTypes list of all supported vehicle types
	 */
	public List<VehicleTypeDto> getAllVehicleTypes()
	{
		List<VehicleType> vehicleTypes = vehicleTypeRepository.findAll();
		List<VehicleTypeDto> vehicleTypeDtos = new ArrayList<>();
		vehicleTypes.iterator().forEachRemaining(
				vehicleType -> vehicleTypeDtos.add(new VehicleTypeDto(vehicleType)));

		return vehicleTypeDtos;
	}

	/**
	 * Add the valid stations into base and remove invalid from passed driverOfferDto.
	 *
	 * @param driverOfferDto the driver offer dto
	 */
	private void addValidStationsIntoBase(DriverOfferDto driverOfferDto)
	{
		// TODO: implement logic for adding stations into data base!!!!!!!!!!!!!
	}

	/**
	 * Get the full filled DriverOfferDto.
	 *
	 * @param driverOffer the driver offer
	 * @return the full filled DriverOfferDto
	 */
	private DriverOfferDto getFullFilledDriverOfferDto(DriverOffer driverOffer)
	{
		DriverOfferDto driverOfferDto = new DriverOfferDto(driverOffer);

		// Set offerStatus.
		OfferStatusDto offerStatusDto = new OfferStatusDto(
				offersStatusRepository.findOne(driverOffer.getOfferStatusId()));
		driverOfferDto.setOfferStatus(offerStatusDto);

		// Set userVehicle.
		UserVehicle userVehicle = userVehicleRepository.findOne(driverOffer.getUserVehicleId());
		Vehicle vehicle = vehicleRepository.findOne(userVehicle.getVehicleId());
		VehicleDto vehicleDto = new VehicleDto(vehicle);
		vehicleDto.setVehicleType(
				new VehicleTypeDto(vehicleTypeRepository.findOne(vehicle.getVehicleTypeId())));
		User user = userRepository.findOne(userVehicle.getUserId());
		UserVehicleDto userVehicleDto = new UserVehicleDto(userVehicle.getId(),
				userService.getFullFilledUserDto(user), vehicleDto);
		driverOfferDto.setUserVehicle(userVehicleDto);

		// TODO: add stations!!!

		// Set claimerOffers.
		List<ClaimerOffer> claimerOffers = claimerOfferRepository
				.findByDriverOfferId(driverOffer.getId());
		for (ClaimerOffer claimerOffer : claimerOffers)
		{
			ClaimerOfferDto claimerOfferDto = new ClaimerOfferDto(claimerOffer);
			claimerOfferDto.setDriverOffer(driverOfferDto);
			claimerOfferDto.setOfferStatus(new OfferStatusDto(
					offersStatusRepository.findOne(claimerOffer.getOfferStatusId())));
			claimerOfferDto.setUser(new UserDto(userRepository.findOne(claimerOffer.getUserId())));

			driverOfferDto.getClaimerOffers().add(claimerOfferDto);
		}

		return driverOfferDto;
	}
}
