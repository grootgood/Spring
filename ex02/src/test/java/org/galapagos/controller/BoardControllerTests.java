package org.galapagos.controller;
import org.galapagos.config.RootConfig;
import org.galapagos.config.ServletConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { RootConfig.class, ServletConfig.class })
@Log4j
public class BoardControllerTests {
	
    @Setter(onMethod_ = { @Autowired })
    private WebApplicationContext ctx;
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }
    
    @Test
    public void testListPaging() throws Exception {
        log.info(
                mockMvc.perform(
                		MockMvcRequestBuilders
                			.get("/board/list")
                			.param("pageNum", "2")
                			.param("amount", "5"))
                        .andReturn()
                        .getModelAndView()
                        .getModelMap());
    }
    
//    @Test
    public void testRegister() throws Exception {
    	
    	String resultPage = mockMvc.perform(
    			MockMvcRequestBuilders.post("/board/register") // Post 요청
    				.param("title", "테스트 새글 제목") // body 구성. ("속성명", "속성값")
    				.param("content", "테스트 새글 내용")
    				.param("writer", "user00"))
    			.andReturn()
    			.getModelAndView()
    			.getViewName();
    	log.info(resultPage);
    }
    
//   @Test
    public void testGet() throws Exception {
    	
    	log.info(
    		mockMvc
    			.perform(MockMvcRequestBuilders.get("/board/get").param("bno", "2"))
    			.andReturn()
    			.getModelAndView()
    			.getModelMap()
    			);
    }
    
//    @Test
    public void testModify() throws Exception {
    	String resultPage = mockMvc
    			.perform(
    				MockMvcRequestBuilders
    					.post("/board/modify")
    					.param("bno", "1")
    					.param("title", "수정된 테스트 새글 제목")
    					.param("content", "수정된 테스트 새글 내용")
    					.param("writer", "user00"))
    			.andReturn()
    			.getModelAndView()
    			.getViewName();
    	
    	log.info(resultPage);
    }
    
//    @Test
    public void testRemove() throws Exception {
    	// 삭제 전 데이터에비스에 게시물 번호 확인할 것
    	String resultPage = mockMvc
    			.perform(MockMvcRequestBuilders.post("/board/remove").param("bno","25"))
    			.andReturn()
    			.getModelAndView()
    			.getViewName();
    	
    	log.info(resultPage);
    }
}