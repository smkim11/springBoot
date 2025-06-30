package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.StoreDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CustomerRepository;
import com.sakila.api.repository.StoreRepository;

@Service
@Transactional
public class StoreService {
	private StoreRepository storeRepository;
	private AddressRepository addressRepository;
	private CustomerRepository customerRepository;
	
	public StoreService(StoreRepository storeRepository, AddressRepository addressRepository, CustomerRepository customerRepository) {
		this.storeRepository = storeRepository;
		this.addressRepository = addressRepository;
		this.customerRepository = customerRepository;
	}
	
	// 한 행 조회
	public StoreEntity findById(int storeId) {
		return storeRepository.findById(storeId).orElse(null);
	}
	
	// 조회
	public List<StoreEntity> findAll(){
		return storeRepository.findAll();
	}
	
	// 입력
	public void save(StoreDto storeDto) {
		StoreEntity entity = storeDto.toEntity();
		
		AddressEntity addressEntity = addressRepository.findById(storeDto.getAddressId()).orElse(null);
		entity.setAddressEntity(addressEntity);
		
		
		storeRepository.save(entity);
	}
	
	// 수정
	public void update(StoreDto storeDto) {
		StoreEntity storeEntity = storeRepository.findById(storeDto.getStoreId()).orElse(null);
		storeEntity.setManagerStaffId(storeDto.getManagerStaffId());
		
		AddressEntity addressEntity = addressRepository.findById(storeDto.getAddressId()).orElse(null);
		storeEntity.setAddressEntity(addressEntity);
	}
	
	// 삭제
	public boolean delete(int storeId) {
		if(customerRepository.countByStoreEntity(storeRepository.findById(storeId).orElse(null)) == 0) {
			storeRepository.delete(storeRepository.findById(storeId).orElse(null));
			return true;
		}
		return false;
	}
}
