package com.daxia.core.web.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.common.Log;
import com.daxia.core.common.LogModule;
import com.daxia.core.common.Module;
import com.daxia.core.dto.AuthorityDTO;
import com.daxia.core.dto.JsonResultDTO;
import com.daxia.core.dto.RoleDTO;
import com.daxia.core.model.Authority;
import com.daxia.core.service.AuthorityService;
import com.daxia.core.service.RoleService;
import com.daxia.core.support.Page;
import com.daxia.core.web.controller.BaseController;

@Module(name = LogModule.Role)
@Controller
@RequestMapping(value = "/admin/role", produces="text/html;charset=UTF-8")
public class AdminRoleController extends BaseController {

	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private RoleService roleService;
	
	/**
	 * map是用来传值 ，在页面通过${key}的方式就能得到值 。比如map里put("role", dto)，在页面取就是${role.id}这种。
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		List<AuthorityDTO> authorities = authorityService.listTops();
		map.put("authorities", authorities);
		
		if (id != null) {
			RoleDTO dto = roleService.load(id);
			if (dto.getAuthorities() != null) {
				List<String> list = new ArrayList<String>();
				for (Authority a : dto.getAuthorities()) {
	                list.add(a.getName());
	                for (AuthorityDTO aDTO : authorities) {
	                    if (aDTO.getName().equals(a.getName())) {
	                    	aDTO.setContains(true);
	                    	continue;
	                    } else {
	                    	processChildren(aDTO.getChildren(), a.getName());
	                    }
                    }
                }
				dto.setAuthorityNames(list);
			}
			map.put("role", dto);
		}
		return "admin/core/role/role_detail";
	}
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@ResponseBody
	@RequestMapping(value = "/save")
	@Log(operation = "保存角色")
	@PreAuthorize("(hasRole('system.authority.update') and #dto.id != null) or (hasRole('system.authority.add') and #dto.id == null)")
	public String save(HttpServletResponse response, RoleDTO dto, JsonResultDTO resultDTO) throws IOException {
		if (dto.getId() == null) {
			roleService.save(dto);
		} else {
			roleService.update(dto);
		}
		return okAndRefresh(resultDTO);
	}
	
	@ResponseBody
	@PreAuthorize("hasRole('system.authority.delete')")
	@RequestMapping(value = "/delete") 
	public String delete(HttpServletResponse response, Long[] ids, JsonResultDTO resultDTO) throws IOException {
		roleService.deleteByIds(ids);
		
		// 如果是删除，不需要关闭当前tab
		// resultDTO.setCallbackType(CallbackType.closeCurrent);
		return okAndRefresh(resultDTO);
	}
	
	@RequestMapping(value = "/list")
	public String list(RoleDTO dto, Map<String, Object> map, Page page) {
		List<RoleDTO> dtos = roleService.list(dto, page);
		map.put("roles", dtos);
		map.put("role", dto);
		return "admin/core/role/role_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/find")
	public void find() {
		
	}

	private void processChildren(List<Authority> children, String name) {
		if (CollectionUtils.isEmpty(children)) {
			return;
		}
		for (Authority child : children) {
			if (child.getName().equals(name)) {
				child.setContains(true);
			} else {
				processChildren(child.getChildren(), name);
			}
		}
    }
	
	@Autowired
	private AuthorityService authorityService;
}
