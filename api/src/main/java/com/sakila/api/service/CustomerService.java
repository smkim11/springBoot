package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	private CustomerRepository customerRepository;
	
	// 생성자로 주입
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public List<CustomerEntity> findAll(){
		return customerRepository.findAll();
	}
}
