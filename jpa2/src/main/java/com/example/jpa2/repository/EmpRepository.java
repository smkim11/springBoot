package com.example.jpa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa2.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer> {
	

}
