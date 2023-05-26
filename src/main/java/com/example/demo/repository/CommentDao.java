package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.VO.Comment;

@Mapper
@Repository
public interface CommentDao {

	int commentWrite(Comment comment);

	List<Comment> getCommentlist(int bbsseq);

	String getCommentusername(int memberseq);

	int commentDel(int commentseq);

	int commentEdit(int commentseq, String content);

}
