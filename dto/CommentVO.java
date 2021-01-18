package com.gsitm.career.dto;


import java.util.Date;

import lombok.Data;

@Data
public class CommentVO {

	private int commentNum;
	private int boardNum;
	private String id;
	private String comments;
	private Date createTime;
	private Date modifyTime;
}
