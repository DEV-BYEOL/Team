package com.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.domain.QuestionVO;
import com.project.mapper.QuestionMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

	private QuestionMapper quem;
	
	@Override
	public QuestionVO get(Long bno) {
		log.info("get..........."+bno);
		return quem.read(bno);
		}

	@Override
	public List<QuestionVO> getList() {
		log.info("get list.....");
		return quem.getList();
	}

	@Override
	public void register(QuestionVO quev) {
		log.info("register:" + quev);
		quem.insertSelectKey(quev);
	}

	@Override
	public boolean modify(QuestionVO quev) {
		log.info("modify..."+quev);
		return quem.update(quev)==1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove.."+bno);
		return quem.delete(bno)==1;
		
	}

}
