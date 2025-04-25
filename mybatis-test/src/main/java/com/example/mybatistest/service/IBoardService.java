package com.example.mybatistest.service;

import java.util.List;

import com.example.mybatistest.dto.Board;

public interface IBoardService {
	List<Board> getAll();
	void remove(int boardNo);
}
