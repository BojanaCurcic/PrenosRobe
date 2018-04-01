package com.prenosrobe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.prenosrobe.data.Impression;
import com.prenosrobe.data.User;
import com.prenosrobe.dto.RestRespondeDto;
import com.prenosrobe.exception.DataNotSavedException;
import com.prenosrobe.exception.Messages;
import com.prenosrobe.service.UserService;
import com.prenosrobe.util.ResponseEntityUtil;

@RestController
public class UserController
{
	@Autowired
	private UserService userService;

	/**
	 * Register the user. Parameter user should have set fields 'name', 'surname', 
	 * 'username', 'password', 'email', 'photo', 'phoneNumber' and list of userLanguages, 
	 * where each userLanguage should have set fields 'userId' and 'language' and each language
	 * should have set field 'name'.
	 *
	 * @param user user
	 * @return user user with all its information
	 */
	@PostMapping("/user/register")
	public ResponseEntity<RestRespondeDto> register(@RequestBody User user, HttpServletRequest request)
	{
		List<String> errorList = userService.register(user);
		if (errorList.isEmpty())
			return new ResponseEntity<>(new RestRespondeDto(HttpStatus.CREATED.value(), user),
					HttpStatus.CREATED);

		return ResponseEntityUtil.createResponseEntityAlreadyReported(errorList);
	}

	/**
	 * Login the user. Parameter user should have set fields 'email' and 'password'.
	 *
	 * @param user user
	 * @return user user with all its information
	 */
	@PostMapping("/user/login")
	public ResponseEntity<RestRespondeDto> login(@RequestBody User user)
	{
		User logdedInUser = userService.login(user);
		if (logdedInUser != null)
			return new ResponseEntity<>(new RestRespondeDto(HttpStatus.OK.value(), logdedInUser),
					HttpStatus.OK);

		return ResponseEntityUtil.createResponseEntityNoContent(Messages.UNKNOWN_USER);
	}

	/**
	 * Logout the user.
	 *
	 * @param token token for user identification
	 */
	@PostMapping("/user/logout")
	public ResponseEntity<RestRespondeDto> logout(@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			if (userService.logout(token))
				return new ResponseEntity<>(new RestRespondeDto(HttpStatus.OK.value()),
						HttpStatus.OK);

			return ResponseEntityUtil.createResponseEntityNoContent(Messages.UNKNOWN_USER);
		}
		return ResponseEntityUtil.createResponseEntityForbidden();
	}

	/**
	 * Get the user by user id.
	 *
	 * @param id user id
	 * @param token token used for user identification
	 * @return user user with all its information
	 */
	@GetMapping("/user/{id}")
	public ResponseEntity<RestRespondeDto> getUserById(@PathVariable Long id,
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			User user = userService.getUserById(id.intValue());
			if (user != null)
				return new ResponseEntity<>(new RestRespondeDto(HttpStatus.OK.value(), user),
						HttpStatus.OK);

			return ResponseEntityUtil.createResponseEntityNoContent(Messages.UNKNOWN_USER);
		}
		return ResponseEntityUtil.createResponseEntityForbidden();
	}

	/**
	 * Update user.
	 * 
	 * @param token token used for user identification
	 * @param user the user
	 * @return updatedUser updated user
	 */
	@PostMapping("/user/update")
	public ResponseEntity<RestRespondeDto> updateUser(@RequestHeader(value = "token") String token,
			@RequestBody User user)
	{
		if (userService.authentication(token))
		{
			List<String> errorList = userService.updateUser(user);
			if (errorList.isEmpty())
				return new ResponseEntity<>(new RestRespondeDto(HttpStatus.OK.value(), user),
						HttpStatus.OK);

			return ResponseEntityUtil.createResponseEntityAlreadyReported(errorList);
		}
		return ResponseEntityUtil.createResponseEntityForbidden();
	}

	/**
	 * Add the impression. Parameter impression should have set fields 'userId',
	 * 'deliveredOnTime', 'pickedOnTime', 'comment' and 'driver'. Field 'driver' defines if this impression is
	 * related to a driver (when its values is true) or to a claimer (when its value is false). If field 
	 * 'driver' is true, impression should also have set fields 'deliveredUndamaged' and 'delivered', otherwise
	 * field 'correctlyPaid' should be set. All of these fields must have values between 1 and 10.  
	 *
	 * @param token token used for user identification
	 * @param impression impression
	 */
	@PostMapping("impression")
	public ResponseEntity<RestRespondeDto> addImpression(
			@RequestHeader(value = "token") String token, @RequestBody Impression impression)
	{
		if (userService.authentication(token))
		{
			List<String> errorList = userService.addImpression(impression);
			if (errorList.isEmpty())
				return new ResponseEntity<>(new RestRespondeDto(HttpStatus.OK.value()),
						HttpStatus.OK);

			return ResponseEntityUtil.createResponseEntityAlreadyReported(errorList);
		}
		return ResponseEntityUtil.createResponseEntityForbidden();
	}

	/**
	 * Get the all languages.
	 *
	 * @return languages list of all supported languages
	 */
	@GetMapping("/languages")
	public ResponseEntity<RestRespondeDto> getAllLanguages()
	{
		return new ResponseEntity<>(
				new RestRespondeDto(HttpStatus.OK.value(), userService.getAllLanguages()),
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
