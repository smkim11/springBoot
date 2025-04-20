package com.example.jpaboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.jpaboard.dto.ArticleForm;
import com.example.jpaboard.entity.Article;
import com.example.jpaboard.repository.ArticleRepository;

@Controller
public class ArticleController {
	@Autowired // 의존성 주입
	private ArticleRepository articleRepository;

	@GetMapping("/articles/new") // servlet에서 -> doGet()
	public String newArticle() {
		
		return "articles/new"; // forward
	}
	
	@PostMapping("/articles/create") // servlet에서 -> doPost()
	public String createArticle(ArticleForm form) { // DTO(커맨드객체)
		System.out.println(form.toString());
		
		// DTO -> Entity 타입으로 변환
		Article entity = form.toEntity();
		
		articleRepository.save(entity); // repository를 호출할때는 Entity가 필요하다
		
		return "redirect:/"; // home("/")로 redirect
	}
}
