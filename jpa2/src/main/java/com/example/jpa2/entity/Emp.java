package com.example.jpa2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="emp")
public class Emp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eno;
	
	@Column(name="ename")
	private String ename;
	
	@Column(name="gender")
	private String gender;
	
	// dept 하나에 여러개의 emp가 들어갈 수 있다
	@JsonIgnoreProperties({"empList"}) // 양방향시 발생하는 무한참조(양쪽 Setter의 재귀참조) 방지
	@ManyToOne
	@JoinColumn(name="dno") // emp테이블의 Fk
	private Dept dept;
}
