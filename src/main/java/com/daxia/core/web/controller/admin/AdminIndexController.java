package com.daxia.core.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.support.OrderQueue;
import com.daxia.core.util.SpringSecurityUtils;
import com.daxia.core.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/index", produces = "text/html;charset=UTF-8")
public class AdminIndexController extends BaseController {
	@Autowired
	private OrderQueue orderQueue;

	/**
	 * 看是否有新的订单
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("checkNewOrder")
    public String checkNewOrder() throws Exception {
		int newOrdeCount = orderQueue.size(SpringSecurityUtils.getCurrentCommunityId());
		String msg = "";
		if (newOrdeCount == 0) {
			return msg;
		}
		
		if (newOrdeCount == 1) {
			msg = orderQueue.poll(SpringSecurityUtils.getCurrentCommunityId());
			if (msg == null) {
				msg = "";
			}
		}
		if (newOrdeCount > 1) {
			msg = "有" + newOrdeCount + "条新的维修申请";
			orderQueue.clear(SpringSecurityUtils.getCurrentCommunityId());
		}
	    return msg;
    }
}	
