package com.daxia.core.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.daxia.core.common.Log;
import com.daxia.core.common.StatusCode;
import com.daxia.core.dto.AuthorityDTO;
import com.daxia.core.dto.JsonResultDTO;
import com.daxia.core.service.AuthorityService;
import com.daxia.core.support.Page;
import com.daxia.core.util.JsonUtils;
import com.daxia.core.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/authority", produces="text/html;charset=UTF-8")
public class AdminAuthorityController extends BaseController {

	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private AuthorityService authorityService;
	
	/**
	 * map是用来传值 ，在页面通过${key}的方式就能得到值 。比如map里put("authority", dto)，在页面取就是${authority.id}这种。
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			AuthorityDTO dto = authorityService.load(id);
			map.put("authority", dto);
		}
		return "admin/core/authority/authority_detail";
	}
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@ResponseBody
	@RequestMapping(value = "/save")
	@Log(operation = "保存权限")
	public String save(HttpServletRequest request, HttpServletResponse response, AuthorityDTO dto, JsonResultDTO resultDTO) throws IOException {
		String parentAuthorityName = request.getParameter("parentAuthority.name");
		if (StringUtils.isBlank(parentAuthorityName)) {
			if (dto.getParentAuthority() != null) {
				dto.getParentAuthority().setId(null);
			}
		}
		if (dto.getId() == null) {
			authorityService.save(dto);
		} else {
			authorityService.update(dto);
		}
		return okAndRefresh(resultDTO);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public void delete(HttpServletResponse response, Long[] ids, JsonResultDTO resultDTO) throws IOException {
		authorityService.deleteByIds(ids);
		
		resultDTO.setStatusCode(StatusCode.OK.code());
		resultDTO.setMessage("操作成功");
		// 如果是删除，不需要关闭当前tab
		// resultDTO.setCallbackType(CallbackType.closeCurrent);
		writeJsonResult(response, resultDTO);
	}
	
	@RequestMapping(value = "/list")
	public String list(AuthorityDTO dto, Map<String, Object> map, Page page) {
		List<AuthorityDTO> dtos = authorityService.list(dto, null);
		map.put("authorities", dtos);
		map.put("authority", dto);
		return "admin/core/authority/authority_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchAuthority")
	public void searchAuthority(HttpServletResponse response, String name) throws IOException {
		
		List<AuthorityDTO> authorityDTOs = authorityService.findLikeAuthorities(name);
		
		/*List<String> list = new ArrayList<String>();
		for (AuthorityDTO a : authorityDTOs) {
	        list.add(a.getName());
        }*/
		
		PropertyFilter filter = JsonUtils.setFiler(new String[] {"parentAuthority", "roles", "children"});
		writeJsonArray(response, authorityDTOs, filter);
	}
	
}
