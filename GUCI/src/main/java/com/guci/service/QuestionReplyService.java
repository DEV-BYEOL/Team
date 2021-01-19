package com.guci.service;

import java.util.List;

import com.guci.domain.QuestionCriteria;
import com.guci.domain.QuestionReplyPageDTO;
import com.guci.domain.QuestionReplyVO;



public interface QuestionReplyService {

	public int register(QuestionReplyVO vo);

	public QuestionReplyVO get(Long replyNo);

	public int modify(QuestionReplyVO vo);

	public int remove(Long replyNo);

	public List<QuestionReplyVO> getList(QuestionCriteria cri, Long quesNo);
	
	// (434)
	public QuestionReplyPageDTO getListPage(QuestionCriteria cri, Long quesNo);
}
