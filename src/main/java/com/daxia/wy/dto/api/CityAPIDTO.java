package com.daxia.wy.dto.api;

public class CityAPIDTO {
    private String id;    
    private String name;
    private String hot;
    private String letter;
    private String fullLetter;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getFullLetter() {
        return fullLetter;
    }

    public void setFullLetter(String fullLetter) {
        this.fullLetter = fullLetter;
    }
    
}
