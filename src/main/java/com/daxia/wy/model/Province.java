package com.daxia.wy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.daxia.core.model.BaseModel;


/**
 * Province
 */
@Entity // 标识这是一个与数据库映射的实体
@Table(name = "province") // 指定与数据库映射的表名
public class Province extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 名称
	 */
	@Column(name = "name")
    private String name;
	private String letter;
	private String fullLetter;
	
	/** 
	 * 获取值：id
	 */
	public Long getId() {
    	return id;
    }
	
	/** 
	 * 设置值：id
	 */    
    public void setId(Long id) {
    	this.id = id;
    }
	
	/** 
	 * 获取值：名称
	 */
	public String getName() {
    	return name;
    }
	
	/** 
	 * 设置值：名称
	 */    
    public void setName(String name) {
    	this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Province other = (Province) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
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
