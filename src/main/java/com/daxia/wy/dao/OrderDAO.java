package com.daxia.wy.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;
	
import com.daxia.core.dao.GenericDAOHibernate;
import com.daxia.wy.dto.OrderDTO;
import com.daxia.wy.model.Order;
import com.daxia.core.support.Page;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。
 * @author Kewen.Zhu
 *
 */
@Repository
public class OrderDAO extends GenericDAOHibernate<Order> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<Order> find(OrderDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Order n where 1 = 1 ");
        appendManagerSql(hql, paras);
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getOrderNo())) {
            hql.append(" and n.orderNo = ? ");
            paras.add(dto.getOrderNo());
        }		
        if (dto.getUser() != null) {
        	if (dto.getUser().getId() != null) {
	            hql.append(" and n.user.id = ? ");
	            paras.add(dto.getUser().getId());
        	}
        }			
        if (dto.getStatus() != null) {
            hql.append(" and n.status = ? ");
            paras.add(dto.getStatus());
        }
        if (dto.getType() != null) {
            hql.append(" and n.type = ? ");
            paras.add(dto.getType());
        }
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    /**
     * 只找出一条符合条件的记录
     * @param dto
     * @return
     */
    public Order findOne(OrderDTO dto) {
		Page page = new Page();
		page.setNumPerPage(1);
		page.setPageNum(1);
		
		List<Order> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    @Override
    public Long create(Order t) {
        if (t.getCreateTime() == null) {
            t.setCreateTime(new Date());
        }
        if (t.getLastUpdatedTime() == null) {
            t.setLastUpdatedTime(new Date());
        }
        return super.create(t);
    }
    
    @Override
    public void update(Order t) {
        if (t.getLastUpdatedTime() == null) {
            t.setLastUpdatedTime(new Date());
        }
        super.update(t);
    }
}
