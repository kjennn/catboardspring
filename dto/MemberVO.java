package com.gsitm.career.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {

	private String id;
	private String passwd;
	private Date createTime;

}
