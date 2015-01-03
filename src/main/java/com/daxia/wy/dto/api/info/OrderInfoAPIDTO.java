package com.daxia.wy.dto.api.info;

import java.util.List;

import com.daxia.wy.dto.api.OrderAPIDTO;

public class OrderInfoAPIDTO extends BaseInfoAPIDTO {
    
    public OrderInfoAPIDTO(List<OrderAPIDTO> orderInfos) {
        super();
        this.orderInfos = orderInfos;
    }

    private List<OrderAPIDTO> orderInfos;

    /**
     * @return the orderInfos
     */
    public List<OrderAPIDTO> getOrderInfos() {
        return orderInfos;
    }

    /**
     * @param orderInfos the orderInfos to set
     */
    public void setOrderInfos(List<OrderAPIDTO> orderInfos) {
        this.orderInfos = orderInfos;
    }
    
}
