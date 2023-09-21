package org.galapagos.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.galapagos.domain.MemberVO;
import org.galapagos.service.MemberService;
import org.galapagos.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

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
			Errors errors,
			MultipartFile avatar) throws IOException {
		
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
		
		// DB 저장
		service.register(member, avatar);
		
		return "redirect:/"; // 홈화면으로 리다이렉트
	}
	
	@GetMapping("/avatar/{size}/{username}") // path variable(경로 변수)
	@ResponseBody
	public void avatar(@PathVariable("size") String size,
						@PathVariable("username") String username,
						HttpServletResponse response) throws IOException {
		
		File src = new File(MemberServiceImpl.AVATAR_UPLOAD_DIR, username + ".png");
		if(!src.exists()) { // file이 있을수도, 없을수도 있기에 검사한다.
			src = new File(MemberServiceImpl.AVATAR_UPLOAD_DIR, "unknown_avatar.png"); 
		} // 없으면 unknown_avatar 이미지로 대체한다.
		log.warn(src);
		response.setHeader("Content-Type", "image/png"); // Content-Type을 image/png로 변경 
														 // default는 text-html이다.
		if(size.equals("sm")) {
			Thumbnails.of(src)
					.size(25,25)
					.toOutputStream(response.getOutputStream());
		} else {
			Thumbnails.of(src)
					.size(50, 50)
					.toOutputStream(response.getOutputStream());
		}
	}
	
	@GetMapping("/profile")
	public void profile() {
		
	}
}
