package com.project.mapper;

import static org.junit.Assert.*;

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


public class QuestionMapperTests {
	

		@Setter(onMethod_ = @Autowired)
		private QuestionMapper quem;
		
		
		@Test
		public void testGetList() {
			quem.getList().forEach(board -> log.info(board));//Lambda Expression
	}
		//junit test에서는 객체직접생성
		@Test
		public void testinsert() {
			QuestionVO quev =new QuestionVO();
			
		    quev.setTitle("테스트제목");
		    quev.setMessage("테스트내용");
		    quev.setName("테스트이름");
		    quev.setEmail("a@com");
			
		    quem.insert(quev);
		    log.info(quev);
		}
		 
		@Test
		public void testInsertSelectKey() {
            QuestionVO quev =new QuestionVO();
			
		    quev.setTitle("selectkey테스트제목");
		    quev.setMessage("selectkey테스트내용");
		    quev.setName("selectkey테스트이름");
		    quev.setEmail("selectkey@com");
			
		    quem.insertSelectKey(quev);
		    log.info(quev);
		}
		
		@Test
	    public void testRead() {
	 		
			QuestionVO quev =quem.read(5L);
			log.info(quev);
	}
		
		@Test
		public void testDelete() {
			log.info("DELETE COUNT: " +quem.delete(14L));
		}
		
		@Test
		public void testUpdate() {
			QuestionVO quev = new QuestionVO();
			quev.setBno(7L);
			quev.setTitle("수정된 제목");
			quev.setName("수정된 네임");
			quev.setEmail("수정된 메일");
			quev.setMessage("수정된 메시지");
			
			int count= quem.update(quev);
			log.info("UPDATE COUNT: "+count);
			
			
		}
}
