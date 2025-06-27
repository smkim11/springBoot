package com.sakila.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.entity.CityEntity;
import com.sakila.api.entity.CountryEntity;

public interface CityRepository extends JpaRepository<CityEntity, Integer>{
	List<CityEntity> findAll();
	Long countByCountryEntity(CountryEntity countryEntity);
	// == select count(*) from city where country_id = countryEntity.getCountryId();
}
