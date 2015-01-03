package com.daxia.core.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.common.Log;
import com.daxia.core.common.LogModule;
import com.daxia.core.common.Module;
import com.daxia.core.dto.JsonResultDTO;
import com.daxia.core.dto.SystemConfigDTO;
import com.daxia.core.service.SystemConfigService;
import com.daxia.core.support.Page;
import com.daxia.core.web.controller.BaseController;

/**
 * 以Admin开头的Controller，都是只为管理后台提供服务的
 */
@Module(name=LogModule.SystemConfig)
@Controller
@RequestMapping(value = "/admin/systemConfig", produces="text/html;charset=UTF-8") // produces是为了解决@ResponseBody返回乱码
public class AdminSystemConfigController extends BaseController {

	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private SystemConfigService systemConfigService;
	
	/**
	 * 详情页面。用来获得单个对象的具体信息的
	 * map是用来传值 ，在页面通过${key}的方式就能得到值 。比如map里put("systemConfig", dto)，在页面取就是${systemConfig.id}这种。
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			SystemConfigDTO dto = systemConfigService.load(id);
			map.put("systemConfig", dto);
		}
		return "admin/core/systemConfig/systemConfig_detail";
	}
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@ResponseBody
	@RequestMapping(value = "/save")
	@PreAuthorize("(hasRole('system.systemConfig.update') and #dto.id != null) or (hasRole('system.systemConfig.add') and #dto.id == null)")
	@Log(operation = "保存系统参数")
	public String save(SystemConfigDTO dto, JsonResultDTO resultDTO) throws Exception {
		if (dto.getId() == null) {
			systemConfigService.create(dto);
		} else {
			systemConfigService.updateAllFields(dto);
		}
		return okAndClose(resultDTO);
	}

    @Log(operation = "删除系统参数")
    @PreAuthorize("hasRole('system.systemConfig.delete')")
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids, JsonResultDTO resultDTO) throws IOException {
		systemConfigService.deleteByIds(ids);
		return okAndRefresh(resultDTO);
	}
	
	@RequestMapping(value = "/list")
	@PreAuthorize("hasRole('system.systemConfig.list')")
	public String list(SystemConfigDTO dto, Map<String, Object> map, Page page) {
		List<SystemConfigDTO> dtos = systemConfigService.find(dto, page);
		// 这个数据是用来展示的
		map.put("systemConfigs", dtos);
		// 这个数据是保存查询条件的
		map.put("systemConfig", dto);
		return "admin/core/systemConfig/systemConfig_list";
	}
}
