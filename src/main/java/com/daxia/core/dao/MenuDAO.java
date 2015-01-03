package com.daxia.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.daxia.core.dto.MenuDTO;
import com.daxia.core.model.Menu;
import com.daxia.core.support.Page;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。
 * 
 * @date 2013-6-13
 *
 */
@Repository
public class MenuDAO extends GenericDAOHibernate<Menu> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<Menu> find(MenuDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Menu n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (dto.getLevel() != null) {
        	hql.append(" and n.level = ? ");
        	paras.add(dto.getLevel());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
        	hql.append(" and n.name = ? ");
        	paras.add(dto.getName());
        }
        if (StringUtils.isNotBlank(dto.getHref())) {
        	hql.append(" and n.href = ? ");
        	paras.add(dto.getHref());
        }
        
        hql.append(" order by n.level asc, n.seq asc ");
        
        return super.find(hql.toString(), paras.toArray(), page);
    }
}
