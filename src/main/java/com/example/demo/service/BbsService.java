package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.VO.BBS;
import com.example.demo.repository.BbsDao;

@Service
public class BbsService {
	
	@Autowired
	private BbsDao bbsdao;
	
	public List<BBS> getAllBBS(int offset, int pageSize, String choice, String search) {
		List<BBS> bbslist = bbsdao.getAllBBS(offset, pageSize, choice, search);
		for (BBS bbs : bbslist) {
			String username = bbsdao.Getusername(bbs.getBbsseq());
			bbs.setUsername(username);
		}
		return bbslist;
	}

	public int getTotalCount(String search) {
		
		return bbsdao.getTotalCount(search);
	}

	public boolean bbswriteAf(String username, String title, String content) {
		
		int memberseq = bbsdao.getMemberseq(username);
		
		int n = bbsdao.bbswriteAf(memberseq, title, content);
//		System.out.println("n: " + n);
		
		return n>0?true:false;
	}

	public BBS bbsdetail(int bbsseq) {
		BBS bbs = bbsdao.bbsdetail(bbsseq);
		String username = bbsdao.Getusername(bbsseq);
		bbs.setUsername(username);
//		System.out.println(bbs.toString());
		return bbs;
	}

	public boolean bbsdelete(int bbsseq) {
		int n = bbsdao.bbsdelete(bbsseq);
		
		return n>0?true:false;
	}

	public boolean bbsupdate(BBS bbs) {
		int n = bbsdao.bbsupdate(bbs);
		
		return n>0?true:false;
	}

	// 로그인한사람과 작성자가 다른 경우에만 조회수 증가를 해주는 서비스 (그리고 중복으로 조회수 증가가 안되도록.)
	public void bbsreadcountupper(int bbsseq, int memberseq) {
		BBS bbs = bbsdao.bbsdetail(bbsseq);
		
//		System.out.println("조회수로직" + bbs.getMemberseq());
//		System.out.println("조회수로직2" + memberseq);
		
		
		
		
		
		if(bbs.getMemberseq() != memberseq) {	// 본인이 작성하지 않은 글에 대해서
			int isread = bbsdao.isreadbbs(bbsseq, memberseq);	// 이미 읽었는지 DB에서 가져오기.
			
			if(isread == 0) {	// 0보다 크다면 DB에 존재. 이미 읽었음.	0이면 읽지 않았으므로 
				bbsdao.bbsreadcountupper(bbsseq);	// 조회수 증가 (update)
				bbsdao.isreadinsert(bbsseq, memberseq);		// 읽음을 알리기위한 DB저장 (insert)
			}
			
		}
		
	}
	
	
	

}
