package com.daxia.wy.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.common.CallbackType;
import com.daxia.core.common.Log;
import com.daxia.core.dto.JsonResultDTO;
import com.daxia.core.support.Page;
import com.daxia.core.web.controller.BaseController;
import com.daxia.wy.common.QuestionStatus;
import com.daxia.wy.dto.QuestionDTO;
import com.daxia.wy.dto.QuestionReplyDTO;
import com.daxia.wy.service.QuestionService;

/**
 * 以Admin开头的Controller，都是只为管理后台提供服务的
 */
// @Module(name = "咨询模块")
@Controller
@RequestMapping(value = "/admin/question", produces="text/html;charset=UTF-8") // produces是为了解决@ResponseBody返回乱码
public class AdminQuestionController extends BaseController {

	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private QuestionService questionService;
	
	/**
	 * 详情页面。用来获得单个对象的具体信息的
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			QuestionDTO dto = questionService.load(id);
			map.put("n", dto);
		}
		map.put("questionStatus", QuestionStatus.values());
		return "admin/question/question_detail";
	}
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@Log(operation = "保存咨询") 
	@ResponseBody
	@RequestMapping(value = "/save")
	@PreAuthorize("(hasRole('question.update') and #dto.id != null) or (hasRole('question.add') and #dto.id == null)")
	public String save(QuestionDTO dto, JsonResultDTO resultDTO) throws Exception {
		if (dto.getId() == null) {
			questionService.create(dto);
		} else {
			questionService.updateAllFields(dto);
		}
		return okAndClose(resultDTO);
	}

    @Log(operation = "删除咨询")
    @PreAuthorize("hasRole('question.delete')")
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids, JsonResultDTO resultDTO) throws IOException {
		questionService.deleteByIds(ids);
		return okAndRefresh(resultDTO);
	}
	
	@RequestMapping(value = "/list")
	@PreAuthorize("hasRole('question.list')")
	public String list(QuestionDTO dto, Map<String, Object> map, Page page) {
		List<QuestionDTO> dtos = questionService.find(dto, page);
		// 这个数据是用来展示的
		map.put("list", dtos);
		// 这个数据是保存查询条件的
		map.put("dto", dto);
		map.put("questionStatus", QuestionStatus.values());
		return "admin/question/question_list";
	}
	
	/**
	 * 用于查找带回的
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("search")
	public String search(QuestionDTO dto, Map<String, Object> map, Page page) {
		List<QuestionDTO> dtos = questionService.find(dto, page);
		// 这个数据是用来展示的
		map.put("list", dtos);
		// 这个数据是保存查询条件的
		map.put("dto", dto);
		return "admin/question/question_search";
	}
    
    @RequestMapping(value = "/reply")
    public String reply(Map<String, Object> map, Long id) {
        
        QuestionDTO dto = questionService.load(id);
        map.put("n", dto);
        
        QuestionReplyDTO replyDTO = new QuestionReplyDTO();
        replyDTO.setQuestion(dto);
        
        List<QuestionReplyDTO> replies = questionReplyService.find(replyDTO, null);
        map.put("replies", replies);
        map.put("questionStatus", QuestionStatus.values());
        return "admin/question/question_reply";
    }
    
    @ResponseBody
    @RequestMapping(value = "/replySubmit", method = RequestMethod.POST)
    public String replySubmit(HttpServletRequest request, JsonResultDTO resultDTO, QuestionReplyDTO replyContentDTO, Map<String, Object> map) {
        questionReplyService.create(replyContentDTO);
        
       /* QuestionDTO dto = questionService.load(replyContentDTO.getQuestion().getId());
        map.put("n", dto);
        
        QuestionReplyDTO replyDTO = new QuestionReplyDTO();
        replyDTO.setQuestion(dto);
        
        List<QuestionReplyDTO> replies = questionReplyService.find(replyDTO, null);
        map.put("replies", replies);
        map.put("questionStatus", QuestionStatus.values());*/
        resultDTO.setCallbackType(CallbackType.forward);
        resultDTO.setForwardUrl(request.getSession().getAttribute("ctx") + "/admin/question/reply?id=" + 
        replyContentDTO.getQuestion().getId() + 
        "&navTabId=" + resultDTO.getNavTabId());
        return okAndRefresh(resultDTO);
    }
}
