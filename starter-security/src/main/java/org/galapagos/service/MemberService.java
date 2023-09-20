package org.galapagos.service;

import org.galapagos.domain.MemberVO;

public interface MemberService {

	public MemberVO get(String username); // 여기서 메소드명 get은 board에서 썼던 메소드명이다. 일치시켜 주는 것이 좋다.
	
	public void register(MemberVO member); // register도 마찬가지이다. board에서 등록하는 기능의 메서드명으로 사용했다.
}
