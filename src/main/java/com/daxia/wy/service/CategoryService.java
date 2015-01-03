package com.daxia.wy.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.CategoryDAO;
import com.daxia.wy.dao.ProductDAO;
import com.daxia.wy.dto.CategoryDTO;
import com.daxia.wy.model.Category;
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
public class CategoryService {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<CategoryDTO> find(CategoryDTO dto, Page page) {
		List<Category> models = categoryDAO.find(dto, page);
		List<CategoryDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<CategoryDTO> toDTOs(List<Category> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<CategoryDTO>(0);
		}
		List<CategoryDTO> dtos = new ArrayList<CategoryDTO>(models.size());
		for (Category model : models) {
	        CategoryDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private CategoryDTO toDTO(Category model) {
		if (model == null) {
			return null;
		}
		CategoryDTO dto = BeanMapper.map(model, CategoryDTO.class);
		
		return dto;
	}
	
	public void create(CategoryDTO dto) {
		Category model = new Category();
		toModel(model, dto);
		categoryDAO.create(model);
	}

	private void toModel(Category model, CategoryDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Category> toModels(List<CategoryDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Category>(0);
		}
		List<Category> models = new ArrayList<Category>(dtos.size());
		for (CategoryDTO dto : dtos) {
	        Category model = new Category();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public CategoryDTO load(Long id) {
	    Category model = categoryDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(CategoryDTO dto) {
		Category model = categoryDAO.load(dto.getId());
		toModel(model, dto);
		categoryDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				categoryDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public CategoryDTO findOne(CategoryDTO dto) {
		Category model = categoryDAO.findOne(dto);
		return toDTO(model);
	}

    public void createCategory() throws Exception {

         Map<Long, String> categoryUrl = new HashMap<Long, String>();
            // 冻肉制品
            categoryUrl.put(6L, "http://www.yhd.com/ctg/s2/c33622-0-60390/k%E7%94%9F%E9%B2%9C/?tc=3.0.9.60390.1&tp=51.%E7%94%9F%E9%B2%9C.114.0.2.UvFj0S");
            // 水产品
            categoryUrl.put(7L, "http://www.yhd.com/ctg/s2/c33623-0-60396/k%E7%94%9F%E9%B2%9C/?tc=3.0.9.60396.7&tp=51.%E7%94%9F%E9%B2%9C.114.0.8.UvFkjG");
            // 新鲜水果
            categoryUrl.put(8L, "http://www.yhd.com/ctg/s2/c33618-0-60364/k%E7%94%9F%E9%B2%9C/?tc=3.0.9.60364.21&tp=51.%E7%94%9F%E9%B2%9C.114.0.22.UvFvR|");
            // 新鲜蔬菜
            categoryUrl.put(9L, "http://www.yhd.com/ctg/s2/c33619-0-60367/k%E7%94%9F%E9%B2%9C/?tc=3.0.9.60367.51&tp=51.%E7%94%9F%E9%B2%9C.114.0.52.UvFyDJ");
            
            for (long catId = 6; catId <= 9; catId++) {
                String url = categoryUrl.get(catId);
                Document doc = Jsoup.connect(url).get();
                //System.out.println(doc.text());
                Elements lis = doc.select(".search_item");
                for (int i = 0; i < lis.size(); i++) {
                    Element li = lis.get(i);
                    String price = li.select(".pricebox").text().split(" ")[0];
                    String description = li.select(".title").text();
                    String image = li.select(".search_prod_img img").attr("original");
                    if (StringUtils.isBlank(image)) {
                        continue;
                    }
                    String name = description.split(" ")[1];
                    System.out.println(price);
                    System.out.println(name);
                    System.out.println(image);
                    
                    Category category = categoryDAO.load(catId);
                    Product product = new Product();
                    product.setCategory(category);
                    // product.setCommunity(community);
                    product.setImage(image);
                    product.setName(name);
                    price = price.replace("¥", "");
                    product.setPrice(new BigDecimal(price));
                    product.setRemark(description);
                    product.setSpecification("元/件");
                    product.setUnit("件");
                    
                    productDAO.create(product);
                }
                
            }
    }
    
    @Autowired
    private ProductDAO productDAO;
}
