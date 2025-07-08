package com.example.diaop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.diaop.service.PersonService;

@RestController
public class GuestController {
	
	// GuestController는 PoliceService가 없으면 구현X
	// PoliceService가 먼저 생성되어야 한다 -> 의존관계
	
	// PoliceService ps = new PoliceService();  의존성을 발생시키는 코드
	
	// 생성자 주입 방식
	PersonService personService; // 생성자의 의존성 삭제, 타입선언에 대한 의존성은 O
	
	// 생성자 주입은 @Autowired 생략 가능
	public GuestController(PersonService personService) {
		// 주입전에 선행되는 코드를 추가
		this.personService = personService;
		// 테스트 코드를 추가
	}
	
	@GetMapping("/guest")
	public void guest() {
		personService.service();
	}
	
	/* Setter 주입 방식
	PoliceService policeService; // 생성자의 의존성 삭제, 타입선언에 대한 의존성은 O
	
	@Autowired
	public void setPoliceService(PoliceService policeService) {
		this.policeService = policeService;
	}
	
	@GetMapping("/guest")
	public void guest() {
		policeService.service();
	}
	*/
	
	/* 필드 주입 방식
	@Autowired PoliceService policeService; // 생성자의 의존성 삭제, 타입선언에 대한 의존성은 O
	
	@GetMapping("/guest")
	public void guest() {
		policeService.service();
	}
	 */
	
	/* guest메소드는 PoliceService를 의존한다
	@GetMapping("/guest")
	public void guest() {
		
		PoliceService ps = new PoliceService();
		ps.service();
	}
	*/
}
