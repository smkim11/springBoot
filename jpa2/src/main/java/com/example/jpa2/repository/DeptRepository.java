package com.example.jpa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa2.entity.Dept;

public interface DeptRepository extends JpaRepository<Dept, Integer>{

}
