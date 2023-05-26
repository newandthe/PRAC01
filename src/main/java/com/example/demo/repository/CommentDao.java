package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.VO.Comment;

@Mapper
@Repository
public interface CommentDao {

	int commentwrite(Comment comment);

	List<Comment> getcommentlist(int bbsseq);

	String getcommentusername(int memberseq);

	int commentdel(int commentseq);

	int commentedit(int commentseq, String content);

}
