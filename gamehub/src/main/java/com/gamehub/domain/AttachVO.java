package com.gamehub.domain;

import java.sql.Date;

public class AttachVO {
/*
	a_fullname	varchar2(100)   primary key,
    pd_num	    number      	REFERENCES tbl_product(pd_num) not null,
    a_filename	varchar2(100)	not null,
    a_format	char(4)			not null,
    a_scalname	varchar2(100),
    a_regdate	date			default sysdate
*/
	private String a_fullname;
	private int pd_num;
	private String a_filename;
	private String a_format;
	private String a_scalname;
	private Date a_regdate;
	public String getA_fullname() {
		return a_fullname;
	}
	public void setA_fullname(String a_fullname) {
		this.a_fullname = a_fullname;
	}
	public int getPd_num() {
		return pd_num;
	}
	public void setPd_num(int pd_num) {
		this.pd_num = pd_num;
	}
	public String getA_filename() {
		return a_filename;
	}
	public void setA_filename(String a_filename) {
		this.a_filename = a_filename;
	}
	public String getA_format() {
		return a_format;
	}
	public void setA_format(String a_format) {
		this.a_format = a_format;
	}
	public String getA_scalname() {
		return a_scalname;
	}
	public void setA_scalname(String a_scalname) {
		this.a_scalname = a_scalname;
	}
	public Date getA_regdate() {
		return a_regdate;
	}
	public void setA_regdate(Date a_regdate) {
		this.a_regdate = a_regdate;
	}
	@Override
	public String toString() {
		return "AttachVO [a_fullname=" + a_fullname + ", pd_num=" + pd_num + ", a_filename=" + a_filename
				+ ", a_format=" + a_format + ", a_scalname=" + a_scalname + ", a_regdate=" + a_regdate + "]";
	}
	
	
}
