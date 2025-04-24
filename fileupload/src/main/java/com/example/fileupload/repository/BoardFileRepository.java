package com.example.fileupload.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fileupload.entity.Boardfile;

import jakarta.transaction.Transactional;

public interface BoardFileRepository extends JpaRepository<Boardfile, Integer>{
	List<Boardfile> findByBno(int bno); //findBy뒤에 입력할 데이터 이름(기본키면 Id로 가능)
	
	// PK로 한행삭제
	// 제공하는 void deleteById(int id) 사용
	
	// FK로 여러행 삭제 (Board 삭제시 같이 삭제 : 트랜잭션처리)
	@Transactional
	void deleteByBno(int bno);
}
