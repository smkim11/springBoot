package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CityDto;
import com.sakila.api.entity.CityEntity;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CityRepository;
import com.sakila.api.repository.CountryRepository;

@Service
@Transactional
public class CityService {
	private CityRepository cityRepository;
	private CountryRepository countryRepository;
	private AddressRepository addressRepository;
	
	// 필드 주입 대신 생성자주입 사용
	public CityService(CityRepository cityRepository, CountryRepository countryRepository, AddressRepository addressRepository) {
		this.cityRepository=cityRepository;
		this.countryRepository=countryRepository;
		this.addressRepository=addressRepository;
	}
	
	// 삭제
	public boolean delete(int cityId) {
		CityEntity deleteCityEntity = cityRepository.findById(cityId).orElse(null);
		
		if(addressRepository.countByCityEntity(deleteCityEntity) ==0) {
			cityRepository.delete(deleteCityEntity);
			return true;
		}
		return false;
	}
	
	// 수정
	public void update(CityDto cityDto) {
		CityEntity updateCityEntity = cityRepository.findById(cityDto.getCityId()).orElse(null);
		updateCityEntity.setCity(cityDto.getCity());
		// CityEntity의 countryEntity 수정
		CountryEntity countryEntity = countryRepository.findById(cityDto.getCountryId()).orElse(null);
		updateCityEntity.setCountryEntity(countryEntity);
	}
	
	// 입력
	public void save(CityDto cityDto) {
		// DTO -> Entity
		CityEntity saveCityEntity = new CityEntity();
		saveCityEntity.setCity(cityDto.getCity());
		
		/* CityDto에서 변환하여 호출
		CityEntity entity = cityDto.toEntity();
		*/
		
		// CountryEntity에서 countryId구해서 입력
		CountryEntity countryEntity = countryRepository.findById(cityDto.getCountryId()).orElse(null);
		saveCityEntity.setCountryEntity(countryEntity);
		
		cityRepository.save(saveCityEntity);
	}
	
	// 조회
	public List<CityEntity> findAll(){
		return cityRepository.findAll();
	}
	
	// 한 행 조회
	public CityEntity findById(int cityId) {
		return cityRepository.findById(cityId).orElse(null);
	}
}
