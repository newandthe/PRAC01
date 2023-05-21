package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Member.Member;
import com.example.demo.repository.MemberDao;

@Service
public class Serviceprac01 {
	
	@Autowired
	private MemberDao memberdao;

	public boolean regiAf(Member member) {
		
		int n = memberdao.regiAf(member);
		
		return n>0?true:false;
		
	}

	public boolean checkDuplicateUsername(String username) {
		
		int n = memberdao.checkDuplicateUsername(username);
//		System.out.println(n);
		// true면 중복이 존재한다는 의미.
		return n>0?true:false;
	}

	public boolean loginAf(Member member) {
		int n = memberdao.loginAf(member);
		
		return n>0?true:false;
			
		
	}

	public Member getUser(String username) {
		
		return memberdao.getUser(username);
	}
	
	

}
