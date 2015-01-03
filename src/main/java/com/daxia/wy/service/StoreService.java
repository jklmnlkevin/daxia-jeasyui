package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.StoreDAO;
import com.daxia.wy.dto.StoreDTO;
import com.daxia.wy.model.Store;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class StoreService {
	
	@Autowired
	private StoreDAO storeDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<StoreDTO> find(StoreDTO dto, Page page) {
		List<Store> models = storeDAO.find(dto, page);
		List<StoreDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<StoreDTO> toDTOs(List<Store> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<StoreDTO>(0);
		}
		List<StoreDTO> dtos = new ArrayList<StoreDTO>(models.size());
		for (Store model : models) {
	        StoreDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private StoreDTO toDTO(Store model) {
		if (model == null) {
			return null;
		}
		StoreDTO dto = BeanMapper.map(model, StoreDTO.class);
		
		return dto;
	}
	
	public void create(StoreDTO dto) {
		Store model = new Store();
		toModel(model, dto);
		storeDAO.create(model);
	}

	private void toModel(Store model, StoreDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Store> toModels(List<StoreDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Store>(0);
		}
		List<Store> models = new ArrayList<Store>(dtos.size());
		for (StoreDTO dto : dtos) {
	        Store model = new Store();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public StoreDTO load(Long id) {
	    Store model = storeDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(StoreDTO dto) {
		Store model = storeDAO.load(dto.getId());
		toModel(model, dto);
		storeDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				storeDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public StoreDTO findOne(StoreDTO dto) {
		Store model = storeDAO.findOne(dto);
		return toDTO(model);
	}
}
