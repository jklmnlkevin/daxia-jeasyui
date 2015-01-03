package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.CommunityAddApplyDAO;
import com.daxia.wy.dto.CommunityAddApplyDTO;
import com.daxia.wy.model.CommunityAddApply;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class CommunityAddApplyService {
	
	@Autowired
	private CommunityAddApplyDAO communityAddApplyDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<CommunityAddApplyDTO> find(CommunityAddApplyDTO dto, Page page) {
		List<CommunityAddApply> models = communityAddApplyDAO.find(dto, page);
		List<CommunityAddApplyDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<CommunityAddApplyDTO> toDTOs(List<CommunityAddApply> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<CommunityAddApplyDTO>(0);
		}
		List<CommunityAddApplyDTO> dtos = new ArrayList<CommunityAddApplyDTO>(models.size());
		for (CommunityAddApply model : models) {
	        CommunityAddApplyDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private CommunityAddApplyDTO toDTO(CommunityAddApply model) {
		if (model == null) {
			return null;
		}
		CommunityAddApplyDTO dto = BeanMapper.map(model, CommunityAddApplyDTO.class);
		
		return dto;
	}
	
	public void create(CommunityAddApplyDTO dto) {
		CommunityAddApply model = new CommunityAddApply();
		toModel(model, dto);
		communityAddApplyDAO.create(model);
	}

	private void toModel(CommunityAddApply model, CommunityAddApplyDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<CommunityAddApply> toModels(List<CommunityAddApplyDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<CommunityAddApply>(0);
		}
		List<CommunityAddApply> models = new ArrayList<CommunityAddApply>(dtos.size());
		for (CommunityAddApplyDTO dto : dtos) {
	        CommunityAddApply model = new CommunityAddApply();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public CommunityAddApplyDTO load(Long id) {
	    CommunityAddApply model = communityAddApplyDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(CommunityAddApplyDTO dto) {
		CommunityAddApply model = communityAddApplyDAO.load(dto.getId());
		toModel(model, dto);
		communityAddApplyDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				communityAddApplyDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public CommunityAddApplyDTO findOne(CommunityAddApplyDTO dto) {
		CommunityAddApply model = communityAddApplyDAO.findOne(dto);
		return toDTO(model);
	}
}
