package com.example.jpaboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jpaboard.dto.ArticleForm;
import com.example.jpaboard.entity.Article;
import com.example.jpaboard.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ArticleController {
	@Autowired // 의존성 주입
	private ArticleRepository articleRepository;

	@GetMapping("/articles/index")
	public String articleList(Model model
							, @RequestParam(value = "currentPage", defaultValue="0") int currentPage
							, @RequestParam(value = "rowPerPage", defaultValue="10") int rowPerPage
							, @RequestParam(value = "word", defaultValue="") String word) {
		
		Sort s1 = Sort.by("title").ascending(); // 정렬
		Sort s2 = Sort.by("content").ascending();
		Sort sort = s1.and(s2);
		
		PageRequest pageable = PageRequest.of(currentPage, rowPerPage, sort); 
		
		Page<Article> list =  articleRepository.findByTitleContaining(pageable, word);
		
		// Page의 추가 속성
		log.debug("TotalElements: "+list.getTotalElements()); // 전체 행의 사이즈
		log.debug("TotalPage: "+list.getTotalPages()); // 전체 페이지 사이즈 -> lastPage
		log.debug("Number: "+list.getNumber()); // 현재 페이지
		log.debug("Size: "+list.getSize()); // rowPerPage
		log.debug("isFirst: "+list.isFirst()); // 1페이지인지 : 이전링크 유무
		log.debug("hasNext: "+list.hasNext()); // 다음페이지 유무
		
		model.addAttribute("word", word);
		model.addAttribute("prePage", list.getNumber()-1);
		model.addAttribute("nextPage", list.getNumber()+1);
		model.addAttribute("list", list);
		
		return "articles/index"; // forward
	}
	
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
		
		return "redirect:/articles/index"; // GET호출 /articles/index
	}
	
}
