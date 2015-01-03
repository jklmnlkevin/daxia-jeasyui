package com.daxia.wy.dto.api;


public class ServiceTypeAPIDTO extends BaseAPIDTO {
    /**
     * id
     */
    private Long id;
    private String name;
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
