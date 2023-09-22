package org.galapagos.controller;

import java.util.List;

import org.galapagos.domain.CommentVO;
import org.galapagos.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board/{bno}/comment")
public class CommentController {
	
	@Autowired
	CommentMapper mapper;
	
	@GetMapping("") // 비어있는 문자열이므로 공통 url 자체가 목록보기
	public List<CommentVO> readComments(@PathVariable Long bno) { // 명시적으로 @PathVariable("bno")~. 같으니 생략 가능
		
		return mapper.readAll(bno);
	}
	
	@GetMapping("/{no}")
	public CommentVO readComment(
			@PathVariable Long no) {
		
			return mapper.get(no);
	}
	
	@PostMapping("")
	public CommentVO create(@RequestBody CommentVO vo) { //@RequestBody -> json을 분석해서 만들어야한다는 의미
		mapper.create(vo);
		return mapper.get(vo.getNo());
	}
	
	@PutMapping("/{no}")
	public CommentVO update(
			@PathVariable Long no,
			@RequestBody CommentVO vo) {
		System.out.println("-->" + vo);
		mapper.update(vo);
		
		return mapper.get(vo.getNo());
		//return vo; // 받은대로 돌려주겠다.
	}
	
	@DeleteMapping("/{no}")
	public String delete(@PathVariable Long no) {
		System.out.println("delete -->" + no);
		mapper.delete(no);
		
		return "OK";
	}
}
