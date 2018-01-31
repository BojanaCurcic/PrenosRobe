package com.prenosrobe.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prenosrobe.dto.LanguageDto;
import com.prenosrobe.dto.UserDto;
import com.prenosrobe.service.UserService;

@RestController
public class UserController
{
	@Autowired
	private UserService userService;

	/**
	 * Register the user. Parameter userDto should have set fields 'name', 'surname', 
	 * 'username', 'password', 'email', 'photo', 'phoneNumber' and list of languages, 
	 * where each language should have set field 'id'.
	 *
	 * @param userDto user
	 * @return userDto user with all its information
	 */
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody UserDto userDto, HttpServletRequest request)
	{
		String errors = userService.register(userDto);
		if (errors.isEmpty())
			return new ResponseEntity<>(userDto, HttpStatus.CREATED);

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Login the user. Parameter userDto should have set fields 'email' and 'password'.
	 *
	 * @param userDto user
	 * @return userDto user with all its information
	 */
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody UserDto userDto)
	{
		UserDto logdedInUser = userService.login(userDto);
		if (logdedInUser != null)
			return new ResponseEntity<>(logdedInUser, HttpStatus.OK);

		return new ResponseEntity<>("Unknown user.", HttpStatus.BAD_REQUEST);
	}

	/**
	 * Logout the user.
	 *
	 * @param token token for user identification
	 */
	@RequestMapping(value = "/user/logout", method = RequestMethod.POST)
	public ResponseEntity<String> logout(@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			if (userService.logout(token))
				return new ResponseEntity<>(HttpStatus.OK);

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get the user by user id.
	 *
	 * @param id user id
	 * @param token token used for user identification
	 * @return userDto user with all its information
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserById(@PathVariable Long id,
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			UserDto userDto = userService.getUserById(id.intValue());
			if (userDto != null)
				return new ResponseEntity<>(userDto, HttpStatus.OK);

			return new ResponseEntity<>("Unknown user.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get the all languages.
	 *
	 * @param token token used for user identification
	 * @return languages list of all supported languages
	 */
	@RequestMapping(value = "/languages", method = RequestMethod.GET)
	public ResponseEntity<List<LanguageDto>> getAllLanguages(
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
			return new ResponseEntity<>(userService.getAllLanguages(), HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
}
