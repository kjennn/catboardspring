package com.gsitm.career.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsitm.career.dto.BoardVO;
import com.gsitm.career.dto.CommentVO;
import com.gsitm.career.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;

	@Override
	public int insertBoard(BoardVO board) {
		return mapper.insertBoard(board);
	}

	@Override
	public int updateBoard(BoardVO board){
		 return mapper.updateBoard(board);

	}

	@Override
	public int deleteBoard(@Param("num")String num){
		System.out.println("매퍼 :" + mapper.deleteBoard(num));

		 return mapper.deleteBoard(num);

	}

	@Override
	public int insertComment(CommentVO comment) {
		return mapper.insertComment(comment);
	}

	@Override
	public int deleteComment(@Param("num")String num){
		System.out.println("매퍼 :" + num);
		 return mapper.deleteComment(num);

	}


}
