package com.daxia.wy.web.controller.m;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.daxia.core.common.SystemConfigType;
import com.daxia.core.model.BaseModel;
import com.daxia.core.service.SystemConfigService;
import com.daxia.core.service.UserService;
import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.core.util.JsonUtils;
import com.daxia.wy.dto.api.MobileApiOutput;
import com.daxia.wy.dto.api.info.BaseInfoAPIDTO;
import com.daxia.wy.service.CityService;
import com.daxia.wy.service.DistrictService;
import com.daxia.wy.service.NoticeService;
import com.daxia.wy.service.ProvinceService;
import com.daxia.wy.service.PushService;
import com.google.common.collect.Lists;

/**
 * 为手机提供服务的
 */
public abstract class MBaseController {
    @Autowired
    protected PushService pushService;
    
	protected UserService userService;
	@Autowired
	protected NoticeService noticeService;
	@Autowired
	protected ProvinceService provinceService;
	@Autowired
	protected CityService cityService;
	@Autowired
	protected DistrictService districtService;
	@Autowired
	protected SystemConfigService systemConfigService;
	
	protected String toJson(Object obj) {
	    MobileApiOutput output = new MobileApiOutput();
	    output.setData(obj);
		return JsonUtils.toJson(output);
	}

	@SuppressWarnings({ "rawtypes"})
    protected String toApiOutput(List<? extends BaseModel> dtos, Class apiDTO, Class infoAPIDTO) throws Exception {
	    return toApiOutput(dtos, apiDTO, infoAPIDTO, null);
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected String toApiOutput(List<? extends BaseModel> dtos, Class apiDTO, Class infoAPIDTO, Page page) throws Exception {
	    List apidtos = Lists.newArrayList();
        
        for (BaseModel d : dtos) {
            apidtos.add(BeanMapper.map(d, apiDTO));
        }
        
        Class cls = infoAPIDTO;  
        Class[] paramTypes = { List.class };  
        Constructor con = cls.getConstructor(paramTypes);  
        BaseInfoAPIDTO infos = (BaseInfoAPIDTO) con.newInstance(apidtos); 
        
        if (infos != null && page != null) {
            infos.setNumPerPage(integerToString(page.getNumPerPage()));
            infos.setPageNum(integerToString(page.getPageNum()));
            infos.setTotalPages(integerToString(page.getTotalPages()));
            infos.setTotalRecords(integerToString(page.getTotalRecords()));
        }
        return this.toJson(infos);
	}
	
	private String integerToString(Integer integer) {
	    if (integer == null) {
	        return "";
	    } else {
	        return integer.toString();
	    }
	    
	}
	
	@SuppressWarnings({ "rawtypes"})
	protected String toApiOutput(BaseModel dto, Class apiDTO, Class infoAPIDTO) throws Exception {
	    List<BaseModel> list = Lists.newArrayList();
	    list.add(dto);
	    return toApiOutput(list, apiDTO, infoAPIDTO);
    }

	protected String getCtx(HttpServletRequest request) {
	    return request.getSession().getAttribute("ctx").toString();
	}
	
	protected String saveFileAndReturnFileName(HttpServletRequest request, String formInputName) throws IOException {
        MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;  
        MultipartFile mf = mhs.getFile(formInputName);
        String fileName = null; 
        if (!mf.isEmpty()) {
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
