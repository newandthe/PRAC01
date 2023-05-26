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

	public boolean commentWrite(Comment comment) {
		
		int n = commentdao.commentWrite(comment);
		
		
		return n>0?true:false;
	}

	public List<Comment> getCommentlist(int bbsseq) {
		List<Comment> commentlist = commentdao.getCommentlist(bbsseq);
		
		// for문으로 닉네임 얻어와서 전부 setter
		for (Comment comment : commentlist) {
			comment.setUsername(commentdao.getCommentusername(comment.getMemberseq()));
		}
		
		return commentlist;
	}

	public boolean commentDel(int commentseq) {
		
		int n = commentdao.commentDel(commentseq);
		return n>0?true:false;
	}

	public boolean commentEdit(int commentseq, String content) {
//		System.out.println("commentseq: " + commentseq);
//		System.out.println("content: " + content);
		int n = commentdao.commentEdit(commentseq, content);
		return n>0?true:false;
	}
}
