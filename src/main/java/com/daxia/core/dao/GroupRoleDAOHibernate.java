package com.daxia.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.daxia.core.model.Group;
import com.daxia.core.model.GroupRole;

@Repository
public class GroupRoleDAOHibernate extends GenericDAOHibernate<GroupRole> {

    public List<GroupRole> findByGroup(Group group) {
		String hql = "from GroupRole gr where gr.group.id = ?";
	    return super.find(hql, new Object[] {group.getId()}, null);
    }

}
