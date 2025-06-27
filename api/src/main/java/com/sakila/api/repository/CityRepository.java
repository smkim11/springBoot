package com.sakila.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.entity.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Integer>{
	List<CityEntity> findAll();
}
