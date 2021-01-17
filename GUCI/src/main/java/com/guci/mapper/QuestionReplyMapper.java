package com.guci.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guci.domain.QuestionCriteria;
import com.guci.domain.QuestionReplyVO;

public interface QuestionReplyMapper {

	
public int insert(QuestionReplyVO vo);
	
	// (384) read
	public QuestionReplyVO read(Long quesNO);
	
	// (385) delete
	public int delete(Long quesNO);
	
	// (386) update
	public int update(QuestionReplyVO reply);
	
	// (387) 댓글 목록
	public List<QuestionReplyVO> getListWithPaging(
			@Param("cri") QuestionCriteria cri,
			@Param("quesNo") Long quesNo);
	
	// (432) 댓글 숫자 파악하기
	public int getCountByBno(Long quesNo);
}
