package com.daxia.core.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daxia.core.common.MenuLevels;
import com.daxia.core.dto.MenuDTO;
import com.daxia.core.service.MenuService;
import com.daxia.core.util.SpringSecurityUtils;

@Controller
public class IndexController extends BaseController {

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/admin/index")
	public String adminIndex(Map<String, Object> map) {
		MenuDTO dto = new MenuDTO();
		dto.setLevel(MenuLevels.Level0.level());
		map.put("menus", menuService.listForAdminPage());
		map.put("user", SpringSecurityUtils.getCurrentUser());
		return "admin/index";
	}
	
	@RequestMapping(value = "/admin/login")
	public String adminLogin(Map<String, Object> map) {
		return "admin/login";
	}
	
	@RequestMapping(value = "/admin/form")
	public String form() {
		return "admin/form";
	}
	
	@Autowired
	private MenuService menuService;
}	
