package com.daxia.wy.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.daxia.core.dao.GenericDAOHibernate;
import com.daxia.core.model.User;
import com.daxia.core.support.Page;
import com.daxia.core.util.SpringSecurityUtils;
import com.daxia.wy.dto.StoreDTO;
import com.daxia.wy.model.Store;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。
 * @author Kewen.Zhu
 *
 */
@Repository
public class StoreDAO extends GenericDAOHibernate<Store> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<Store> find(StoreDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Store n where 1 = 1 ");
        
        appendManagerSqlForStore(hql, paras);
        
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            hql.append(" and n.name like ? ");
            paras.add("%" + dto.getName() + "%");
        }
        if (StringUtils.isNotBlank(dto.getAddress())) {
            hql.append(" and n.address like ? ");
            paras.add("%" + dto.getAddress() + "%");
        }
        if (StringUtils.isNotBlank(dto.getPhone())) {
            hql.append(" and n.phone = ? ");
            paras.add(dto.getPhone());
        }

        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    /**
     * 只找出一条符合条件的记录
     * @param dto
     * @return
     */
    public Store findOne(StoreDTO dto) {
		Page page = new Page();
		page.setNumPerPage(1);
		page.setPageNum(1);
		
		List<Store> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    private void appendManagerSqlForStore(StringBuilder hql, List<Object> paras) {
		/*User manager = SpringSecurityUtils.getCurrentUser();
		if (!manager.isHead()) {
			hql.append(" and id = ? ");
			paras.add(manager.getStore().getId());
		}*/
	}
}
