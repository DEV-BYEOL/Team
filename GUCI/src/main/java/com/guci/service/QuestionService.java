package com.guci.service;

import java.util.List;



import com.guci.domain.QuestionAttachVO;
import com.guci.domain.QuestionCriteria;
import com.guci.domain.QuestionVO;


public interface QuestionService {

		public void register(QuestionVO board); // insert
		
		public QuestionVO get(Long quesNo); // select
		
		public boolean modify(QuestionVO board); // update

		public boolean remove(Long quesNo); // delete
		
		public List<QuestionVO> getList(QuestionCriteria cri); // select *
		
		public int getTotal(QuestionCriteria cri); // p323
		
		public List<QuestionAttachVO> getAttachList(Long quesNo);
		
}
