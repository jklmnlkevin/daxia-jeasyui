package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.ConvenienceDAO;
import com.daxia.wy.dto.ConvenienceDTO;
import com.daxia.wy.model.Convenience;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class ConvenienceService {
	
	@Autowired
	private ConvenienceDAO convenienceDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<ConvenienceDTO> find(ConvenienceDTO dto, Page page) {
		List<Convenience> models = convenienceDAO.find(dto, page);
		List<ConvenienceDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<ConvenienceDTO> toDTOs(List<Convenience> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<ConvenienceDTO>(0);
		}
		List<ConvenienceDTO> dtos = new ArrayList<ConvenienceDTO>(models.size());
		for (Convenience model : models) {
	        ConvenienceDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private ConvenienceDTO toDTO(Convenience model) {
		if (model == null) {
			return null;
		}
		ConvenienceDTO dto = BeanMapper.map(model, ConvenienceDTO.class);
		
		return dto;
	}
	
	public void create(ConvenienceDTO dto) {
		Convenience model = new Convenience();
		toModel(model, dto);
		convenienceDAO.create(model);
	}

	private void toModel(Convenience model, ConvenienceDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Convenience> toModels(List<ConvenienceDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Convenience>(0);
		}
		List<Convenience> models = new ArrayList<Convenience>(dtos.size());
		for (ConvenienceDTO dto : dtos) {
	        Convenience model = new Convenience();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public ConvenienceDTO load(Long id) {
	    Convenience model = convenienceDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(ConvenienceDTO dto) {
		Convenience model = convenienceDAO.load(dto.getId());
		toModel(model, dto);
		convenienceDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				convenienceDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public ConvenienceDTO findOne(ConvenienceDTO dto) {
		Convenience model = convenienceDAO.findOne(dto);
		return toDTO(model);
	}
}
