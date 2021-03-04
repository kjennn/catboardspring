

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/admin")
public class CatBoardController {

	@Autowired
	CatBoardService catBoardService;

	@Autowired
	BoardService boardService;

	/**
	 * 메인 view
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/catboard")
	public ModelAndView view(HttpServletRequest request,ModelAndView modelAndView) {

		modelAndView.setViewName("admin/index");
		return modelAndView;
	}


	//고양이 정보
	@RequestMapping("/cat_info")
	public String viewinfo(Model model) {


		List<BoardVO> catinfolist = catBoardService.CatInfoList();

	//	log.info(catinfolist.toString());

		model.addAttribute("catinfolist", catinfolist);

		return "admin/cat_info";
	}

	//고양이 입양
	@RequestMapping("/cat_adopt")
	public String viewadopt(Model model) {

		List<BoardVO> catadoptlist = catBoardService.CatAdoptList();

		model.addAttribute("catadoptlist", catadoptlist);

		return "admin/cat_adopt";

	}

	//고양이 질문
	@RequestMapping("/cat_qa")
	public String viewqa(Model model) {

		List<BoardVO> catqalist = catBoardService.CatQaList();

		model.addAttribute("catqalist", catqalist);

		return "admin/cat_qa";
	}


	//글 상세보기
	@RequestMapping("/article")
	public String viewArticle(Model model , @RequestParam String num) {
		List<BoardVO> boardList = catBoardService.article(num);
		List<CommentVO> comment = catBoardService.comment(num);

		//log.info(num);
		//log.info(comment.toString());

		model.addAttribute("boardlist", boardList);
		model.addAttribute("comment", comment);

		return "admin/article";
	}

	@RequestMapping("/qa_article")
	public String viewqaArticle(Model model , @RequestParam String num) {
		List<BoardVO> boardList = catBoardService.article(num);
		List<CommentVO> comment = catBoardService.comment(num);

		model.addAttribute("boardlist", boardList);
		model.addAttribute("comment", comment);
		return "admin/qa_article";
	}

	@RequestMapping("/login")
	private String memberSearch(HttpServletRequest request, HttpServletRequest response) throws Exception {

		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");

		System.out.println("아이디 : "+id );

		MemberVO member = catBoardService.memberSearch(id);

		if(member == null) {

			request.setAttribute("error","아이디 또는 비밀번호가 일치하지 않습니다.");
			System.out.println("로그인 실패");
			return "admin/index";
		}else {

			System.out.println("아이디 : "+member.getId());
			System.out.println("비밀번호: "+member.getPasswd());

			if(member.getPasswd().equals(passwd)) {
				HttpSession session = request.getSession();
				session.setAttribute("id", member.getId());
				request.setAttribute("login","로그인 완료");
				System.out.println("로그인 완료");
				return "admin/index";
			}else {
				request.setAttribute("error","아이디 또는 비밀번호가 일치하지 않습니다.");
				System.out.println("로그인 실패");
				return "admin/index";
			}
		}

	}

	@RequestMapping("/logout")
	private String memberlogout(MemberVO member,HttpServletRequest request){

		HttpSession session = request.getSession();
		session.invalidate();

		request.removeAttribute("login");
		request.removeAttribute("error");
		System.out.println("로그아웃 완료");
		return "admin/index";
	}

	@PostMapping("/join")
    private String insertMember(HttpServletRequest request){
		MemberVO member = new MemberVO();
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));

        System.out.println(member);
        catBoardService.insertMember(member);
        System.out.println("회원가입 완");

        return "admin/index";
	}

	@PostMapping("/insert")
    private String insertBoard(HttpServletRequest request){

		BoardVO board = new BoardVO();

		board.setBoardId(request.getParameter("select"));
        StringBuffer cat = new StringBuffer( request.getParameter("select"));
        cat.insert(3, "_");
        System.out.println(cat);


        board.setId(request.getParameter("id"));
        board.setTitle(request.getParameter("bbsTitle"));
        board.setContent(request.getParameter("bbsContent"));

        System.out.println(board);

        boardService.insertBoard(board);

        return "redirect:/admin/"+cat;
	}

	@PostMapping("/update")
    private String updateBoard(HttpServletRequest request){

		BoardVO board = new BoardVO();
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));

		board.setBoardNum(boardNum);
        board.setTitle(request.getParameter("bbsTitle"));
        board.setContent(request.getParameter("bbsContent"));

        System.out.println(board);

        boardService.updateBoard(board);

        return "redirect:/admin/article?num="+boardNum;

    }

	@PostMapping("/delete")
    private String deleteBoard(HttpServletRequest request){

		String num = request.getParameter("boardNum");
		boardService.deleteBoard(num);

        return "redirect:/admin/cat_info";

    }

	@PostMapping("/insertcomment")
    private String insertComment(HttpServletRequest request){

		CommentVO comment = new CommentVO();
		int bnum = Integer.parseInt(request.getParameter("boardNum"));
		comment.setBoardNum(bnum);
        comment.setId(request.getParameter("id"));
        comment.setComments(request.getParameter("cmt"));

        System.out.println(comment);

        boardService.insertComment(comment);

        return "redirect:/admin/article?num="+bnum;
	}

	@PostMapping("/deletecomment")
    private String deleteComment(HttpServletRequest request){

		String num = request.getParameter("CommentNum");
		int bnum = Integer.parseInt(request.getParameter("BoardNum")) ;

		System.out.println("commentNum : "+ num);

		boardService.deleteComment(num);

        return "redirect:/admin/article?num="+bnum;

    }



}
