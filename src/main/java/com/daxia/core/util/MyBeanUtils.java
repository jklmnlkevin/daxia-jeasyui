package com.daxia.core.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class MyBeanUtils extends BeanUtils {

    public MyBeanUtils() {
    }

    static {
        java.util.Date defaultValue = null;
        DateConverter converter = new DateConverter(defaultValue);
        ConvertUtils.register(converter, java.util.Date.class);
    }

    public static void copyProperties(Object target, Object source) throws InvocationTargetException,
            IllegalAccessException {
        // 支持对日期copy
        org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);

    }
}
