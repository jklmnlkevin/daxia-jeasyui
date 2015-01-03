package com.daxia.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.daxia.core.dto.SystemLogDTO;
import com.daxia.core.model.SystemLog;
import com.daxia.core.support.Page;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。
 * @author Kewen.Zhu
 *
 */
@Repository
public class SystemLogDAO extends GenericDAOHibernate<SystemLog> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<SystemLog> find(SystemLogDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From SystemLog n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getModule())) {
            hql.append(" and n.module = ? ");
            paras.add(dto.getModule());
        }
        if (StringUtils.isNotBlank(dto.getOperation())) {
            hql.append(" and n.operation = ? ");
            paras.add(dto.getOperation());
        }
        if (dto.getDate() != null) {
            hql.append(" and n.date = ? ");
            paras.add(dto.getDate());
        }

        if (dto.getStartDate() != null) {
        	hql.append(" and n.date >= ? ");
        	paras.add(dto.getStartDate());
        }
        if (dto.getEndDate() != null) {
        	hql.append(" and n.date <= ? ");
        	paras.add(dto.getEndDate());
        }
        if (dto.getIsSuccess() != null) {
        	hql.append(" and n.isSuccess = ? ");
        	paras.add(dto.getIsSuccess());
        }
        if (dto.getUser() != null) {
        	if (StringUtils.isNotBlank(dto.getUser().getUsername())) {
        		hql.append(" and n.user.username like ? ");
        		paras.add("%" + dto.getUser().getUsername() + "%");
        	}
        }
        
        hql.append(" order by n.date desc ");
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    /**
     * 只找出一条符合条件的记录
     * @param dto
     * @return
     */
    public SystemLog findOne(SystemLogDTO dto) {
		Page page = new Page();
		page.setNumPerPage(1);
		page.setPageNum(1);
		
		List<SystemLog> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
