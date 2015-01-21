package com.daxia.core.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daxia.core.common.SystemConfigType;
import com.daxia.core.dao.SystemConfigDAO;
import com.daxia.core.dto.SystemConfigDTO;
import com.daxia.core.model.SystemConfig;

@Component
public class SystemConfigCache {
    
    @Autowired
    private SystemConfigDAO systemConfigDAO;
    
    private boolean init = false;
    private Map<String, String> map = new ConcurrentHashMap<String, String>();
    
    private void init() {
        List<SystemConfig> configs = systemConfigDAO.find(new SystemConfigDTO(), null);
        for (SystemConfig sc : configs) {
            map.put(sc.getKey(), sc.getValue());
            System.out.println("init.");
        }
    }
    
    public String get(SystemConfigType type) {
        if (!init) {
            init();
            init = true;
        }
        return map.get(type.value());
    }
}
