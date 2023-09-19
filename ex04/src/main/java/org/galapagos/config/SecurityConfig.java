package org.galapagos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j;

// 스프링 5 버전에서의 설정 방법. 버전마다 조금씩 다를 수 있다.
@Configuration
@EnableWebSecurity // web 보안을 가동하라는 어노테이션
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter{ // 부모 클래스와 EnableWebSecurity 어노테이션이 가장 중요
	// 이번 실습의 주요 내용은 이 안에 무엇을 채우느냐이다.
	
	// 비밀번호 암호화 처리 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/security/all").permitAll() // 이 url이 맞다면 모두에게 허용하겠다라는 설정
			.antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')") // 특정 역할에게만 허용
			.antMatchers("/security/member").access("hasRole('ROLE_MEMBER')"); // 특정 역할에게만 허용
		
		http.formLogin()
			.loginPage("/security/login") // 우리가 처리해야한다. GET 요청에 대한 처리
			.loginProcessingUrl("/security/login") // security가 처리해준다. POST 요청에 대한 처리
			// /security/login이 login.jsp의 action url이 된
			.defaultSuccessUrl("/")
			.failureUrl("/security/login?error=true"); // 로그인 실패 시 이동할 페이지. el : parma.error
		
		http.logout()
			.logoutUrl("/security/logout") // POST: 로그아웃 호출 url
			.invalidateHttpSession(true) // 세션 invalidate
			.deleteCookies("remember-me", "JSESSION-ID") // 삭제할 쿠키 목록
			.logoutSuccessUrl("/security/logout"); // 로그아웃 이후 이동할 페이지. GET 요청에 대한 처리
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { // Authentication -> id, password 설정
		
		log.info("configure...............");
		
		// 메모리에 사용자 정보 구축하는 방법
		auth.inMemoryAuthentication()
			.withUser("admin") // username, 사용자 id
			.password("$2a$10$aHyvHOwjGtVPya3yODgQeeTaF2AyUd8yFe6eB.5GSTIebydu7SU4a") // 비밀번호, {noop} -> '암호화 없음' 의미. No Operation
			.roles("ADMIN"); // 역할설정.  앞에 'ROLE_'를 붙이지 않으면 security가 자동으로 붙여준다.
		
		auth.inMemoryAuthentication()
			.withUser("member")
//			.password("{noop}1234") 
			.password("$2a$10$aHyvHOwjGtVPya3yODgQeeTaF2AyUd8yFe6eB.5GSTIebydu7SU4a")
			.roles("MEMBER");
	} 
	
}
