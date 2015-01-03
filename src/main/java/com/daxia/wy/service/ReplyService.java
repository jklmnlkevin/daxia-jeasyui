package com.daxia.wy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.wy.dao.ReplyDAO;
import com.daxia.wy.model.Reply;
import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class ReplyService {
	
	@Autowired
	private ReplyDAO replyDAO;
	
	/**
	 * 根据model的查询条件和分页条件查找记录
	 * @param model
	 * @param page
	 * @return
	 */
	public List<Reply> find(Reply model, Page page) {
		List<Reply> models = replyDAO.find(model, page);
		return models;
	}
	
	public Long create(Reply model) {
		return replyDAO.create(model);
	}
	
	public Reply load(Long id) {
	    Reply model = replyDAO.load(id);
	    return model;
    }

	/**
	 * 这个方法会把model所有的字段都赋给model，然后更新model。
	 * @param model
	 */
	public void updateAllFields(Reply dto) {
		Reply model = replyDAO.load(dto.getId());
		BeanMapper.copy(dto, model);
		replyDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				replyDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public Reply findOne(Reply query) {
		Reply model = replyDAO.findOne(query);
		return model;
	}
}
