package org.galapagos.controller;

import java.util.ArrayList;
import java.util.Date;

import org.galapagos.domain.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller // Bean으로 등록하라는 어노테이션
@RequestMapping("/sample/")// 요청 url중에 sample로 시작한는 url은 이 Controller가 담당한다라는 의미
@Log4j // log라고 하는 static 멤버가 추가된다. 그래서 아무런 선언없이 이 변수를 사용 가능
		// 클래스 정도가 생성자 
public class SampleController {
	@RequestMapping("") 
	public void basic() {
		log.info("basic............");
	}
	
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get............");
		
		//return "sample/basic"
		// void 일 경우 현재 url이 view의 이름이 된다.
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get.............");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(""+dto);
		
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02( @RequestParam("name") String name,
						@RequestParam("age") int age) {
		log.info("name: "+name);
		log.info("age: "+age);
		
		return "ex02";
	}
	
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids" + ids);
		
		return "ex02List";
	}
	
	@GetMapping("/ex04")
	public String ex04(
						@ModelAttribute("sample") SampleDTO dto, 
						@ModelAttribute("page") 
						@RequestParam("page") int page,
						Model model) {
						//@RequestParam 생략가능
		
		model.addAttribute("now", new Date());
		log.info("dto" + dto);
		log.info("page: " + page);
		
		return "ex04";
	}
	
}
