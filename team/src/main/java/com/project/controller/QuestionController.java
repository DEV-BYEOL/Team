package com.project.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.domain.QuestionVO;
import com.project.service.QuestionService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/question/*")
@AllArgsConstructor
public class QuestionController {
	/*Rest API의  URI는 정보의 자원을 표현해야 한다.delete와같은 행위에대한 정보는 절대표시하지않으며
	 HTTP Method(GET, POST, PUT, DELETE)로 표현한다.*/
   private QuestionService qService;

	@GetMapping(value ="/list", produces = {MediaType.APPLICATION_XML_VALUE, 
	                                        MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<QuestionVO>> getList() {

		log.info("list");
	
		return new ResponseEntity<>(qService.getList(),HttpStatus.OK);
	
	    }
	@GetMapping(value ="/list/{bno}", produces = {MediaType.APPLICATION_XML_VALUE, 
	                                              MediaType.APPLICATION_JSON_UTF8_VALUE})
	 public ResponseEntity<QuestionVO> get(@PathVariable("bno")Long bno) {
		
		log.info("get" +bno);
		
		return new ResponseEntity<>(qService.get(bno),HttpStatus.OK);
	}

	@PostMapping(value="/list",		consumes ="application/json",
	        produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> register(@RequestBody QuestionVO quev){
		
		log.info("QuestionVO: "+quev);
         qService.register(quev);
	   
		      return new ResponseEntity<>("success",HttpStatus.OK);
	}
	

	@RequestMapping(method = {RequestMethod.PUT,RequestMethod.PATCH},
			        value="list/{bno}",
			        		consumes ="application/json",
			        produces= {MediaType.TEXT_PLAIN_VALUE})
	
	public ResponseEntity<String> modify(@RequestBody QuestionVO quev,
			                             @PathVariable("bno") Long bno){
	quev.setBno(bno);
	log.info("modify: "+quev );
	qService.modify(quev);
	
	return new ResponseEntity<>("success",HttpStatus.OK);
	}

	@DeleteMapping(value="/list/{bno}", produces = {MediaType.APPLICATION_XML_VALUE, 
            MediaType.APPLICATION_JSON_UTF8_VALUE})
	
	public ResponseEntity<String> remove(@PathVariable("bno") Long bno){
		
		log.info("remove: "+bno);
		
		return qService.remove(bno)
		 ?new ResponseEntity<>("success",HttpStatus.OK)
		 : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)	;	
	}
}