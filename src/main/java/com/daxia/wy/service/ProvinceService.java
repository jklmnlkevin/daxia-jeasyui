package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.ProvinceDAO;
import com.daxia.wy.dto.ProvinceDTO;
import com.daxia.wy.model.Province;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class ProvinceService {
	
	@Autowired
	private ProvinceDAO provinceDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<ProvinceDTO> find(ProvinceDTO dto, Page page) {
		List<Province> models = provinceDAO.find(dto, page);
		List<ProvinceDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<ProvinceDTO> toDTOs(List<Province> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<ProvinceDTO>(0);
		}
		List<ProvinceDTO> dtos = new ArrayList<ProvinceDTO>(models.size());
		for (Province model : models) {
	        ProvinceDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private ProvinceDTO toDTO(Province model) {
		if (model == null) {
			return null;
		}
		ProvinceDTO dto = BeanMapper.map(model, ProvinceDTO.class);
		
		return dto;
	}
	
	public void create(ProvinceDTO dto) {
		Province model = new Province();
		toModel(model, dto);
		provinceDAO.create(model);
	}

	private void toModel(Province model, ProvinceDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Province> toModels(List<ProvinceDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Province>(0);
		}
		List<Province> models = new ArrayList<Province>(dtos.size());
		for (ProvinceDTO dto : dtos) {
	        Province model = new Province();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public ProvinceDTO load(Long id) {
	    Province model = provinceDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(ProvinceDTO dto) {
		Province model = provinceDAO.load(dto.getId());
		toModel(model, dto);
		provinceDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				provinceDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public ProvinceDTO findOne(ProvinceDTO dto) {
		Province model = provinceDAO.findOne(dto);
		return toDTO(model);
	}
}
