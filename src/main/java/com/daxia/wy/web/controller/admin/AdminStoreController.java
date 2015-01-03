package com.daxia.wy.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.common.Log;
import com.daxia.core.dto.JsonResultDTO;
import com.daxia.core.support.Page;
import com.daxia.core.web.controller.BaseController;
import com.daxia.wy.dto.StoreDTO;
import com.daxia.wy.service.StoreService;

/**
 * 以Admin开头的Controller，都是只为管理后台提供服务的
 */
// @Module(name = "商家模块")
@Controller
@RequestMapping(value = "/admin/store", produces="text/html;charset=UTF-8") // produces是为了解决@ResponseBody返回乱码
public class AdminStoreController extends BaseController {

	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private StoreService storeService;
	
	/**
	 * 详情页面。用来获得单个对象的具体信息的
	 * map是用来传值 ，在页面通过${key}的方式就能得到值 。比如map里put("store", dto)，在页面取就是${store.id}这种。
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			StoreDTO dto = storeService.load(id);
			map.put("store", dto);
		}
		return "admin/store/store_detail";
	}
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@Log(operation = "保存商家") 
	@ResponseBody
	@RequestMapping(value = "/save")
	@PreAuthorize("(hasRole('store.update') and #dto.id != null) or (hasRole('store.add') and #dto.id == null)")
	public String save(StoreDTO dto, JsonResultDTO resultDTO) throws Exception {
		if (dto.getId() == null) {
			storeService.create(dto);
		} else {
			storeService.updateAllFields(dto);
		}
		return okAndClose(resultDTO);
	}

    @Log(operation = "删除商家")
    @PreAuthorize("hasRole('store.delete')")
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids, JsonResultDTO resultDTO) throws IOException {
		storeService.deleteByIds(ids);
		return okAndRefresh(resultDTO);
	}
	
	@RequestMapping(value = "/list")
	@PreAuthorize("hasRole('store.list')")
	public String list(StoreDTO dto, Map<String, Object> map, Page page) {
		List<StoreDTO> dtos = storeService.find(dto, page);
		// 这个数据是用来展示的
		map.put("stores", dtos);
		// 这个数据是保存查询条件的
		map.put("store", dto);
		return "admin/store/store_list";
	}
	
	@ResponseBody
    @RequestMapping("getStoreSelect")
    public String getStoreSelect(String submitName) throws Exception {
		/*User manager = SpringSecurityUtils.getCurrentUser();
		StringBuilder outBuilder = new StringBuilder();
		if (manager.isHead()) {
			List<StoreDTO> allStores = storeService.find(new StoreDTO(), null);
			outBuilder.append("<select name='").append(submitName).append("'>");
			for (StoreDTO a : allStores) {
	            outBuilder.append("<option value='").append(a.getId()).append("'>").append(a.getName()).append("</option>");
            }
			outBuilder.append("</select>");
		} else {
			outBuilder.append("<input class='required' type='hidden' name='").append(submitName).append("' value='").append(manager.getStore().getId()).append("' size='18' />");
			outBuilder.append("<input type='text' readonly=readonly value='").append(manager.getStore().getName()).append("' size='18' />");
		}
	    return outBuilder.toString();*/
	    return null;
    }
}
