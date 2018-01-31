package com.prenosrobe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.Language;

public interface LanguageRepository extends CrudRepository<Language, Integer>
{
	List<Language> findAll();
}
