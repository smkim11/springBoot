package com.example.dbtest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="test")
public class TestEntity { // -> DTO 역할
	@Id // 기본키 필드 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;
	
	@Column(name="id")
	private String id;
}
