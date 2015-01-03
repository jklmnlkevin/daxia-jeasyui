package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.RepairReplyDAO;
import com.daxia.wy.dto.RepairReplyDTO;
import com.daxia.wy.model.RepairReply;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class RepairReplyService {
	
	@Autowired
	private RepairReplyDAO repairReplyDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<RepairReplyDTO> find(RepairReplyDTO dto, Page page) {
		List<RepairReply> models = repairReplyDAO.find(dto, page);
		List<RepairReplyDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<RepairReplyDTO> toDTOs(List<RepairReply> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<RepairReplyDTO>(0);
		}
		List<RepairReplyDTO> dtos = new ArrayList<RepairReplyDTO>(models.size());
		for (RepairReply model : models) {
	        RepairReplyDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private RepairReplyDTO toDTO(RepairReply model) {
		if (model == null) {
			return null;
		}
		RepairReplyDTO dto = BeanMapper.map(model, RepairReplyDTO.class);
		
		return dto;
	}
	
	public Long create(RepairReplyDTO dto) {
		RepairReply model = new RepairReply();
		toModel(model, dto);
		return repairReplyDAO.create(model);
	}

	private void toModel(RepairReply model, RepairReplyDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<RepairReply> toModels(List<RepairReplyDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<RepairReply>(0);
		}
		List<RepairReply> models = new ArrayList<RepairReply>(dtos.size());
		for (RepairReplyDTO dto : dtos) {
	        RepairReply model = new RepairReply();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public RepairReplyDTO load(Long id) {
	    RepairReply model = repairReplyDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(RepairReplyDTO dto) {
		RepairReply model = repairReplyDAO.load(dto.getId());
		toModel(model, dto);
		repairReplyDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				repairReplyDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public RepairReplyDTO findOne(RepairReplyDTO dto) {
		RepairReply model = repairReplyDAO.findOne(dto);
		return toDTO(model);
	}
}
