package com.daxia.core.support;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.daxia.core.config.AppProperties;

public class Page {
	private Integer pageNum = 1;
	private Integer totalRecords;
	private String dir;
	private String sort;
	private String alias;
	
	private Integer page;
	private Integer rows;
	
	private Integer totalPages;

	private Integer numPerPage = AppProperties.get("page.default.pageSize") == null ? 15 : Integer.valueOf(AppProperties.get("page.default.pageSize"));
	
	public Integer getNumPerPage() {
	    if (rows != null) {
	        return rows;
	    } else {
	        return numPerPage;
	    }
	}
	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}
	public Integer getPageNum() {
	    if (page != null) {
	        return page;
	    } else {
	        return pageNum;
	    }
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
    public void setPage(Integer page) {
        this.page = page;
    }
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/kevin/agent.txt")));
        String line = null;
        String tmp = "update mm_agent set id = {id} where agent = '{agent}';";
        int id = 1;
        while ((line = reader.readLine()) != null) {
            String sql = tmp.replace("{id}", (id ++) + "").replace("{agent}", line);
            System.out.println(sql);
        }
    }
}
