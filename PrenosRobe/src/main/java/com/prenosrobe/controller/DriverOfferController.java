package com.prenosrobe.controller;

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

import com.prenosrobe.data.Area;
import com.prenosrobe.data.DriverOffer;
import com.prenosrobe.data.OfferStatus;
import com.prenosrobe.data.Station;
import com.prenosrobe.data.UserVehicle;
import com.prenosrobe.data.VehicleType;
import com.prenosrobe.exception.DataNotSavedException;
import com.prenosrobe.exception.Messages;
import com.prenosrobe.service.DriverOfferService;
import com.prenosrobe.service.UserService;

@RestController
public class DriverOfferController
{
	private static final String UNKNOWN_DRIVER_OFFER = "Unknown driver offer. ";

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
	public ResponseEntity<?> add(@RequestHeader(value = "token") String token,
			@RequestBody DriverOffer driverOffer)
	{
		if (userService.authentication(token))
		{
			String errors = driverOfferService.add(driverOffer);
			if (errors.isEmpty())
				return new ResponseEntity<>(driverOffer, HttpStatus.CREATED);

			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get the driver offer by driver offer id.
	 *
	 * @param id driver offer id
	 * @param token token used for user identification
	 * @return driverOffer driver offer with all its information
	 */
	@GetMapping("/driverOffer/{id}")
	public ResponseEntity<?> getDriverOfferById(@PathVariable Long id,
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			DriverOffer driverOffer = driverOfferService.getDriverOfferById(id.intValue());
			if (driverOffer != null)
				return new ResponseEntity<>(driverOffer, HttpStatus.OK);

			return new ResponseEntity<>(UNKNOWN_DRIVER_OFFER, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get my driver offers.
	 *
	 * @param token token used for user identification
	 * @return my driver offers
	 */
	@GetMapping("/myDriverOffers")
	public ResponseEntity<List<DriverOffer>> getMyDriverOffers(
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
	 * @return updatedDriverOffer updated driver offer
	 */
	@PostMapping("/driverOffer/update")
	public ResponseEntity<?> updateDriverOffer(@RequestHeader(value = "token") String token,
			@RequestBody DriverOffer driverOffer)
	{
		if (userService.authentication(token))
		{
			String errors = driverOfferService.updateDriverOffer(driverOffer);
			if (errors.isEmpty())
				return new ResponseEntity<>(driverOffer, HttpStatus.OK);

			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get the all driver offers.
	 *
	 * @return driver offers list of all driver offers
	 */
	@GetMapping("/driverOffers")
	public ResponseEntity<List<DriverOffer>> getAllDriverOffers()
	{
		return new ResponseEntity<>(driverOfferService.getAll(), HttpStatus.OK);
	}

	/**
	 * Get the all offer statuses.
	 *
	 * @return offerStatuses list of all supported offer statuses
	 */
	@GetMapping("/offerStatuses")
	public ResponseEntity<List<OfferStatus>> getAllOfferStatuses()
	{
		return new ResponseEntity<>(driverOfferService.getAllOfferStatuses(), HttpStatus.OK);
	}

	/**
	 * Get the vehicle by registration number.
	 *
	 * @param token token used for user identification
	 * @param registrationNumber registration number
	 * @return the vehicle by registration number
	 */
	@GetMapping("/userVehicle/{registrationNumber}")
	public ResponseEntity<?> getUserVehicleByRegistrationNumber(
			@RequestHeader(value = "token") String token, @PathVariable String registrationNumber)
	{
		if (userService.authentication(token))
		{
			UserVehicle foundUserVehicle = driverOfferService
					.getUserVehicleByRegistrationNumber(registrationNumber, token);
			if (foundUserVehicle != null)
				return new ResponseEntity<>(foundUserVehicle, HttpStatus.OK);

			return new ResponseEntity<>(Messages.UNKNOWN_VEHICLE, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get the all vehicle types.
	 *
	 * @param token token used for user identification
	 * @return vehicleTypes list of all supported vehicle types
	 */
	@GetMapping("/vehicleTypes")
	public ResponseEntity<List<VehicleType>> getAllVehicleTypes(
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			List<VehicleType> vehicleTypes = driverOfferService.getAllVehicleTypes();
			if (!vehicleTypes.isEmpty())
				return new ResponseEntity<>(vehicleTypes, HttpStatus.OK);

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get the all stations.
	 *
	 * @param token token used for user identification
	 * @return stations list of all currently supported stations
	 */
	@GetMapping("/stations")
	public ResponseEntity<List<Station>> getAllStations(
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			List<Station> stations = driverOfferService.getAllStations();
			if (!stations.isEmpty())
				return new ResponseEntity<>(stations, HttpStatus.OK);

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get the all areas.
	 *
	 * @param token token used for user identification
	 * @return areas list of all currently supported areas
	 */
	@GetMapping("/areas")
	public ResponseEntity<List<Area>> getAllAreas(@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			List<Area> areas = driverOfferService.getAllAreas();
			if (!areas.isEmpty())
				return new ResponseEntity<>(areas, HttpStatus.OK);

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Handle data not saved.
	 *
	 * @param exc exception
	 */
	@ExceptionHandler(DataNotSavedException.class)
	public ResponseEntity<String> handleDataNotSaved(DataNotSavedException exc)
	{
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
