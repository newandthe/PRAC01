package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.VO.BBS;
import com.example.demo.VO.Comment;
import com.example.demo.VO.Member;
import com.example.demo.service.BbsService;
import com.example.demo.service.CommentService;

@Controller
public class BbsController {

	@Autowired
	BbsService bbsservice;

	// bbsdetail 반환시 딱 한번 필요...
	@Autowired
	CommentService commentservice;

	@GetMapping("/bbslist")
	public String getBbsList(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "choice", defaultValue = "recentorderby") String choice,
			@RequestParam(name = "search", defaultValue = "") String search, Model model) {
		// 처음에는 defaultValue로 접근.

		int pageSize = 2; // 페이지당 게시물 수
		int offset = (page - 1) * pageSize; // 가져올 게시물의 시작 위치

//	    System.out.println("choice: " + choice);
//	    System.out.println("search: " + search);
//	    System.out.println("flag0");

		// , choice, search
		// list 또한 choice와 search에 따라 새롭게..
		List<BBS> bbsList = bbsservice.getAllBBS(offset, pageSize, choice, search);

//	    System.out.println(bbsList.toString());

//	    System.out.println("flag1");

		// search를 고려해서, totalCount 새롭게 구해야한다.. (choice는 단순 정렬이기때문에 개수에 영향 X)
		int totalCount = bbsservice.getTotalCount(search);
//	    int totalCount = bbsList.size();
//	    System.out.println("flag2");
		int totalPages = (int) Math.ceil((double) totalCount / pageSize);

		model.addAttribute("bbsList", bbsList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("choice", choice);
		model.addAttribute("search", search);
//	    System.out.println("flag3");
//	    System.out.println(bbsList.toString());
		// 추후 주석처리 (옵션으로 정렬기준 완성 한 뒤)

		return "bbslist";
	}

	@GetMapping("/bbswrite")
	public String bbsWrite(Model model) {

		// 로그인 인증세션
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() == "anonymousUser") {
			return "login";
		}

		Member member = (Member) authentication.getPrincipal();
		// System.out.println(member.toString());
		model.addAttribute("username", member.getUsername());

		return "bbswrite";
	}

	@PostMapping("/bbswriteAf")
	@ResponseBody
	public String bbsWriteAf(@RequestParam("username") String username, @RequestParam("title") String title,
			@RequestParam("content") String content) {

//		System.out.println(username);
//		System.out.println(title);
//		System.out.println(content);

		// username, title, content 콘트롤러에서의 null 체크 (글 작성시)
		if (username == null || username == "anonymousUser") {

			return "loginisnull";
		}

		else if (title == null || title.trim() == "") {

			return "titleblank";
		}

		else if (content == null || content.trim() == "") {

			return "contentblank";
		}

		boolean isSuccess = bbsservice.bbsWriteAf(username, title, content);
//		System.out.println("write boolean: " + isSuccess);

		if (isSuccess) {
			return "success";
		} else {
			return "fail";
		}

	}

	@GetMapping("/bbsdetail/{bbsseq}") // 조회수 로직 여기에 ..
	public String bbsDetail(@PathVariable("bbsseq") int bbsseq, Model model) {

		// 로그인 인증세션
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() == "anonymousUser" || authentication.getPrincipal() == null) {
			System.out.println("비인가 회원");
			return "redirect:/login/sessionout";
		} else {
			// System.out.println(authentication.toString());
			Member member = (Member) authentication.getPrincipal();
			// System.out.println(member.toString());
			// System.out.println(member.getMemberseq());
			model.addAttribute("memberseq", member.getMemberseq());

			// 조회수 로직 (본인이 작성하지 않는 글만 조회수 증가, 동일한 사용자가 조회할 경우 중복으로 조회수 증가하지 않음)
			// 비 인가 회원도 조회수 증가하지 않게 하려면 클라이언트에 쿠키로 구현해야.. ( 그래서 세션검사 진행했어요.. )
			bbsservice.bbsReadcountupper(bbsseq, member.getMemberseq());
		}

		// System.out.println("조회수 하기위한 " + bbsseq);
		BBS bbs = bbsservice.bbsDetail(bbsseq);
		model.addAttribute("bbs", bbs);

		// 댓글 리스트 로직
		List<Comment> commentlist = commentservice.getCommentlist(bbsseq);

//		System.out.println(commentlist.toString());

		model.addAttribute("commentlist", commentlist);

		return "bbsdetail";
	}

	@PostMapping("/bbsdelete")
	@ResponseBody
	public String bbsDelete(@RequestParam("bbsseq") int bbsseq) {
		// System.out.println(bbsseq);

		if ((Integer) bbsseq == null || bbsseq == 0) {
			return "false";
		}

		if (bbsservice.bbsDelete(bbsseq)) {

			return "success";
		} else {
			return "false";
		}
	}

	@PostMapping("/bbsupdate")
	public String bbsUpdate(@RequestParam("bbsseq") int bbsseq, Model model) {

		// System.out.println(bbsseq);

		// 기존 정보 전달
		BBS bbs = bbsservice.bbsDetail(bbsseq);
		model.addAttribute("bbs", bbs);

		// 로그인 인증세션
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println("로그인 인증세션: " + authentication.getPrincipal());
		if (authentication.getPrincipal().equals("anonymousUser")) {
			return "login"; // 인증 세션 만료시 로그인으로 이동
		} else {
			Member member = (Member) authentication.getPrincipal();
			model.addAttribute("memberseq", member.getMemberseq()); // 로그인 한 사람의 memberseq
			model.addAttribute("username", member.getUsername()); // 로그인 한 사람의 username
		}

		return "bbsupdate";
	}

	@PostMapping("/bbsupdateAf")
	@ResponseBody
	public String bbsUpdateAf(@ModelAttribute BBS bbs) { // 객체로 받아서 매핑

		// BBS [bbsseq=2, title=test2, content=test2,
		// memberseq=0, readcount=0, wdate=null, del=0, username=abc]
		
		// 콘트롤러 null 체크
		if(bbs.getTitle() == null) {
			return "titleblank";
		} else if(bbs.getContent() == null) {
			return "contentblank";
		} 

		// 게시물 수정
		if (bbsservice.bbsUpdate(bbs)) {
			return "success";
		} else {
			return "fail";
		}

	}

}
