package com.daxia.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.common.SystemConfigType;
import com.daxia.core.dao.SystemConfigDAO;
import com.daxia.core.dto.SystemConfigDTO;
import com.daxia.core.model.SystemConfig;
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
public class SystemConfigService {
	
	@Autowired
	private SystemConfigDAO systemConfigDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<SystemConfigDTO> find(SystemConfigDTO dto, Page page) {
		List<SystemConfig> models = systemConfigDAO.find(dto, page);
		List<SystemConfigDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<SystemConfigDTO> toDTOs(List<SystemConfig> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<SystemConfigDTO>(0);
		}
		List<SystemConfigDTO> dtos = new ArrayList<SystemConfigDTO>(models.size());
		for (SystemConfig model : models) {
	        SystemConfigDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private SystemConfigDTO toDTO(SystemConfig model) {
		if (model == null) {
			return null;
		}
		SystemConfigDTO dto = BeanMapper.map(model, SystemConfigDTO.class);
		
		return dto;
	}
	
	public void create(SystemConfigDTO dto) {
		SystemConfig model = new SystemConfig();
		toModel(model, dto);
		systemConfigDAO.create(model);
	}

	private void toModel(SystemConfig model, SystemConfigDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<SystemConfig> toModels(List<SystemConfigDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<SystemConfig>(0);
		}
		List<SystemConfig> models = new ArrayList<SystemConfig>(dtos.size());
		for (SystemConfigDTO dto : dtos) {
	        SystemConfig model = new SystemConfig();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public SystemConfigDTO load(Long id) {
	    SystemConfig model = systemConfigDAO.load(id);
	    return toDTO(model);
    }
	
	public SystemConfigDTO load(SystemConfigType type) {
		SystemConfigDTO dto = new SystemConfigDTO();
		dto.setKey(type.value());
	    SystemConfig model = systemConfigDAO.findOne(dto);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(SystemConfigDTO dto) {
		SystemConfig model = systemConfigDAO.load(dto.getId());
		toModel(model, dto);
		systemConfigDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				systemConfigDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public SystemConfigDTO findOne(SystemConfigDTO dto) {
		SystemConfig model = systemConfigDAO.findOne(dto);
		return toDTO(model);
	}
	
	public String get(SystemConfigType type) {
		SystemConfigDTO dto = new SystemConfigDTO();
		dto.setKey(type.value());
		SystemConfig model = systemConfigDAO.findOne(dto);
		if (model == null) {
			return null;
		}
		return model.getValue();
	}
}
