package com.daxia.wy.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Repository;

import com.daxia.core.dao.GenericDAOHibernate;
import com.daxia.core.support.Page;
import com.daxia.wy.dto.ProductDTO;
import com.daxia.wy.model.Product;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。
 * @author Kewen.Zhu
 *
 */
@Repository
public class ProductDAO extends GenericDAOHibernate<Product> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<Product> find(ProductDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Product n where 1 = 1 ");
        appendManagerSql(hql, paras);
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            hql.append(" and n.name = ? ");
            paras.add(dto.getName());
        }		
        if (dto.getCategory() != null) {
        	if (dto.getCategory().getId() != null) {
	            hql.append(" and n.category.id = ? ");
	            paras.add(dto.getCategory().getId());
        	}
        }	
        if (StringUtils.isNotBlank(dto.getSearchTerm())) {
            hql.append(" and (n.name like ? or n.category.name like ? or n.remark like ? ) ");
            paras.add("%" + dto.getSearchTerm() + "%");
            paras.add("%" + dto.getSearchTerm() + "%");
            paras.add("%" + dto.getSearchTerm() + "%");
        }
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    /**
     * 只找出一条符合条件的记录
     * @param dto
     * @return
     */
    public Product findOne(ProductDTO dto) {
		Page page = new Page();
		page.setNumPerPage(1);
		page.setPageNum(1);
		
		List<Product> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public void updateStock(Long id, int amount) {
        String sql = "update Product set stock = stock + ? where id = ? ";
        super.executeUpdate(sql, new Object[] {amount, id});
    }
}
