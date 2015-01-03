package com.daxia.core.support;

public class MPage extends Page {
    private Integer numPerPage = 10;

    public Integer getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(Integer numPerPage) {
        this.numPerPage = numPerPage;
    }

    public Integer getTotalPages() {
        int totalPages = 0;
        if (numPerPage == null || getTotalRecords() == null) {
            return 0;
        }
        if (getTotalRecords() <= numPerPage) {
            return 1;
        }
        totalPages = getTotalRecords() / numPerPage;
        if (getTotalRecords() % numPerPage != 0) {
            totalPages += 1;
        }
        return totalPages;
    }
}
