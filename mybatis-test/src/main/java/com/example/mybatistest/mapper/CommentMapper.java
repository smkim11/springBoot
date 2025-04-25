package com.example.mybatistest.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
	public void deleteByBoardNo(int boardNo); // mybatis는 update, delete, insert는 자동으로 Integer반환
}
