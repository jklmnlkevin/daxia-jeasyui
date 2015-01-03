package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.TopicDAO;
import com.daxia.wy.dto.TopicDTO;
import com.daxia.wy.model.Topic;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class TopicService {
	
	@Autowired
	private TopicDAO topicDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<TopicDTO> find(TopicDTO dto, Page page) {
		List<Topic> models = topicDAO.find(dto, page);
		List<TopicDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<TopicDTO> toDTOs(List<Topic> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<TopicDTO>(0);
		}
		List<TopicDTO> dtos = new ArrayList<TopicDTO>(models.size());
		for (Topic model : models) {
	        TopicDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private TopicDTO toDTO(Topic model) {
		if (model == null) {
			return null;
		}
		TopicDTO dto = BeanMapper.map(model, TopicDTO.class);
		
		return dto;
	}
	
	public Long create(TopicDTO dto) {
		Topic model = new Topic();
		toModel(model, dto);
		if (model.getCreateTime() == null) {
		    model.setCreateTime(new Date());
		}
		return topicDAO.create(model);
	}

	private void toModel(Topic model, TopicDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Topic> toModels(List<TopicDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Topic>(0);
		}
		List<Topic> models = new ArrayList<Topic>(dtos.size());
		for (TopicDTO dto : dtos) {
	        Topic model = new Topic();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public TopicDTO load(Long id) {
	    Topic model = topicDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(TopicDTO dto) {
		Topic model = topicDAO.load(dto.getId());
		toModel(model, dto);
		topicDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				topicDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public TopicDTO findOne(TopicDTO dto) {
		Topic model = topicDAO.findOne(dto);
		return toDTO(model);
	}
	
	public void addClick(Long topicId) {
	    topicDAO.addClick(topicId);
	}
}
