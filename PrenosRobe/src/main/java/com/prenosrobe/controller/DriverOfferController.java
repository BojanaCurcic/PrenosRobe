package com.prenosrobe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.prenosrobe.data.DriverOffer;
import com.prenosrobe.data.UserVehicle;
import com.prenosrobe.dto.HomeSearchDto;
import com.prenosrobe.dto.RestRespondeDto;
import com.prenosrobe.exception.DataNotSavedException;
import com.prenosrobe.exception.Messages;
import com.prenosrobe.service.DriverOfferService;
import com.prenosrobe.service.UserService;
import com.prenosrobe.util.ResponseEntityUtil;

@RestController
public class DriverOfferController
{
	@Autowired
	private DriverOfferService driverOfferService;

	@Autowired
	private UserService userService;

	/**
	 * Add the driver offer. Parameter driverOffer should have set fields 'departureLocation', 
	 * 'arrivalLocation', 'date', 'time', 'offerStatus' and 'userVehicle'. 'offerStatus' should 
	 * have set field 'name' and 'userVehicle' should have set all fields.
	 *
	 * @param token token used for user identification
	 * @param driverOffer driver offer
	 * @return driverOffer driver offer with all its information
	 */
	@PostMapping("/driverOffer/add")
	public ResponseEntity<RestRespondeDto> add(@RequestHeader(value = "token") String token,
			@RequestBody DriverOffer driverOffer)
	{
		if (userService.authentication(token))
		{
			List<String> errorList = driverOfferService.add(driverOffer);
			if (errorList.isEmpty())
				return new ResponseEntity<>(
						new RestRespondeDto(HttpStatus.CREATED.value(), driverOffer),
						HttpStatus.CREATED);

			return ResponseEntityUtil.createResponseEntityAlreadyReported(errorList);
		}
		return ResponseEntityUtil.createResponseEntityForbidden();
	}

	/**
	 * Get the driver offer by driver offer id.
	 *
	 * @param id driver offer id
	 * @param token token used for user identification
	 * @return driverOffer driver offer with all its information
	 */
	@GetMapping("/driverOffer/{id}")
	public ResponseEntity<RestRespondeDto> getDriverOfferById(@PathVariable Long id,
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			DriverOffer driverOffer = driverOfferService.getDriverOfferById(id.intValue());
			if (driverOffer != null)
				return new ResponseEntity<>(new RestRespondeDto(HttpStatus.OK.value(), driverOffer),
						HttpStatus.OK);

			return ResponseEntityUtil.createResponseEntityNoContent(Messages.UNKNOWN_DRIVER_OFFER);
		}
		return ResponseEntityUtil.createResponseEntityForbidden();
	}

	/**
	 * Get my driver offers.
	 *
	 * @param token token used for user identification
	 * @return my driver offers
	 */
	@GetMapping("/myDriverOffers")
	public ResponseEntity<RestRespondeDto> getMyDriverOffers(
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			return new ResponseEntity<>(new RestRespondeDto(HttpStatus.OK.value(),
					driverOfferService.getMyDriverOffers(token)), HttpStatus.OK);
		}
		return ResponseEntityUtil.createResponseEntityForbidden();
	}

	/**
	 * Get the driver offers by locations and date. HomeSearchDto field date can be null, 
	 * but locations shouldn't be null.
	 *
	 * @param homeSearchDto homeSearchDto
	 * @return driver offers by locations and date
	 */
	@PostMapping("/driverOffer/homeSearch")
	public ResponseEntity<RestRespondeDto> getDriverOffersByLocationsAndDate(
			@RequestBody HomeSearchDto homeSearchDto)
	{
		String departureLocation = homeSearchDto.getDepartureLocation();
		String arrivalLocation = homeSearchDto.getArrivalLocation();

		if (departureLocation == null || departureLocation.isEmpty() || arrivalLocation == null
				|| arrivalLocation.isEmpty())
			return ResponseEntityUtil.createResponseEntityNoContent(Messages.INVALID_DATA);

		return new ResponseEntity<>(new RestRespondeDto(HttpStatus.OK.value(),
				driverOfferService.getDriverOffersByLocationsAndDate(departureLocation,
						arrivalLocation, homeSearchDto.getDate())),
				HttpStatus.OK);
	}

	/**
	 * Update driver offer.
	 * 
	 * @param token token used for user identification
	 * @param driverOffer the driver offer
	 * @return updatedDriverOffer updated driver offer
	 */
	@PostMapping("/driverOffer/update")
	public ResponseEntity<RestRespondeDto> updateDriverOffer(
			@RequestHeader(value = "token") String token, @RequestBody DriverOffer driverOffer)
	{
		if (userService.authentication(token))
		{
			List<String> errorList = driverOfferService.updateDriverOffer(driverOffer);
			if (errorList.isEmpty())
				return new ResponseEntity<>(new RestRespondeDto(HttpStatus.OK.value(), driverOffer),
						HttpStatus.OK);

			return ResponseEntityUtil.createResponseEntityAlreadyReported(errorList);
		}
		return ResponseEntityUtil.createResponseEntityForbidden();
	}

	/**
	 * Get the all driver offers.
	 *
	 * @return driver offers list of all driver offers
	 */
	@GetMapping("/driverOffers")
	public ResponseEntity<RestRespondeDto> getAllDriverOffers()
	{
		return new ResponseEntity<>(
				new RestRespondeDto(HttpStatus.OK.value(), driverOfferService.getAll()),
				HttpStatus.OK);
	}

	/**
	 * Get the all offer statuses.
	 *
	 * @return offerStatuses list of all supported offer statuses
	 */
	@GetMapping("/offerStatuses")
	public ResponseEntity<RestRespondeDto> getAllOfferStatuses()
	{
		return new ResponseEntity<>(new RestRespondeDto(HttpStatus.OK.value(),
				driverOfferService.getAllOfferStatuses()), HttpStatus.OK);
	}

	/**
	 * Get the vehicle by registration number.
	 *
	 * @param token token used for user identification
	 * @param registrationNumber registration number
	 * @return the vehicle by registration number
	 */
	@GetMapping("/userVehicle/{registrationNumber}")
	public ResponseEntity<RestRespondeDto> getUserVehicleByRegistrationNumber(
			@RequestHeader(value = "token") String token, @PathVariable String registrationNumber)
	{
		if (userService.authentication(token))
		{
			UserVehicle foundUserVehicle = driverOfferService
					.getUserVehicleByRegistrationNumber(registrationNumber, token);
			if (foundUserVehicle != null)
				return new ResponseEntity<>(
						new RestRespondeDto(HttpStatus.OK.value(), foundUserVehicle),
						HttpStatus.OK);

			return ResponseEntityUtil.createResponseEntityNoContent(Messages.UNKNOWN_VEHICLE);
		}
		return ResponseEntityUtil.createResponseEntityForbidden();
	}

	/**
	 * Get the all vehicle types.
	 *
	 * @param token token used for user identification
	 * @return vehicleTypes list of all supported vehicle types
	 */
	@GetMapping("/vehicleTypes")
	public ResponseEntity<RestRespondeDto> getAllVehicleTypes(
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			return new ResponseEntity<>(new RestRespondeDto(HttpStatus.OK.value(),
					driverOfferService.getAllVehicleTypes()), HttpStatus.OK);
		}
		return ResponseEntityUtil.createResponseEntityForbidden();
	}

	/**
	 * Get the all stations.
	 *
	 * @param token token used for user identification
	 * @return stations list of all currently supported stations
	 */
	@GetMapping("/stations")
	public ResponseEntity<RestRespondeDto> getAllStations(
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			return new ResponseEntity<>(
					new RestRespondeDto(HttpStatus.OK.value(), driverOfferService.getAllStations()),
					HttpStatus.OK);
		}
		return ResponseEntityUtil.createResponseEntityForbidden();
	}

	/**
	 * Get the all areas.
	 *
	 * @return areas list of all currently supported areas
	 */
	@GetMapping("/areas")
	public ResponseEntity<RestRespondeDto> getAllAreas()
	{
		return new ResponseEntity<>(
				new RestRespondeDto(HttpStatus.OK.value(), driverOfferService.getAllAreas()),
				HttpStatus.OK);
	}

	/**
	 * Handle data not saved.
	 *
	 * @param exc exception
	 */
	@ExceptionHandler(DataNotSavedException.class)
	public ResponseEntity<RestRespondeDto> handleDataNotSaved(DataNotSavedException exc)
	{
		List<String> errorList = new ArrayList<>();
		errorList.add(exc.getMessage());

		return ResponseEntityUtil.createResponseEntityBadRequest(errorList);
	}
}
