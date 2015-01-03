package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.ApiTestDAO;
import com.daxia.wy.dto.ApiTestDTO;
import com.daxia.wy.model.ApiTest;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class ApiTestService {
	
	@Autowired
	private ApiTestDAO apiTestDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<ApiTestDTO> find(ApiTestDTO dto, Page page) {
		List<ApiTest> models = apiTestDAO.find(dto, page);
		List<ApiTestDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<ApiTestDTO> toDTOs(List<ApiTest> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<ApiTestDTO>(0);
		}
		List<ApiTestDTO> dtos = new ArrayList<ApiTestDTO>(models.size());
		for (ApiTest model : models) {
	        ApiTestDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private ApiTestDTO toDTO(ApiTest model) {
		if (model == null) {
			return null;
		}
		ApiTestDTO dto = BeanMapper.map(model, ApiTestDTO.class);
		
		return dto;
	}
	
	public void create(ApiTestDTO dto) {
		ApiTest model = new ApiTest();
		toModel(model, dto);
		apiTestDAO.create(model);
	}

	private void toModel(ApiTest model, ApiTestDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<ApiTest> toModels(List<ApiTestDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<ApiTest>(0);
		}
		List<ApiTest> models = new ArrayList<ApiTest>(dtos.size());
		for (ApiTestDTO dto : dtos) {
	        ApiTest model = new ApiTest();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public ApiTestDTO load(Long id) {
	    ApiTest model = apiTestDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(ApiTestDTO dto) {
		ApiTest model = apiTestDAO.load(dto.getId());
		toModel(model, dto);
		apiTestDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				apiTestDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public ApiTestDTO findOne(ApiTestDTO dto) {
		ApiTest model = apiTestDAO.findOne(dto);
		return toDTO(model);
	}
}
