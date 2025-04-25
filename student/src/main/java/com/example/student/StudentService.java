package com.example.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {
	@Autowired
	StudentMapper studentMapper;
	
	public List<Student> getAll(){
		return studentMapper.selectAll();
	}
	
	public Student getOneByName(String name) {
		return studentMapper.selectOneByName(name);
	}
	
	public int add(Student student) {
		return studentMapper.insert(student);
	}
	
	public int modify(Student student) {
		return studentMapper.update(student);
	}
	
	public int remove(String name) {
		return studentMapper.delete(name);
	}
}
