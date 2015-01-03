package com.daxia.core.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;



/**
 * 
 * 
 * Aug 30, 2012
 *
 */
public abstract class BaseModel {
   
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
}
