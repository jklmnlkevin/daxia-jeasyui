package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.CityDAO;
import com.daxia.wy.dto.CityDTO;
import com.daxia.wy.model.City;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class CityService {
	
	@Autowired
	private CityDAO cityDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<CityDTO> find(CityDTO dto, Page page) {
		List<City> models = cityDAO.find(dto, page);
		List<CityDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<CityDTO> toDTOs(List<City> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<CityDTO>(0);
		}
		List<CityDTO> dtos = new ArrayList<CityDTO>(models.size());
		for (City model : models) {
	        CityDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private CityDTO toDTO(City model) {
		if (model == null) {
			return null;
		}
		CityDTO dto = BeanMapper.map(model, CityDTO.class);
		
		return dto;
	}
	
	public void create(CityDTO dto) {
		City model = new City();
		toModel(model, dto);
		cityDAO.create(model);
	}

	private void toModel(City model, CityDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<City> toModels(List<CityDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<City>(0);
		}
		List<City> models = new ArrayList<City>(dtos.size());
		for (CityDTO dto : dtos) {
	        City model = new City();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public CityDTO load(Long id) {
	    City model = cityDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(CityDTO dto) {
		City model = cityDAO.load(dto.getId());
		toModel(model, dto);
		cityDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				cityDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public CityDTO findOne(CityDTO dto) {
		City model = cityDAO.findOne(dto);
		return toDTO(model);
	}
}
