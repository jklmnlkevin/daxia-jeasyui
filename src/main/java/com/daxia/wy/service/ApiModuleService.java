package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.ApiModuleDAO;
import com.daxia.wy.dto.ApiModuleDTO;
import com.daxia.wy.model.ApiModule;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。

 *
 */
@Service
public class ApiModuleService {
	
	@Autowired
	private ApiModuleDAO apiModuleDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<ApiModuleDTO> find(ApiModuleDTO dto, Page page) {
		List<ApiModule> models = apiModuleDAO.find(dto, page);
		List<ApiModuleDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<ApiModuleDTO> toDTOs(List<ApiModule> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<ApiModuleDTO>(0);
		}
		List<ApiModuleDTO> dtos = new ArrayList<ApiModuleDTO>(models.size());
		for (ApiModule model : models) {
	        ApiModuleDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private ApiModuleDTO toDTO(ApiModule model) {
		if (model == null) {
			return null;
		}
		ApiModuleDTO dto = BeanMapper.map(model, ApiModuleDTO.class);
		
		return dto;
	}
	
	public void create(ApiModuleDTO dto) {
		ApiModule model = new ApiModule();
		toModel(model, dto);
		apiModuleDAO.create(model);
	}

	private void toModel(ApiModule model, ApiModuleDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<ApiModule> toModels(List<ApiModuleDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<ApiModule>(0);
		}
		List<ApiModule> models = new ArrayList<ApiModule>(dtos.size());
		for (ApiModuleDTO dto : dtos) {
	        ApiModule model = new ApiModule();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public ApiModuleDTO load(Long id) {
	    ApiModule model = apiModuleDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(ApiModuleDTO dto) {
		ApiModule model = apiModuleDAO.load(dto.getId());
		toModel(model, dto);
		apiModuleDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				apiModuleDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public ApiModuleDTO findOne(ApiModuleDTO dto) {
		ApiModule model = apiModuleDAO.findOne(dto);
		return toDTO(model);
	}
}
