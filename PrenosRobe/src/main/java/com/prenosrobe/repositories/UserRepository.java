package com.prenosrobe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.User;

public interface UserRepository extends CrudRepository<User, Integer>
{

}
