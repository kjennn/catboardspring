package com.gsitm.career.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gsitm.career.dto.BoardVO;
import com.gsitm.career.dto.CommentVO;
import com.gsitm.career.dto.MemberVO;


@Repository
@Mapper
public interface DemoMapper {

//	List<CamelCaseMap> selectUser();

	/**
	 * 로그인
	 * @param id
	 * @return
	 */
	public MemberVO memberSearch(String id);

	/**
	 * 회원가입
	 * @param  member
	 * @return
	 */
	public int insertMember(MemberVO member);

	/**
	 * 글 상세보기
	 * @param num
	 * @return
	 */
	public ArrayList<BoardVO> article(@Param("num")String num);

	/**
	 * 댓글 보기
	 * @param num
	 * @return
	 */
	public ArrayList<CommentVO> comment(@Param("num")String num);


	/**
	 * 고양이 정보
	 * list에 문자열 변수를 파라미터 값으로 넣어준다.
	 * @param catinfo
	 * @return
	 */
	public ArrayList<BoardVO> CatInfoList(@Param("catinfo") String catinfo);

	/**
	 * 고양이 입양
	 * list에 문자열 변수를 파라미터 값으로 넣어준다.
	 * @param catinfo
	 * @return
	 */
	public ArrayList<BoardVO> CatAdoptList(@Param("catadopt") String catadopt);

	/**
	 * 고양이 질문
	 * list에 문자열 변수를 파라미터 값으로 넣어준다.
	 * @param catinfo
	 * @return
	 */
	public ArrayList<BoardVO> CatQaList(@Param("catqa") String catqa);









}
