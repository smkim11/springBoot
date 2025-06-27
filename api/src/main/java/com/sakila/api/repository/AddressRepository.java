package com.sakila.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer>{
	List<AddressEntity> findAll();

}
