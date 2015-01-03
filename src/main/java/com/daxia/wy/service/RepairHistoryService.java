package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.RepairHistoryDAO;
import com.daxia.wy.dto.RepairHistoryDTO;
import com.daxia.wy.model.RepairHistory;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class RepairHistoryService {
	
	@Autowired
	private RepairHistoryDAO repairHistoryDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<RepairHistoryDTO> find(RepairHistoryDTO dto, Page page) {
		List<RepairHistory> models = repairHistoryDAO.find(dto, page);
		List<RepairHistoryDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<RepairHistoryDTO> toDTOs(List<RepairHistory> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<RepairHistoryDTO>(0);
		}
		List<RepairHistoryDTO> dtos = new ArrayList<RepairHistoryDTO>(models.size());
		for (RepairHistory model : models) {
	        RepairHistoryDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private RepairHistoryDTO toDTO(RepairHistory model) {
		if (model == null) {
			return null;
		}
		RepairHistoryDTO dto = BeanMapper.map(model, RepairHistoryDTO.class);
		
		return dto;
	}
	
	public void create(RepairHistoryDTO dto) {
		RepairHistory model = new RepairHistory();
		toModel(model, dto);
		repairHistoryDAO.create(model);
	}

	private void toModel(RepairHistory model, RepairHistoryDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<RepairHistory> toModels(List<RepairHistoryDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<RepairHistory>(0);
		}
		List<RepairHistory> models = new ArrayList<RepairHistory>(dtos.size());
		for (RepairHistoryDTO dto : dtos) {
	        RepairHistory model = new RepairHistory();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public RepairHistoryDTO load(Long id) {
	    RepairHistory model = repairHistoryDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(RepairHistoryDTO dto) {
		RepairHistory model = repairHistoryDAO.load(dto.getId());
		toModel(model, dto);
		repairHistoryDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				repairHistoryDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public RepairHistoryDTO findOne(RepairHistoryDTO dto) {
		RepairHistory model = repairHistoryDAO.findOne(dto);
		return toDTO(model);
	}
}
