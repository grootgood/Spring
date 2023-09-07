package org.galapagos.controller;

import org.galapagos.domain.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user")
@Log4j
public class UserController {
	
	@GetMapping("/join")
	public String joingGet() { // Get 요청 담당
		return "user/join";
	}
	
	@PostMapping("/join")
	public String joinPost(UserDTO user) { // Post 요청 담당
		//DB에 Insert 작업을 함
		log.info(user);
		
		// redirect: 이동할 url 주소
		return "redirect:/user/join_result";
	}
	
	@GetMapping("/join_result")
	public String joinResult() { // Get 요청 담당
		return "user/join_result";
	}

}
