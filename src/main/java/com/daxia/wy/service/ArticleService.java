package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.ArticleDAO;
import com.daxia.wy.dto.ArticleDTO;
import com.daxia.wy.model.Article;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class ArticleService {
	
	@Autowired
	private ArticleDAO articleDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<ArticleDTO> find(ArticleDTO dto, Page page) {
		List<Article> models = articleDAO.find(dto, page);
		List<ArticleDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<ArticleDTO> toDTOs(List<Article> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<ArticleDTO>(0);
		}
		List<ArticleDTO> dtos = new ArrayList<ArticleDTO>(models.size());
		for (Article model : models) {
	        ArticleDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private ArticleDTO toDTO(Article model) {
		if (model == null) {
			return null;
		}
		ArticleDTO dto = BeanMapper.map(model, ArticleDTO.class);
		
		return dto;
	}
	
	public Long create(ArticleDTO dto) {
		Article model = new Article();
		toModel(model, dto);
		return articleDAO.create(model);
	}

	private void toModel(Article model, ArticleDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Article> toModels(List<ArticleDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Article>(0);
		}
		List<Article> models = new ArrayList<Article>(dtos.size());
		for (ArticleDTO dto : dtos) {
	        Article model = new Article();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public ArticleDTO load(Long id) {
	    Article model = articleDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(ArticleDTO dto) {
		Article model = articleDAO.load(dto.getId());
		toModel(model, dto);
		articleDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				articleDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public ArticleDTO findOne(ArticleDTO dto) {
		Article model = articleDAO.findOne(dto);
		return toDTO(model);
	}
}
