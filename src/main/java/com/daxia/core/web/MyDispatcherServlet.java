package com.daxia.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.DispatcherServlet;

import com.daxia.core.common.StatusCode;
import com.daxia.core.dto.JsonResultDTO;
import com.daxia.core.util.JsonUtils;

public class MyDispatcherServlet extends DispatcherServlet {
    private static final long serialVersionUID = 1L;
    
    private static Logger logger = Logger.getLogger(MyDispatcherServlet.class);
    private static Logger timeLogger = Logger.getLogger("timeLogger");
    
    
    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	long start = System.currentTimeMillis();
        try {
            super.doService(request, response);
        } catch (Exception e) {
            logger.error(e, e);
            e.printStackTrace();
            if (request.getServletPath().startsWith("/admin/")) {
                // 后台管理的
                JsonResultDTO dto = new JsonResultDTO();
                dto.setStatusCode(StatusCode.ERROR.code());
                dto.setMessage(convertMessage(e));
                response.getWriter().write(JsonUtils.toJson(dto));
            } else {
            	if (isAjax(request)) {
            		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            		JsonResultDTO dto = new JsonResultDTO();
            		dto.setStatusCode(StatusCode.ERROR.code());
            		dto.setMessage(convertMessage(e));
            		response.getWriter().write(JsonUtils.toJson(dto));
            	} else {
            		throw e;
            	}
            }
            
        } finally {
        	if (timeLogger.isDebugEnabled()) {
        		timeLogger.debug("花费[" + (System.currentTimeMillis() - start) + "]处理请求" + request.getServletPath());
            }
        }

    }
    
    private String convertMessage(Exception e) {
        if (e instanceof DataAccessException) {
            return "数据库访问异常";
        } else if (e instanceof NullPointerException) {
            return "空指针异常";
        } else if ("Access is denied".equals(e.getMessage())){
        	return "没有权限";
        } else {
            return e.getMessage();
        }
    }
    
    private boolean isAjax(HttpServletRequest request) {
    	String requestType = request.getHeader("X-Requested-With");  
    	return StringUtils.isNotBlank(requestType);
    }
}

