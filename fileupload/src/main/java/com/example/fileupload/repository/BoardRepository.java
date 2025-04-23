package com.example.fileupload.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fileupload.entity.Board;
import com.example.fileupload.entity.BoardMapping;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	List<BoardMapping> findAllBy(); // 매핑하여 비밀번호 제외하고 가져옴
	BoardMapping findByBno(int bno);
}
