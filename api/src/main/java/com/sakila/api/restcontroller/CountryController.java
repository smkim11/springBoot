package com.sakila.api.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
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
	@PostMapping("/addCountry")
	public ResponseEntity<Map<String,String>> addCountry(@RequestBody CountryDto countryDto){
		// @RequestBody json형태의 문자열 매개값을 CountryDto타입으로 변환시킨다.
		
		/*
		CountryDto에 작성한 entity변환 사용
		CountryEntity countryEntity = countryDto.toEntity();
		*/
		
		countryService.save(countryDto);
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("result","입력 성공");
		return new ResponseEntity<Map<String,String>>(resultMap, HttpStatus.OK);
	}
	
	// 수정
	@PatchMapping("/updateCountry")
	public ResponseEntity<Map<String,String>> updateCountry(@RequestBody CountryDto countryDto){
		countryService.update(countryDto);
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("result","수정 성공");
		return new ResponseEntity<Map<String,String>>(resultMap, HttpStatus.OK);
	}
		
	// 삭제
	@DeleteMapping("/deleteCountry/{countryId}")
	public ResponseEntity<Map<String,String>> deleteCountry(@PathVariable int countryId){
		boolean result = countryService.delete(countryId); 
		Map<String,String> resultMap = new HashMap<String,String>();
		
		if(result) {
			resultMap.put("result","삭제성공");
			return new ResponseEntity<Map<String,String>>(resultMap, HttpStatus.OK);
		}
		resultMap.put("result","삭제실패");
		return new ResponseEntity<Map<String,String>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}