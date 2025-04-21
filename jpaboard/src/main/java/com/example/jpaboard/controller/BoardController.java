package com.example.jpaboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jpaboard.dto.BoardForm;
import com.example.jpaboard.entity.Board;
import com.example.jpaboard.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	@Autowired
	BoardRepository boardRepository;
	
	@GetMapping("/board/boardList")
	public String boardList(Model model
							,@RequestParam(value="currentPage",defaultValue = "0") int currentPage
							,@RequestParam(value="rowPerPage",defaultValue = "10") int rowPerPage
							,@RequestParam(value="word",defaultValue = "") String word) {
		Sort sort = Sort.by("boardNo").descending();
		PageRequest pageable = PageRequest.of(currentPage, rowPerPage, sort);
		Page<Board> list = boardRepository.findByBoardTitleContains(pageable, word);
	
		log.debug("현재 페이지: "+list.getNumber());
		
		model.addAttribute("list",list);
		model.addAttribute("nextPage",list.getNumber()+1);
		model.addAttribute("prePage",list.getNumber()-1);
		model.addAttribute("currentPage",list.getNumber());
		model.addAttribute("lastPage",list.getTotalPages());
		model.addAttribute("first",list.isFirst());
		model.addAttribute("hasNext",list.hasNext());
		
		return "board/boardList";
	}
	
	@GetMapping("/board/boardOne")
	public String boardOne(Model model ,@RequestParam int boardNo) {
		Board list = boardRepository.findById(boardNo).orElse(null);
		
		model.addAttribute("list", list);
		model.addAttribute("boardNo", boardNo);
		return "board/boardOne";
	}
	
	@GetMapping("/board/addBoard")
	public String addBoardForm() {
			
		return "board/addBoard";
	}
	
	@PostMapping("/board/addBoard")
	public String addBoardAction(BoardForm boardForm) {
		Board entity = boardForm.toEntity();
		
		boardRepository.save(entity);
			
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/board/modifyBoard")
	public String modifyBoardForm(Model model ,@RequestParam int boardNo) {
		Board list = boardRepository.findById(boardNo).orElse(null);
		
		model.addAttribute("list", list);
		model.addAttribute("boardNo", boardNo);
		return "board/modifyBoard";
	}
	
	@PostMapping("/board/modifyBoard")
	public String modifyBoardAction(BoardForm boardForm, @RequestParam int boardNo) {
		Board entity = boardForm.toEntity();
		
		boardRepository.save(entity);
		
		return "redirect:/board/boardOne?boardNo="+boardNo;
	}
	
	@GetMapping("/board/deleteBoard")
	public String deleteBoard(@RequestParam int boardNo) {
		boardRepository.deleteById(boardNo);
		
		return "redirect:/board/boardList";
	}
}
