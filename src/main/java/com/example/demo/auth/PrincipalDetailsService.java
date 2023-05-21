package com.example.demo.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Member.Member;
import com.example.demo.repository.MemberDao;
import com.mysql.cj.xdevapi.XDevAPIError;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

	private final MemberDao memberdao;
	
	public PrincipalDetailsService(MemberDao memberRepository) {
        this.memberdao = memberRepository;
    }

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member userEntity = (Member) MemberDao.findByUserId(username);
        if (userEntity == null) {
//            throw new XDevAPIError(ErrorCode.USER_ID_NOT_FOUND);
        }
        return new PrincipalDetails(userEntity);
    }

}
