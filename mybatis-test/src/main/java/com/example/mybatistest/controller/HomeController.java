package com.example.mybatistest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@GetMapping("/")
	public String home() {
		log.debug("home test");
		return "home"; // /webapp/WEB-INF/view/home.jsp 포워딩
	}
}
