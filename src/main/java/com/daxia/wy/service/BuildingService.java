package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.BuildingDAO;
import com.daxia.wy.dto.BuildingDTO;
import com.daxia.wy.model.Building;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class BuildingService {
	
	@Autowired
	private BuildingDAO buildingDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<BuildingDTO> find(BuildingDTO dto, Page page) {
		List<Building> models = buildingDAO.find(dto, page);
		List<BuildingDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<BuildingDTO> toDTOs(List<Building> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<BuildingDTO>(0);
		}
		List<BuildingDTO> dtos = new ArrayList<BuildingDTO>(models.size());
		for (Building model : models) {
	        BuildingDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private BuildingDTO toDTO(Building model) {
		if (model == null) {
			return null;
		}
		BuildingDTO dto = BeanMapper.map(model, BuildingDTO.class);
		
		return dto;
	}
	
	public void create(BuildingDTO dto) {
		Building model = new Building();
		toModel(model, dto);
		buildingDAO.create(model);
	}

	private void toModel(Building model, BuildingDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Building> toModels(List<BuildingDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Building>(0);
		}
		List<Building> models = new ArrayList<Building>(dtos.size());
		for (BuildingDTO dto : dtos) {
	        Building model = new Building();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public BuildingDTO load(Long id) {
	    Building model = buildingDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(BuildingDTO dto) {
		Building model = buildingDAO.load(dto.getId());
		toModel(model, dto);
		buildingDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				buildingDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public BuildingDTO findOne(BuildingDTO dto) {
		Building model = buildingDAO.findOne(dto);
		return toDTO(model);
	}
}
