package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.ApiTestParameterDAO;
import com.daxia.wy.dto.ApiTestParameterDTO;
import com.daxia.wy.model.ApiTestParameter;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class ApiTestParameterService {
	
	@Autowired
	private ApiTestParameterDAO apiTestParameterDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<ApiTestParameterDTO> find(ApiTestParameterDTO dto, Page page) {
		List<ApiTestParameter> models = apiTestParameterDAO.find(dto, page);
		List<ApiTestParameterDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<ApiTestParameterDTO> toDTOs(List<ApiTestParameter> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<ApiTestParameterDTO>(0);
		}
		List<ApiTestParameterDTO> dtos = new ArrayList<ApiTestParameterDTO>(models.size());
		for (ApiTestParameter model : models) {
	        ApiTestParameterDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private ApiTestParameterDTO toDTO(ApiTestParameter model) {
		if (model == null) {
			return null;
		}
		ApiTestParameterDTO dto = BeanMapper.map(model, ApiTestParameterDTO.class);
		
		return dto;
	}
	
	public void create(ApiTestParameterDTO dto) {
		ApiTestParameter model = new ApiTestParameter();
		toModel(model, dto);
		apiTestParameterDAO.create(model);
	}

	private void toModel(ApiTestParameter model, ApiTestParameterDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<ApiTestParameter> toModels(List<ApiTestParameterDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<ApiTestParameter>(0);
		}
		List<ApiTestParameter> models = new ArrayList<ApiTestParameter>(dtos.size());
		for (ApiTestParameterDTO dto : dtos) {
	        ApiTestParameter model = new ApiTestParameter();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public ApiTestParameterDTO load(Long id) {
	    ApiTestParameter model = apiTestParameterDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(ApiTestParameterDTO dto) {
		ApiTestParameter model = apiTestParameterDAO.load(dto.getId());
		toModel(model, dto);
		apiTestParameterDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				apiTestParameterDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public ApiTestParameterDTO findOne(ApiTestParameterDTO dto) {
		ApiTestParameter model = apiTestParameterDAO.findOne(dto);
		return toDTO(model);
	}
}
