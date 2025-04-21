package com.example.jpaboard.entity;

public interface ArticleMapping {
	Long getId();
	String getTitle();
	String getContent();
	// String getPw(); 비밀번호 제외하고 가져오도록 필터링
}
