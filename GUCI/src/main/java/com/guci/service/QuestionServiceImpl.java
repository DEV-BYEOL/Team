package com.guci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guci.domain.QuestionAttachVO;
import com.guci.domain.QuestionCriteria;
import com.guci.domain.QuestionVO;
import com.guci.mapper.QuestionAttachMapper;
import com.guci.mapper.QuestionMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Setter(onMethod_ = @Autowired)
    private QuestionMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private QuestionAttachMapper attachMapper;
	
	@Transactional
	@Override
	public void register(QuestionVO board) {
		
		log.info("register....." + board);
		mapper.insertSelectKey(board);
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}
		
		board.getAttachList().forEach(attach -> {
			attach.setQuesNo(board.getQuesNo());
			attachMapper.insert(attach);
		});
		
	}

	@Override
	public QuestionVO get(Long quesNo) {
		log.info("get..." + quesNo);
		return mapper.read(quesNo);
	}
	@Transactional
	@Override
	public boolean modify(QuestionVO board) {
       log.info("modify..." + board);
		
		attachMapper.deleteAll(board.getQuesNo());
		
		boolean modifyResult = mapper.update(board) == 1;
		if(modifyResult && board.getAttachList() != null && board.getAttachList().size() > 0) {
			board.getAttachList().forEach(attach -> {
				attach.setQuesNo(board.getQuesNo());
				attachMapper.insert(attach);
			});
		}
		return modifyResult;
	}
	
	
	@Transactional
	@Override
	public boolean remove(Long quesNo) {
		log.info("remove..." + quesNo);

		attachMapper.deleteAll(quesNo);
		
		return mapper.delete(quesNo) == 1;
	}

	@Override
	public List<QuestionVO> getList(QuestionCriteria cri) {
		log.info("Get List with criteria : " + cri);
		return mapper.getListWithPaging(cri);
	
	}

	@Override
	public int getTotal(QuestionCriteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<QuestionAttachVO> getAttachList(Long quesNo) {
		log.info("get Attach list by bno : " + quesNo);
		return attachMapper.findByBno(quesNo);
	}


	
	}


