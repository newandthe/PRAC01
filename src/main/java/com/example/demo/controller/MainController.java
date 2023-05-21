package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Member.Member;
import com.example.demo.service.Serviceprac01;

@Controller
public class MainController {
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public MainController(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	 
	
	@Autowired
	private Serviceprac01 serviceprac;
	
	@GetMapping("/")
    public String index() {
        return "login";
    }
	
	
	@PostMapping("/loginAf")
	@ResponseBody
    public String loginFormSubmit(@RequestParam("username") String username, @RequestParam("password") String password) {
//        System.out.println(username);
//        System.out.println(password);
        
//        String encodedPassword = bCryptPasswordEncoder.encode(password);
//        System.out.println("암호화 비밀번호 password: " + encodedPassword);
        
        
        Member member = serviceprac.getUser(username);
        System.out.println(member.toString());
        if(member.getUsername() == null) { // 입력한 아이디가 존재하지 않을경우 실패처리.
        	return "fail";
        }
        
        
        // 입력한 비밀번호가 복호화된 비밀번호와 같다면 로그인 성공
        if(bCryptPasswordEncoder.matches(password, member.getPassword())) {
        	return "success";
        } else {
        	return "fail";
        }
        
        
        // return "redirect:/bbslist";
    }
    
    @GetMapping("/regi")
    public String regi() {
        return "regi";
    }
    
    @PostMapping("/checkDuplicateUsername")
    @ResponseBody
    public String checkDuplicateUsername(@RequestParam("username") String username) {
//	    System.out.println(username);
        boolean isDuplicate = serviceprac.checkDuplicateUsername(username);
        if (isDuplicate) {
            return "duplicate";
        } else {
            return "notduplicate";
        }
    }
    
    @PostMapping("/regiAf")
    public String regiAf(@RequestParam("username") String username, @RequestParam("password") String password) {
//        System.out.println(username);
//        System.out.println(password);
    	
        Member member = new Member();
        
        member.setUsername(username);
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        member.setPassword(encodedPassword);
        
        boolean isSuccess = serviceprac.regiAf(member);
        
        System.out.println(isSuccess);
        
        
        
    	return "login";
    }
    
    

	
	
	@GetMapping("/bbslist")
	public String bbslist() {
		
		return "bbslist";
	}

}
