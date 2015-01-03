package com.daxia.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.dao.SystemLogDAO;
import com.daxia.core.dto.SystemLogDTO;
import com.daxia.core.model.SystemLog;
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
public class SystemLogService {
	
	@Autowired
	private SystemLogDAO systemLogDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<SystemLogDTO> find(SystemLogDTO dto, Page page) {
		List<SystemLog> models = systemLogDAO.find(dto, page);
		List<SystemLogDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<SystemLogDTO> toDTOs(List<SystemLog> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<SystemLogDTO>(0);
		}
		List<SystemLogDTO> dtos = new ArrayList<SystemLogDTO>(models.size());
		for (SystemLog model : models) {
	        SystemLogDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private SystemLogDTO toDTO(SystemLog model) {
		if (model == null) {
			return null;
		}
		SystemLogDTO dto = BeanMapper.map(model, SystemLogDTO.class);
		
		return dto;
	}
	
	public void create(SystemLogDTO dto) {
		SystemLog model = new SystemLog();
		toModel(model, dto);
		systemLogDAO.create(model);
	}

	private void toModel(SystemLog model, SystemLogDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<SystemLog> toModels(List<SystemLogDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<SystemLog>(0);
		}
		List<SystemLog> models = new ArrayList<SystemLog>(dtos.size());
		for (SystemLogDTO dto : dtos) {
	        SystemLog model = new SystemLog();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public SystemLogDTO load(Long id) {
	    SystemLog model = systemLogDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(SystemLogDTO dto) {
		SystemLog model = systemLogDAO.load(dto.getId());
		toModel(model, dto);
		systemLogDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				systemLogDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public SystemLogDTO findOne(SystemLogDTO dto) {
		SystemLog model = systemLogDAO.findOne(dto);
		return toDTO(model);
	}
}
