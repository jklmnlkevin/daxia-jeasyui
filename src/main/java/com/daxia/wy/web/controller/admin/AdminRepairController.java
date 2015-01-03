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
import com.daxia.core.service.UserService;
import com.daxia.core.support.Page;
import com.daxia.core.web.controller.BaseController;
import com.daxia.wy.common.PushCode;
import com.daxia.wy.common.RepairStatus;
import com.daxia.wy.common.RepairStatus2;
import com.daxia.wy.dto.PushDTO;
import com.daxia.wy.dto.RepairDTO;
import com.daxia.wy.dto.RepairReplyDTO;
import com.daxia.wy.service.RepairService;

/**
 * 以Admin开头的Controller，都是只为管理后台提供服务的
 */
// @Module(name = "物业维修模块")
@Controller
@RequestMapping(value = "/admin/repair", produces="text/html;charset=UTF-8") // produces是为了解决@ResponseBody返回乱码
public class AdminRepairController extends BaseController {

	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private RepairService repairService;
	
	/**
	 * 详情页面。用来获得单个对象的具体信息的
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			RepairDTO dto = repairService.load(id);
			map.put("n", dto);
		}
		map.put("repairStatus", RepairStatus2.values());
		return "admin/repair/repair_detail";
	}
	
	@RequestMapping(value = "/reply")
	public String reply(Map<String, Object> map, Long id) {
        RepairDTO dto = repairService.load(id);
        map.put("n", dto);
        
        RepairReplyDTO replyDTO = new RepairReplyDTO();
        replyDTO.setRepair(dto);
        
        List<RepairReplyDTO> replies = repairReplyService.find(replyDTO, null);
        map.put("replies", replies);
        map.put("repairStatus", RepairStatus.values());
        return "admin/repair/repair_reply";
    }
	
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/replySubmit", method = RequestMethod.POST)
    public String replySubmit(HttpServletRequest request, JsonResultDTO resultDTO, RepairReplyDTO replyContentDTO, Map<String, Object> map) {
	    repairReplyService.create(replyContentDTO);
	    
	    PushDTO push = new PushDTO();
	    push.setCode(PushCode.RepairStateChanged.getValue());
	    push.setTitle("物业回复了您的维修申请");
	    push.setId(replyContentDTO.getRepair().getId().toString());
	    
	    pushService.push(push, userService.load(replyContentDTO.getUser().getId()).getUsername());
	    
	   /* RepairDTO dto = repairService.load(replyContentDTO.getRepair().getId());
        map.put("n", dto);
        
        RepairReplyDTO replyDTO = new RepairReplyDTO();
        replyDTO.setRepair(dto);
        
        List<RepairReplyDTO> replies = repairReplyService.find(replyDTO, null);
        map.put("replies", replies);
        map.put("repairStatus", RepairStatus.values());*/
	    resultDTO.setCallbackType(CallbackType.forward);
        resultDTO.setForwardUrl(request.getSession().getAttribute("ctx") + "/admin/repair/reply?id=" + 
	    replyContentDTO.getRepair().getId() + 
	    "&navTabId=" + resultDTO.getNavTabId());
        return okAndRefresh(resultDTO);
	}
	
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@Log(operation = "保存物业维修") 
	@ResponseBody
	@RequestMapping(value = "/save")
	@PreAuthorize("(hasRole('repair.update') and #dto.id != null) or (hasRole('repair.add') and #dto.id == null)")
	public String save(RepairDTO dto, JsonResultDTO resultDTO) throws Exception {
		if (dto.getId() == null) {
			repairService.create(dto);
		} else {
			repairService.updateAllFields(dto);
		}
		return okAndClose(resultDTO);
	}

    @Log(operation = "删除物业维修")
    @PreAuthorize("hasRole('repair.delete')")
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids, JsonResultDTO resultDTO) throws IOException {
		repairService.deleteByIds(ids);
		return okAndRefresh(resultDTO);
	}
	
	@RequestMapping(value = "/list")
	@PreAuthorize("hasRole('repair.list')")
	public String list(RepairDTO dto, Map<String, Object> map, Page page) {
		List<RepairDTO> dtos = repairService.find(dto, page);
		// 这个数据是用来展示的
		map.put("list", dtos);
		// 这个数据是保存查询条件的
		map.put("dto", dto);
		map.put("repairStatus", RepairStatus2.values());
		return "admin/repair/repair_list";
	}
	
	/**
	 * 用于查找带回的
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("search")
	public String search(RepairDTO dto, Map<String, Object> map, Page page) {
		List<RepairDTO> dtos = repairService.find(dto, page);
		// 这个数据是用来展示的
		map.put("list", dtos);
		// 这个数据是保存查询条件的
		map.put("dto", dto);
		return "admin/repair/repair_search";
	}
    
    /**
     * 后台查看图片
     * @return
     * @throws Exception
     */
    @RequestMapping("images")
    public String images(HttpServletRequest request, Long id, Map<String, Object> map) throws Exception {
        RepairDTO repair = repairService.load(id);
        String[] arr = repair.getImageArr();
        String[] images = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String image = request.getSession().getAttribute("ctx") + "/image/" + arr[i];
            images[i] = image;
        }
        repair.setImages(images);
        map.put("images", images);
        return "admin/repair/repair_images";
    }
}
