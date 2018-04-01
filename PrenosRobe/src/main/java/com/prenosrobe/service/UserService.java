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
import com.prenosrobe.exception.DataNotSavedException;
import com.prenosrobe.exception.Messages;
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
	public List<String> register(User user)
	{
		List<String> errorList = validateUser(user);

		if (errorList.isEmpty())
		{
			// TODO: ocekuj da je password vec kriptovan (smisliti kako kriptovan)
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			// TODO: promeni logiku za pravljenje tokena
			user.setToken(passwordEncoder.encode(user.getUsername() + ":" + user.getPassword()));
			user.setActive(true);

			try
			{
				User savedUser = new User(user);
				userRepository.save(savedUser);
				user.setId(savedUser.getId());
			} catch (Exception e)
			{
				throw new DataNotSavedException(e.getMessage(), e);
			}

			addValidUserLanguagesIntoBase(user);
		}
		return errorList;
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
		// TODO: ocekuj da je password vec kriptovan (smisliti kako kriptovan)
		if (foundUser != null
				&& passwordEncoder.matches(user.getPassword(), foundUser.getPassword()))
		{
			try
			{
				// TODO: promeni token svaki put kad se loguje
				foundUser.setActive(true);
				userRepository.save(foundUser);

				return foundUser;
			} catch (Exception e)
			{
				throw new DataNotSavedException(e.getMessage(), e);
			}
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
			try
			{
				userRepository.save(foundUser);

				return true;
			} catch (Exception e)
			{
				throw new DataNotSavedException(e.getMessage(), e);
			}
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
	 * @return error list
	 */
	private List<String> validateUser(final User user)
	{
		List<String> errorList = new ArrayList<>();

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		constraintViolations.iterator().forEachRemaining(constrain -> errorList
				.add(constrain.getMessage()));

		if (userRepository.findByEmail(user.getEmail()) != null)
			errorList.add(Messages.EMAIL_USED);
		if (userRepository.findByUsername(user.getUsername()) != null)
			errorList.add(Messages.USERNAME_USED);
		if (userRepository.findByPhoneNumber(user.getPhoneNumber()) != null)
			errorList.add(Messages.PHONE_NUMBER_USED);

		return errorList;
	}

	/**
	 * Validate updated user.
	 *
	 * @param updatedUser the updated user
	 * @return error list
	 */
	private List<String> validateUpdatedUser(final User updatedUser)
	{
		List<String> errorList = new ArrayList<>();

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(updatedUser);
		constraintViolations.iterator().forEachRemaining(constrain -> errorList
				.add("\"" + constrain.getPropertyPath() + "\" " + constrain.getMessage() + ". "));

		if (errorList.isEmpty())
		{
			User foundUser = userRepository.findOne(updatedUser.getId());

			if (foundUser != null && !foundUser.getEmail().equals(updatedUser.getEmail())
					&& userRepository.findByEmail(updatedUser.getEmail()) != null)
				errorList.add(Messages.EMAIL_USED);
			if (foundUser != null && !foundUser.getUsername().equals(updatedUser.getUsername())
					&& userRepository.findByUsername(updatedUser.getUsername()) != null)
				errorList.add(Messages.USERNAME_USED);
			if (foundUser != null
					&& !foundUser.getPhoneNumber().equals(updatedUser.getPhoneNumber())
					&& userRepository.findByPhoneNumber(updatedUser.getPhoneNumber()) != null)
				errorList.add(Messages.PHONE_NUMBER_USED);
		}

		return errorList;
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
	 * Update user. Parameter user should have set all fields, including 'id'.
	 * 
	 * @param updatedUser updated user
	 * @return error list
	 */
	public List<String> updateUser(User updatedUser)
	{
		List<String> errorList = validateUpdatedUser(updatedUser);
		if (errorList.isEmpty())
		{
			try
			{
				User user = new User(updatedUser);
				user.setId(updatedUser.getId());

				userRepository.save(user);
			} catch (Exception e)
			{
				throw new DataNotSavedException(e.getMessage(), e);
			}
		}
		return errorList;
	}

	/**
	 * Add the impression. Parameter impression should have set fields 'userId',
	 * 'deliveredOnTime', 'pickedOnTime', 'comment' and 'driver'. Field 'driver' defines if this impression
	 * is related to a driver (when its values is true) or to a claimer (when its value is false). If field 
	 * 'driver' is true, impression should also have set fields 'deliveredUndamaged' and 'delivered', otherwise
	 * field 'correctlyPaid' should be set. All of these fields must have values between 1 and 10. 
	 *
	 * @param impression impression
	 * @return error list
	 */
	public List<String> addImpression(Impression impression)
	{
		List<String> errorList = validateImpression(impression);

		if (errorList.isEmpty())
		{
			try
			{
				impressionRepository.save(impression);
			} catch (Exception e)
			{
				throw new DataNotSavedException(e.getMessage(), e);
			}
		}
		return errorList;
	}

	/**
	 * Validate impression.
	 *
	 * @param impression impression
	 * @return error list
	 */
	private List<String> validateImpression(Impression impression)
	{
		List<String> errorList = new ArrayList<>();

		Set<ConstraintViolation<Impression>> constraintViolations = validator.validate(impression);
		constraintViolations.iterator().forEachRemaining(constrain -> errorList
				.add("\"" + constrain.getPropertyPath() + "\" " + constrain.getMessage() + ". "));

		if (impression.isDriver() != null && impression.isDriver())
		{
			if (impression.getDelivered() == null)
				errorList.add(Messages.DELIVERED_IS_NULL);
			if (impression.getDeliveredUndamaged() == null)
				errorList.add(Messages.DELIVERED_UNDAMAGED_IS_NULL);
		}
		else if (impression.isDriver() != null && !impression.isDriver()
				&& impression.getCorrectlyPaid() == null)
			errorList.add(Messages.CORRECTLY_PAID_IS_NULL);
		if (userRepository.findOne(impression.getUserId()) == null)
			errorList.add(Messages.UNKNOWN_USER);

		return errorList;
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
			userLanguage.setUserId(user.getId());

			Language language = userLanguage.getLanguage();
			Language foundLanguage = languageRepository.findByName(language.getName());
			if (foundLanguage != null)
			{
				try
				{
					userLanguage.setLanguage(foundLanguage);
					UserLanguage validUserLanguage = userLanguageRepository.save(userLanguage);
					validUserLanguages.add(validUserLanguage);
				} catch (Exception e)
				{
					throw new DataNotSavedException(e.getMessage(), e);
				}
			}
		}

		user.setUserLanguages(validUserLanguages);
	}
}
