package com.daxia.wy.web.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daxia.core.common.CallbackType;
import com.daxia.core.common.Log;
import com.daxia.core.dto.JsonResultDTO;
import com.daxia.core.service.UserService;
import com.daxia.core.support.Page;
import com.daxia.core.web.controller.BaseController;
import com.daxia.wy.common.PushCode;
import com.daxia.wy.dto.NoticeDTO;
import com.daxia.wy.dto.PushDTO;
import com.daxia.wy.dto.NoticeDTO;
import com.daxia.wy.dto.NoticeReplyDTO;
import com.daxia.wy.service.NoticeReplyService;
import com.daxia.wy.service.NoticeService;
import com.google.gson.JsonArray;

/**
 * 以Admin开头的Controller，都是只为管理后台提供服务的
 */
// @Module(name = "通知模块")
@Controller
@RequestMapping(value = "/admin/notice", produces="text/html;charset=UTF-8") // produces是为了解决@ResponseBody返回乱码
public class AdminNoticeController extends BaseController {

	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private NoticeReplyService noticeReplyService;
	@Autowired
	private UserService userService;
	
	/**
	 * 详情页面。用来获得单个对象的具体信息的
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			NoticeDTO dto = noticeService.load(id);
			map.put("notice", dto);
		}
		return "admin/notice/notice_detail";
	}
	
	@RequestMapping(value = "/push")
    public String push(Map<String, Object> map, Long id) {
        if (id != null) {
            NoticeDTO dto = noticeService.load(id);
            map.put("notice", dto);
        }
        return "admin/notice/notice_push";
    }
	
	@ResponseBody
	@RequestMapping(value = "/doPush")
    public String doPush(Map<String, Object> map, Long id, JsonResultDTO resultDTO) {
	    NoticeDTO notice = noticeService.load(id);
	    notice.setPublished(true);
	    notice.setPublishTime(new Date());
	    noticeService.push(notice);
        return okAndClose(resultDTO);
    }
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@Log(operation = "保存通知") 
	@ResponseBody
	@RequestMapping(value = "/save")
	@PreAuthorize("(hasRole('notice.update') and #dto.id != null) or (hasRole('notice.add') and #dto.id == null)")
	public String save(NoticeDTO dto, JsonResultDTO resultDTO) throws Exception {
		if (dto.getId() == null) {
			noticeService.create(dto);
		} else {
			noticeService.updateAllFields(dto);
		}
		return okAndClose(resultDTO);
	}

    @Log(operation = "删除通知")
    @PreAuthorize("hasRole('notice.delete')")
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids, JsonResultDTO resultDTO) throws IOException {
		noticeService.deleteByIds(ids);
		return okAndRefresh(resultDTO);
	}
	
	@RequestMapping(value = "/list")
	@PreAuthorize("hasRole('notice.list')")
	public String list(NoticeDTO dto, Map<String, Object> map, Page page) {
		List<NoticeDTO> dtos = noticeService.find(dto, page);
		// 这个数据是用来展示的
		map.put("notices", dtos);
		// 这个数据是保存查询条件的
		map.put("notice", dto);
		return "admin/notice/notice_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/datagrid")
    public String datagrid(NoticeDTO dto, Map<String, Object> map, Page page) {
        List<NoticeDTO> dtos = noticeService.find(dto, page);
        
        JSONObject json = new JSONObject();
        json.put("total", page.getTotalRecords());
        json.put("rows", JSONArray.toJSON(dtos));
        return json.toJSONString();
    }
	
	/**
	 * 用于查找带回的
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("search")
	public String search(NoticeDTO dto, Map<String, Object> map, Page page) {
		List<NoticeDTO> dtos = noticeService.find(dto, page);
		// 这个数据是用来展示的
		map.put("notices", dtos);
		// 这个数据是保存查询条件的
		map.put("notice", dto);
		return "admin/core/notice/notice_search";
	}
    
    @RequestMapping(value = "/reply")
    public String reply(Map<String, Object> map, Long id) {
        NoticeDTO dto = noticeService.load(id);
        map.put("n", dto);
        
        NoticeReplyDTO replyDTO = new NoticeReplyDTO();
        replyDTO.setNotice(dto);
        
        List<NoticeReplyDTO> replies = noticeReplyService.find(replyDTO, null);
        map.put("replies", replies);
        return "admin/notice/notice_reply";
    }
    
    @ResponseBody
    @RequestMapping(value = "/replySubmit", method = RequestMethod.POST)
    public String replySubmit(HttpServletRequest request, JsonResultDTO resultDTO, NoticeReplyDTO replyContentDTO, Map<String, Object> map) {
        if (replyContentDTO.getParentNoticeReply() != null) {
            if (replyContentDTO.getParentNoticeReply().getId() == null) {
                replyContentDTO.setParentNoticeReply(null);
            }
        }
        NoticeDTO noticeDTO = noticeService.load(replyContentDTO.getNotice().getId());
        replyContentDTO.setFloor(noticeDTO.getCurrentFloor() + 1);
        noticeDTO.setCurrentFloor(noticeDTO.getCurrentFloor() + 1);
        noticeReplyService.create(replyContentDTO);
        noticeService.updateAllFields(noticeDTO);
        resultDTO.setCallbackType(CallbackType.forward);
        resultDTO.setForwardUrl(request.getSession().getAttribute("ctx") + "/admin/notice/reply?id=" + 
        replyContentDTO.getNotice().getId() + 
        "&navTabId=" + resultDTO.getNavTabId());
        return okAndRefresh(resultDTO);
    }
}
