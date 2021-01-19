package com.guci.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guci.domain.QuestionCriteria;
import com.guci.domain.QuestionReplyPageDTO;
import com.guci.domain.QuestionReplyVO;
import com.guci.service.QuestionReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/questionreplies/")
@RestController
@Log4j
@AllArgsConstructor
public class QuestionReplyController {

	// (393)
	private QuestionReplyService service;
	
	
	// (393~4) - Advanced Rest Client 통해서 확인
	@PostMapping(value = "/new", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody QuestionReplyVO vo) {
		log.info("ReplyVO: " + vo);
		int insertCount = service.register(vo);
		log.info("Reply INSERT COUNT: " + insertCount);
		return insertCount == 1  
				?  new ResponseEntity<>("success", HttpStatus.OK)//200
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
	}
	
	// (395) - 특정 게시물의 댓글 목록 확인하기
	@GetMapping(value = "/pages/{quesNo}/{page}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<QuestionReplyPageDTO> getList(
			@PathVariable("page") int page,
			@PathVariable("quesNo") Long quesNo){
		QuestionCriteria cri = new QuestionCriteria(page, 10);
		log.info("get Reply List quesNo : " + quesNo);
		log.info("cri:" + cri);
		return new ResponseEntity<>(service.getListPage(cri, quesNo), HttpStatus.OK);
	}
	
	// (397 ~ 398) 댓글 수정, 삭제, 조회
	@GetMapping(value = "/{replyNo}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<QuestionReplyVO> get(@PathVariable("replyNo") Long replyNo){
		log.info("get : " + replyNo);
		return new ResponseEntity<>(service.get(replyNo), HttpStatus.OK);
	}
	
	//ARC에서 GET선택, Delete, PUT을 선택한다.
	
	@DeleteMapping(value = "/{replyNo}", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("replyNo") Long replyNo){
		log.info("remove : " + replyNo);
		
		return service.remove(replyNo) == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
					value="/{replyNo}",
					consumes = "application/json",
					produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(
				@RequestBody QuestionReplyVO vo,
				@PathVariable ("replyNo") Long replyNo){
		vo.setReplyNo(replyNo);
		log.info("replyNo : " + replyNo);
		log.info("modify : " + vo);
		
		return service.modify(vo) == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
