package com.gsitm.career.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsitm.career.dto.BoardVO;
import com.gsitm.career.dto.CommentVO;
import com.gsitm.career.dto.MemberVO;
import com.gsitm.career.mapper.DemoMapper;

import lombok.extern.java.Log;

@Log
@Service
public class CatBoardServiceImpl implements CatBoardService {

	@Autowired
	private DemoMapper mapper;

	//글상세보기
	@Override
	public ArrayList<BoardVO> article(String num) {
		return mapper.article(num);

	}

	@Override
	public ArrayList<CommentVO> comment(String num) {
		return mapper.comment(num);
	}

	//고양이 정보 , sql에 사용할 문자열변수 선언
	@Override
	public ArrayList<BoardVO> CatInfoList() {
		String catinfo = "catinfo";
		return mapper.CatInfoList(catinfo);
	}

	//고양이 입양, sql에 사용할 문자열변수 선언
	@Override
	public ArrayList<BoardVO> CatAdoptList() {

		String catadopt = "catadopt";
		return mapper.CatAdoptList(catadopt);
	}

	//고양이 질문, sql에 사용할 문자열변수 선언
	@Override
	public ArrayList<BoardVO> CatQaList() {

		String catqa = "catqa";
		return mapper.CatQaList(catqa);
	}


	public MemberVO memberSearch(String id) {
		return mapper.memberSearch(id);
	}

	@Override
	public int insertMember(MemberVO member) {
		return mapper.insertMember(member);
	}

}
