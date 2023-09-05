package org.galapagos.sample;

import static org.junit.Assert.*;

import org.galapagos.config.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j // 콘솔에서 확인하기 위함
public class SampleTests {
	
	@Autowired // reflection 기능을 이용해서 설정
	private Restaurant restaurant;

	@Test
	public void test() {
		
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("-----------------------------");
		log.info(restaurant.getChef());
	}

}
