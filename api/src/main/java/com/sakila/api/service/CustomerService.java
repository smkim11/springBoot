package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CustomerDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CustomerRepository;
import com.sakila.api.repository.StoreRepository;

@Service
@Transactional
public class CustomerService {
	private CustomerRepository customerRepository;
	private AddressRepository addressRepository;
	private StoreRepository storeRepository;
	
	// 생성자로 주입
	public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository, StoreRepository storeRepository) {
		this.customerRepository = customerRepository;
		this.storeRepository = storeRepository;
		this.addressRepository = addressRepository;
	}
	
	// 한 행 조회
	public CustomerEntity findById(int customerId) {
		return customerRepository.findById(customerId).orElse(null);
	}
	
	// 조회
	public List<CustomerEntity> findAll(){
		return customerRepository.findAll();
	}
	
	// 입력
	public void save(CustomerDto customerDto) {
		CustomerEntity entity = customerDto.toEntity();
		
		StoreEntity storeEntity = storeRepository.findById(customerDto.getStoreId()).orElse(null);
		entity.setStoreEntity(storeEntity);
		
		AddressEntity addressEntity = addressRepository.findById(customerDto.getAddressId()).orElse(null);
		entity.setAddressEntity(addressEntity);
		
		customerRepository.save(entity);
	}
	
	// 수정
	public void update(CustomerDto customerDto) {
		CustomerEntity entity = customerRepository.findById(customerDto.getCustomerId()).orElse(null);
		entity.setFirstName(customerDto.getFirstName());
		entity.setLastName(customerDto.getLastName());
		entity.setEmail(customerDto.getEmail());
		entity.setActive(customerDto.getActive());
		
		StoreEntity storeEntity = storeRepository.findById(customerDto.getStoreId()).orElse(null);
		entity.setStoreEntity(storeEntity);
		
		AddressEntity addressEntity = addressRepository.findById(customerDto.getAddressId()).orElse(null);
		entity.setAddressEntity(addressEntity);
	}
	
	// 삭제
	public void delete(int customerId) {
		customerRepository.delete(customerRepository.findById(customerId).orElse(null));
	}
}
