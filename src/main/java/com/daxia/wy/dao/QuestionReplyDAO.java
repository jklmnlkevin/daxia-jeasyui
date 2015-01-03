package com.daxia.wy.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
	
import com.daxia.core.dao.GenericDAOHibernate;
import com.daxia.wy.dto.QuestionReplyDTO;
import com.daxia.wy.model.QuestionReply;
import com.daxia.core.support.Page;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。
 * @author Kewen.Zhu
 *
 */
@Repository
public class QuestionReplyDAO extends GenericDAOHibernate<QuestionReply> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<QuestionReply> find(QuestionReplyDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From QuestionReply n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (dto.getQuestion() != null) {
        	if (dto.getQuestion().getId() != null) {
	            hql.append(" and n.question.id = ? ");
	            paras.add(dto.getQuestion().getId());
        	}
        }	
        hql.append(" order by n.createTime desc ");
        
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    /**
     * 只找出一条符合条件的记录
     * @param dto
     * @return
     */
    public QuestionReply findOne(QuestionReplyDTO dto) {
		Page page = new Page();
		page.setNumPerPage(1);
		page.setPageNum(1);
		
		List<QuestionReply> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    @Override
    public Long create(QuestionReply t) {
        if (t.getCreateTime() == null) {
            t.setCreateTime(new Date());
        }
        return super.create(t);
    }
}
