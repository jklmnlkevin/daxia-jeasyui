package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daxia.core.support.MPage;
import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.TopicDAO;
import com.daxia.wy.dao.TopicReplyDAO;
import com.daxia.wy.dto.TopicReplyDTO;
import com.daxia.wy.model.Topic;
import com.daxia.wy.model.TopicReply;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class TopicReplyService {
	
    @Autowired
    private TopicDAO topicDAO;
	@Autowired
	private TopicReplyDAO topicReplyDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<TopicReplyDTO> find(TopicReplyDTO dto, Page page) {
		List<TopicReply> models = topicReplyDAO.find(dto, page);
		List<TopicReplyDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<TopicReplyDTO> toDTOs(List<TopicReply> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<TopicReplyDTO>(0);
		}
		List<TopicReplyDTO> dtos = new ArrayList<TopicReplyDTO>(models.size());
		for (TopicReply model : models) {
	        TopicReplyDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private TopicReplyDTO toDTO(TopicReply model) {
		if (model == null) {
			return null;
		}
		TopicReplyDTO dto = BeanMapper.map(model, TopicReplyDTO.class);
		
		return dto;
	}
	
	public Long create(TopicReplyDTO dto) {
		TopicReply model = new TopicReply();
		toModel(model, dto);
		return topicReplyDAO.create(model);
	}

	private void toModel(TopicReply model, TopicReplyDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<TopicReply> toModels(List<TopicReplyDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<TopicReply>(0);
		}
		List<TopicReply> models = new ArrayList<TopicReply>(dtos.size());
		for (TopicReplyDTO dto : dtos) {
	        TopicReply model = new TopicReply();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public TopicReplyDTO load(Long id) {
	    TopicReply model = topicReplyDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(TopicReplyDTO dto) {
		TopicReply model = topicReplyDAO.load(dto.getId());
		toModel(model, dto);
		topicReplyDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				topicReplyDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public TopicReplyDTO findOne(TopicReplyDTO dto) {
		TopicReply model = topicReplyDAO.findOne(dto);
		return toDTO(model);
	}
	
	@Transactional
    public Long reply(TopicReplyDTO replyDTO) {
        Topic topic = topicDAO.load(replyDTO.getTopic().getId());
        Integer floor = topic.getCurrentFloor() + 1;
        replyDTO.setFloor(floor);
        topic.setReplyCount(topic.getReplyCount() + 1);
        long replyId = this.create(replyDTO);
        
        topic.setCurrentFloor(floor);
        topicDAO.update(topic);
        return replyId;
    }

    public List<TopicReplyDTO> findByTopic(Long id, boolean isEstate, MPage page) {
        TopicReplyDTO query = new TopicReplyDTO();
        query.setTopic(new Topic());
        query.getTopic().setId(id);
        query.setIsEstate(isEstate);
        return this.find(query, page); 
    }
}
