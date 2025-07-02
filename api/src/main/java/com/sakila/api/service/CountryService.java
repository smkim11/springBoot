package com.sakila.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CountryDto;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.entity.CountryMapping;
import com.sakila.api.repository.CityRepository;
import com.sakila.api.repository.CountryRepository;

@Service
@Transactional

public class CountryService {
	private CountryRepository countryRepository;
	private CityRepository cityRepository;
	
	// 필드주입대신 생성자 주입 Autowired 생략가능
	public CountryService(CountryRepository countryRepository, CityRepository cityRepository) {
		this.countryRepository = countryRepository;
		this.cityRepository = cityRepository;
	}
	
	// 한 행 조회
	public CountryEntity findById(int countryId) {
		return countryRepository.findById(countryId).orElse(null);
	}
	
	// 전체 조회
	public Page<CountryMapping> findAll(int currentPage){
		int pageSize=10;
		int pageNum = currentPage-1;
		Sort sort = Sort.by("countryId").ascending();
		
		PageRequest pageable = PageRequest.of(pageNum, pageSize,sort);
		return countryRepository.findAllBy(pageable);
	}
		
	// 삭제
	public boolean delete(int countryId) {
		// 자식테이블에 외래키로 참조하는 행이 있는지 검사
		CountryEntity countryEntity = countryRepository.findById(countryId).orElse(null);
		
		// 자식테이블에 외래키로 참조하는 행이 있다면
		if(cityRepository.countByCountryEntity(countryEntity) != 0) {
			System.out.println("자식테이블에 외래키로 참조하는 행이 있습니다.");
			return false;
		}
		// 자식테이블에 참조하는 행이 없다면(select count(*) from city where country_id=countryId)
		countryRepository.deleteById(countryId);
		return true;
	}
	
	// 수정
	public void update(CountryDto countryDto) {
		CountryEntity updateCountryEntity = countryRepository.findById(countryDto.getCountryId()).orElse(null);
		updateCountryEntity.setCountry(countryDto.getCountry());
	}
	
	// CountryEntity 입력
	public void save(CountryDto countryDto) {
		// 직접 Dto를 entity로 변환
		CountryEntity saveCountryEntity = new CountryEntity();
		saveCountryEntity.setCountry(countryDto.getCountry());
		
		countryRepository.save(saveCountryEntity);
	}
	
}
