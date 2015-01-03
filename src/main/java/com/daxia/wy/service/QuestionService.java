package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.common.MessagePushType;
import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.common.QuestionStatus;
import com.daxia.wy.dao.QuestionDAO;
import com.daxia.wy.dto.QuestionDTO;
import com.daxia.wy.model.Question;
import com.daxia.wy.utils.MessagePushUtils;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class QuestionService {
	
	@Autowired
	private QuestionDAO questionDAO;
	@Autowired
	private MessagePushUtils messagePushUtils;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<QuestionDTO> find(QuestionDTO dto, Page page) {
		List<Question> models = questionDAO.find(dto, page);
		List<QuestionDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<QuestionDTO> toDTOs(List<Question> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<QuestionDTO>(0);
		}
		List<QuestionDTO> dtos = new ArrayList<QuestionDTO>(models.size());
		for (Question model : models) {
	        QuestionDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private QuestionDTO toDTO(Question model) {
		if (model == null) {
			return null;
		}
		QuestionDTO dto = BeanMapper.map(model, QuestionDTO.class);
		
		return dto;
	}
	
	public Long create(QuestionDTO dto) {
		Question model = new Question();
		toModel(model, dto);
		return questionDAO.create(model);
	}

	private void toModel(Question model, QuestionDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Question> toModels(List<QuestionDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Question>(0);
		}
		List<Question> models = new ArrayList<Question>(dtos.size());
		for (QuestionDTO dto : dtos) {
	        Question model = new Question();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public QuestionDTO load(Long id) {
	    Question model = questionDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(QuestionDTO dto) {
		Question model = questionDAO.load(dto.getId());
		toModel(model, dto);
		boolean isAnswer = false;
		if (StringUtils.isNotBlank(model.getReply())) {
		    model.setStatus(QuestionStatus.Answered.getValue());
		    isAnswer = true;
		}
		questionDAO.update(model);
		
		if (isAnswer) {
		    messagePushUtils.push(model.getUser().getUsername(), MessagePushType.Test, "您的咨询有新的回复", model);
		}
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				questionDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public QuestionDTO findOne(QuestionDTO dto) {
		Question model = questionDAO.findOne(dto);
		return toDTO(model);
	}
}
