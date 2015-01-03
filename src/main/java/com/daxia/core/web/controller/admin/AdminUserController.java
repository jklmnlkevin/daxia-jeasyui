package com.daxia.core.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.common.Log;
import com.daxia.core.common.LogModule;
import com.daxia.core.common.Module;
import com.daxia.core.common.UserType;
import com.daxia.core.dto.JsonResultDTO;
import com.daxia.core.dto.RoleDTO;
import com.daxia.core.dto.UserDTO;
import com.daxia.core.service.RoleService;
import com.daxia.core.service.UserService;
import com.daxia.core.support.Page;
import com.daxia.core.util.ValidationUtils;
import com.daxia.core.web.controller.BaseController;
import com.daxia.wy.dto.CityDTO;
import com.daxia.wy.dto.DistrictDTO;
import com.daxia.wy.dto.ProvinceDTO;

/**
 * 以Admin开头的Controller，都是只为管理后台提供服务的
 */
@Module(name = LogModule.User)
@Controller
@RequestMapping(value = "/admin/user", produces="text/html;charset=UTF-8") // produces是为了解决@ResponseBody返回乱码
public class AdminUserController extends BaseController {

	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	/**
	 * 详情页面。用来获得单个对象的具体信息的
	 * map是用来传值 ，在页面通过${key}的方式就能得到值 。比如map里put("user", dto)，在页面取就是${user.id}这种。
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id, Integer type) {
		if (id != null) {
			UserDTO dto = userService.load(id);
			map.put("user", dto);
			if (dto.getProvince() != null) {
				CityDTO cityDTO = new CityDTO();
				cityDTO.setProvince(dto.getProvince());
				map.put("cities", cityService.find(cityDTO, null));
			}
			if (dto.getCity() != null) {
				DistrictDTO districtDTO = new DistrictDTO();
				districtDTO.setCity(dto.getCity());
				map.put("districts", districtService.find(districtDTO, null));
			}
		} else {
			map.put("type", type);
		}
		
		map.put("userTypes", UserType.values());
		List<ProvinceDTO> provinces = provinceService.find(new ProvinceDTO(), null);
		map.put("provinces", provinces);
		return "admin/core/user/user_detail";
	}
	
	@RequestMapping(value = "/detailRole")
	public String detailRole(Map<String, Object> map, Long id) {
		if (id != null) {
			UserDTO dto = userService.load(id);
			map.put("user", dto);
		}
		
		map.put("roles", roleService.list(new RoleDTO(), null));
		return "admin/core/user/user_detailRole";
	}
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@Log(operation = "保存用户") 
	@ResponseBody
	@RequestMapping(value = "/save")
	@PreAuthorize("(hasRole('user.update') and #dto.id != null) or (hasRole('user.add') and #dto.id == null)")
	public String save(UserDTO dto, JsonResultDTO resultDTO) throws Exception {
		if (dto.getId() == null) {
			userService.save(dto);
		} else {
		    UserDTO model = userService.load(dto.getId());
		    // 公共字段
		    model.setUserType(dto.getUserType());
		    model.setTelephone(dto.getTelephone());
		    model.setProvince(dto.getProvince());
		    model.setCity(dto.getCity());
		    model.setDistrict(dto.getDistrict());
		    model.setCommunity(dto.getCommunity());
		    
		    // 普通用户的话有更多字段
		    model.setIdCard(dto.getIdCard());
		    model.setLatitude(dto.getLatitude());
		    model.setLongitude(dto.getLongitude());
		    model.setBuilding(dto.getBuilding());
		    model.setDoorplate(dto.getDoorplate());
		    model.setOwner(dto.getOwner());
		    model.setOwnerIdCard(dto.getOwnerIdCard());
		    model.setAuthenticated(dto.isAuthenticated());
		    model.setReceiveNotice(dto.isReceiveNotice());
		    
		    userService.updateAllFields(model);
		}
		return okAndClose(resultDTO);
	}
	
	@Log(operation = "保存用户角色") 
	@ResponseBody
	@PreAuthorize("hasRole('user.updateRole')")
	@RequestMapping(value = "/saveRole")
	public String saveRole(Long id, Long[] roleIds, JsonResultDTO resultDTO) throws Exception {
		userService.saveRole(id, roleIds);
		return okAndClose(resultDTO);
	}

    @Log(operation = "禁用用户")
    @PreAuthorize("hasRole('user.disable')")
	@ResponseBody
	@RequestMapping(value = "/disable") 
	public String disable(Long[] ids, JsonResultDTO resultDTO) throws IOException {
		userService.updateDisabled(ids);
		return okAndRefresh(resultDTO);
	}
    
    @Log(operation = "解禁用户")
    @PreAuthorize("hasRole('user.active')")
	@ResponseBody
	@RequestMapping(value = "/active") 
	public String active(Long[] ids, JsonResultDTO resultDTO) throws IOException {
		userService.updateActive(ids);
		return okAndRefresh(resultDTO);
	}
	
	@RequestMapping(value = "/list")
	@PreAuthorize("hasRole('user.list')")
	public String list(UserDTO dto, Map<String, Object> map, Page page) {
		List<UserDTO> dtos = userService.list(dto, page);
		// 这个数据是用来展示的
		map.put("users", dtos);
		// 这个数据是保存查询条件的
		map.put("user", dto);
		map.put("userTypes", UserType.values());
		return "admin/core/user/user_list";
	}
	
	@ResponseBody
	@RequestMapping("autocomplete")
	public String autocomplete(String term) {
		UserDTO dto = new UserDTO();
		//dto.setTerm(term);
		Page page = new Page();
		page.setNumPerPage(50);
		List<UserDTO> users = userService.list(dto, page);
		return toJson(users);
	}
	
    @RequestMapping("resetPassword")
    public String resetPassword(Map<String, Object> map, Long id) throws Exception {
		if (id != null) {
			UserDTO dto = userService.load(id);
			map.put("user", dto);
		}
		return "admin/core/user/user_resetPassword";
	}
	
	@Log(operation = "重置密码")
	@ResponseBody
	@PreAuthorize("hasRole('user.resetPassword')")
    @RequestMapping("doResetPassword")
    public String doResetPassword(Long id, String password, String repassword, JsonResultDTO resultDTO) throws Exception {
		ValidationUtils.assertTrue(StringUtils.isNotBlank(password), "密码不能为空");
		password = password.trim();
		ValidationUtils.assertTrue(password.length() >= 6, "密码不能少于6位数");
		ValidationUtils.assertTrue(password.equals(repassword), "两次密码输入不一致");

		userService.updatePassword(id, password);
		
		return okAndClose(resultDTO);
	}
	
	/**
	 * 用于查找带回的
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("search")
	public String search(UserDTO dto, Map<String, Object> map, Page page) {
		List<UserDTO> dtos = userService.list(dto, page);
		// 这个数据是用来展示的
		map.put("users", dtos);
		// 这个数据是保存查询条件的
		map.put("user", dto);
		return "admin/core/user/user_search";
	}
    
    @Log(operation = "删除用户") 
    @ResponseBody
    @RequestMapping(value = "/delete")
    @PreAuthorize("hasRole('user.delete')")
    public String delete(Long[] ids, JsonResultDTO resultDTO) throws Exception {
        userService.deleteByIds(ids);
        return okAndRefresh(resultDTO);
    }
	
}