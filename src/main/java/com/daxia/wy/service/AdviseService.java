package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.AdviseDAO;
import com.daxia.wy.dto.AdviseDTO;
import com.daxia.wy.model.Advise;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class AdviseService {
	
	@Autowired
	private AdviseDAO adviseDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<AdviseDTO> find(AdviseDTO dto, Page page) {
		List<Advise> models = adviseDAO.find(dto, page);
		List<AdviseDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<AdviseDTO> toDTOs(List<Advise> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<AdviseDTO>(0);
		}
		List<AdviseDTO> dtos = new ArrayList<AdviseDTO>(models.size());
		for (Advise model : models) {
	        AdviseDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private AdviseDTO toDTO(Advise model) {
		if (model == null) {
			return null;
		}
		AdviseDTO dto = BeanMapper.map(model, AdviseDTO.class);
		
		return dto;
	}
	
	public Long create(AdviseDTO dto) {
		Advise model = new Advise();
		toModel(model, dto);
		return adviseDAO.create(model);
	}

	private void toModel(Advise model, AdviseDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Advise> toModels(List<AdviseDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Advise>(0);
		}
		List<Advise> models = new ArrayList<Advise>(dtos.size());
		for (AdviseDTO dto : dtos) {
	        Advise model = new Advise();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public AdviseDTO load(Long id) {
	    Advise model = adviseDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(AdviseDTO dto) {
		Advise model = adviseDAO.load(dto.getId());
		toModel(model, dto);
		adviseDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				adviseDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public AdviseDTO findOne(AdviseDTO dto) {
		Advise model = adviseDAO.findOne(dto);
		return toDTO(model);
	}
}
