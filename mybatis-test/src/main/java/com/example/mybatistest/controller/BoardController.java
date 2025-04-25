package com.example.mybatistest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mybatistest.dto.Board;
import com.example.mybatistest.service.BoardService;
import com.example.mybatistest.service.IBoardService;

@Controller
public class BoardController {
	//@Autowired
	//BoardService boardService; // 클래스와 통신
	@Autowired
	IBoardService boardService; // 인터페이스와 통신
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		List<Board> list = boardService.getAll();
		
		model.addAttribute("list", list);
		return "boardList";
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam int boardNo) {
		boardService.remove(boardNo);
		return "redirect:/boardList";
	}
}
