package com.guci.domain;

import java.util.Date;

import lombok.Data;

@Data
public class QuestionReplyVO {
	
	private Long replyNo;
	private Long quesNo;
	
	private String replyCon;
	private String replyWri;
	private Date replyDate;

}
