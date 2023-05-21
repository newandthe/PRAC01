package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.Member.Member;

@Mapper
@Repository
public interface MemberDao {

	static Member findByUserId(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	int regiAf(Member member);

	int checkDuplicateUsername(String username);

	int loginAf(Member member);

	Member getUser(String username);

}
