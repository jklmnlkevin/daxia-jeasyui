package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.AdviseReplyDAO;
import com.daxia.wy.dto.AdviseReplyDTO;
import com.daxia.wy.model.AdviseReply;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class AdviseReplyService {
	
	@Autowired
	private AdviseReplyDAO adviseReplyDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<AdviseReplyDTO> find(AdviseReplyDTO dto, Page page) {
		List<AdviseReply> models = adviseReplyDAO.find(dto, page);
		List<AdviseReplyDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<AdviseReplyDTO> toDTOs(List<AdviseReply> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<AdviseReplyDTO>(0);
		}
		List<AdviseReplyDTO> dtos = new ArrayList<AdviseReplyDTO>(models.size());
		for (AdviseReply model : models) {
	        AdviseReplyDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private AdviseReplyDTO toDTO(AdviseReply model) {
		if (model == null) {
			return null;
		}
		AdviseReplyDTO dto = BeanMapper.map(model, AdviseReplyDTO.class);
		
		return dto;
	}
	
	public void create(AdviseReplyDTO dto) {
		AdviseReply model = new AdviseReply();
		toModel(model, dto);
		adviseReplyDAO.create(model);
	}

	private void toModel(AdviseReply model, AdviseReplyDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<AdviseReply> toModels(List<AdviseReplyDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<AdviseReply>(0);
		}
		List<AdviseReply> models = new ArrayList<AdviseReply>(dtos.size());
		for (AdviseReplyDTO dto : dtos) {
	        AdviseReply model = new AdviseReply();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public AdviseReplyDTO load(Long id) {
	    AdviseReply model = adviseReplyDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(AdviseReplyDTO dto) {
		AdviseReply model = adviseReplyDAO.load(dto.getId());
		toModel(model, dto);
		adviseReplyDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				adviseReplyDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public AdviseReplyDTO findOne(AdviseReplyDTO dto) {
		AdviseReply model = adviseReplyDAO.findOne(dto);
		return toDTO(model);
	}
}
