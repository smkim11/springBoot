package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.api.entity.AddressEntity;
import com.sakila.api.service.AddressService;

@RestController
public class AddressController {
	private AddressService addressService;
	
	// 생성자로 주입
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@GetMapping("/address")
	public ResponseEntity<List<AddressEntity>> address(){
		return new ResponseEntity<List<AddressEntity>>(addressService.findAll(),HttpStatus.OK);
	}
}
