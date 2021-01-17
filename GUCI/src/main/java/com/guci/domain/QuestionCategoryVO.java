package com.guci.domain;

import lombok.Data;

@Data
public class QuestionCategoryVO {
	private String quesCateCode;
    private String quesCateName;
    private String quesCateCodeRef; 
    private int level;
}
