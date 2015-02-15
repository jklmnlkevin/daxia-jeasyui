package com.daxia.core.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.daxia.core.common.CallbackType;
import com.daxia.core.common.StatusCode;
import com.daxia.core.common.SystemConfigType;
import com.daxia.core.dto.JsonResultDTO;
import com.daxia.core.service.SystemConfigService;
import com.daxia.core.util.JsonUtils;
import com.daxia.wy.service.CityService;
import com.daxia.wy.service.DistrictService;
import com.daxia.wy.service.ProvinceService;
import com.daxia.wy.service.PushService;
import com.daxia.wy.service.SMSService;

public class BaseController {
    @Autowired
    protected PushService pushService;
    
	@Autowired
	protected ProvinceService provinceService;
	@Autowired
	protected CityService cityService;
	@Autowired
	protected DistrictService districtService;
	
	@Autowired
	protected SystemConfigService systemConfigService;
	@Autowired
	protected SMSService smsService;
	/**
	 * 返回调用成功的信息，并且【关闭】当前的tab页
	 * @param resultDTO
	 * @return
	 */
	protected String okAndClose(JsonResultDTO resultDTO) {
		resultDTO.setStatusCode(StatusCode.OK.code());
		resultDTO.setMessage("操作成功");
		resultDTO.setCallbackType(CallbackType.closeCurrent);
		return JsonUtils.toJson(resultDTO);
    }
	
	protected String okOnly(JsonResultDTO resultDTO) {
		resultDTO.setStatusCode(StatusCode.OK.code());
		resultDTO.setMessage("操作成功");
		return JsonUtils.toJson(resultDTO);
    }
	
	/**
	 * 返回调用成功的信息，并且【刷新】当前的tab页
	 * @param resultDTO
	 * @return
	 */
	protected String okAndRefresh(JsonResultDTO resultDTO) {
		resultDTO.setStatusCode(StatusCode.OK.code());
		resultDTO.setMessage("操作成功");
		// resultDTO.setCallbackType(CallbackType.);
		return JsonUtils.toJson(resultDTO);
    }

    protected void writeJsonResult(HttpServletResponse response, JsonResultDTO resultDTO) throws IOException {
        response.getWriter().write(JsonUtils.toJson(resultDTO));
        System.out.println("1---" + JsonUtils.toJson(resultDTO));
    }
    
    protected void writeJson(HttpServletResponse response, Object obj) throws IOException {
    	System.out.println("2---" + JsonUtils.toJson(obj));
    	response.getWriter().write(JsonUtils.toJson(obj));
    }

    protected void writeJsonArray(HttpServletResponse response, Object obj) throws IOException {
    	response.getWriter().write(JsonUtils.toJson(obj));
    }
    
    protected void writeJsonArray(HttpServletResponse response, Object obj, PropertyFilter filter) throws IOException {
//    	JSONArray jsonArray = JSONArray.fromObject(obj, jsonConfig);
//    	System.out.println("4---" + jsonArray);
    	response.getWriter().write(JsonUtils.toJson(obj, filter));
    }
    
    protected void writeJsonString(HttpServletResponse response, String string) throws IOException {
    	System.out.println("5---" + string);
    	response.getWriter().write(string);
    }
    
    protected String toJson(Object obj) {
		return JsonUtils.toJson(obj);
	}

    protected String saveFileAndReturnFileName(HttpServletRequest request, String formInputName) throws IOException {
        MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;  
        MultipartFile mf = mhs.getFile(formInputName);
        String fileName = null; 
        if (mf != null && !mf.isEmpty()) {
            fileName = mf.getOriginalFilename();
            fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
            File file = new File(systemConfigService.get(SystemConfigType.ImagePath) + "/" + fileName);
            if (!file.exists()) {
                mf.transferTo(file);
            }
        }
        return fileName;
    }
}
