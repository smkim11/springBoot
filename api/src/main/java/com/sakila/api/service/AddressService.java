package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.entity.AddressEntity;
import com.sakila.api.repository.AddressRepository;

@Service
@Transactional
public class AddressService {
	private AddressRepository addressRepository;
	
	// 생성자로 주입
	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	public List<AddressEntity> findAll(){
		return addressRepository.findAll();
	}
}
