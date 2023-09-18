package org.galapagos.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.galapagos.domain.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/test")
@Log4j
public class TestController {
//	private int[] years = {2000, 2001, 2002, 2003};
	
	// 1950년부터 2023년까지 구성
	@ModelAttribute("years") 
	public List<Integer> years() {	// 일반 메서드인데 앞에 ModelAttirubute 어노테이션이 붙었다.
		List<Integer> years = new ArrayList<Integer>();
		for(int i = 1950; i<=2023; i++) {
			years.add(i);
		}
		return years; // 자동으로 메서드는 뷰로 넘어갈 때 무조건 호출되고 리턴값이 모델에 추가된다.
	}
	
	@ModelAttribute("roles")
	public Map<String, String> getRoles() {
		Map<String, String> map = new LinkedHashMap<>(); //HashMap은 순서에 상관 X, LinkedHashMap은 put한 순서로 순회한다.
		map.put("ROLE_ADMIN", "Admin");
		map.put("ROLE_MANAGER", "관리자");
		map.put("ROLE_MEMBER", "일반회원");
		
		return map;
	}
	
	private String[] hobbies = {"야구", "축구", "배구"}; // 배열로
	
	public Map<String, String> getHobbies() { // Map으로
		Map<String, String> hobbies = new LinkedHashMap<String, String>();
		hobbies.put("BASEBALL", "야구");
		hobbies.put("FOOTBALL", "축구");
		hobbies.put("VOLLYBALL", "배구");
		return hobbies;
	}
	
	@ModelAttribute("genders") // 이번엔 함수로 처리
	private String[] genders() {
		return new String[] {"남자", "여자"};
	}
	
	@GetMapping("/join")
	public void join(
			@ModelAttribute("member") MemberVO member, Model model) {
//		model.addAttribute("years", years);
		member.setNo(10L);
		model.addAttribute("hobbies", hobbies);
		model.addAttribute("hobbies", getHobbies());
	// Get에서도 Model 객체 제시	
	// view 이름 : test/join
	}
	
	@PostMapping("/join")
	public String join(
			@Valid
			@ModelAttribute("member") MemberVO member,
			Errors errors, Model model) {
		// Model 객체 제시 후 Error 처리가 붙음
//		model.addAttribute("years", years);
		
		if(!member.getPassword().equals(member.getPassword2())) {
			errors.rejectValue("password", "비밀번호 확인 에러", "비밀번호 확인이 일치하지 않습니다.");
		}
		
		if(errors.hasErrors()) {
			model.addAttribute("hobbies", hobbies);
			model.addAttribute("hobbies", getHobbies());
			
			return "test/join"; // forwarding
		}
		
		return "redirect:/";
		
		
		// 코드를 수정해서 이 밑의 주석 내용과 조금 다름.
		// 기존에는 Post 처리 후 redirect를 했지만, 지금은 주석처리
		// return "redirect:/";
		// 이게 어떤 의미를 가짐 -> form을 다시 입력하도록 함(이전 값으로 복원)
		
		// view 이름 : test/join (Get요청과 동일한 view의 이름)
		// form에 에러가 있다고 가정한 후 다시 form으로 돌아가서 다시 입력하게끔
		// 에러가 없을 때 redirec 함 (지금은 막아둔 상태)
	}

}
