package com.prenosrobe.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prenosrobe.data.User;
import com.prenosrobe.repositories.UserRepository;

@RestController
public class UserController
{
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public @ResponseBody String addUser(@Valid @RequestParam String name,
			@Valid @RequestParam String surname, @Valid @RequestParam String username,
			@Valid @RequestParam String password, @Valid @RequestParam String email,
			@Valid @RequestParam String phoneNumber)
	{
		User user = new User(name, surname, username, password, email, phoneNumber);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		if(constraintViolations.isEmpty())
		{
			userRepository.save(user);
			return "Saved";
		}
		
		return "Rejected";
	}
}
