package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.CommunityDAO;
import com.daxia.wy.dto.CommunityDTO;
import com.daxia.wy.model.Community;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class CommunityService {
	
	@Autowired
	private CommunityDAO communityDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<CommunityDTO> find(CommunityDTO dto, Page page) {
		List<Community> models = communityDAO.find(dto, page);
		List<CommunityDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<CommunityDTO> toDTOs(List<Community> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<CommunityDTO>(0);
		}
		List<CommunityDTO> dtos = new ArrayList<CommunityDTO>(models.size());
		for (Community model : models) {
	        CommunityDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private CommunityDTO toDTO(Community model) {
		if (model == null) {
			return null;
		}
		CommunityDTO dto = BeanMapper.map(model, CommunityDTO.class);
		
		return dto;
	}
	
	public void create(CommunityDTO dto) {
		Community model = new Community();
		toModel(model, dto);
		communityDAO.create(model);
	}

	private void toModel(Community model, CommunityDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Community> toModels(List<CommunityDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Community>(0);
		}
		List<Community> models = new ArrayList<Community>(dtos.size());
		for (CommunityDTO dto : dtos) {
	        Community model = new Community();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public CommunityDTO load(Long id) {
	    Community model = communityDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(CommunityDTO dto) {
		Community model = communityDAO.load(dto.getId());
		toModel(model, dto);
		communityDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				communityDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public CommunityDTO findOne(CommunityDTO dto) {
		Community model = communityDAO.findOne(dto);
		return toDTO(model);
	}
}
