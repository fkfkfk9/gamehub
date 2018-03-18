package com.gamehub.domain;

public class LoginDTO {
	private String mem_id;
	private String mem_name;
	private String mem_passwd;
	private String mem_nick;
	private String mem_verify;
	private String mem_level;
	private String mem_tel;
	
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
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
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	public String getMem_verify() {
		return mem_verify;
	}
	public void setMem_verify(String mem_verify) {
		this.mem_verify = mem_verify;
	}
	public String getMem_level() {
		return mem_level;
	}
	public void setMem_level(String mem_level) {
		this.mem_level = mem_level;
	}
	@Override
	public String toString() {
		return "LoginDTO [mem_id=" + mem_id + ", mem_name=" + mem_name + ", mem_passwd=" + mem_passwd + ", mem_nick="
				+ mem_nick + ", mem_verify=" + mem_verify + ", mem_level=" + mem_level + ", mem_tel=" + mem_tel + "]";
	}
	
	
}
