package com.example.jpaboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpaboard.entity.Member;
import com.example.jpaboard.entity.MemberOnlyMemberId;

public interface MemberRepository extends JpaRepository<Member,Integer> {
	// memberId 중복 검사 메소드
	boolean existsByMemberId(String memberId);
	
	// 로그인 하는 추상메소드
	MemberOnlyMemberId findByMemberIdAndMemberPw(String memberId, String memberPw);
	
	Page<MemberOnlyMemberId> findAllBy(Pageable pageable);
	// 페이징, 검색 리스트 
	Page<MemberOnlyMemberId> findByMemberIdContains(Pageable pageable, String word);
	
	// 비밀번호 수정
	@Transactional
	@Modifying
	@Query(nativeQuery = true, 
			value="update member set "
					+ "member_pw = :memberNewPw "
					+ "where member_id like :memberId "
					+ "and member_pw= :memberPw")
	void modifyPw(String memberId, String memberPw, String memberNewPw);
	
	// 회원 탈퇴
	@Transactional
	@Modifying
	@Query(nativeQuery = true, 
			value="delete from member "
					+ "where member_id like :memberId "
					+ "and member_pw = :memberPw")
	void removeMember(String memberId, String memberPw);
}
