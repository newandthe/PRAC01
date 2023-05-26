package com.example.demo.VO;

public class BBS {
	
	private int bbsseq;
	private String title;
	private String content;
	private int memberseq;
	private int readcount;
	private String wdate;
	private int del;
	
	private String username;
	
	public BBS() {
		super();
	}

	public BBS(int bbsseq, String title, String content, int memberseq, int readcount, String wdate) {
		super();
		this.bbsseq = bbsseq;
		this.title = title;
		this.content = content;
		this.memberseq = memberseq;
		this.readcount = readcount;
		this.wdate = wdate;
	}
	
	

	

	public BBS(int bbsseq, String title, String content, int memberseq, int readcount, String wdate, int del,
			String username) {
		super();
		this.bbsseq = bbsseq;
		this.title = title;
		this.content = content;
		this.memberseq = memberseq;
		this.readcount = readcount;
		this.wdate = wdate;
		this.del = del;
		this.username = username;
	}

	public int getBbsseq() {
		return bbsseq;
	}

	public void setBbsseq(int bbsseq) {
		this.bbsseq = bbsseq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "BBS [bbsseq=" + bbsseq + ", title=" + title + ", content=" + content + ", memberseq=" + memberseq
				+ ", readcount=" + readcount + ", wdate=" + wdate + ", del=" + del + ", username=" + username + "]";
	}

	

	
	
	
	

}
