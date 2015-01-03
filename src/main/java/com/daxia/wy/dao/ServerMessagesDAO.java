package com.daxia.wy.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.daxia.wy.model.ServerMessages;

/**
 * A data access object (DAO) providing persistence and search support for
 * ServerMessages entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.daxia.webchat.model.zzc.wechat.dao.ServerMessages
 * @author MyEclipse Persistence Tools
 */

public class ServerMessagesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ServerMessagesDAO.class);
	// property constants
	public static final String TO_USERNAME = "toUsername";
	public static final String FROM_USERNAME = "fromUsername";
	public static final String MSG_TYPE = "msgType";
	public static final String MSG_ID = "msgId";
	public static final String CONTENT = "content";

	protected void initDao() {
		// do nothing
	}

	public void save(ServerMessages transientInstance) {
		log.debug("saving ServerMessages instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ServerMessages persistentInstance) {
		log.debug("deleting ServerMessages instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ServerMessages findById(java.lang.Integer id) {
		log.debug("getting ServerMessages instance with id: " + id);
		try {
			ServerMessages instance = (ServerMessages) getHibernateTemplate()
					.get("org.zzc.wechat.dao.ServerMessages", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ServerMessages instance) {
		log.debug("finding ServerMessages instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ServerMessages instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ServerMessages as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByToUsername(Object toUsername) {
		return findByProperty(TO_USERNAME, toUsername);
	}

	public List findByFromUsername(Object fromUsername) {
		return findByProperty(FROM_USERNAME, fromUsername);
	}

	public List findByMsgType(Object msgType) {
		return findByProperty(MSG_TYPE, msgType);
	}

	public List findByMsgId(Object msgId) {
		return findByProperty(MSG_ID, msgId);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all ServerMessages instances");
		try {
			String queryString = "from ServerMessages";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ServerMessages merge(ServerMessages detachedInstance) {
		log.debug("merging ServerMessages instance");
		try {
			ServerMessages result = (ServerMessages) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ServerMessages instance) {
		log.debug("attaching dirty ServerMessages instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ServerMessages instance) {
		log.debug("attaching clean ServerMessages instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ServerMessagesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ServerMessagesDAO) ctx.getBean("ServerMessagesDAO");
	}
}