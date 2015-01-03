package com.daxia.wy.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.daxia.core.dao.GenericDAOHibernate;
import com.daxia.core.support.Page;
import com.daxia.wy.dto.FeeItemDTO;
import com.daxia.wy.model.FeeItem;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。
 * @author Kewen.Zhu
 *
 */
@Repository
public class FeeItemDAO extends GenericDAOHibernate<FeeItem> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<FeeItem> find(FeeItemDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From FeeItem n where 1 = 1 ");
        appendManagerSql(hql, paras);
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (dto.getType() != null) {
            hql.append(" and n.type = ? ");
            paras.add(dto.getType());
        }
        if (dto.getCommunity() != null) {
            if (dto.getCommunity().getId() != null) {
                hql.append(" and n.community.id = ? ");
                paras.add(dto.getCommunity().getId());
            }
        }
        hql.append(" order by n.id desc ");
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    /**
     * 只找出一条符合条件的记录
     * @param dto
     * @return
     */
    public FeeItem findOne(FeeItemDTO dto) {
		Page page = new Page();
		page.setNumPerPage(1);
		page.setPageNum(1);
		
		List<FeeItem> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    @Override
    public Long create(FeeItem t) {
        if (t.getCreateTime() == null) {
            t.setCreateTime(new Date());
        }
        return super.create(t);
    }
}
