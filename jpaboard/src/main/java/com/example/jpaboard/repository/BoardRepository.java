package com.example.jpaboard.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpaboard.entity.Board;

public interface BoardRepository extends JpaRepository<Board,Integer>{
	Page<Board> findByBoardTitleContains(PageRequest pageable, String word);
}
