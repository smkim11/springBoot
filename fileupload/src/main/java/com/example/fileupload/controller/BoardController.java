package com.example.fileupload.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.fileupload.dto.BoardForm;
import com.example.fileupload.entity.Board;
import com.example.fileupload.entity.BoardMapping;
import com.example.fileupload.entity.Boardfile;
import com.example.fileupload.repository.BoardFileRepository;
import com.example.fileupload.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	BoardFileRepository boardFileRepository;
	
	@GetMapping({"/","/boardList"}) // 둘다 이동 가능
	public String boardList(Model model
							,@RequestParam(defaultValue = "0") int currentPage
							,@RequestParam(defaultValue = "5") int rowPerPage
							,@RequestParam(defaultValue = "") String word) {
		// 페이징
		// Sort
		Sort sort = Sort.by("bno").descending();
		// PageRequest
		PageRequest pageable = PageRequest.of(currentPage, rowPerPage, sort);
		// Page<BoardMapping>
		Page<BoardMapping> list = boardRepository.findByTitleContains(pageable, word);
		
		model.addAttribute("list", list);	
		model.addAttribute("prePage", list.getNumber()-1);
		model.addAttribute("nextPage", list.getNumber()+1);
		model.addAttribute("lastPage", list.getTotalPages());
		model.addAttribute("isFirst", list.isFirst());
		model.addAttribute("hasNext", list.hasNext());
		return "boardList";
	}
	
	@GetMapping("/boardOne")
	public String boardOne(Model model, @RequestParam int bno) {
		BoardMapping boardMapping = boardRepository.findByBno(bno);

		log.debug(boardMapping.toString());
		List<Boardfile> fileList = boardFileRepository.findByBno(bno);
		log.debug("size: "+fileList.size());
		
		model.addAttribute("boardMapping", boardMapping);
		model.addAttribute("fileList", fileList);
		return "boardOne";
	}
	
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
		
		// Board dto->entity 변환
		Board board = new Board();
		board.setTitle(boardForm.getTitle());
		board.setPw(boardForm.getPw());
		boardRepository.save(board); // board 저장
		int bno = board.getBno(); // 저장 후 bno가 동기화되어 들어왔는지 확인
		log.debug("bno: "+bno);
		// 파일 분리
		List<MultipartFile> list = boardForm.getFileList();
		
		// 파일을 첨부했는지 안했는지 확인
		long firstFileSize = list.get(0).getSize();
		log.debug("첫번째 파일 사이즈: "+firstFileSize);
		
		if(firstFileSize>0) { // 첫번째 파일 사이즈가 0이상이다 == 첨부된 파일이 있다
			
			// 업로드 하면 안되는 파일이 있거나 파일크기가 10MB 이상이면 저장 X
			for(MultipartFile f : list) {
				if(f.getContentType().equals("application/octet-stream") || f.getSize()>1024*1024*10) { // 10MB
					return "redirect:/addBoard";
				}
			}
			
			// 파일 업로드 
			for(MultipartFile f : list) {
				log.debug("파일 타입: "+f.getContentType()); 
				log.debug("파일원본 이름: "+f.getOriginalFilename()); 
				log.debug("파일 크기: "+f.getSize()); 
				// 확장자만 추출 
				String ext = f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf(".")+1);
				log.debug("확장자: "+ ext);
				// 저장될 파일이름 
				String fname = UUID.randomUUID().toString().replace("-", "");
				log.debug("저장파일이름: "+ fname);
				
				File emptyFile = new File("C:/project/upload/"+fname+"."+ext);
				// f의 byte를 emptyFile 복사
				
				try {
					f.transferTo(emptyFile);
				} catch (Exception e) {
					log.error("파일저장 실패");
					e.printStackTrace();
				}
				
				// BoardFile도 변환하여 저장
				Boardfile boardFile = new Boardfile();
				boardFile.setBno(board.getBno());
				boardFile.setFname(fname);
				boardFile.setFtype(f.getContentType());
				boardFile.setFext(ext);
				boardFile.setForiginname(f.getOriginalFilename());
				boardFile.setFsize(f.getSize());
				boardFileRepository.save(boardFile);
			}
		}
		return "redirect:/";
	}
	
	// 게시글 수정페이지 이동
	@GetMapping("/modifyBoard")
	public String modifyBoard(Model model,@RequestParam int bno) {
		BoardMapping boardMapping = boardRepository.findByBno(bno);
		
		model.addAttribute("boardMapping", boardMapping);
		return "modifyBoard";
	}
	
	// 게시글 수정 실행
	@PostMapping("/modifyBoard")
	public String modifyBoard(BoardForm boardForm, @RequestParam int bno) {
		Board board = boardRepository.findById(bno);
		log.debug(board.getPw());
		log.debug(boardForm.getPw());
		if(boardForm.getPw().equals(board.getPw())) {
			boardRepository.modifyBoard(boardForm.getTitle(), bno);
			return "redirect:/boardOne?bno="+bno;
		}
		
		return "redirect:/modifyBoard?bno="+bno;
	}
	
	// 게시글 삭제 폼 이동
	@GetMapping("/deleteBoard")
	public String deleteBoard(Model model,@RequestParam int bno) {
		
		model.addAttribute("bno", bno);
		return "deleteBoard";
	}
	
	// 게시글 삭제
	@PostMapping("/deleteBoard")
	public String deleteBoard(BoardForm boardForm, @RequestParam int bno) {
		Board board = boardRepository.findById(bno);
		List<Boardfile> list = boardFileRepository.findByBno(bno);
		
		if(boardForm.getPw().equals(board.getPw())) { // 게시글에 있는 파일 삭제 후 게시글 삭제
			for(Boardfile bf : list) {
				File file = new File("C:/project/upload/"+bf.getFname()+"."+bf.getFext());
				if(file.exists()) {
					file.delete();
				}
			}
			boardFileRepository.deleteByBno(bno);
			boardRepository.deleteById(bno);
			
			return "redirect:/";
		}
		
		return "redirect:/deleteBoard?bno="+bno;
	}
	
}
