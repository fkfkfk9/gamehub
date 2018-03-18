package com.gamehub.domain;

import java.sql.Date;

public class MemberVO {
/*
 	mem_id	            varchar2(50) primary key,
    mem_name	        varchar2(30) not null,
    mem_passwd	        varchar2(60) not null,
    mem_addr_num	    varchar2(10) not null,
    mem_addr_basic	    varchar2(50) not null,
    mem_addr_detail	    varchar2(50) not null,
    mem_tel	            varchar2(20) not null,
    mem_nick	        varchar2(30) UNIQUE not null,
    mem_receive	        char(1)      not null,
    mem_verify	        char(1)		 default 'N' null,
    mem_point	        number       default 0,
    mem_regdate	        date         default sysdate,
    mem_updatedate	    date         default sysdate,
    mem_lastlogin	    date         ,
    mem_level	        char(1)      default 1
*/
	private String mem_id;
	private String mem_name;
	private String mem_passwd;
	private String mem_addr_num;
	private String mem_addr_basic;
	private String mem_addr_detail;
	private String mem_tel;
	private String mem_nick;
	private String mem_receive;
	private String mem_verify;
	private int mem_point;
	private Date mem_regdate;
	private Date mem_updatedate;
	private Date mem_lastlogin;
	private String mem_level;
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_passwd() {
		return mem_passwd;
	}
	public void setMem_passwd(String mem_passwd) {
		this.mem_passwd = mem_passwd;
	}
	public String getMem_addr_num() {
		return mem_addr_num;
	}
	public void setMem_addr_num(String mem_addr_num) {
		this.mem_addr_num = mem_addr_num;
	}
	public String getMem_addr_basic() {
		return mem_addr_basic;
	}
	public void setMem_addr_basic(String mem_addr_basic) {
		this.mem_addr_basic = mem_addr_basic;
	}
	public String getMem_addr_detail() {
		return mem_addr_detail;
	}
	public void setMem_addr_detail(String mem_addr_detail) {
		this.mem_addr_detail = mem_addr_detail;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	public String getMem_receive() {
		return mem_receive;
	}
	public void setMem_receive(String mem_receive) {
		this.mem_receive = mem_receive;
	}
	public String getMem_verify() {
		return mem_verify;
	}
	public void setMem_verify(String mem_verify) {
		this.mem_verify = mem_verify;
	}
	public int getMem_point() {
		return mem_point;
	}
	public void setMem_point(int mem_point) {
		this.mem_point = mem_point;
	}
	public Date getMem_regdate() {
		return mem_regdate;
	}
	public void setMem_regdate(Date mem_regdate) {
		this.mem_regdate = mem_regdate;
	}
	public Date getMem_updatedate() {
		return mem_updatedate;
	}
	public void setMem_updatedate(Date mem_updatedate) {
		this.mem_updatedate = mem_updatedate;
	}
	public Date getMem_lastlogin() {
		return mem_lastlogin;
	}
	public void setMem_lastlogin(Date mem_lastlogin) {
		this.mem_lastlogin = mem_lastlogin;
	}
	public String getMem_level() {
		return mem_level;
	}
	public void setMem_level(String mem_level) {
		this.mem_level = mem_level;
	}
	@Override
	public String toString() {
		return "MemberVO [mem_id=" + mem_id + ", mem_name=" + mem_name + ", mem_passwd=" + mem_passwd
				+ ", mem_addr_num=" + mem_addr_num + ", mem_addr_basic=" + mem_addr_basic + ", mem_addr_detail="
				+ mem_addr_detail + ", mem_tel=" + mem_tel + ", mem_nick=" + mem_nick + ", mem_receive=" + mem_receive
				+ ", mem_verify=" + mem_verify + ", mem_point=" + mem_point + ", mem_regdate=" + mem_regdate
				+ ", mem_updatedate=" + mem_updatedate + ", mem_lastlogin=" + mem_lastlogin + ", mem_level=" + mem_level
				+ "]";
	}
	
	
}
