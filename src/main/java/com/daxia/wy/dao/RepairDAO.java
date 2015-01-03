package com.daxia.wy.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.daxia.core.dao.GenericDAOHibernate;
import com.daxia.core.support.Page;
import com.daxia.wy.dto.RepairDTO;
import com.daxia.wy.model.Repair;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。
 * @author Kewen.Zhu
 *
 */
@Repository
public class RepairDAO extends GenericDAOHibernate<Repair> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<Repair> find(RepairDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Repair n where 1 = 1 ");
        
        appendManagerSql(hql, paras);
        
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (dto.getUser() != null) {
        	if (dto.getUser().getId() != null) {
	            hql.append(" and n.user.id = ? ");
	            paras.add(dto.getUser().getId());
        	}
        }			
        /*if (dto.getState() != null) {
            hql.append(" and n.state = ? ");
            paras.add(dto.getState());
        }*/
        if (dto.getBatch() != null) {
            hql.append(" and n.batch = ? ");
            paras.add(dto.getBatch());
        }
        hql.append(" order by n.createTime desc, n.id desc ");
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    /**
     * 只找出一条符合条件的记录
     * @param dto
     * @return
     */
    public Repair findOne(RepairDTO dto) {
		Page page = new Page();
		page.setNumPerPage(1);
		page.setPageNum(1);
		
		List<Repair> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
