package com.prenosrobe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.User;

public interface UserRepository extends CrudRepository<User, Integer>
{
	User findByEmail(String email);

	User findByUsername(String username);

	User findByPhoneNumber(String phoneNumber);

	User findByToken(String token);
}
