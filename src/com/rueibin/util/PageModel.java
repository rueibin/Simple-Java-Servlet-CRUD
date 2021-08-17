package com.rueibin.util;

import java.util.List;

public class PageModel {
	private List list;

	private int currentPageNum;
	private int pageSize;
	private int totalRecords;

	private int totalPageNum; 
	private int startIndex;
	private int prevPageNum;
	private int nextPageNum;

	private int startPage;
	private int endPage;

	private String url;

	public PageModel(int currentPageNum, int totalRecords, int pageSize) {
		this.currentPageNum = currentPageNum;
		this.totalRecords = totalRecords;
		this.pageSize = pageSize;

		totalPageNum = (totalRecords % pageSize == 0) ? (totalRecords / pageSize) : ((totalRecords / pageSize) + 1);
		startIndex = (currentPageNum - 1) * pageSize;

		prevPageNum = currentPageNum - 1;
		if (currentPageNum <= 1) {
			prevPageNum = 1;
		}

		nextPageNum = currentPageNum + 1;
		if (currentPageNum == totalRecords) {
			nextPageNum = totalRecords;
		}

		startPage = currentPageNum - 4;
		endPage = currentPageNum + 4;
		if (totalPageNum <= 9) {
			startPage = 1;
			endPage = totalPageNum;
		} else {
			if (startPage < 1) {
				startPage = 1;
				endPage = startPage + 8;
			}
			if (endPage > totalPageNum) {
				endPage = totalPageNum;
				startPage = totalPageNum - 8;
			}
		}

	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getPrevPageNum() {
		return prevPageNum;
	}

	public void setPrevPageNum(int prevPageNum) {
		this.prevPageNum = prevPageNum;
	}

	public int getNextPageNum() {
		return nextPageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
