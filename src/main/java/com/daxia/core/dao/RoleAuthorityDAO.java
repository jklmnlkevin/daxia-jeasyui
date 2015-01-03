package com.daxia.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.daxia.core.model.Role;
import com.daxia.core.model.RoleAuthority;

@Repository
public class RoleAuthorityDAO extends GenericDAOHibernate<RoleAuthority> {

    public List<RoleAuthority> findByRole(Role role) {
		String hql = "from RoleAuthority ra where ra.role.id = ?";
	    return super.find(hql, new Object[] {role.getId()}, null);
    }

}
