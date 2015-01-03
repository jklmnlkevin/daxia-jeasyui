package com.daxia.wy.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.daxia.core.dao.GenericDAOHibernate;
import com.daxia.core.support.Page;
import com.daxia.wy.dto.QuestionDTO;
import com.daxia.wy.model.Question;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。
 * @author Kewen.Zhu
 *
 */
@Repository
public class QuestionDAO extends GenericDAOHibernate<Question> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<Question> find(QuestionDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Question n where 1 = 1 ");
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
        	if (StringUtils.isNotBlank(dto.getUser().getUsername())) {
        	    hql.append(" and n.username like ? ");
        	    paras.add("%" + dto.getUser().getUsername() + "%");
        	}
        }			
        if (StringUtils.isNotBlank(dto.getTitle())) {
            hql.append(" and n.title like ? ");
            paras.add("%" + dto.getTitle() + "%");
        }					
        if (StringUtils.isNotBlank(dto.getContent())) {
            hql.append(" and n.content like ? ");
            paras.add("%" + dto.getContent() + "%");
        }					
        if (StringUtils.isNotBlank(dto.getTerm())) {
            hql.append(" and (n.title like ? or n.content like ?)");
            paras.add("%" + dto.getTerm() + "%");
            paras.add("%" + dto.getTerm() + "%");
        }
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    /**
     * 只找出一条符合条件的记录
     * @param dto
     * @return
     */
    public Question findOne(QuestionDTO dto) {
		Page page = new Page();
		page.setNumPerPage(1);
		page.setPageNum(1);
		
		List<Question> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    @Override
    public Long create(Question t) {
        if (t.getCreateTime() == null) {
            t.setCreateTime(new Date());
        }
        return super.create(t);
    }
}
