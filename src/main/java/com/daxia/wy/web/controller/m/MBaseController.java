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
import com.daxia.wy.service.AdviseReplyService;
import com.daxia.wy.service.AdviseService;
import com.daxia.wy.service.BuildingService;
import com.daxia.wy.service.CategoryService;
import com.daxia.wy.service.CityService;
import com.daxia.wy.service.CommunityAddApplyService;
import com.daxia.wy.service.CommunityService;
import com.daxia.wy.service.ConvenienceService;
import com.daxia.wy.service.DistrictService;
import com.daxia.wy.service.DoorplateService;
import com.daxia.wy.service.FeeItemService;
import com.daxia.wy.service.HouseKeepingService;
import com.daxia.wy.service.NoticeReplyService;
import com.daxia.wy.service.NoticeService;
import com.daxia.wy.service.OrderService;
import com.daxia.wy.service.PayRecordService;
import com.daxia.wy.service.ProductService;
import com.daxia.wy.service.ProvinceService;
import com.daxia.wy.service.PushService;
import com.daxia.wy.service.QuestionService;
import com.daxia.wy.service.RepairHistoryService;
import com.daxia.wy.service.RepairReplyService;
import com.daxia.wy.service.RepairService;
import com.daxia.wy.service.ServiceTypeService;
import com.daxia.wy.service.SystemMessageService;
import com.daxia.wy.service.TopicReplyService;
import com.daxia.wy.service.TopicService;
import com.google.common.collect.Lists;

/**
 * 为手机提供服务的
 */
public abstract class MBaseController {
    @Autowired
    protected ServiceTypeService serviceTypeService;
    @Autowired
    protected HouseKeepingService houseKeepingService;
    @Autowired
    protected OrderService orderService;
    @Autowired
    protected ProductService productService;
    @Autowired
    protected CategoryService categoryService;
    @Autowired
    protected FeeItemService feeItemService;
    @Autowired
    protected PayRecordService payRecordService;
    @Autowired
    protected SystemMessageService systemMessageService;
    @Autowired
    protected PushService pushService;
    @Autowired
    protected ConvenienceService convenienceService;
    @Autowired
    protected AdviseReplyService adviseReplyService;
    @Autowired
    protected AdviseService adviseService;
    @Autowired
    protected RepairHistoryService repairHistoryService;
    @Autowired
    protected TopicService topicService;
    @Autowired
    protected TopicReplyService topicReplyService;
	@Autowired
	protected UserService userService;
	@Autowired
	protected CommunityService communityService;
	@Autowired
	protected NoticeService noticeService;
	@Autowired
	protected ProvinceService provinceService;
	@Autowired
	protected CityService cityService;
	@Autowired
	protected DistrictService districtService;
	@Autowired
	protected BuildingService buildingService;
	@Autowired
	protected DoorplateService doorplateService;
	@Autowired
	protected RepairService repairService;
	@Autowired
	protected SystemConfigService systemConfigService;
	@Autowired
	protected QuestionService questionService;
	@Autowired
	protected CommunityAddApplyService communityAddApplyService;
	@Autowired
	protected NoticeReplyService noticeReplyService;
	@Autowired
	protected RepairReplyService repairReplyService;
	
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
