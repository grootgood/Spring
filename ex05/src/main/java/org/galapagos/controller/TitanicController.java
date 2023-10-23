package org.galapagos.controller;

import org.galapagos.domain.TitanicVO;
import org.galapagos.service.TitanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/titanic")
public class TitanicController {

	@Autowired
	TitanicService service;
	
	@GetMapping("/predict")
	public void getPredict(TitanicVO vo) { // 스프링 폼 태그를 사용하기 위해 vo 객체 요청
		
	}
	
	@PostMapping("/predict")
	public void getPredict(TitanicVO vo, Model model) {
		String result = service.predict(vo);
		model.addAttribute("result", result);
	}
}
