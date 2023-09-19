package org.galapagos.security;

import org.galapagos.config.RootConfig;
import org.galapagos.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		RootConfig.class,
		SecurityConfig.class
})
@Log4j
public class PasswordEncoderTests {
	@Autowired
	private PasswordEncoder pwEncoder;
	
	@Test
	public void testEncode() {
		String str = "1234";
		
		String enStr = pwEncoder.encode(str); // encode 메서드에 의해 암호화. 회원가입 시 직접 구현
		log.info("password: " + enStr);
		
		String enStr2 = pwEncoder.encode(str); // 같은 원문에 대해서 암호화를 한번 더 시행
		log.info("password: " + enStr2);
		
		log.info("match :" + pwEncoder.matches(str, enStr)); // str은 사용자 입력값이고, enStr은 DB에 저장된 값
		log.info("match :" + pwEncoder.matches(str, enStr2)); 
	}
}
