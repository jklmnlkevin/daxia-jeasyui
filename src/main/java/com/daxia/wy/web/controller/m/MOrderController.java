package com.daxia.wy.web.controller.m;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.common.Log;
import com.daxia.core.support.MPage;
import com.daxia.wy.common.CategoryType;
import com.daxia.wy.dto.OrderDTO;
import com.daxia.wy.dto.api.OrderAPIDTO;
import com.daxia.wy.dto.api.info.OrderInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/order", produces = "text/html;charset=UTF-8")
public class MOrderController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(OrderDTO dto, MPage page, HttpServletRequest request) throws Exception {
        // dto.setType(ServletRequestUtils.getIntParameter(request, "type", CategoryType.Market.getValue()));
        List<OrderDTO> orderDTOs = orderService.find(dto, page);
        return toApiOutput(orderDTOs, OrderAPIDTO.class, OrderInfoAPIDTO.class, page);
    }
    
    @Log(operation = "取消订单")
    @ResponseBody
    @RequestMapping("cancel")
    public String cancel(Long id) throws Exception {
        orderService.cancel(id);
        return toJson("ok");
    }
 
    @Log(operation = "手机端下单")
    @ResponseBody
    @RequestMapping("order")
    public String order(OrderDTO dto, MPage page, HttpServletRequest request) throws Exception {
        Long communityId = ServletRequestUtils.getLongParameter(request, "community.id");
        Long userId = ServletRequestUtils.getLongParameter(request, "user.id");
        String items = ServletRequestUtils.getStringParameter(request, "items");
        Double money = ServletRequestUtils.getDoubleParameter(request, "money");
        String remark = ServletRequestUtils.getStringParameter(request, "remark");
        Integer type = ServletRequestUtils.getIntParameter(request, "type", CategoryType.Market.getValue());
        
        Long orderId = orderService.order(communityId, userId, items, money, type, remark);

        OrderDTO order = orderService.load(orderId);
        return toApiOutput(order, OrderAPIDTO.class, OrderInfoAPIDTO.class);
    }
}
