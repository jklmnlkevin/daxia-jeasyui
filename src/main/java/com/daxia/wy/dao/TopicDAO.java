package com.daxia.wy.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.daxia.core.dao.GenericDAOHibernate;
import com.daxia.core.support.Page;
import com.daxia.wy.dto.TopicDTO;
import com.daxia.wy.model.Topic;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。
 * @author Kewen.Zhu
 *
 */
@Repository
public class TopicDAO extends GenericDAOHibernate<Topic> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<Topic> find(TopicDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Topic n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getTitle())) {
            hql.append(" and n.title like ? ");
            paras.add("%" + dto.getTitle() + "%");
        }					
        if (dto.getIsInviteEstate() != null) {
            hql.append(" and n.isInviteEstate = ? ");
            paras.add(dto.getIsInviteEstate());
        }
        if (dto.getUser() != null) {
            if (dto.getUser().getId() != null) {
                hql.append(" and n.user.id = ? ");
                paras.add(dto.getUser().getId());
            }
        }
        if (dto.getCommunity() != null) {
            if (dto.getCommunity().getId() != null) {
                hql.append(" and n.community.id = ? ");
                paras.add(dto.getCommunity().getId());
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
    public Topic findOne(TopicDTO dto) {
		Page page = new Page();
		page.setNumPerPage(1);
		page.setPageNum(1);
		
		List<Topic> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public void addClick(Long topicId) {
        String hql = "update Topic set clickCount = clickCount + 1 where id = ? ";
        super.executeUpdate(hql, new Object[] {topicId});
    }
}
