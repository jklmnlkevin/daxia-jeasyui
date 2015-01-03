package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.wy.dao.OrderItemDAO;
import com.daxia.wy.dto.OrderItemDTO;
import com.daxia.wy.model.OrderItem;
import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemDAO orderItemDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<OrderItemDTO> find(OrderItemDTO dto, Page page) {
		List<OrderItem> models = orderItemDAO.find(dto, page);
		List<OrderItemDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<OrderItemDTO> toDTOs(List<OrderItem> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<OrderItemDTO>(0);
		}
		List<OrderItemDTO> dtos = new ArrayList<OrderItemDTO>(models.size());
		for (OrderItem model : models) {
	        OrderItemDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private OrderItemDTO toDTO(OrderItem model) {
		if (model == null) {
			return null;
		}
		OrderItemDTO dto = BeanMapper.map(model, OrderItemDTO.class);
		
		return dto;
	}
	
	public void create(OrderItemDTO dto) {
		OrderItem model = new OrderItem();
		toModel(model, dto);
		orderItemDAO.create(model);
	}

	private void toModel(OrderItem model, OrderItemDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<OrderItem> toModels(List<OrderItemDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<OrderItem>(0);
		}
		List<OrderItem> models = new ArrayList<OrderItem>(dtos.size());
		for (OrderItemDTO dto : dtos) {
	        OrderItem model = new OrderItem();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public OrderItemDTO load(Long id) {
	    OrderItem model = orderItemDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(OrderItemDTO dto) {
		OrderItem model = orderItemDAO.load(dto.getId());
		toModel(model, dto);
		orderItemDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				orderItemDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public OrderItemDTO findOne(OrderItemDTO dto) {
		OrderItem model = orderItemDAO.findOne(dto);
		return toDTO(model);
	}
}
