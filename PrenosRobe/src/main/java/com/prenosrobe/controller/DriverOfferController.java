package com.prenosrobe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prenosrobe.data.DriverOffer;
import com.prenosrobe.dto.DriverOfferDto;
import com.prenosrobe.dto.OfferStatusDto;
import com.prenosrobe.dto.UserVehicleDto;
import com.prenosrobe.dto.VehicleTypeDto;
import com.prenosrobe.service.DriverOfferService;
import com.prenosrobe.service.UserService;

@RestController
public class DriverOfferController
{
	@Autowired
	private DriverOfferService driverOfferService;

	@Autowired
	private UserService userService;

	/**
	 * Add the driver offer. Parameter driverOfferDto should have set fields 'departureLocation', 
	 * 'arrivalLocation', 'date', 'time', 'offerStatus' and 'userVehicle'. 'offerStatus' and 
	 * 'userVehicle' should have set fields 'id'.
	 *
	 * @param token token used for user identification
	 * @param driverOfferDto driver offer
	 * @return driverOfferDto driver offer with all its information
	 */
	@RequestMapping(value = "/driverOffer/add", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestHeader(value = "token") String token,
			@RequestBody DriverOfferDto driverOfferDto)
	{
		if (userService.authentication(token))
		{
			String errors = driverOfferService.add(driverOfferDto);
			if (errors.isEmpty())
				return new ResponseEntity<>(driverOfferDto, HttpStatus.CREATED);

			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get the driver offer by driver offer id.
	 *
	 * @param id driver offer id
	 * @param token token used for user identification
	 * @return driverOfferDto driver offer with all its information
	 */
	@RequestMapping(value = "/driverOffer/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDriverOfferById(@PathVariable Long id,
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			DriverOfferDto driverOfferDto = driverOfferService.getDriverOfferById(id.intValue());
			if (driverOfferDto != null)
				return new ResponseEntity<>(driverOfferDto, HttpStatus.OK);

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get my driver offers.
	 *
	 * @param token token used for user identification
	 * @return my driver offers
	 */
	@RequestMapping(value = "/myDriverOffers", method = RequestMethod.GET)
	public ResponseEntity<List<DriverOfferDto>> getMyDriverOffers(
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			return new ResponseEntity<>(driverOfferService.getMyDriverOffers(token), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Update driver offer.
	 * 
	 * @param token token used for user identification
	 * @param driverOffer the driver offer
	 * @return the response entity
	 */
	@RequestMapping(value = "/driverOffer/update", method = RequestMethod.GET)
	public ResponseEntity<String> updateDriverOffer(@RequestHeader(value = "token") String token,
			@RequestBody DriverOfferDto driverOffer)
	{
		if (userService.authentication(token))
		{
//			if (driverOfferService.updateDriverOffer(driverOffer))
//				return new ResponseEntity<>(HttpStatus.OK);

			return new ResponseEntity<>("Unknown driverOffer.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get the all driver offers.
	 *
	 * @param token token used for user identification
	 * @return driver offers list of all driver offers
	 */
	@RequestMapping(value = "/driverOffers", method = RequestMethod.GET)
	public ResponseEntity<List<DriverOffer>> getAllDriverOffers(
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			List<DriverOffer> driverOffers = driverOfferService.getAll();
			if (!driverOffers.isEmpty())
				return new ResponseEntity<>(driverOffers, HttpStatus.OK);

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get the all offer statuses.
	 *
	 * @param token token used for user identification
	 * @return offerStatuses list of all supported offer statuses
	 */
	@RequestMapping(value = "/offerStatuses", method = RequestMethod.GET)
	public ResponseEntity<List<OfferStatusDto>> getAllOfferStatuses(
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			List<OfferStatusDto> offerStatuses = driverOfferService.getAllOfferStatuses();
			if (!offerStatuses.isEmpty())
				return new ResponseEntity<>(offerStatuses, HttpStatus.OK);

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get the vehicle by registration number.
	 *
	 * @param token token used for user identification
	 * @param registrationNumber registration number
	 * @return the vehicle by registration number
	 */
	@RequestMapping(value = "/userVehicle/{registrationNumber}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserVehicleByRegistrationNumber(
			@RequestHeader(value = "token") String token, @PathVariable String registrationNumber)
	{
		if (userService.authentication(token))
		{
			UserVehicleDto foundUserVehicle = driverOfferService
					.getUserVehicleByRegistrationNumber(registrationNumber, token);
			if (foundUserVehicle != null)
				return new ResponseEntity<>(foundUserVehicle, HttpStatus.OK);

			return new ResponseEntity<>("Unknown vehicle.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get the all vehicle types.
	 *
	 * @param token token used for user identification
	 * @return vehicleTypes list of all supported vehicle types
	 */
	@RequestMapping(value = "/vehicleTypes", method = RequestMethod.GET)
	public ResponseEntity<List<VehicleTypeDto>> getAllVehicleTypes(
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			List<VehicleTypeDto> vehicleTypes = driverOfferService.getAllVehicleTypes();
			if (!vehicleTypes.isEmpty())
				return new ResponseEntity<>(vehicleTypes, HttpStatus.OK);

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
}
