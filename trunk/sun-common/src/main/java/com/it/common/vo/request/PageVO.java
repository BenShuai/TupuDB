package com.it.common.vo.request;

public class PageVO {
	/**
	 * 页大小
	 */
	private Integer pageSize;
	/**
	 * 页码
	 */
	private Integer pageNumber;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
}
