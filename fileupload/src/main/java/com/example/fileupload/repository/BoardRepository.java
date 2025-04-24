package com.example.fileupload.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.fileupload.entity.Board;
import com.example.fileupload.entity.BoardMapping;

import jakarta.transaction.Transactional;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	List<BoardMapping> findAllBy(); // 매핑하여 비밀번호 제외하고 가져옴
	BoardMapping findByBno(int bno);
	Board findById(int bno);
	Page<BoardMapping> findByTitleContains(PageRequest pageable, String word);
	
	// 게시글 수정
	@Transactional
	@Modifying
	@Query(nativeQuery = true, 
			value="update board set "
				 + "title= :title "
				 + "where bno= :bno")
	void modifyBoard(String title, int bno);
}
