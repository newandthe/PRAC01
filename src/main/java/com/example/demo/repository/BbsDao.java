package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.VO.BBS;

@Mapper
@Repository
public interface BbsDao {

	List<BBS> getAllBBS(int offset, int pageSize, String choice, String search);

	int getTotalCount(String search);

	int bbsWriteAf(int memberseq, String title, String content);

	int getMemberseq(String username);

	BBS bbsDetail(int bbsseq);

	String getUsername(int bbsseq);

	int bbsDelete(int bbsseq);

	int bbsUpdate(BBS bbs);

	int isReadbbs(int bbsseq, int memberseq);

	void bbsReadcountupper(int bbsseq);

	void isReadinsert(int bbsseq, int memberseq);

}
