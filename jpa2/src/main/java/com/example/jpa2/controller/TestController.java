package com.example.jpa2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa2.entity.Dept;
import com.example.jpa2.entity.Emp;
import com.example.jpa2.repository.DeptRepository;
import com.example.jpa2.repository.EmpRepository;

@RestController // View가 없는 컨트롤러 -> Model값을 Json문자열 값으로 반환
public class TestController {
	@Autowired
	EmpRepository empRepository;
	
	@Autowired
	DeptRepository deptRepository;
	
	@GetMapping("/empList")
	public List<Emp> empList(){
		
		return empRepository.findAll(); // Emp Entity의 select
	}
	
	@GetMapping("/deptList")
	public List<Dept> deptList(){
		
		return deptRepository.findAll(); // Dept Entity의 select
	}
}
