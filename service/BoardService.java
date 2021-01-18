package com.gsitm.career.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsitm.career.dto.BoardVO;
import com.gsitm.career.dto.CommentVO;

public interface BoardService {

	public int insertBoard(BoardVO board);

	public int updateBoard(BoardVO board);
	public int deleteBoard(@Param("num") String num);

	public int insertComment(CommentVO comment);
	public int deleteComment(@Param("num") String num);

}
