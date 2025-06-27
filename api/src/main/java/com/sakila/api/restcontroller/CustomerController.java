package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.service.CustomerService;

@RestController
public class CustomerController {
	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/customer")
	public ResponseEntity<List<CustomerEntity>> customer(){
		return new ResponseEntity<List<CustomerEntity>>(customerService.findAll(), HttpStatus.OK);
	}
}
