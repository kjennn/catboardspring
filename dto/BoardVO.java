package com.gsitm.career.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {

		private int boardNum;
		private String boardId;
		private String id;
		private String title;
		private String content;
		private Date createTime;
		private Date modifyTime;

}
