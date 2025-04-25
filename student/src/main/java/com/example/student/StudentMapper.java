package com.example.student;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
	List<Student> selectAll();
	Student selectOneByName(String name);
	int insert(Student student);
	int update(Student student);
	int delete(String name);
}
