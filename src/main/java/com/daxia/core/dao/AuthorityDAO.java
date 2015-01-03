package com.daxia.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.daxia.core.dto.AuthorityDTO;
import com.daxia.core.model.Authority;
import com.daxia.core.support.Page;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。
 * @author Kewen.Zhu
 *
 */
@Repository
public class AuthorityDAO extends GenericDAOHibernate<Authority> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<Authority> find(AuthorityDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Authority n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getCode())) {
        	hql.append(" and n.code = ? ");
        	paras.add(dto.getCode());
        }
        
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    public Authority uniqueFind(AuthorityDTO dto) {
		Page page = new Page();
		page.setNumPerPage(1);
		page.setPageNum(1);
		
		List<Authority> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    public List<Authority> findLikeAuthorities(String name) {
		StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Authority n where n.name like ? ");
        paras.add("%" + name + "%");
        
        return super.find(hql.toString(), paras.toArray(), null);
    }

    public Authority loadByName(String name) {
		StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Authority n where n.name = ? ");
        paras.add(name);
        
        List<Authority> list = super.find(hql.toString(), paras.toArray(), null);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public List<Authority> listTops() {
		StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Authority n where n.parentAuthority is null ");
        
        return super.find(hql.toString(), paras.toArray(), null);
    }
}
