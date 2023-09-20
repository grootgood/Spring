package org.galapagos.controller;

import javax.validation.Valid;

import org.galapagos.domain.MemberVO;
import org.galapagos.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/security")
@Controller

public class SecurityController {
	
	@Autowired
	MemberService service;
	
	@GetMapping("/login") 
	public void login() {
		log.info("login page");
	}
	
	@GetMapping("/signup")
	public void signup(@ModelAttribute("member") MemberVO member) {
		
	}
	
	@PostMapping("/signup")
	public String signup( // 매개변수와 리턴타입이 다르기 때문에 오버로딩이 가능하다.
			@Valid @ModelAttribute("member") MemberVO member,
			Errors errors) {
		
		// 1. 비밀번호, 비밀번호 확인 일치여부
		if(!member.getPassword().equals(member.getPassword2())) {
			// 에러 추가
			errors.rejectValue("password2", "비밀번호 불일치", "비밀번호가 일치하지 않습니다."); // 세번째 인자가 출력할 에러 메시지
		} // 그냥 reject는 전역 필드 설정
		
		// 2. 아이디 중복
		if(!errors.hasFieldErrors("username")) { // username 유효성 통과한 경우 특정 필드에 에러가 있는지 확인
			// DB에서 username을 검사
			if(service.get(member.getUsername()) != null) { // 중복이다.
				errors.rejectValue("username", "ID 중복", "이미 사용중인 ID입니다."); 
			}
		}
		if(errors.hasErrors()) { // 전체적으로 봤을 때 에러가 있는지 확인
			return "security/signup";
		}
		if(errors.hasFieldErrors()) { // 에러 발생할 경우
			return "security/signup"; // view 이름을 제시해야 한다.
		}
		
		// 유효성 검사를 통과하고 나면 DB에 저장해야 된다.
		// DB에 저장되었다고 가정하면 redirect 해야된다.
		
		return "redirect:/"; // 홈화면으로 리다이렉트
	}
}
