package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.NatureDAO;
import com.daxia.wy.dto.NatureDTO;
import com.daxia.wy.model.Nature;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class NatureService {
	
	@Autowired
	private NatureDAO natureDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<NatureDTO> find(NatureDTO dto, Page page) {
		List<Nature> models = natureDAO.find(dto, page);
		List<NatureDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<NatureDTO> toDTOs(List<Nature> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<NatureDTO>(0);
		}
		List<NatureDTO> dtos = new ArrayList<NatureDTO>(models.size());
		for (Nature model : models) {
	        NatureDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private NatureDTO toDTO(Nature model) {
		if (model == null) {
			return null;
		}
		NatureDTO dto = BeanMapper.map(model, NatureDTO.class);
		
		return dto;
	}
	
	public void create(NatureDTO dto) {
		Nature model = new Nature();
		toModel(model, dto);
		natureDAO.create(model);
	}

	private void toModel(Nature model, NatureDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Nature> toModels(List<NatureDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Nature>(0);
		}
		List<Nature> models = new ArrayList<Nature>(dtos.size());
		for (NatureDTO dto : dtos) {
	        Nature model = new Nature();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public NatureDTO load(Long id) {
	    Nature model = natureDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(NatureDTO dto) {
		Nature model = natureDAO.load(dto.getId());
		toModel(model, dto);
		natureDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				natureDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public NatureDTO findOne(NatureDTO dto) {
		Nature model = natureDAO.findOne(dto);
		return toDTO(model);
	}
}
