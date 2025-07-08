package com.example.aopex;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/")
	public String hello() {
		System.out.println("hello");
		return "hello";
	}
	
	@GetMapping("/admin/login")
	public String adminLogin() {
		System.out.println("adminLogin");
		return "adminLogin";
	}
}
