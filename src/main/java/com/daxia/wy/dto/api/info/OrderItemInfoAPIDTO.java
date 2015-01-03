package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.OrderItemAPIDTO;

public class OrderItemInfoAPIDTO extends BaseInfoAPIDTO {
    
    public OrderItemInfoAPIDTO(List<OrderItemAPIDTO> orderItemInfos) {
        super();
        this.orderItemInfos = orderItemInfos;
    }

    private List<OrderItemAPIDTO> orderItemInfos;

    /**
     * @return the orderItemInfos
     */
    public List<OrderItemAPIDTO> getOrderItemInfos() {
        return orderItemInfos;
    }

    /**
     * @param orderItemInfos the orderItemInfos to set
     */
    public void setOrderItemInfos(List<OrderItemAPIDTO> orderItemInfos) {
        this.orderItemInfos = orderItemInfos;
    }
    
}
