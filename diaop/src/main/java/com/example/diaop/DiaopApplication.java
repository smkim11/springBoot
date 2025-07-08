package com.example.diaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.diaop.service.OfficerService;
import com.example.diaop.service.PersonService;

// @SpringBootApplication는 @Configuration을 포함한다 -> 설정파일 역할을 한다
@SpringBootApplication
public class DiaopApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiaopApplication.class, args);
	}

	// 설정파일에서 빈을 생성
	@Bean
	public PersonService personService() {
		return new OfficerService();
	}
}
