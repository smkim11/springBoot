package com.example.mybatistest.dto;

import lombok.Data;

@Data
public class Comment {
	private Integer commentNo;
	private Integer boardNo;
	private String commentContent;
}
