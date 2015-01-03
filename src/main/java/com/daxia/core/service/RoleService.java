package com.daxia.core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.dao.AuthorityDAO;
import com.daxia.core.dao.RoleDAO;
import com.daxia.core.dto.RoleDTO;
import com.daxia.core.model.Authority;
import com.daxia.core.model.Role;
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
public class RoleService {
	
	@Autowired
	private RoleDAO roleDAO;
	
	public List<RoleDTO> list(RoleDTO dto, Page page) {
		List<Role> models = roleDAO.find(dto, page);
		List<RoleDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<RoleDTO> toDTOs(List<Role> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<RoleDTO>(0);
		}
		List<RoleDTO> dtos = new ArrayList<RoleDTO>(models.size());
		for (Role model : models) {
	        RoleDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private RoleDTO toDTO(Role model) {
		if (model == null) {
			return null;
		}
		RoleDTO dto = BeanMapper.map(model, RoleDTO.class);
		
		return dto;
	}
	
	public void save(RoleDTO dto) {
		Role model = new Role();
		toModel(model, dto);
		processAuthority(model, dto);
		roleDAO.create(model);
	}

	private void toModel(Role model, RoleDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Role> toModels(List<RoleDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Role>(0);
		}
		List<Role> models = new ArrayList<Role>(dtos.size());
		for (RoleDTO dto : dtos) {
	        Role model = new Role();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public RoleDTO load(Long id) {
	    Role model = roleDAO.load(id);
	    return toDTO(model);
    }

	public void update(RoleDTO dto) {
		Role model = roleDAO.load(dto.getId());
		
		// set values
		
		processAuthority(model, dto);
		// toModel(model, dto);
		System.out.println("正确值为："+model.getName());
		roleDAO.update(model);
    }
	
	public void updateAllField(RoleDTO dto) {
		Role model = roleDAO.load(dto.getId());
		toModel(model, dto);
		roleDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				roleDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		roleDAO.deleteById(id);
	}

	public RoleDTO uniqueFind(RoleDTO dto) {
		Role model = roleDAO.uniqueFind(dto);
		return toDTO(model);
	}
	
	private void processAuthority(Role model, RoleDTO dto) {
		if (CollectionUtils.isEmpty(dto.getAuthorityIds())) {
			model.setAuthorities(null);
			return;
		}
		Set<Authority> set = new HashSet<Authority>();
		List<Long> authorityIds = dto.getAuthorityIds();
		if (CollectionUtils.isNotEmpty(authorityIds)) {
			for (Long id : authorityIds) {
	            Authority a = authorityDAO.load(id);
	            set.add(a);
	            // set.addAll(processAuthorityChildren(a));
            }
		}
		model.setName(dto.getName());
		model.setAuthorities(set);
    }
	
	/*private Collection<? extends Authority> processAuthorityChildren(Authority a) {
		if (CollectionUtils.isEmpty(a.getChildren())) {
			return new HashSet<Authority>(0);
		}
		Set<Authority> set = new HashSet<Authority>();
		for (Authority child : a.getChildren()) {
	        set.add(child);
	        set.addAll(processAuthorityChildren(child));
        }
	    return set;
    }*/
	
	@Autowired
	private AuthorityDAO authorityDAO;
}
