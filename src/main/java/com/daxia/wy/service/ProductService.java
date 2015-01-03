package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.ProductDAO;
import com.daxia.wy.dto.ProductDTO;
import com.daxia.wy.model.Product;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<ProductDTO> find(ProductDTO dto, Page page) {
		List<Product> models = productDAO.find(dto, page);
		List<ProductDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<ProductDTO> toDTOs(List<Product> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<ProductDTO>(0);
		}
		List<ProductDTO> dtos = new ArrayList<ProductDTO>(models.size());
		for (Product model : models) {
	        ProductDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private ProductDTO toDTO(Product model) {
		if (model == null) {
			return null;
		}
		ProductDTO dto = BeanMapper.map(model, ProductDTO.class);
		
		return dto;
	}
	
	public void create(ProductDTO dto) {
		Product model = new Product();
		toModel(model, dto);
		productDAO.create(model);
	}

	private void toModel(Product model, ProductDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Product> toModels(List<ProductDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Product>(0);
		}
		List<Product> models = new ArrayList<Product>(dtos.size());
		for (ProductDTO dto : dtos) {
	        Product model = new Product();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public ProductDTO load(Long id) {
	    Product model = productDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(ProductDTO dto) {
		Product model = productDAO.load(dto.getId());
		toModel(model, dto);
		productDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				productDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public ProductDTO findOne(ProductDTO dto) {
		Product model = productDAO.findOne(dto);
		return toDTO(model);
	}
}
