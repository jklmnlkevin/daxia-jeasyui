package com.daxia.wy.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daxia.wy.dao.OrderDAO;
import com.daxia.wy.dao.OrderItemDAO;
import com.daxia.wy.dao.ProductDAO;
import com.daxia.wy.dto.OrderDTO;
import com.daxia.wy.model.Community;
import com.daxia.wy.model.Order;
import com.daxia.wy.model.OrderItem;
import com.daxia.wy.model.Product;
import com.daxia.core.common.OrderStatus;
import com.daxia.core.model.User;
import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.core.util.ValidationUtils;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class OrderService {
	
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private OrderItemDAO orderItemDAO;
	@Autowired
	private ProductDAO productDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<OrderDTO> find(OrderDTO dto, Page page) {
		List<Order> models = orderDAO.find(dto, page);
		List<OrderDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<OrderDTO> toDTOs(List<Order> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<OrderDTO>(0);
		}
		List<OrderDTO> dtos = new ArrayList<OrderDTO>(models.size());
		for (Order model : models) {
	        OrderDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private OrderDTO toDTO(Order model) {
		if (model == null) {
			return null;
		}
		OrderDTO dto = BeanMapper.map(model, OrderDTO.class);
		
		return dto;
	}
	
	public void create(OrderDTO dto) {
		Order model = new Order();
		toModel(model, dto);
		orderDAO.create(model);
	}

	private void toModel(Order model, OrderDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Order> toModels(List<OrderDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Order>(0);
		}
		List<Order> models = new ArrayList<Order>(dtos.size());
		for (OrderDTO dto : dtos) {
	        Order model = new Order();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public OrderDTO load(Long id) {
	    Order model = orderDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(OrderDTO dto) {
		Order model = orderDAO.load(dto.getId());
		toModel(model, dto);
		orderDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				orderDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public OrderDTO findOne(OrderDTO dto) {
		Order model = orderDAO.findOne(dto);
		return toDTO(model);
	}

	/**
	 * items=1-2,3-4,5-6
	 * 第一位数字是产品id，第二位数字是这个产品买了多少个
	 * @param communityId
	 * @param userId
	 * @param items
	 * @param money
	 * @param type CategoryType
	 */
	@Transactional
    public Long order(Long communityId, Long userId, String items, Double money, Integer type, String remark) {
        validate(communityId, userId, items, money);
        
        // parse
        String[] productMountArr = items.split(",");
        Map<Long, Integer> productAmountMap = new HashMap<Long, Integer>();
        for (String pm : productMountArr) {
            String[] arr = pm.split("-");
            Long productId = Long.valueOf(arr[0]);
            int amount = Integer.parseInt(arr[1]);
            if (amount > 0) {
                productAmountMap.put(productId, amount);
            }
        }
        
        // validate products and money
        BigDecimal totalMoney = new BigDecimal("0");
        for (Long productId : productAmountMap.keySet()) {
            Product product = productDAO.get(productId);
            ValidationUtils.assertTrue(product != null, "产品" + productId + "不存在");
            int amount = productAmountMap.get(productId);
            ValidationUtils.assertTrue(amount > 0, "产品" + product.getName() + "的购买数量小于0");
            ValidationUtils.assertTrue(product.getStock() >= amount, "产品" + product.getName() + "库存不足，需要库存" + amount + "，实际库存" + product.getStock());
            
            totalMoney = totalMoney.add(product.getPrice());
        }
        
        if (totalMoney.doubleValue() != money) {
            throw new RuntimeException("传进来的总金额的实际不符，传进来的是" + money + "，实际金额是" + totalMoney.doubleValue());
        }
        
        // 正式生成订单
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderNo = dateFormat.format(now) + (1000 + new Random().nextInt(999));
        
        Order order = new Order();
        order.setCommunity(new Community());
        order.getCommunity().setId(communityId);
        order.setUser(new User());
        order.getUser().setId(userId);
        order.setCreateTime(now);
        order.setMoney(totalMoney);
        order.setOrderNo(orderNo);
        order.setType(type);
        order.setRemark(remark);
        order.setStatus(OrderStatus.New.getValue());
        
        Long orderId = orderDAO.create(order);
        
        for (Long productId : productAmountMap.keySet()) {
            Product product = productDAO.load(productId);
            OrderItem item = new OrderItem();
            item.setOrder(new Order());
            item.getOrder().setId(orderId);
            item.setAmount(productAmountMap.get(productId));
            item.setProduct(product);
            item.setPrice(product.getPrice());
            
            orderItemDAO.create(item);
            
            productDAO.updateStock(product.getId(), -item.getAmount());
        }
        return orderId;
    }

    private void validate(Long communityId, Long userId, String items, Double money) {
        ValidationUtils.assertTrue(communityId != null, "小区不能为空");
        ValidationUtils.assertTrue(userId != null, "用户不能为空");
        ValidationUtils.assertTrue(StringUtils.isNotBlank(items), "items不能为空");
        ValidationUtils.assertTrue(money != null, "总金额不能为空");
    }

    @Transactional
    public void cancel(Long id) {
        Order order = orderDAO.load(id);
        if (order.getStatus() != OrderStatus.New.getValue()) {
            throw new RuntimeException("订单目前是" + OrderStatus.getByValue(order.getStatus()) + "，无法撤单");
        }
        order.setStatus(OrderStatus.Cancelled.getValue());
        orderDAO.update(order);
    }
}
