package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.api.dto.CountryDto;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.service.CountryService;

@RestController
public class CountryController {
	private CountryService countryService;
	
	// 필드주입 대신 생성자 주입 사용
	public CountryController(CountryService countryService) {
		this.countryService=countryService;
	}
	
	// 전체 조회
	@GetMapping("/country")
	public ResponseEntity<List<CountryEntity>> country(){
		return new ResponseEntity<List<CountryEntity>>(countryService.findAll(), HttpStatus.OK);
	}
	
	// 한 행 조회
	@GetMapping("/countryOne/{countryId}")
	public ResponseEntity<CountryEntity> countryOne(@PathVariable int countryId){
		return new ResponseEntity<CountryEntity>(countryService.findById(countryId), HttpStatus.OK);
	}
	
	// 저장
	@PostMapping("/country")
	public ResponseEntity<String> country(@RequestBody CountryDto countryDto){
		// @RequestBody json형태의 문자열 매개값을 CountryDto타입으로 변환시킨다.
		
		/*
		CountryDto에 작성한 entity변환 사용
		CountryEntity countryEntity = countryDto.toEntity();
		*/
		
		countryService.save(countryDto);
		return new ResponseEntity<String>("입력성공", HttpStatus.OK);
	}
	
	// 수정
	@PatchMapping("/country")
	public ResponseEntity<String> updateCountry(@RequestBody CountryDto countryDto){
		countryService.update(countryDto);
		return new ResponseEntity<String>("수정성공", HttpStatus.OK);
	}
		
	// 삭제
	@DeleteMapping("/country/{countryId}")
	public ResponseEntity<String> deleteCountry(@PathVariable int countryId){
		boolean result = countryService.delete(countryId); 
		
		if(result) {
			return new ResponseEntity<String>("삭제성공", HttpStatus.OK);
		}
		return new ResponseEntity<String>("삭제실패", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}