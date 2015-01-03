package com.daxia.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.daxia.core.dto.UserDTO;
import com.daxia.core.model.User;
import com.daxia.core.support.Page;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。
 * @author Kewen.Zhu
 *
 */
@Repository
public class UserDAO extends GenericDAOHibernate<User> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<User> find(UserDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From User n where 1 = 1 ");
        
        appendManagerSql(hql, paras);
        
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getUsername())) {
        	hql.append(" and n.username = ? ");
        	paras.add(dto.getUsername());
        }
        if (StringUtils.isNotBlank(dto.getPassword())) {
            hql.append(" and n.password = ? ");
            paras.add(dto.getPassword());
        }
        if (StringUtils.isNotBlank(dto.getTelephone())) {
            hql.append(" and n.telephone = ? ");
            paras.add(dto.getTelephone());
        }
        if (dto.getOnlyFindType() != null) {
            hql.append(" and n.userType = ? ");
            paras.add(dto.getOnlyFindType());
        }
        if (dto.getCommunity() != null) {
            if (dto.getCommunity().getId() != null) {
                hql.append(" and n.community.id = ? ");
                paras.add(dto.getCommunity().getId());
            }
        }
        
        long start = System.currentTimeMillis();
        List<User> users = super.find(hql.toString(), paras.toArray(), page);
        System.out.println("took " + (System.currentTimeMillis() - start) + "ms to findUser");
        return users;
    }
    
    public User findOne(UserDTO dto) {
		Page page = new Page();
		page.setNumPerPage(1);
		page.setPageNum(1);
		
		List<User> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    /**
     * 推荐好友,即找出在自己的通讯录里，但不是自己好友的用户。
     * @return
     */
    public List<User> recommendFriend(String username) {
    	List<Object> paras = new ArrayList<Object>();
    	
    	StringBuilder hql = new StringBuilder();
    	hql.append(" select u from User u, Contract c ");
    	hql.append(" where 1 = 1 ");
    	hql.append(" and c.user.username = ? ");
    	paras.add(username);
    	hql.append(" and c.mobile = u.mobile ");
    	hql.append(" and u.mobile is not null ");
    	hql.append(" and u.username not in (select f.friendUser.username from Friend f where f.friendUser.username = ? ) ");
    	paras.add(username);
    	
    	return super.find(hql.toString(), paras.toArray(), null);
    }
    
    @Override
    public Long create(User t) {
        return super.create(t);
    }
}
