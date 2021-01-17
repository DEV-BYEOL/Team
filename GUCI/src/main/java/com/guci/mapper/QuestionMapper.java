package com.guci.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guci.domain.QuestionCriteria;
import com.guci.domain.QuestionVO;

public interface QuestionMapper {

	
    public List<QuestionVO> getlist();
	

	public List<QuestionVO> getListWithPaging(QuestionCriteria cri);
	
	
	public void insert(QuestionVO board);
	
	
	public Integer insertSelectKey(QuestionVO board);


	public QuestionVO read(Long quesNo);
	

	public int delete(Long quesNo);
	

	public int update(QuestionVO board);
	

	public int getTotalCount(QuestionCriteria cri);
	
	
	public void updateReplyCnt(@Param("quesNo") Long quesNo, @Param("amount") int amount);
}
