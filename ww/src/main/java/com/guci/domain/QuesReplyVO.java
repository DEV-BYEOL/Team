package com.guci.domain;

import java.util.Date;
import lombok.Data;
//(388) VO 생성
@Data
public class QuesReplyVO {
	
	private Long replyNo;
	private Long quesNo;
	
	private String replyCon;
	private String replyWri;
	private Date replyDate;

}
