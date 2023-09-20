package org.galapagos.security;

import org.galapagos.domain.MemberVO;
import org.galapagos.mapper.MemberMapper;
import org.galapagos.security.domain.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username)  // 로그인 시도 시 호출된다. username이 있으면 상세정보 리턴
			throws UsernameNotFoundException {				// id 즉, username이 없으면 예외처리
															// MemberVO는 security가 모르기 때문에 UserDetails로 변환해서 리턴해야함
		log.warn("Load User By Username: " + username);		// 로그 레벨을 warning으로 높였다.
		
		MemberVO vo = mapper.read(username);
		if(vo == null) {
			throw new UsernameNotFoundException(username + "은 없는 id입니다.");  
		}		// 예외객체를 생성시켜서 강제로 예외를 생성시킬 때 매개변수의 의미는 "오류메시지"를 의미한다.
		
		log.warn("user vo: " + vo);
		
		return new CustomUser(vo);  // pwd, getPassword가 일치하면 sessionScope에 CustomUser를 저장
									// 실제로 저장되는 것은 MemberVO가 아니라 CustomUser가 저장된다.
	}
}
