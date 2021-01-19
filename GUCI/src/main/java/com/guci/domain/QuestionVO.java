package com.guci.domain;

import java.util.Date;
import java.util.List;


import lombok.Data;

@Data
public class QuestionVO {
/* 
 create table guci_question(
quesNo number PRIMARY key,
quesCateCode VARCHAR2(50) not null,
quesTit VARCHAR2(50) not null,
quesCon VARCHAR2(1000) not null,
quesWri VARCHAR2(30) not null,
quesImg VARCHAR2(2000),
quesDate date DEFAULT sysdate
);
 */
private Long quesNo;

private String quesTit;

private String quesCon;

private String quesWri;

private String quesImg;

private Date quesDate;

private String quesCateCode;

//댓글수처리
private int replyCnt;

private List<QuestionAttachVO> attachList;




}


