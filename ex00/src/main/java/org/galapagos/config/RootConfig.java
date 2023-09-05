package org.galapagos.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정 파일이니 스프링에게 분석하라는 어노테이션
@ComponentScan(basePackages = {"org.galapagos.sample"}) // 어노테이션에서 {}는 배열을 의미
public class RootConfig {

}
