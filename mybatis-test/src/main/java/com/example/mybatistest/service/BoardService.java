package com.example.mybatistest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mybatistest.dto.Board;
import com.example.mybatistest.mapper.BoardMapper;
import com.example.mybatistest.mapper.CommentMapper;

@Service
@Transactional // 여러 쿼리를 실행시 하나라도 오류가나면 실행 취소
// 인터페이스를 하나 만들어서 상속받게 하고 controller에서 호출할때 인터페이스 호출
// 여러 service를 생성했을 때 controller에서 하나하나 불러서 사용하지 않고 한개의 인터페이스를 호출하여 사용가능
public class BoardService implements IBoardService{ 
	@Autowired
	BoardMapper boardMapper;
	@Autowired
	CommentMapper commentMapper;
	
	@Override
	public List<Board> getAll(){
		// getAll()은 중계역할
		return boardMapper.selectAll();
	}
	
	// commentMapper에서 댓글삭제 메소드와 boardMapper에서 글삭제 메소드를 불러와서 remove()메소드에 저장
	// 글 삭제 시 controller에서 두개의 메소드를 호출할 필요 없이 boardService의 remove()만 호출하면 삭제
	@Override
	public void remove(int boardNo) {
		commentMapper.deleteByBoardNo(boardNo);
		boardMapper.deleteByBoardNo(boardNo);
	}
}
