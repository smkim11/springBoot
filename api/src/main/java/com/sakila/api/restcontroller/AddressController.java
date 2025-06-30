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

import com.sakila.api.dto.AddressDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.service.AddressService;

@RestController
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
	@PostMapping("/address")
	public ResponseEntity<String> address(@RequestBody AddressDto addressDto){
		addressService.save(addressDto);
		return new ResponseEntity<String>("입력성공",HttpStatus.OK);
	}
	
	// 수정
	@PatchMapping("/address")
	public ResponseEntity<String> updateAddress(@RequestBody AddressDto addressDto){
		
		addressService.update(addressDto);
		return new ResponseEntity<String>("수정성공",HttpStatus.OK);
	}
	
	// 삭제
	@DeleteMapping("/address/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable int addressId){
		if(addressService.delete(addressId)) {
			return new ResponseEntity<String>("삭제성공",HttpStatus.OK);
		}
		return new ResponseEntity<String>("삭제실패",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
