package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.wy.common.QuestionStatus;
import com.daxia.wy.dao.QuestionDAO;
import com.daxia.wy.dao.QuestionReplyDAO;
import com.daxia.wy.dto.QuestionReplyDTO;
import com.daxia.wy.model.Question;
import com.daxia.wy.model.QuestionReply;
import com.daxia.core.common.UserType;
import com.daxia.core.dao.UserDAO;
import com.daxia.core.model.User;
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
public class QuestionReplyService {
	
	@Autowired
	private QuestionReplyDAO questionReplyDAO;
	@Autowired
	private QuestionDAO questionDAO;
	@Autowired
	private UserDAO userDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<QuestionReplyDTO> find(QuestionReplyDTO dto, Page page) {
		List<QuestionReply> models = questionReplyDAO.find(dto, page);
		List<QuestionReplyDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<QuestionReplyDTO> toDTOs(List<QuestionReply> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<QuestionReplyDTO>(0);
		}
		List<QuestionReplyDTO> dtos = new ArrayList<QuestionReplyDTO>(models.size());
		for (QuestionReply model : models) {
	        QuestionReplyDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private QuestionReplyDTO toDTO(QuestionReply model) {
		if (model == null) {
			return null;
		}
		QuestionReplyDTO dto = BeanMapper.map(model, QuestionReplyDTO.class);
		
		return dto;
	}
	
	public void create(QuestionReplyDTO dto) {
		QuestionReply model = new QuestionReply();
		toModel(model, dto);
		questionReplyDAO.create(model);
		
		User user = userDAO.load(dto.getUser().getId());
		if (user.getUserType() == UserType.EstateManager.getValue()) {
		    Question question = questionDAO.load(dto.getQuestion().getId());
		    question.setStatus(QuestionStatus.Answered.getValue());
		    questionDAO.update(question);
		}
	}

	private void toModel(QuestionReply model, QuestionReplyDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<QuestionReply> toModels(List<QuestionReplyDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<QuestionReply>(0);
		}
		List<QuestionReply> models = new ArrayList<QuestionReply>(dtos.size());
		for (QuestionReplyDTO dto : dtos) {
	        QuestionReply model = new QuestionReply();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public QuestionReplyDTO load(Long id) {
	    QuestionReply model = questionReplyDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(QuestionReplyDTO dto) {
		QuestionReply model = questionReplyDAO.load(dto.getId());
		toModel(model, dto);
		questionReplyDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				questionReplyDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public QuestionReplyDTO findOne(QuestionReplyDTO dto) {
		QuestionReply model = questionReplyDAO.findOne(dto);
		return toDTO(model);
	}
}
