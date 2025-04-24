package com.example.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.fileupload.dto.BoardForm;
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
	
	@GetMapping("/addFile")
	public String addFile(Model model, @RequestParam int bno) {
		
		model.addAttribute("bno", bno);
		return "addFile";
	}
	
	@PostMapping("/addFile")
	public String addFile(BoardForm boardForm, @RequestParam int bno) {
		List<MultipartFile> list = boardForm.getFileList();
		if(!list.get(0).isEmpty()) {
			for(MultipartFile file : list) {
				// 파일의 확장자 추출
				String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
				// 저장되는 파일이름 UUID로 다르게 설정
				String fileName = UUID.randomUUID().toString().replace("-", "");
				
				// 실제 폴더 저장 위치
				File saveFolder = new File("C:/project/upload/"+fileName+"."+ext);
				
				try {
					file.transferTo(saveFolder);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				
				Boardfile boardfile = new Boardfile();
				boardfile.setForiginname(file.getOriginalFilename());
				boardfile.setBno(bno);
				boardfile.setFsize(file.getSize());
				boardfile.setFext(ext);
				boardfile.setFtype(file.getContentType());
				boardfile.setFname(fileName);
				boardFileRepository.save(boardfile);
			}
		}
		return "redirect:/boardOne?bno="+bno;
	}
}
