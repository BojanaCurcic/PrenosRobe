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

import com.prenosrobe.data.ClaimerOffer;
import com.prenosrobe.data.DriverOffer;
import com.prenosrobe.data.Impression;
import com.prenosrobe.data.Language;
import com.prenosrobe.data.User;
import com.prenosrobe.data.UserLanguage;
import com.prenosrobe.data.UserVehicle;
import com.prenosrobe.data.Vehicle;
import com.prenosrobe.dto.LanguageDto;
import com.prenosrobe.dto.UserDto;
import com.prenosrobe.repositories.ClaimerOfferRepository;
import com.prenosrobe.repositories.DriverOfferRepository;
import com.prenosrobe.repositories.ImpressionRepository;
import com.prenosrobe.repositories.LanguageRepository;
import com.prenosrobe.repositories.UserLanguageRepository;
import com.prenosrobe.repositories.UserRepository;
import com.prenosrobe.repositories.UserVehicleRepository;
import com.prenosrobe.repositories.VehicleRepository;

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ImpressionRepository impressionRepository;

	@Autowired
	private UserLanguageRepository userLanguageRepository;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private UserVehicleRepository userVehicleRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private DriverOfferRepository driverOfferRepository;

	@Autowired
	private ClaimerOfferRepository claimerOfferRepository;

	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * Register the user. Parameter userDto should have set fields 'name', 'surname', 
	 * 'username', 'password', 'email', 'photo', 'phoneNumber' and list of languages, 
	 * where each language should have set field 'id'.
	 *
	 * @param userDto user
	 * @return userDto user with all its information
	 */
	public String register(UserDto userDto)
	{
		String errors = validateUser(userDto);

		if (errors.isEmpty())
		{
			userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
			userDto.setToken(
					passwordEncoder.encode(userDto.getUsername() + ":" + userDto.getPassword()));
			userDto.setActive(true);

			User savedUser = userRepository.save(new User(userDto));
			userDto.setId(savedUser.getId());

			addValidLanguagesIntoBase(userDto);
		}
		return errors;
	}

	/**
	 * Login the user. Parameter userDto should have set fields 'email' and 'password'.
	 *
	 * @param userDto user
	 * @return userDto user with all its information
	 */
	public UserDto login(final UserDto userDto)
	{
		User foundUser = userRepository.findByEmail(userDto.getEmail());
		if (foundUser != null
				&& passwordEncoder.matches(userDto.getPassword(), foundUser.getPassword()))
		{
			foundUser.setActive(true);
			foundUser = userRepository.save(foundUser);

			return getFullFilledUserDto(foundUser);
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
	public boolean authentication(String token)
	{
		User user = userRepository.findByToken(token);

		return user != null && token.equals(user.getToken());
	}

	/**
	 * Validate user.
	 *
	 * @param userDto the user
	 * @return errors
	 */
	public String validateUser(UserDto userDto)
	{
		StringBuilder errors = new StringBuilder();

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(new User(userDto));
		constraintViolations.iterator().forEachRemaining(constrain -> errors.append(
				"\"" + constrain.getPropertyPath() + "\" " + constrain.getMessage() + ". "));

		if (userRepository.findByEmail(userDto.getEmail()) != null)
			errors.append("\"email\" is already used. ");
		if (userRepository.findByUsername(userDto.getUsername()) != null)
			errors.append("\"username\" is already used. ");
		if (userRepository.findByPhoneNumber(userDto.getPhoneNumber()) != null)
			errors.append("\"phone number\" is already used. ");

		return errors.toString();
	}

	/**
	 * Get the user by user id.
	 *
	 * @param id user id
	 * @return userDto user with all its information
	 */
	public UserDto getUserById(final int id)
	{
		User user = userRepository.findOne(id);
		if (user != null)
		{
			return getFullFilledUserDto(user);
		}

		return null;
	}

	/**
	 * Get the all languages.
	 *
	 * @return languages list of all supported languages
	 */
	public List<LanguageDto> getAllLanguages()
	{
		List<Language> languages = languageRepository.findAll();
		List<LanguageDto> languageDtos = new ArrayList<>();
		languages.iterator()
				.forEachRemaining(language -> languageDtos.add(new LanguageDto(language)));

		return languageDtos;
	}

	/**
	 * Add the valid languages into base and remove invalid from passed userDto.
	 *
	 * @param userDto the user dto
	 */
	private void addValidLanguagesIntoBase(UserDto userDto)
	{
		List<Integer> removeIndexes = new ArrayList<>();
		for (int i = 0; i < userDto.getLanguages().size(); i++)
		{
			LanguageDto language = userDto.getLanguages().get(i);

			if (languageRepository.findOne(language.getId()) != null)
				userLanguageRepository.save(new UserLanguage(userDto.getId(), language.getId()));
			else
				removeIndexes.add(i);
		}
		for (int i = removeIndexes.size() - 1; i >= 0; i--)
			userDto.getLanguages().remove(removeIndexes.get(i).intValue());
	}

	/**
	 * Get the full filled UserDto.
	 *
	 * @param user the user
	 * @return the full filled user dto
	 */
	public UserDto getFullFilledUserDto(User user)
	{
		UserDto userDto = new UserDto(user);

		List<Impression> impressions = impressionRepository.findByUserId(user.getId());
		userDto.setImpressions(impressions);

		List<UserLanguage> userLanguages = userLanguageRepository.findByUserId(user.getId());
		for (UserLanguage userLanguage : userLanguages)
		{
			Language language = languageRepository.findOne(userLanguage.getLanguageId());
			if (language != null)
				userDto.addLanguage(new LanguageDto(language));
		}

		List<UserVehicle> userVehicles = userVehicleRepository.findByUserId(user.getId());
		for (UserVehicle userVehicle : userVehicles)
		{
			Vehicle vehicle = vehicleRepository.findOne(userVehicle.getVehicleId());
			if (vehicle != null)
				userDto.addVehicle(vehicle);

			List<DriverOffer> driverOffers = driverOfferRepository
					.findByUserVehicleId(userVehicle.getId());
			for (DriverOffer driverOffer : driverOffers)
				userDto.addDriverOffer(driverOffer);
		}

		List<ClaimerOffer> claimerOffers = claimerOfferRepository.findByUserId(user.getId());
		userDto.setClaimerOffers(claimerOffers);

		return userDto;
	}
}
