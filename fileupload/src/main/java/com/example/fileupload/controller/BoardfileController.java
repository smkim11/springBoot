package com.example.fileupload.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fileupload.entity.Boardfile;
import com.example.fileupload.repository.BoardFileRepository;
import com.example.fileupload.repository.BoardRepository;

@Controller
public class BoardfileController {
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	BoardFileRepository boardFileRepository;
	
	// 파일 하나 삭제
	@GetMapping("removeBoardfile")
	public String removeBoardfile(@RequestParam int fno, @RequestParam int bno) {
		
		// 파일 먼저 삭제 후 
		Boardfile boardfile = boardFileRepository.findById(fno).orElse(null);
		File file = new File("C:/project/upload/"+boardfile.getFname()+"."+boardfile.getFext());
		if(file.exists()) {
			file.delete();
		}
		
		// 테이블 삭제
		boardFileRepository.deleteById(fno);
		return "redirect:/boardOne?bno="+bno;
	}
}
