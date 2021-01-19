package com.guci.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guci.domain.QuestionVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class QuestionMapperTests {


	@Setter(onMethod_= @Autowired)
	private QuestionMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getlist().forEach(board -> log.info(board)); // 람다식으로 작성되어 있다.
	}
	@Test
	public void testinsert() {
		QuestionVO board = new QuestionVO();
		board.setQuesTit("테스트제목");
		board.setQuesCon("테스트 내용");
		board.setQuesWri("테스트작성자");
		board.setQuesCateCode("a-1");
		
		
		mapper.insert(board);
		
		log.info(board);
	}
	
	@Test
	public void testinsertSelectKey() {
		QuestionVO board = new QuestionVO();
		board.setQuesTit("테스트제목 1");
		board.setQuesCon("테스트 내용 1");
		board.setQuesWri("테스트작성자1");
		board.setQuesCateCode("a-2");
		
		mapper.insertSelectKey(board);
		
		log.info(board);
	}
	
	
	@Test
	public void testRead() {
		QuestionVO board = mapper.read(22L);
		
		log.info(board);
	}
	
	
	@Test
	public void testDelete() {
		log.info("Delete Count : " + mapper.delete(22L)); 
	}
	

	@Test
	public void testUpdate() {
		QuestionVO board = new QuestionVO();
		board.setQuesNo(12L);
		board.setQuesTit("업로드제목 테스트");
		board.setQuesCon("업로드내용 테스트");
		board.setQuesWri("업로드유저 테스트");
		board.setQuesCateCode("b-3");
		
		int count = mapper.update(board);
		
		log.info("Update Count : " + count);
	}
}
