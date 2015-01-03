package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.DistrictDAO;
import com.daxia.wy.dto.DistrictDTO;
import com.daxia.wy.model.District;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class DistrictService {
	
	@Autowired
	private DistrictDAO districtDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<DistrictDTO> find(DistrictDTO dto, Page page) {
		List<District> models = districtDAO.find(dto, page);
		List<DistrictDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<DistrictDTO> toDTOs(List<District> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<DistrictDTO>(0);
		}
		List<DistrictDTO> dtos = new ArrayList<DistrictDTO>(models.size());
		for (District model : models) {
	        DistrictDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private DistrictDTO toDTO(District model) {
		if (model == null) {
			return null;
		}
		DistrictDTO dto = BeanMapper.map(model, DistrictDTO.class);
		
		return dto;
	}
	
	public void create(DistrictDTO dto) {
		District model = new District();
		toModel(model, dto);
		districtDAO.create(model);
	}

	private void toModel(District model, DistrictDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<District> toModels(List<DistrictDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<District>(0);
		}
		List<District> models = new ArrayList<District>(dtos.size());
		for (DistrictDTO dto : dtos) {
	        District model = new District();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public DistrictDTO load(Long id) {
	    District model = districtDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(DistrictDTO dto) {
		District model = districtDAO.load(dto.getId());
		toModel(model, dto);
		districtDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				districtDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public DistrictDTO findOne(DistrictDTO dto) {
		District model = districtDAO.findOne(dto);
		return toDTO(model);
	}
}
