package com.guci.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.guci.domain.QuesAttachVO;
import com.guci.domain.QuesCriteria;
import com.guci.domain.QuesPageDTO;
import com.guci.domain.QuesVO;
import com.guci.service.QuesService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/question/*")
@AllArgsConstructor
public class QuesController {
	
	private QuesService service;
	

	@GetMapping("/list")
	public void list(QuesCriteria cri, Model model) {
		log.info("list" + cri);
		model.addAttribute("list", service.getList(cri));
		// model.addAttribute("pageMaker", new PageDTO(cri, 1600));
		int total = service.getTotal(cri);
		
		log.info("total : " + total);
		
		model.addAttribute("pageMaker", new QuesPageDTO(cri, total));
	}
	

	@PostMapping("/register")
	public String register(QuesVO board, RedirectAttributes rttr) {
		
		log.info("=========================================");
		log.info("register : " + board);
		if(board.getAttachList() != null) {
				board.getAttachList().forEach(attach -> log.info(attach));
		}
		log.info("=========================================");
		 service.register(board);
		
		 rttr.addFlashAttribute("result", board.getQuesNo()); // model
		
		return "redirect:/question/list"; // URL
	}
	
	// (570) BoardController 변경 화면 처리
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<QuesAttachVO>> getAttachList(Long quesNo){
		log.info("getAttachList" + quesNo);
		
		return new ResponseEntity<>(service.getAttachList(quesNo), HttpStatus.OK);
	}
	
	// (239)

	@GetMapping({"/get","modify"})
	public void get(@RequestParam("quesNo") Long quesNo, @ModelAttribute("cri") QuesCriteria cri, Model model) {
		log.info("/get or modify");
		model.addAttribute("board", service.get(quesNo));
	}
	

	@GetMapping("/register")
	public void register() {

	}
	
	@PostMapping("/modify")
	public String modify(QuesVO board, @ModelAttribute("cri") QuesCriteria cri, RedirectAttributes rttr) {
		
		log.info("modify : " + board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/question/list" + cri.getListLink(); // URL
	}
	
	// 삭제 처리 테스트 (220)
	// Criteria 추가 (320)
	// Criteria의 getListLink를 사용함으로 코드를 수정(350)
	// (581) 파일 삭제 처리를 위한 일부 코드 수정
	@PostMapping("/remove")
	public String remove(@RequestParam("quesNo") Long quesNo, QuesCriteria cri, RedirectAttributes rttr) {
		
		log.info("remove : " + quesNo);
		
		List<QuesAttachVO> attachList = service.getAttachList(quesNo);
		
		if(service.remove(quesNo)) {
			
			// 첨부파일 삭제 코드
			deleteFiles(attachList);
			
			rttr.addFlashAttribute("result", "success");
		}
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/question/list" + cri.getListLink(); // URL
	}
	
	// (581) 파일 삭제 처리
	private void deleteFiles(List<QuesAttachVO> attachList) {
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		
		log.info("delete attach files...........");
		log.info(attachList);
		
		attachList.forEach(attach -> {
			
			try {
				Path file = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\" + attach.getUuid()+"_"+attach.getFileName());
				Files.deleteIfExists(file);
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
					Files.delete(thumbNail);
				}
			} catch (Exception e) {
				log.error("delete file error : " + e.getMessage());
			}
		});
	}
	

}
