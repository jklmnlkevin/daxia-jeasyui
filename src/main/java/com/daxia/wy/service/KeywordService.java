package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.KeywordDAO;
import com.daxia.wy.dto.KeywordDTO;
import com.daxia.wy.model.Keyword;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class KeywordService {
	
	@Autowired
	private KeywordDAO keywordDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<KeywordDTO> find(KeywordDTO dto, Page page) {
		List<Keyword> models = keywordDAO.find(dto, page);
		List<KeywordDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<KeywordDTO> toDTOs(List<Keyword> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<KeywordDTO>(0);
		}
		List<KeywordDTO> dtos = new ArrayList<KeywordDTO>(models.size());
		for (Keyword model : models) {
	        KeywordDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private KeywordDTO toDTO(Keyword model) {
		if (model == null) {
			return null;
		}
		KeywordDTO dto = BeanMapper.map(model, KeywordDTO.class);
		
		return dto;
	}
	
	public void create(KeywordDTO dto) {
		Keyword model = new Keyword();
		toModel(model, dto);
		keywordDAO.create(model);
	}

	private void toModel(Keyword model, KeywordDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Keyword> toModels(List<KeywordDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Keyword>(0);
		}
		List<Keyword> models = new ArrayList<Keyword>(dtos.size());
		for (KeywordDTO dto : dtos) {
	        Keyword model = new Keyword();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public KeywordDTO load(Long id) {
	    Keyword model = keywordDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(KeywordDTO dto) {
		Keyword model = keywordDAO.load(dto.getId());
		toModel(model, dto);
		keywordDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				keywordDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public KeywordDTO findOne(KeywordDTO dto) {
		Keyword model = keywordDAO.findOne(dto);
		return toDTO(model);
	}
}
