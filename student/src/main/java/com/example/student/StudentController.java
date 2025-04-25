package com.example.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody View가 없는 컨트롤러
public class StudentController {
	@Autowired
	StudentService studentService;
	
	// 전체 출력
	@GetMapping("/students")
	public List<Student> students() {
	
		return studentService.getAll(); // List<Student> -> JSON 문자열로 변경
	}
	
	// 하나만 출력
	@GetMapping("/student/{name}") // 'student?name=이름' 이 아니라 'student/이름' 으로 검색 가능
	public Student studentOne(@PathVariable String name) {
		return studentService.getOneByName(name);
	}
	
	@GetMapping("/student") 
	public Student studentOne2(@RequestParam String name) {
		return studentService.getOneByName(name);
	}
	
	// 추가
	@PostMapping("/student")
	public Student add(@RequestBody Student student) { // JSON형식의 값을 받기위해 @RequestBody 사용
		int row =studentService.add(student);
		if(row==1) {
			return student;
		}
		return null;
	}
	
	// 수정
	@PatchMapping("/student")
	public Student modify(@RequestBody Student student) { // JSON형식의 값을 받기위해 @RequestBody 사용
		int row =studentService.modify(student);
		if(row==1) {
			return student;
		}
		return null;
	}
	
	// 삭제
	@DeleteMapping("/student/{name}")
	public String remove(@PathVariable String name) {
		int row = studentService.remove(name);
		if(row==1) {
			return name+"삭제 성공";
		}
		return name+"삭제 실패";
	}
}
