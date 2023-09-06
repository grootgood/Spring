package org.galapagos.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	@Select("SELECT sysdate()")
	String getTime(); // 어노테이션으로 정의
	
	String getTime2(); // TimeMapper.xml에서 정의하겠다라는 의미
}
