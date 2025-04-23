package com.example.fileupload.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardForm {
	private String title;
	private String pw;
	private List<MultipartFile> fileList; // spring 에서 input type="file" 사용(여러개 입력하기 위해 List로)
}
