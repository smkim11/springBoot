package com.example.dbtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dbtest.entity.TestEntity;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Integer>{ // -> DAO 역할
	
}


