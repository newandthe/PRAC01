package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.VO.Comment;
import com.example.demo.repository.CommentDao;

@Service
public class CommentService {

	@Autowired
	CommentDao commentdao;

	public boolean commentwrite(Comment comment) {
		
		int n = commentdao.commentwrite(comment);
		
		
		return n>0?true:false;
	}

	public List<Comment> getcommentlist(int bbsseq) {
		List<Comment> commentlist = commentdao.getcommentlist(bbsseq);
		
		// for문으로 닉네임 얻어와서 전부 setter
		for (Comment comment : commentlist) {
			comment.setUsername(commentdao.getcommentusername(comment.getMemberseq()));
		}
		
		return commentlist;
	}

	public boolean commentdel(int commentseq) {
		
		int n = commentdao.commentdel(commentseq);
		return n>0?true:false;
	}

	public boolean commentedit(int commentseq, String content) {
//		System.out.println("commentseq: " + commentseq);
//		System.out.println("content: " + content);
		int n = commentdao.commentedit(commentseq, content);
		return n>0?true:false;
	}
}
