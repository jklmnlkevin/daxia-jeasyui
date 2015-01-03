package com.daxia.core.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.common.Log;
import com.daxia.core.common.LogModule;
import com.daxia.core.common.MenuLevels;
import com.daxia.core.common.Module;
import com.daxia.core.common.StatusCode;
import com.daxia.core.dto.JsonResultDTO;
import com.daxia.core.dto.MenuDTO;
import com.daxia.core.service.MenuService;
import com.daxia.core.support.Page;
import com.daxia.core.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/menu", produces="text/html;charset=UTF-8")
@Module(name = LogModule.Menu)
public class AdminMenuController extends BaseController {

	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private MenuService menuService;
	
	/**
	 * map是用来传值 ，在页面通过${key}的方式就能得到值 。比如map里put("menu", dto)，在页面取就是${menu.id}这种。
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			MenuDTO dto = menuService.load(id);
			map.put("menu", dto);
			if (dto.getLevel() == MenuLevels.Level2.level()) {
				// load出所有的一级菜单
				map.put("parentLevelMenus", menuService.loadByLevel(MenuLevels.Level1));
			} else if (dto.getLevel() == MenuLevels.Level1.level()) {
				map.put("parentLevelMenus", menuService.loadByLevel(MenuLevels.Level0));
			}
		}
		return "admin/core/menu/menu_detail";
	}
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@ResponseBody
	@RequestMapping(value = "/save")
	@Log(operation = "保存菜单")
	@PreAuthorize("(hasRole('system.menu.update') and #dto.id != null) or (hasRole('system.menu.add') and #dto.id == null)")
	public String save(HttpServletResponse response, MenuDTO dto, JsonResultDTO resultDTO) throws IOException {
		if (StringUtils.isBlank(dto.getAuthority().getName())) {
			dto.setAuthority(null);
		}
		if (dto.getId() == null) {
			menuService.save(dto);
		} else {
			menuService.updateAllField(dto);
		}
		return okAndRefresh(resultDTO);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete") 
	@Log(operation = "删除菜单")
	@PreAuthorize("hasRole('system.menu.delete')")
	public void delete(HttpServletResponse response, Long[] ids, JsonResultDTO resultDTO) throws IOException {
		menuService.deleteByIds(ids);
		
		resultDTO.setStatusCode(StatusCode.OK.code());
		resultDTO.setMessage("操作成功");
		// 如果是删除，不需要关闭当前tab
		// resultDTO.setCallbackType(CallbackType.closeCurrent);
		writeJsonResult(response, resultDTO);
	}
	
	@RequestMapping(value = "/list")
	public String list(MenuDTO dto, Map<String, Object> map, Page page) {
		List<MenuDTO> dtos = menuService.list(dto, null);
		map.put("menus", dtos);
		map.put("menu", dto);
		return "admin/core/menu/menu_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/find")
	public void find() {
		
	}

	@ResponseBody
    @RequestMapping(value = "/findParent")
    public String searchChildren(HttpServletResponse response, Integer level) throws IOException {
        StringBuilder builder = new StringBuilder();
        MenuDTO dto = new MenuDTO();
        dto.setLevel(level - 1);
        List<MenuDTO> menus = menuService.list(dto, null);
        builder.append("[");
        for (MenuDTO menu : menus) {
            builder.append("[\"" + menu.getId() + "\", \"" + menu.getName() + "\"],");
        }
        if (builder.toString().endsWith(",")) {
            builder.delete(builder.length() - 1, builder.length());
        }
        builder.append("]");
        writeJsonString(response, builder.toString());
        return null;
    }
}
