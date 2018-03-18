package com.gamehub.domain;

public class SearchPaging extends Paging{
	private String searchType;
	private String keyword;
	private int minPrice;
	private int maxPrice;
	private int date;
	private String cateCode;
	private String display;
	
	
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public String getCateCode() {
		return cateCode;
	}
	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "SearchPaging [searchType=" + searchType + ", keyword=" + keyword + ", minPrice=" + minPrice
				+ ", maxPrice=" + maxPrice + ", date=" + date + ", cateCode=" + cateCode + ", display=" + display
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
