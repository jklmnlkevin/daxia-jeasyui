package com.daxia.core.support;

import com.daxia.core.config.AppProperties;

public class Page {
	private Integer pageNum = 1;
	private Integer totalRecords;
	private String dir;
	private String sort;
	private String alias;
	
	private Integer totalPages;

	private Integer numPerPage = AppProperties.get("page.default.pageSize") == null ? 15 : Integer.valueOf(AppProperties.get("page.default.pageSize"));
	
	public Integer getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Integer getTotalPages() {
		if (numPerPage == null || totalRecords == null) {
			return 0;
		}
		if (totalRecords <= numPerPage) {
			return 1;
		}
		totalPages = totalRecords / numPerPage;
		if (totalRecords % numPerPage != 0) {
			totalPages += 1;
		}
		return totalPages;
    }
	public void setTotalPages(Integer totalPages) {
    	this.totalPages = totalPages;
    }
	
}
