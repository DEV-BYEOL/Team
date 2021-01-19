package com.guci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guci.domain.QuesAttachVO;

import com.guci.domain.QuesVO;
import com.guci.domain.QuesCriteria;
import com.guci.mapper.QuesAttachMapper;
import com.guci.mapper.QuesMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
//@AllArgsConstructor
public class QuesServiceImpl implements QuesService {
	
	// 4.3 이상에서는 자동 처리가 가능하게 되어졌다.
	@Setter(onMethod_ = @Autowired)
	private QuesMapper mapper;
	
	// (566) BoardServiceImpl를 처리하기 위한 setter 메서드 추가
	@Setter(onMethod_ = @Autowired)
	private QuesAttachMapper attachMapper;
	
	// (204)
	// (566) BoardServiceImpl
	@Transactional
	@Override
	public void register(QuesVO board) {
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
	public List<QuesVO> getList(QuesCriteria cri) {
		log.info("Get List with criteria : " + cri);
		return mapper.getListWithPaging(cri);
	}
//	@Override
//	public List<BoardVO> getList() {
//		log.info("GetList...........");
//		return mapper.getlist();
//	}
	
	@Override
	public QuesVO get(Long quesNo) {
		log.info("get..." + quesNo);
		return mapper.read(quesNo);
	}
	
	// (591) 서버 측 게시물 수정과 첨부파일 관련 코드 수정
	@Transactional
	@Override
	public boolean modify(QuesVO board) {
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
	
	// (579) BoardServiceImpl 변경
	@Transactional
	@Override
	public boolean remove(Long quesNo) {
		log.info("remove..." + quesNo);

		attachMapper.deleteAll(quesNo);
		
		return mapper.delete(quesNo) == 1;
	}

	@Override
	public int getTotal(QuesCriteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

	// (569) 게시물의 첨부파일의 목록을 가져오게 한다.
	@Override
	public List<QuesAttachVO> getAttachList(Long quesNo) {
		log.info("get Attach list by quesNo : " + quesNo);
		return attachMapper.findByquesNo(quesNo);
	}


}
