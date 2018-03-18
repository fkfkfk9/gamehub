package com.gamehub.domain;

import java.sql.Date;
import java.util.Arrays;

public class ProductVO {
/*
	pd_num	        number	        primary key,
    cg_code	        varchar2(20)	REFERENCES tbl_category(cg_code) not null,
    mem_id          varchar2(80)    REFERENCES tbl_member(mem_id) not null,
    pd_name	        varchar2(50)	not null,
    pd_price	    number			not null,
    pd_discount	    number			not null,
    pd_develop	    varchar2(50)	not null,
    pd_content	    CLOB			not null,
    pd_bonus	    varchar2(500),
    pd_requirement	varchar2(1000)	not null,
    pd_publisher    varchar2(50)    not null,
    pd_img	        varchar(50)		not null,
    pd_amount	    number			not null,
    pd_korea	    char(1)         not null,
    pd_buy	        char(1)			not null,
    pd_release	    varchar(30)		not null,
    pd_regdate	    date			default sysdate,
    pd_updatedate	date			default sysdate
*/
	private int pd_num;
	private String cg_code;//3차 카테고리
	private String mem_id;
	private String pd_name;
	private int pd_price;
	private int pd_discount;
	private String pd_develop;
	private String pd_content;
	private String pd_bonus;
	private String pd_requirement;
	private String pd_publisher;
	private String pd_img;
	private String cg_ancestor;//1차 카테고리
	private String cg_parent;//2차 카테고리
	private int pd_amount;
	private String pd_korea;
	private String pd_buy;
	private String pd_release;
	private Date pd_regdate;
	private Date pd_updatedate;
	private String[] files;
	private String cg_p_name;
	private String cg_name;
	
	public String getCg_parent() {
		return cg_parent;
	}
	public void setCg_parent(String cg_parent) {
		this.cg_parent = cg_parent;
	}
	public String getCg_p_name() {
		return cg_p_name;
	}
	public void setCg_p_name(String cg_p_name) {
		this.cg_p_name = cg_p_name;
	}
	public String getCg_name() {
		return cg_name;
	}
	public void setCg_name(String cg_name) {
		this.cg_name = cg_name;
	}
	public String getCg_ancestor() {
		return cg_ancestor;
	}
	public void setCg_ancestor(String cg_ancestor) {
		this.cg_ancestor = cg_ancestor;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}	
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	public int getPd_num() {
		return pd_num;
	}
	public void setPd_num(int pd_num) {
		this.pd_num = pd_num;
	}
	public String getCg_code() {
		return cg_code;
	}
	public void setCg_code(String cg_code) {
		this.cg_code = cg_code;
	}
	public String getPd_name() {
		return pd_name;
	}
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	public int getPd_price() {
		return pd_price;
	}
	public void setPd_price(int pd_price) {
		this.pd_price = pd_price;
	}
	public int getPd_discount() {
		return pd_discount;
	}
	public void setPd_discount(int pd_discount) {
		this.pd_discount = pd_discount;
	}
	public String getPd_develop() {
		return pd_develop;
	}
	public void setPd_develop(String pd_develop) {
		this.pd_develop = pd_develop;
	}
	public String getPd_content() {
		return pd_content;
	}
	public void setPd_content(String pd_content) {
		this.pd_content = pd_content;
	}
	public String getPd_bonus() {
		return pd_bonus;
	}
	public void setPd_bonus(String pd_bonus) {
		this.pd_bonus = pd_bonus;
	}
	public String getPd_requirement() {
		return pd_requirement;
	}
	public void setPd_requirement(String pd_requirement) {
		this.pd_requirement = pd_requirement;
	}
	public String getPd_publisher() {
		return pd_publisher;
	}
	public void setPd_publisher(String pd_publisher) {
		this.pd_publisher = pd_publisher;
	}
	public String getPd_img() {
		return pd_img;
	}
	public void setPd_img(String pd_img) {
		this.pd_img = pd_img;
	}
	public int getPd_amount() {
		return pd_amount;
	}
	public void setPd_amount(int pd_amount) {
		this.pd_amount = pd_amount;
	}
	public String getPd_korea() {
		return pd_korea;
	}
	public void setPd_korea(String pd_korea) {
		this.pd_korea = pd_korea;
	}
	public String getPd_buy() {
		return pd_buy;
	}
	public void setPd_buy(String pd_buy) {
		this.pd_buy = pd_buy;
	}
	public String getPd_release() {
		return pd_release;
	}
	public void setPd_release(String pd_release) {
		this.pd_release = pd_release;
	}
	public Date getPd_regdate() {
		return pd_regdate;
	}
	public void setPd_regdate(Date pd_regdate) {
		this.pd_regdate = pd_regdate;
	}
	public Date getPd_updatedate() {
		return pd_updatedate;
	}
	public void setPd_updatedate(Date pd_updatedate) {
		this.pd_updatedate = pd_updatedate;
	}
	@Override
	public String toString() {
		return "ProductVO [pd_num=" + pd_num + ", cg_code=" + cg_code + ", mem_id=" + mem_id + ", pd_name=" + pd_name
				+ ", pd_price=" + pd_price + ", pd_discount=" + pd_discount + ", pd_develop=" + pd_develop
				+ ", pd_content=" + pd_content + ", pd_bonus=" + pd_bonus + ", pd_requirement=" + pd_requirement
				+ ", pd_publisher=" + pd_publisher + ", pd_img=" + pd_img + ", cg_ancestor=" + cg_ancestor
				+ ", cg_parent=" + cg_parent + ", pd_amount=" + pd_amount + ", pd_korea=" + pd_korea + ", pd_buy="
				+ pd_buy + ", pd_release=" + pd_release + ", pd_regdate=" + pd_regdate + ", pd_updatedate="
				+ pd_updatedate + ", files=" + Arrays.toString(files) + ", cg_p_name=" + cg_p_name + ", cg_name="
				+ cg_name + "]";
	}
}
