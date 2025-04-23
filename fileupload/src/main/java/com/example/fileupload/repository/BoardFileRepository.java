package com.example.fileupload.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fileupload.entity.Board;
import com.example.fileupload.entity.Boardfile;

public interface BoardFileRepository extends JpaRepository<Boardfile, Integer>{
	List<Boardfile> findByBoard(Board board); //findBy뒤에 입력할 데이터 이름(기본키면 Id로 가능)
}
