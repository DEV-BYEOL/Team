package com.project.mapper;

import java.util.List;

import com.project.domain.QuestionVO;

//Persistence Tier(영속계층 혹은 데이터계층) 데이터 보관방식과 사용법에 대한 설계가들어간다.

public interface QuestionMapper {

	
	//여러건의 record를 리턴받음
	public List<QuestionVO> getList();
	
	//한건의 record를 리턴받음
	public QuestionVO read(Long bno);
	
	public void insertSelectKey(QuestionVO quev);
	
	public void insert(QuestionVO quev);
	
	//특정한 데이터를 삭제할때는 bno같은 pk값을 이용하도록한다.
	public int delete(Long bno);
	
	public int update(QuestionVO quev);
	
	
	
}
