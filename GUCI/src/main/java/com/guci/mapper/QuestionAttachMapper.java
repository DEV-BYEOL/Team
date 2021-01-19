package com.guci.mapper;

import java.util.List;

import com.guci.domain.QuestionAttachVO;



public interface QuestionAttachMapper {

public void insert(QuestionAttachVO vo);
	
	public void delete(String uuid);
	
	public List<QuestionAttachVO> findByBno(Long quesNo);
	
	// (578) 첨부파일 삭제 처리
	public void deleteAll(Long quesNo);
	
	// (600) 데이터베이스에 저장된 모든 파일의 목록을 사용하기 위한 코드 추가
	public List<QuestionAttachVO> getOldFiles();
}
