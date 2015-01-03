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
import com.daxia.wy.dto.TopicReplyDTO;
import com.daxia.wy.service.TopicReplyService;

/**
 * 以Admin开头的Controller，都是只为管理后台提供服务的
 */
// @Module(name = "帖子回复模块")
@Controller
@RequestMapping(value = "/admin/topicReply", produces="text/html;charset=UTF-8") // produces是为了解决@ResponseBody返回乱码
public class AdminTopicReplyController extends BaseController {

	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private TopicReplyService topicReplyService;
	
	/**
	 * 详情页面。用来获得单个对象的具体信息的
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			TopicReplyDTO dto = topicReplyService.load(id);
			map.put("n", dto);
		}
		return "admin/topicReply/topicReply_detail";
	}
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@Log(operation = "保存帖子回复") 
	@ResponseBody
	@RequestMapping(value = "/save")
	@PreAuthorize("(hasRole('topicReply.update') and #dto.id != null) or (hasRole('topicReply.add') and #dto.id == null)")
	public String save(TopicReplyDTO dto, JsonResultDTO resultDTO) throws Exception {
		if (dto.getId() == null) {
			topicReplyService.create(dto);
		} else {
			topicReplyService.updateAllFields(dto);
		}
		return okAndClose(resultDTO);
	}

    @Log(operation = "删除帖子回复")
    @PreAuthorize("hasRole('topicReply.delete')")
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids, JsonResultDTO resultDTO) throws IOException {
		topicReplyService.deleteByIds(ids);
		return okAndRefresh(resultDTO);
	}
	
	@RequestMapping(value = "/list")
	@PreAuthorize("hasRole('topicReply.list')")
	public String list(TopicReplyDTO dto, Map<String, Object> map, Page page) {
		List<TopicReplyDTO> dtos = topicReplyService.find(dto, page);
		// 这个数据是用来展示的
		map.put("list", dtos);
		// 这个数据是保存查询条件的
		map.put("dto", dto);
		return "admin/topicReply/topicReply_list";
	}
	
	/**
	 * 用于查找带回的
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("search")
	public String search(TopicReplyDTO dto, Map<String, Object> map, Page page) {
		List<TopicReplyDTO> dtos = topicReplyService.find(dto, page);
		// 这个数据是用来展示的
		map.put("list", dtos);
		// 这个数据是保存查询条件的
		map.put("dto", dto);
		return "admin/topicReply/topicReply_search";
	}
}
