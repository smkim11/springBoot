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

import com.sakila.api.dto.CustomerDto;
import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.service.CustomerService;

@RestController
public class CustomerController {
	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	// 한 행 조회
	@GetMapping("/customerOne/{customerId}")
	public ResponseEntity<CustomerEntity> customerOne(@PathVariable int customerId){
		return new ResponseEntity<CustomerEntity>(customerService.findById(customerId),HttpStatus.OK);
	}
	
	// 조회
	@GetMapping("/customer")
	public ResponseEntity<List<CustomerEntity>> customer(){
		return new ResponseEntity<List<CustomerEntity>>(customerService.findAll(), HttpStatus.OK);
	}
	
	// 입력
	@PostMapping("/addCustomer")
	public ResponseEntity<String> addCustomer(@RequestBody CustomerDto customerDto){
		customerService.save(customerDto);
		return new ResponseEntity<String>("입력 성공",HttpStatus.OK);
	}
	
	// 수정
	@PatchMapping("/updateCustomer")
	public ResponseEntity<String> updateCustomer(@RequestBody CustomerDto customerDto){
		customerService.update(customerDto);
		return new ResponseEntity<String>("수정 성공",HttpStatus.OK);
	}
	
	// 삭제
	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int customerId){
		customerService.delete(customerId);
		return new ResponseEntity<String>("삭제 성공",HttpStatus.OK);
	}
}
