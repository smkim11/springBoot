package com.example.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.fileupload.dto.BoardForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	// 입력 폼
	@GetMapping("/addBoard")
	public String addBoard() {
		return "addBoard";
	}
	// 입력 액션
	@PostMapping("/addBoard")
	public String addBoard(BoardForm boardForm) {
		log.debug(boardForm.toString());
		// 파일을 첨부하지 않아도 fileSize는 1이다
		log.debug("파일 개수: "+boardForm.getFileList().size());
		
		// 파일 분리
		List<MultipartFile> list = boardForm.getFileList();
		
		// 파일을 첨부했는지 안했는지 확인
		long firstFileSize = list.get(0).getSize();
		log.debug("첫번째 파일 사이즈: "+firstFileSize);
		
		if(firstFileSize>0) { // 첫번째 파일 사이즈가 0이상이다 == 첨부된 파일이 있다
			for(MultipartFile f : list) {
				log.debug("파일 타입: "+f.getContentType()); 
				log.debug("파일원본 이름: "+f.getOriginalFilename()); 
				log.debug("파일 크기: "+f.getSize()); 
				// 확장자만 추출 
				String ext = f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf(".")+1);
				log.debug("확장자: "+ ext);
				// 저장될 파일이름 
				String saveName = UUID.randomUUID().toString().replace("-", "")+"."+ext;
				log.debug("저장파일이름: "+ saveName);
				
				File emptyFile = new File("C:/project/upload/"+saveName);
				// f의 byte를 emptyFile 복사
				
				try {
					f.transferTo(emptyFile);
				} catch (Exception e) {
					log.error("파일저장 실패");
					e.printStackTrace();
				}
			}
		}
		return "redirect:/";
	}
}
