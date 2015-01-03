package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.wy.dao.FeeItemDAO;
import com.daxia.wy.dto.FeeItemDTO;
import com.daxia.wy.model.FeeItem;
import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class FeeItemService {
	
	@Autowired
	private FeeItemDAO feeItemDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<FeeItemDTO> find(FeeItemDTO dto, Page page) {
		List<FeeItem> models = feeItemDAO.find(dto, page);
		List<FeeItemDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<FeeItemDTO> toDTOs(List<FeeItem> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<FeeItemDTO>(0);
		}
		List<FeeItemDTO> dtos = new ArrayList<FeeItemDTO>(models.size());
		for (FeeItem model : models) {
	        FeeItemDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private FeeItemDTO toDTO(FeeItem model) {
		if (model == null) {
			return null;
		}
		FeeItemDTO dto = BeanMapper.map(model, FeeItemDTO.class);
		
		return dto;
	}
	
	public void create(FeeItemDTO dto) {
		FeeItem model = new FeeItem();
		toModel(model, dto);
		feeItemDAO.create(model);
	}

	private void toModel(FeeItem model, FeeItemDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<FeeItem> toModels(List<FeeItemDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<FeeItem>(0);
		}
		List<FeeItem> models = new ArrayList<FeeItem>(dtos.size());
		for (FeeItemDTO dto : dtos) {
	        FeeItem model = new FeeItem();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public FeeItemDTO load(Long id) {
	    FeeItem model = feeItemDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(FeeItemDTO dto) {
		FeeItem model = feeItemDAO.load(dto.getId());
		toModel(model, dto);
		feeItemDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				feeItemDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public FeeItemDTO findOne(FeeItemDTO dto) {
		FeeItem model = feeItemDAO.findOne(dto);
		return toDTO(model);
	}
}
