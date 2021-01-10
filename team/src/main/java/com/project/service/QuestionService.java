package com.project.service;

import java.util.List;

import com.project.domain.QuestionVO;


public interface QuestionService {

	
//  반환해야할 데이터가 있는 select문은 리턴타입을 지정할수있다.(get(),getList()	
	public QuestionVO get(Long bno);
	
	public List<QuestionVO> getList();
	
	public void register(QuestionVO quev);
	
	public boolean modify(QuestionVO quev);
	
	public boolean remove(Long bno);
	
	
}
