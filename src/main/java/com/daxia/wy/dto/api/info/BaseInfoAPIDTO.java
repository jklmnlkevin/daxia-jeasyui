package com.daxia.wy.dto.api.info;

public class BaseInfoAPIDTO {
    private String totalPages = "";
    private String pageNum = "";
    private String numPerPage = "";
    private String totalRecords = "";
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
     * @return the numPerPage
     */
    public String getNumPerPage() {
        return numPerPage;
    }
    /**
     * @param numPerPage the numPerPage to set
     */
    public void setNumPerPage(String numPerPage) {
        this.numPerPage = numPerPage;
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
