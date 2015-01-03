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
import com.daxia.wy.dto.CategoryDTO;
import com.daxia.wy.service.CategoryService;

/**
 * 以Admin开头的Controller，都是只为管理后台提供服务的
 */
// @Module(name = "分类模块")
@Controller
@RequestMapping(value = "/admin/category", produces="text/html;charset=UTF-8") // produces是为了解决@ResponseBody返回乱码
public class AdminCategoryController extends BaseController {

	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 详情页面。用来获得单个对象的具体信息的
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			CategoryDTO dto = categoryService.load(id);
			map.put("category", dto);
		}
		return "admin/category/category_detail";
	}
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@Log(operation = "保存分类") 
	@ResponseBody
	@RequestMapping(value = "/save")
	@PreAuthorize("(hasRole('category.update') and #dto.id != null) or (hasRole('category.add') and #dto.id == null)")
	public String save(CategoryDTO dto, JsonResultDTO resultDTO) throws Exception {
		if (dto.getId() == null) {
			categoryService.create(dto);
		} else {
			categoryService.updateAllFields(dto);
		}
		return okAndClose(resultDTO);
	}

    @Log(operation = "删除分类")
    @PreAuthorize("hasRole('category.delete')")
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids, JsonResultDTO resultDTO) throws IOException {
		categoryService.deleteByIds(ids);
		return okAndRefresh(resultDTO);
	}
	
	@RequestMapping(value = "/list")
	@PreAuthorize("hasRole('category.list')")
	public String list(CategoryDTO dto, Map<String, Object> map, Page page) {
		List<CategoryDTO> dtos = categoryService.find(dto, page);
		// 这个数据是用来展示的
		map.put("categorys", dtos);
		// 这个数据是保存查询条件的
		map.put("category", dto);
		return "admin/category/category_list";
	}
	
	/**
	 * 用于查找带回的
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("search")
	public String search(CategoryDTO dto, Map<String, Object> map, Page page) {
		List<CategoryDTO> dtos = categoryService.find(dto, page);
		// 这个数据是用来展示的
		map.put("categorys", dtos);
		// 这个数据是保存查询条件的
		map.put("category", dto);
		return "admin/category/category_search";
	}
}
