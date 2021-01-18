package com.gsitm.career.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsitm.career.dto.BoardVO;
import com.gsitm.career.dto.CommentVO;
import com.gsitm.career.dto.MemberVO;

public interface CatBoardService {

	public MemberVO memberSearch(String id);
	public int insertMember(MemberVO member);
// memberInsert 회원가입

	public ArrayList<CommentVO> comment(String num);
	public ArrayList<BoardVO> article(String num);

	public ArrayList<BoardVO> CatInfoList();
	public ArrayList<BoardVO> CatAdoptList();
	public ArrayList<BoardVO> CatQaList();




}
