package com.project.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.domain.QuestionVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j

public class QuestionServiceTests {
	

	@Setter(onMethod_= {@Autowired})
	private QuestionService qservice;
	
	@Test
	public void testExist() {
		log.info(qservice);
		assertNotNull(qservice);
	}
	
	@Test
	public void testRegister() {
		QuestionVO quev =new QuestionVO();
		quev.setTitle("register 제목");
		quev.setName("register이름");
		quev.setMessage("register내용");
		quev.setEmail("register이메일");
	

		qservice.register(quev);
		log.info("생성된 게시물의 번호:" +quev.getBno());
	}
	@Test
	public void testGetList() {
		qservice.getList().forEach(quev ->log.info(quev));
	}
	
	@Test
	public void testGet() {
		log.info(qservice.get(22L));
	}
	
	@Test
	public void testDelete() {
		log.info("REMOVE RESULT: "+qservice.remove(16L));
	}
	
	@Test
	public void testUpdate() {
		QuestionVO quev = qservice.get(17L);
		if(quev==null) {
			return;}
		quev.setTitle("제목 수정");
		log.info("MODIFY RESULT: "+qservice.modify(quev));
		}
	}
	