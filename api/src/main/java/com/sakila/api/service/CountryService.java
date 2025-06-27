package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CountryDto;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.repository.CountryRepository;

@Service
@Transactional

public class CountryService {
	private CountryRepository countryRepository;
	
	// 필드주입대신 생성자 주입 Autowired 생략가능
	public CountryService(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}
	
	// CountryEntity 입력
	public void save(CountryDto countryDto) {
		// 직접 Dto를 entity로 변환
		CountryEntity saveCountryEntity = new CountryEntity();
		saveCountryEntity.setCountry(countryDto.getCountry());
		
		countryRepository.save(saveCountryEntity);
	}
	
	// 전체 조회
	public List<CountryEntity> findAll(){
		return countryRepository.findAll();
	}
	
}
