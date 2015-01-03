package com.daxia.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory;
import org.hibernate.hql.spi.QueryTranslator;
import org.hibernate.hql.spi.QueryTranslatorFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.daxia.core.common.UserType;
import com.daxia.core.model.User;
import com.daxia.core.support.Page;
import com.daxia.core.util.SpringSecurityUtils;
import com.daxia.wy.common.ICommunityRelatedModel;
import com.daxia.wy.common.IUserRelatedModel;

/**
 * This class implements Data Access Object (DAO) interface using Hibernate.
 * This is also an base class used to provide common methods to all DAOs.
 * 
 */
@SuppressWarnings("unchecked")
public class GenericDAOHibernate<T> {
	@Autowired
	private SessionFactory sessionFactory;
	protected Class<T> clz;

	/*
	 * Construct method
	 */
	public GenericDAOHibernate() {
		clz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected Session getSession() {
		try {
	        return sessionFactory.getCurrentSession();
        } catch (Exception e) {
	        e.printStackTrace();
        }
		return null;
	}

	public List<T> find(String hql, Object[] paras, Page page) {
		Query query = getSession().createQuery(hql);
		
		if (paras != null) {
			for (int i = 0; i < paras.length; i++) {
				query.setParameter(i, paras[i]);
			}
		}

		if (page != null) {
			if (StringUtils.isNotBlank(page.getSort())) {
			}

			int totalCount = queryTotalCount(hql, paras);
			page.setTotalRecords(totalCount);
			query.setFirstResult((page.getPageNum() - 1) * page.getNumPerPage());
			query.setMaxResults(page.getNumPerPage());
		}

		return query.list();
	}

	protected int queryTotalCount(String hql, Object[] paras) {
		int beginPos = hql.toLowerCase().indexOf("from");
		String countHql = "select count(*) " + hql.substring(beginPos);
		Query query = getSession().createQuery(countHql);
		if (paras != null) {
			for (int i = 0; i < paras.length; i++) {
				query.setParameter(i, paras[i]);
			}
		}
		List<Object> list = query.list();
		if (CollectionUtils.isEmpty(list)) {
			return 0;
		} else {
			return Integer.valueOf(list.get(0).toString());
		}
	}

	public Long create(T t) {
		return (Long) getSession().save(t);
	}

	public T load(Serializable id) {
		return (T) getSession().load(clz, id);
	}
	
	public T get(Serializable id) {
        return (T) getSession().get(clz, id);
    }

	public void update(T t) {
		getSession().update(t);
	}

	public void deleteById(Serializable id) {
		getSession().delete(load(id));
	}

	public void executeUpdate(String hql, Object[] paras) {
		Query query = getSession().createQuery(hql);
		if (paras != null) {
			for (int i = 0; i < paras.length; i++) {
				query.setParameter(i, paras[i]);
			}
		}
		query.executeUpdate();
	}
	
	public void executeSQLUpdate(String sql, Object[] paras) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		if (paras != null) {
			for (int i = 0; i < paras.length; i++) {
				sqlQuery.setParameter(i, paras[i]);
			}
		}
	}

	protected int queryTotalCount2(String hql, Object[] paras) {
		Map<String, String> replacements = new HashMap<String, String>();
		boolean scalar = false;
		QueryTranslator newQueryTranslator;
		QueryTranslatorFactory ast = new ASTQueryTranslatorFactory();
		SessionFactoryImplementor factory = getSessionFactoryImplementor();
		newQueryTranslator = ast.createQueryTranslator(hql, hql, Collections.EMPTY_MAP, factory);
		newQueryTranslator.compile(replacements, scalar);
		String sql = newQueryTranslator.getSQLString();

		String countSql = "select count(*) from (" + sql + ") as aaaaaaaaaaaaaaa";

		SQLQuery sqlQuery = getSession().createSQLQuery(countSql);

		if (ArrayUtils.isNotEmpty(paras)) {
			for (int i = 0; i < paras.length; i++) {
				sqlQuery.setParameter(i, paras[i]);
			}
		}
		List<Object> list = sqlQuery.list();
		if (CollectionUtils.isNotEmpty(list)) {
			return Integer.parseInt(list.get(0).toString());
		} else {
			return 0;
		}

	}

	private SessionFactoryImplementor getSessionFactoryImplementor() {
		SessionFactoryImpl sfi = (SessionFactoryImpl) getSession().getSessionFactory();
		return sfi;
	}
	
	/**
	 * 不要乱用这个方法，尤其是在表之间有关联的情况下
	 */
	public void deleteAll() {
		getSession().createSQLQuery("delete from `" + clz.getSimpleName().toLowerCase() + "`").executeUpdate();
	}
	
	protected void appendManagerSql(StringBuilder hql, List<Object> paras) {
	    boolean isUserRelated = isUserRelated();
	    if (isUserRelated) {
	        hql.append(" and user.userType != -1 ");
	    }
	    
	    User currentUser = SpringSecurityUtils.getCurrentUser();
	    boolean isEstate = false;
	    if (currentUser != null && currentUser.getUserType() == UserType.EstateManager.getValue()) {
	        isEstate = true;
	    }
	    
	    boolean isCommunityRelated = isCommunityRelated();
	    if (isEstate && isCommunityRelated) {
	        hql.append(" and community.id = ? ");
	        paras.add(currentUser.getCommunity().getId());
	    }
	    /*
		User manager = SpringSecurityUtils.getCurrentUser();
		if (manager != null && !manager.isHead()) {
			hql.append(" and store.id = ? ");
			paras.add(manager.getStore().getId());
		} */
	}

    @SuppressWarnings("rawtypes")
    private boolean isCommunityRelated() {
        Class[] interfaces = clz.getInterfaces();
	    if (ArrayUtils.isNotEmpty(interfaces)) {
	        for (Class interf : interfaces) {
                if (interf == ICommunityRelatedModel.class) {
                    return true;
                }
            }
	    }
	    return false;
    }
    
    @SuppressWarnings("rawtypes")
    private boolean isUserRelated() {
        Class[] interfaces = clz.getInterfaces();
        if (ArrayUtils.isNotEmpty(interfaces)) {
            for (Class interf : interfaces) {
                if (interf == IUserRelatedModel.class) {
                    return true;
                }
            }
        }
        return false;
    }
	
}
