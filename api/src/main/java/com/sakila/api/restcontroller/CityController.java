package com.sakila.api.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
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

import com.sakila.api.dto.CityDto;
import com.sakila.api.entity.CityEntity;
import com.sakila.api.entity.CityMapping;
import com.sakila.api.service.CityService;

@RestController
@CrossOrigin
public class CityController {
	private CityService cityService;
	
	// 생성자로 주입
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}
	
	// 한 행 조회
	@GetMapping("/cityOne/{cityId}")
	public ResponseEntity<CityEntity> cityOne(@PathVariable int cityId){
		return new ResponseEntity<CityEntity>(cityService.findById(cityId),HttpStatus.OK);
	}
	
	// 조회
	@GetMapping("/cityList/{currentPage}")
	public ResponseEntity<Page<CityMapping>> city(@PathVariable int currentPage){
		return new ResponseEntity<Page<CityMapping>>(cityService.findAll(currentPage), HttpStatus.OK);
	}
	
	// 입력
	@PostMapping("/addCity")
	public ResponseEntity<Map<String,String>> addCity(@RequestBody CityDto cityDto){
		
		cityService.save(cityDto);
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("result","입력 성공");
		return new ResponseEntity<Map<String,String>>(resultMap,HttpStatus.OK);
	}
	
	// 수정
	@PatchMapping("/updateCity")
	public ResponseEntity<Map<String,String>> updateCity(@RequestBody CityDto cityDto){
		cityService.update(cityDto);
		
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("result","수정 성공");
		return new ResponseEntity<Map<String,String>>(resultMap,HttpStatus.OK);
	}
	
	// 삭제
	@DeleteMapping("/deleteCity/{cityId}")
	public ResponseEntity<Map<String,String>> deleteCity(@PathVariable int cityId){
		Map<String,String> resultMap = new HashMap<String,String>();
		if(cityService.delete(cityId)) {
			resultMap.put("result","삭제성공");
			return new ResponseEntity<Map<String,String>>(resultMap, HttpStatus.OK);
		}
		resultMap.put("result","삭제실패");
		return new ResponseEntity<Map<String,String>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
