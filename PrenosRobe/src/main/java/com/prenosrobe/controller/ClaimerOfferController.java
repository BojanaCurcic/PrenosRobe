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

import com.prenosrobe.data.ClaimerOffer;
import com.prenosrobe.exception.DataNotSavedException;
import com.prenosrobe.exception.Messages;
import com.prenosrobe.service.ClaimerOfferService;
import com.prenosrobe.service.UserService;

@RestController
public class ClaimerOfferController
{
	@Autowired
	private ClaimerOfferService claimerOfferService;

	@Autowired
	private UserService userService;

	/**
	 * Adds the claimer offer. Parameter claimerOffer should have set fields 'departureLocation',
	 * 'arrivalLocation', 'data', 'photo', 'user', 'driverOffer' and 'offerStatus'. 'user', 'driverOffer' 
	 * and 'offerStatus' should have set all fields.
	 *
	 * @param token token used for user identification
	 * @param claimerOffer claimer offer
	 * @return claimerOffer claimer offer with all its information
	 */
	@PostMapping("/claimerOffer/add")
	public ResponseEntity<?> add(@RequestHeader(value = "token") String token,
			@RequestBody ClaimerOffer claimerOffer)
	{
		if (userService.authentication(token))
		{
			String errors = claimerOfferService.add(claimerOffer);
			if (errors.isEmpty())
				return new ResponseEntity<>(claimerOffer, HttpStatus.CREATED);

			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get the claimer offer by claimer offer id.
	 *
	 * @param id claimer offer id
	 * @param token token used for user identification
	 * @return claimerOffer claimer offer with all its information
	 */
	@GetMapping("/claimerOffer/{id}")
	public ResponseEntity<?> getClaimerOfferById(@PathVariable Long id,
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			ClaimerOffer claimerOffer = claimerOfferService.getClaimerOfferById(id.intValue());
			if (claimerOffer != null)
				return new ResponseEntity<>(claimerOffer, HttpStatus.OK);

			return new ResponseEntity<>(Messages.UNKNOWN_CLAIMER_OFFER, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get my claimer offers.
	 *
	 * @param token token used for user identification
	 * @return my claimer offers
	 */
	@GetMapping("/myClaimerOffers")
	public ResponseEntity<List<ClaimerOffer>> myClaimerOffers(
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			return new ResponseEntity<>(claimerOfferService.myClaimerOffers(token), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get the claimer offers given for driver offer.
	 *
	 * @param driverOfferId driver offer id
	 * @return claimer offers given for driver offer
	 */
	@GetMapping("/claimerOffers/{driverOfferId}")
	public ResponseEntity<?> getClaimerOffersByDriverOfferId(@PathVariable Long driverOfferId,
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			List<ClaimerOffer> claimerOffers = claimerOfferService
					.getClaimerOffersByDriverOfferId(driverOfferId.intValue());
			if (claimerOffers != null)
				return new ResponseEntity<>(claimerOffers, HttpStatus.OK);

			return new ResponseEntity<>(Messages.UNKNOWN_DRIVER_OFFER, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Update claimer offer.
	 * 
	 * @param token token used for user identification
	 * @param claimerOffer the claimer offer
	 * @return updatedClaimerOffer updated claimer offer
	 */
	@PostMapping("/claimerOffer/update")
	public ResponseEntity<?> updateClaimerOffer(@RequestHeader(value = "token") String token,
			@RequestBody ClaimerOffer claimerOffer)
	{
		if (userService.authentication(token))
		{
			String errors = claimerOfferService.updateClaimerOffer(claimerOffer);
			if (errors.isEmpty())
				return new ResponseEntity<>(claimerOffer, HttpStatus.OK);

			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
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
