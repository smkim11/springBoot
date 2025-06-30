package com.sakila.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.entity.StoreEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{
	List<CustomerEntity> findAll();
	Long countByAddressEntity(AddressEntity addressEntity);
	Long countByStoreEntity(StoreEntity storeEntity);
}
