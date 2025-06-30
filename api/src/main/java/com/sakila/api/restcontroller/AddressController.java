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

import com.sakila.api.dto.AddressDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.service.AddressService;

@RestController
@CrossOrigin
public class AddressController {
	private AddressService addressService;
	
	// 생성자로 주입
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	// 한 행 조회
	@GetMapping("/addressOne/{addressId}")
	public ResponseEntity<AddressEntity> addressOne(@PathVariable int addressId){
		return new ResponseEntity<AddressEntity>(addressService.findById(addressId), HttpStatus.OK);
	}
	
	// 조회
	@GetMapping("/address")
	public ResponseEntity<List<AddressEntity>> address(){
		return new ResponseEntity<List<AddressEntity>>(addressService.findAll(),HttpStatus.OK);
	}
	
	// 입력
	@PostMapping("/addAddress")
	public ResponseEntity<Map<String,String>> addAddress(@RequestBody AddressDto addressDto){
		addressService.save(addressDto);
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("result","입력 성공");
		return new ResponseEntity<Map<String,String>>(resultMap,HttpStatus.OK);
	}
	
	// 수정
	@PatchMapping("/updateAddress")
	public ResponseEntity<Map<String,String>> updateAddress(@RequestBody AddressDto addressDto){
		
		addressService.update(addressDto);
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("result","수정 성공");
		return new ResponseEntity<Map<String,String>>(resultMap,HttpStatus.OK);
	}
	
	// 삭제
	@DeleteMapping("/deleteAddress/{addressId}")
	public ResponseEntity<Map<String,String>> deleteAddress(@PathVariable int addressId){
		Map<String,String> resultMap = new HashMap<String,String>();
		if(addressService.delete(addressId)) {
			resultMap.put("result","삭제성공");
			return new ResponseEntity<Map<String,String>>(resultMap, HttpStatus.OK);
		}
		resultMap.put("result","삭제실패");
		return new ResponseEntity<Map<String,String>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
