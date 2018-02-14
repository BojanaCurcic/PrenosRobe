package com.prenosrobe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prenosrobe.data.Impression;
import com.prenosrobe.data.Language;
import com.prenosrobe.data.User;
import com.prenosrobe.data.UserLanguage;
import com.prenosrobe.repositories.ImpressionRepository;
import com.prenosrobe.repositories.LanguageRepository;
import com.prenosrobe.repositories.UserLanguageRepository;
import com.prenosrobe.repositories.UserRepository;

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private UserLanguageRepository userLanguageRepository;

	@Autowired
	private ImpressionRepository impressionRepository;

	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * Register the user. Parameter user should have set fields 'name', 'surname', 
	 * 'username', 'password', 'email', 'photo', 'phoneNumber' and list of userLanguages, 
	 * where each userLanguage should have set fields 'userId' and 'language' and each language
	 * should have set field 'name'.
	 *
	 * @param user user
	 * @return user user with all its information
	 */
	public String register(User user)
	{
		String errors = validateUser(user);

		if (errors.isEmpty())
		{
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setToken(passwordEncoder.encode(user.getUsername() + ":" + user.getPassword()));
			user.setActive(true);

			User savedUser = new User(user);
			userRepository.save(savedUser);
			user.setId(savedUser.getId());

			addValidUserLanguagesIntoBase(user);
		}
		return errors;
	}

	/**
	 * Login the user. Parameter user should have set fields 'email' and 'password'.
	 *
	 * @param user user
	 * @return user user with all its information
	 */
	public User login(final User user)
	{
		User foundUser = userRepository.findByEmail(user.getEmail());
		if (foundUser != null
				&& passwordEncoder.matches(user.getPassword(), foundUser.getPassword()))
		{
			foundUser.setActive(true);
			userRepository.save(foundUser);

			return foundUser;
		}
		return null;
	}

	/**
	 * Logout the user.
	 *
	 * @param token token for user identification
	 */
	public boolean logout(final String token)
	{
		User foundUser = userRepository.findByToken(token);
		if (foundUser != null)
		{
			foundUser.setActive(false);
			userRepository.save(foundUser);
			return true;
		}
		return false;
	}

	/**
	 * User authentication.
	 *
	 * @param token token for user identification
	 * @return true, if successful
	 */
	public boolean authentication(final String token)
	{
		User user = userRepository.findByToken(token);

		return user != null && token.equals(user.getToken());
	}

	/**
	 * Validate user.
	 *
	 * @param user user
	 * @return errors
	 */
	private String validateUser(final User user)
	{
		StringBuilder errors = new StringBuilder();

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		constraintViolations.iterator().forEachRemaining(constrain -> errors.append(
				"\"" + constrain.getPropertyPath() + "\" " + constrain.getMessage() + ". "));

		if (userRepository.findByEmail(user.getEmail()) != null)
			errors.append("\"email\" is already used. ");
		if (userRepository.findByUsername(user.getUsername()) != null)
			errors.append("\"username\" is already used. ");
		if (userRepository.findByPhoneNumber(user.getPhoneNumber()) != null)
			errors.append("\"phone number\" is already used. ");

		return errors.toString();
	}

	/**
	 * Get the user by user id.
	 *
	 * @param id user id
	 * @return user user with all its information
	 */
	public User getUserById(final int id)
	{
		return userRepository.findOne(id);
	}

	/**
	 * Add the impression. Parameter impression should have set fields 'userId',
	 * 'deliveredOnTime', 'pickedOnTime', 'comment' and 'driver'. Field 'driver' defines if this impression
	 * is related to a driver (when its values is true) or to a claimer (when its value is false). If field 
	 * 'driver' is true, impression should also have set fields 'deliveredUndamaged' and 'delivered', otherwise
	 * field 'correctlyPaid' should be set. All of these fields must have values between 1 and 10. 
	 *
	 * @param impression impression
	 * @return errors
	 */
	public String addImpression(Impression impression)
	{
		String errors = validateImpression(impression);

		if (errors.isEmpty())
			impressionRepository.save(impression);

		return errors;
	}

	/**
	 * Validate impression.
	 *
	 * @param impression impression
	 * @return errors
	 */
	private String validateImpression(Impression impression)
	{
		StringBuilder errors = new StringBuilder();

		Set<ConstraintViolation<Impression>> constraintViolations = validator.validate(impression);
		constraintViolations.iterator().forEachRemaining(constrain -> errors.append(
				"\"" + constrain.getPropertyPath() + "\" " + constrain.getMessage() + ". "));

		if (impression.isDriver() != null && impression.isDriver())
		{
			if (impression.getDelivered() == null)
				errors.append("\"delivered\" may not be null. ");
			if (impression.getDeliveredUndamaged() == null)
				errors.append("\"deliveredUndamaged\" may not be null. ");
		}
		else if (impression.isDriver() != null && !impression.isDriver()
				&& impression.getCorrectlyPaid() == null)
			errors.append("\"correctlyPaid\" may not be null. ");
		if (userRepository.findOne(impression.getUserId()) == null)
			errors.append("Unknown user. ");

		return errors.toString();
	}

	/**
	 * Get the all languages.
	 *
	 * @return languages list of all supported languages
	 */
	public List<Language> getAllLanguages()
	{
		return languageRepository.findAll();
	}

	/**
	 * Add the valid userLanguages into base and remove invalid ones from passed user.
	 *
	 * @param user the user
	 */
	private void addValidUserLanguagesIntoBase(User user)
	{
		List<UserLanguage> validUserLanguages = new ArrayList<>();

		for (UserLanguage userLanguage : user.getUserLanguages())
		{
			if (userLanguage.getUserId() != user.getId())
				userLanguage.setUserId(user.getId());

			Language language = userLanguage.getLanguage();
			Language foundLanguage = languageRepository.findByName(language.getName());
			if (foundLanguage != null)
			{
				userLanguage.setLanguage(foundLanguage);
				UserLanguage validUserLanguage = userLanguageRepository.save(userLanguage);
				validUserLanguages.add(validUserLanguage);
			}
		}

		user.setUserLanguages(validUserLanguages);
	}
}
