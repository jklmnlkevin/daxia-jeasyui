package com.daxia.wy.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.common.Log;
import com.daxia.core.dto.JsonResultDTO;
import com.daxia.core.support.Page;
import com.daxia.core.web.controller.BaseController;
import com.daxia.wy.common.ConvenienceType;
import com.daxia.wy.dto.ConvenienceDTO;
import com.daxia.wy.service.ConvenienceService;

/**
 * 以Admin开头的Controller，都是只为管理后台提供服务的
 */
// @Module(name = "便民服务模块")
@Controller
@RequestMapping(value = "/admin/convenience", produces="text/html;charset=UTF-8") // produces是为了解决@ResponseBody返回乱码
public class AdminConvenienceController extends BaseController {

	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private ConvenienceService convenienceService;
	
	/**
	 * 详情页面。用来获得单个对象的具体信息的
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			ConvenienceDTO dto = convenienceService.load(id);
			map.put("n", dto);
		}
		map.put("convenienceTypes", ConvenienceType.values());
		return "admin/convenience/convenience_detail";
	}
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@Log(operation = "保存便民服务") 
	@ResponseBody
	@RequestMapping(value = "/save")
	@PreAuthorize("(hasRole('convenience.update') and #dto.id != null) or (hasRole('convenience.add') and #dto.id == null)")
	public String save(HttpServletRequest request, ConvenienceDTO dto, JsonResultDTO resultDTO) throws Exception {
	    String imageFileName = saveFileAndReturnFileName(request, "imageFile");
	    if (StringUtils.isNotBlank(imageFileName)) {
	        dto.setImage(imageFileName);
	    }
		if (dto.getId() == null) {
			convenienceService.create(dto);
		} else {
			convenienceService.updateAllFields(dto);
		}
		return okAndClose(resultDTO);
	}

    @Log(operation = "删除便民服务")
    @PreAuthorize("hasRole('convenience.delete')")
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids, JsonResultDTO resultDTO) throws IOException {
		convenienceService.deleteByIds(ids);
		return okAndRefresh(resultDTO);
	}
	
	@RequestMapping(value = "/list")
	@PreAuthorize("hasRole('convenience.list')")
	public String list(ConvenienceDTO dto, Map<String, Object> map, Page page) {
		List<ConvenienceDTO> dtos = convenienceService.find(dto, page);
		// 这个数据是用来展示的
		map.put("list", dtos);
		// 这个数据是保存查询条件的
		map.put("dto", dto);
		map.put("convenienceTypes", ConvenienceType.values());
		return "admin/convenience/convenience_list";
	}
	
	/**
	 * 用于查找带回的
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("search")
	public String search(ConvenienceDTO dto, Map<String, Object> map, Page page) {
		List<ConvenienceDTO> dtos = convenienceService.find(dto, page);
		// 这个数据是用来展示的
		map.put("list", dtos);
		// 这个数据是保存查询条件的
		map.put("dto", dto);
		return "admin/convenience/convenience_search";
	}
}
