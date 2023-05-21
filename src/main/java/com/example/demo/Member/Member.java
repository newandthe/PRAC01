package com.example.demo.Member;

//import lombok.Data;

//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;


public class Member {

	private int memberseq;
	private String username;
	private String password;
	private String role;
	
	public Member() {
		super();
	}

	public Member(int memberseq, String username, String password, String role) {
		super();
		this.memberseq = memberseq;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public int getMemberseq() {
		return memberseq;
	}

	public void setMemberseq(int memberseq) {
		this.memberseq = memberseq;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Member [memberseq=" + memberseq + ", username=" + username + ", password=" + password + ", role=" + role
				+ "]";
	}

	

	
	
	
	
	
}
