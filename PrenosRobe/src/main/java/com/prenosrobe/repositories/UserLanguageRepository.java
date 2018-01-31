package com.prenosrobe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.UserLanguage;

public interface UserLanguageRepository extends CrudRepository<UserLanguage, Integer>
{
	List<UserLanguage> findByUserId(Integer userId);
}
