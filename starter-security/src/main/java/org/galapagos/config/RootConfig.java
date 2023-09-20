package org.galapagos.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration // 설정 파일이니 스프링에게 분석하라는 어노테이션
@ComponentScan(basePackages = {"org.galapagos.sample", "org.galapagos.service"}) // 어노테이션에서 {}는 배열을 의미
@MapperScan(basePackages = {"org.galapagos.mapper"})
public class RootConfig {
	
	@Autowired
	ApplicationContext applicationContext;
	@Bean // 이 메서드의 리턴 값이 Bean으로 등록된다는 의미
	public DataSource dataSource() { // DataSource가 키가 됨
		HikariConfig config = new HikariConfig();
//		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		config.setJdbcUrl("jdbc:mysql://localhost:3309/glory_db");
		
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		config.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3309/glory_db");
		
		config.setUsername("glory");
		config.setPassword("1234");
		
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setConfigLocation(
		        applicationContext.getResource(
		             "classpath:/mybatis-config.xml"));
		sqlSessionFactory.setDataSource(dataSource());
		return (SqlSessionFactory) sqlSessionFactory.getObject();
		// sqlSessionFactory.getObject() --> 빈 객체
	}
}