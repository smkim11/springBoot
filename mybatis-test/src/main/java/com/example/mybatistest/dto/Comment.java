package com.example.mybatistest.dto;

import lombok.Data;

@Data
public class Comment {
	private int commentNo;
	private int boardNo;
	private String commentContent;
}
