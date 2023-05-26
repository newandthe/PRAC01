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

	int bbswriteAf(int memberseq, String title, String content);

	int getMemberseq(String username);

	BBS bbsdetail(int bbsseq);

	String Getusername(int bbsseq);

	int bbsdelete(int bbsseq);

	int bbsupdate(BBS bbs);

	int isreadbbs(int bbsseq, int memberseq);

	void bbsreadcountupper(int bbsseq);

	void isreadinsert(int bbsseq, int memberseq);

}
