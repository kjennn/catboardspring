package com.gsitm.career.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gsitm.career.dto.BoardVO;
import com.gsitm.career.dto.CommentVO;

@Repository
@Mapper
public interface BoardMapper {

	/**
	 * 글 작성
	 * @param board
	 * @return
	 */
	public int insertBoard(BoardVO board);

	/**
	 * 글 수정
	 * @param board,num
	 * @return
	 */
	public int updateBoard(BoardVO board);

	/**
	 * 글 삭제
	 * @param board,num
	 * @return
	 */
	public int deleteBoard(String num);


	public int insertComment(CommentVO comment);

	public int deleteComment(String num);
}
