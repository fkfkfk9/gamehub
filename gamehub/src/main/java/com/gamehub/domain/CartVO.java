package com.gamehub.domain;

public class CartVO {
/*
	CT_CODE		NUMBER				No		1	장바구니 코드
	PD_NUM		NUMBER				No		2	상품번호
	MEM_ID		VARCHAR2(80 BYTE)	No		3	회원ID
	CT_AMOUNT	NUMBER				No		4	구매수량
*/
	//DB상 데이터
	private int ct_code;
	private int pd_num;
	private String mem_id;
	private int ct_amount;
	//조인 데이터
	private int pd_price;
	private int pd_discount;
	private String pd_img;
	private String pd_name;
	
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
	public String getPd_img() {
		return pd_img;
	}
	public void setPd_img(String pd_img) {
		this.pd_img = pd_img;
	}
	public String getPd_name() {
		return pd_name;
	}
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	public int getCt_code() {
		return ct_code;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public void setCt_code(int ct_code) {
		this.ct_code = ct_code;
	}
	public int getPd_num() {
		return pd_num;
	}
	public void setPd_num(int pd_num) {
		this.pd_num = pd_num;
	}
	public int getCt_amount() {
		return ct_amount;
	}
	public void setCt_amount(int ct_amount) {
		this.ct_amount = ct_amount;
	}
	@Override
	public String toString() {
		return "CartVO [ct_code=" + ct_code + ", pd_num=" + pd_num + ", mem_id=" + mem_id + ", ct_amount=" + ct_amount
				+ ", pd_price=" + pd_price + ", pd_discount=" + pd_discount + ", pd_img=" + pd_img + ", pd_name="
				+ pd_name + "]";
	}
	
	
}
