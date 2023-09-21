package org.galapagos.mapper;

import org.galapagos.domain.AuthVO;
import org.galapagos.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String username);
	
	public void insert(MemberVO member); // member 테이블에 대한 insert
	
	public void insertAuth(AuthVO auth); // auth 테이블에 대한 insert
}
