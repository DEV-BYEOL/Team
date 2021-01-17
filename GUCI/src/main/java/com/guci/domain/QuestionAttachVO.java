package com.guci.domain;

import lombok.Data;

@Data
public class QuestionAttachVO {

	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	
	private Long quesNo;
}
