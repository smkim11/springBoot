package com.example.jpaboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpaboard.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Integer> {
	// memberId 중복 검사 메소드
	// 로그인 하는 추상메소드
	boolean existsByMemberId(String memberId);
}
