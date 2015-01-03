package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.DoorplateDAO;
import com.daxia.wy.dto.DoorplateDTO;
import com.daxia.wy.model.Doorplate;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class DoorplateService {
	
	@Autowired
	private DoorplateDAO doorplateDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<DoorplateDTO> find(DoorplateDTO dto, Page page) {
		List<Doorplate> models = doorplateDAO.find(dto, page);
		List<DoorplateDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<DoorplateDTO> toDTOs(List<Doorplate> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<DoorplateDTO>(0);
		}
		List<DoorplateDTO> dtos = new ArrayList<DoorplateDTO>(models.size());
		for (Doorplate model : models) {
	        DoorplateDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private DoorplateDTO toDTO(Doorplate model) {
		if (model == null) {
			return null;
		}
		DoorplateDTO dto = BeanMapper.map(model, DoorplateDTO.class);
		
		return dto;
	}
	
	public void create(DoorplateDTO dto) {
		Doorplate model = new Doorplate();
		toModel(model, dto);
		doorplateDAO.create(model);
	}

	private void toModel(Doorplate model, DoorplateDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Doorplate> toModels(List<DoorplateDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Doorplate>(0);
		}
		List<Doorplate> models = new ArrayList<Doorplate>(dtos.size());
		for (DoorplateDTO dto : dtos) {
	        Doorplate model = new Doorplate();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public DoorplateDTO load(Long id) {
	    Doorplate model = doorplateDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(DoorplateDTO dto) {
		Doorplate model = doorplateDAO.load(dto.getId());
		toModel(model, dto);
		doorplateDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				doorplateDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public DoorplateDTO findOne(DoorplateDTO dto) {
		Doorplate model = doorplateDAO.findOne(dto);
		return toDTO(model);
	}
}
