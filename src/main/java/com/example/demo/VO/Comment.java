package com.example.demo.VO;

public class Comment {
	private int commentseq;
	private String content;
	private int memberseq;
	private String wdate;
	private int del;
	private int bbsseq;
	
	private String username;

	public Comment() {
		super();
	}

	public Comment(int commentseq, String content, int memberseq, String wdate, int del, int bbsseq, String username) {
		super();
		this.commentseq = commentseq;
		this.content = content;
		this.memberseq = memberseq;
		this.wdate = wdate;
		this.del = del;
		this.bbsseq = bbsseq;
		this.username = username;
	}

	public int getCommentseq() {
		return commentseq;
	}

	public void setCommentseq(int commentseq) {
		this.commentseq = commentseq;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getMemberseq() {
		return memberseq;
	}

	public void setMemberseq(int memberseq) {
		this.memberseq = memberseq;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getBbsseq() {
		return bbsseq;
	}

	public void setBbsseq(int bbsseq) {
		this.bbsseq = bbsseq;
	}
	
	

	@Override
	public String toString() {
		return "Comment [commentseq=" + commentseq + ", content=" + content + ", memberseq=" + memberseq + ", wdate="
				+ wdate + ", del=" + del + ", bbsseq=" + bbsseq + ", username=" + username + "]";
	}

	
	
	

}
