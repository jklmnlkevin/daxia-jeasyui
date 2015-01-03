package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.SystemConfigAPIDTO;

/**
 * 这个类所有包装所有的AuthorityAPIDTO记录的，也就是我们返回的查询的记录都是放在一个xxxInfos的数组里。
 * 如果这个模块的所有请求都不需要用到分页，请把totalPages、 pageNum和totalRecords删掉。
 */
public class SystemConfigInfoAPIDTO {
	private List<SystemConfigAPIDTO> systemConfigInfos;
	private String totalPages = "";
	private String pageNum = "";
	private String totalRecords = "";
	
	/**
     * @return the systemConfigInfos
     */
    public List<SystemConfigAPIDTO> getSystemConfigInfos() {
    	return systemConfigInfos;
    }
	/**
     * @param systemConfigInfos the systemConfigInfos to set
     */
    public void setSystemConfigInfos(List<SystemConfigAPIDTO> systemConfigInfos) {
    	this.systemConfigInfos = systemConfigInfos;
    }
	/**
     * @return the totalPages
     */
    public String getTotalPages() {
    	return totalPages;
    }
	/**
     * @param totalPages the totalPages to set
     */
    public void setTotalPages(String totalPages) {
    	this.totalPages = totalPages;
    }
	/**
     * @return the pageNum
     */
    public String getPageNum() {
    	return pageNum;
    }
	/**
     * @param pageNum the pageNum to set
     */
    public void setPageNum(String pageNum) {
    	this.pageNum = pageNum;
    }
	/**
     * @return the totalRecords
     */
    public String getTotalRecords() {
    	return totalRecords;
    }
	/**
     * @param totalRecords the totalRecords to set
     */
    public void setTotalRecords(String totalRecords) {
    	this.totalRecords = totalRecords;
    }
	

}
