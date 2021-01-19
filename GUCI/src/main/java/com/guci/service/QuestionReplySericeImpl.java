package com.guci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.guci.domain.QuestionCriteria;
import com.guci.domain.QuestionReplyPageDTO;
import com.guci.domain.QuestionReplyVO;
import com.guci.mapper.QuestionMapper;
import com.guci.mapper.QuestionReplyMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class QuestionReplySericeImpl implements QuestionReplyService{

	@Setter(onMethod_= @Autowired)
	private QuestionReplyMapper mapper;
	
	@Setter(onMethod_= @Autowired)
	private QuestionMapper boardMapper;
	
	@Transactional
	@Override
	public int register(QuestionReplyVO vo) {
		
        log.info("register..." + vo);
		
		boardMapper.updateReplyCnt(vo.getQuesNo(), 1);
		
		return mapper.insert(vo);
	}

	@Override
	public QuestionReplyVO get(Long replyNo) {
		log.info("get..." + replyNo);
		return mapper.read(replyNo);
	}

	@Override
	public int modify(QuestionReplyVO vo) {
		log.info("modify..." + vo);
		return mapper.update(vo);
	}

	@Override
	public int remove(Long replyNo) {
log.info("remove..." + replyNo);
		
		QuestionReplyVO vo = mapper.read(replyNo);
		
		boardMapper.updateReplyCnt(vo.getQuesNo(), -1);
		return mapper.delete(replyNo);
	}

	@Override
	public List<QuestionReplyVO> getList(QuestionCriteria cri, Long quesNo) {
       log.info("get Reply List of a Board" + quesNo);
		
		return mapper.getListWithPaging(cri, quesNo);
	}

	@Override
	public QuestionReplyPageDTO getListPage(QuestionCriteria cri, Long quesNo) {
		return new QuestionReplyPageDTO(
				mapper.getCountByBno(quesNo),
				mapper.getListWithPaging(cri, quesNo));
	}

}
