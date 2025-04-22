package com.example.jpaboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.jpaboard.dto.MemberForm;
import com.example.jpaboard.entity.Member;
import com.example.jpaboard.entity.MemberOnlyMemberId;
import com.example.jpaboard.repository.MemberRepository;
import com.example.jpaboard.util.SHA256Util;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	@Autowired
	MemberRepository memberRepository;
	
	// 회원목록
	@GetMapping("/member/memberList")
	public String memberList(HttpSession session, Model model
							,@RequestParam(value="currentPage", defaultValue = "0") int currentPage
							,@RequestParam(value="rowPerPage", defaultValue = "5") int rowPerPage
							,@RequestParam(value="word", defaultValue = "") String word) {
		// session 인증/인가 검사
		if(session.getAttribute("loginMember")==null) {
			return "redirect:/member/login";
		}
		
		// 사용자 목록 + 페이징 + id 검색
		Sort sort = Sort.by("memberNo").descending();
		PageRequest pageable = PageRequest.of(currentPage,rowPerPage,sort);
		Page<MemberOnlyMemberId> list = memberRepository.findByMemberIdContains(pageable, word);
		
		model.addAttribute("list",list);
		model.addAttribute("first", list.isFirst());
		model.addAttribute("hasNext", list.hasNext());
		model.addAttribute("prePage", list.getNumber()-1);
		model.addAttribute("nextPage", list.getNumber()+1);
		model.addAttribute("lastPage", list.getTotalPages());
		return "member/memberList";
	}
	
	// 회원 가입 + memberId 중복확인
	@GetMapping("/member/joinMember")
	public String joinMember() {
		return "member/joinMember";
	}
	
	@PostMapping("/member/joinMember")
	public String joinMember(MemberForm memberForm, RedirectAttributes rda) {
		
		log.debug(memberForm.toString());
		log.debug("MemberId: "+memberRepository.existsByMemberId(memberForm.getMemberId()));
		
		// memberForm.getMemberId()가 DB에 존재한다면 회원가입 X
		if(memberRepository.existsByMemberId(memberForm.getMemberId())) {
			rda.addFlashAttribute("msg",memberForm.getMemberId()+" ID가 이미 존재합니다.");
			return "redirect:/member/joinMember"; 
		}
		
		// memberForm.getMemberPw()값을 SHA-256방식으로 암호화
		memberForm.setMemberPw(SHA256Util.encoding(memberForm.getMemberPw()));
		
		// 존재하지 않으면 회원가입 진행
		Member entity = memberForm.toEntity();
		memberRepository.save(entity); // entity저장 -> 최종 커밋시 -> 테이블에 행이 추가(insert)
		
		return "redirect:/member/login";
	}
	
	// 로그인
	@GetMapping("/member/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/member/login")
	public String login(HttpSession session, MemberForm memberForm, RedirectAttributes rda) {
		// pw 암호화
		memberForm.setMemberPw(SHA256Util.encoding(memberForm.getMemberPw()));
		// 로그인 확인 메소드
		MemberOnlyMemberId loginMember = memberRepository.findByMemberIdAndMemberPw(memberForm.getMemberId(),memberForm.getMemberPw());
		
		if(loginMember == null) {
			rda.addFlashAttribute("msg","로그인 실패");
			return "redirect:/member/login";
		}
		
		// 로그인 성공 코드 구현
		session.setAttribute("loginMember", loginMember); // pw정보까지 세션에 저장
		return "redirect:/member/memberList";
	}
	
	// 로그아웃
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/member/login";
		
	}
	// 회원정보 수정
	@GetMapping("/member/modifyPw")
	public String modifyPw(HttpSession session) {
		if(session.getAttribute("loginMember")==null) {
			return "redirect:/member/login";
		}
		
		return "member/modifyPw";
	}
	
	@PostMapping("/member/modifyPw")
	public String modifyPw(HttpSession session,MemberForm memberForm
						  ,@RequestParam String memberNewPw) {
		memberForm.setMemberPw(SHA256Util.encoding(memberForm.getMemberPw()));
		// 변경 비밀번호 암호화
		String encodingNewPw = SHA256Util.encoding(memberNewPw);
		log.debug("ID: "+memberForm.getMemberId());
		
		memberRepository.modifyPw(memberForm.getMemberId(), memberForm.getMemberPw(), encodingNewPw);
		
		return "redirect:/member/login";
	}
	
	// 회원탈퇴
	@GetMapping("/member/removeMember")
	public String removeMember(HttpSession session) {
		if(session.getAttribute("loginMember")==null) {
			return "redirect:/member/login";
		}
		
		return "member/removeMember";
	}
	
	@PostMapping("/member/removeMember")
	public String removeMember(MemberForm memberForm) {
		// 입력한 비밀번호 암호화
		memberForm.setMemberPw(SHA256Util.encoding(memberForm.getMemberPw()));
		memberRepository.removeMember(memberForm.getMemberId(), memberForm.getMemberPw());
		return "redirect:/";
	}
}
