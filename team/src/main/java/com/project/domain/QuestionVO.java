package com.project.domain;

import java.util.Date;

import lombok.Data;


@Data
public class QuestionVO {
	  private Long bno;
	  private String title;
	  private String message;
	  private String name;
	  private String email;
	  private Date regdate;

}
