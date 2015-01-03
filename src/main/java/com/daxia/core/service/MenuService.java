package com.daxia.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.daxia.core.common.MenuLevels;
import com.daxia.core.dao.MenuDAO;
import com.daxia.core.dto.MenuDTO;
import com.daxia.core.model.Menu;
import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.core.util.SpringSecurityUtils;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * 
 * @date 2013-6-14
 *
 */
@Service
public class MenuService {
	
	@Autowired
	private MenuDAO menuDAO;
	
	public List<MenuDTO> list(MenuDTO dto, Page page) {
		List<Menu> models = menuDAO.find(dto, page);
		List<MenuDTO> dtos = toDTOs(models);
		return dtos;
	}
	
	/**
	 * 为后台管理的主页面服务的
	 * @return
	 */
	public List<MenuDTO> listForAdminPage() {
		MenuDTO dto = new MenuDTO();
		dto.setLevel(MenuLevels.Level0.level());
		List<Menu> models = menuDAO.find(dto, null);
		List<MenuDTO> dtos = toDTOs(models);
		
		Collection<? extends GrantedAuthority> authorities = SpringSecurityUtils.getCurrentUser().getAuthorities();
		Set<String> set = new HashSet<String>();
		for (GrantedAuthority a : authorities) {
	        set.add(a.getAuthority());
        }
		
		for (MenuDTO level0 : dtos) {
			for (int i = level0.getChildren().size() - 1; i >= 0; i--) {
				Menu level1 = level0.getChildren().get(i);
				/*if (!set.contains(level1.getAuthority().getCode())) {
					level0.getChildren().remove(i);
					continue;
				}*/
				for (int j = level1.getChildren().size() - 1; j >= 0; j--) {
					Menu level2 = level1.getChildren().get(j);
					if (level2.getAuthority() != null && !set.contains(level2.getAuthority().getCode())) {
						level1.getChildren().remove(j);
					}
				}
				if (CollectionUtils.isEmpty(level1.getChildren())) {
					level0.getChildren().remove(i);
				}
			}
        }
		return dtos;
	}

	public MenuDTO findOne(MenuDTO dto) {
		Page page = new Page();
		page.setNumPerPage(1);
		page.setPageNum(1);
		List<MenuDTO> list = this.list(dto, page);
		
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<MenuDTO> toDTOs(List<Menu> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<MenuDTO>(0);
		}
		List<MenuDTO> dtos = new ArrayList<MenuDTO>(models.size());
		for (Menu model : models) {
	        MenuDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private MenuDTO toDTO(Menu model) {
		if (model == null) {
			return null;
		}
		MenuDTO dto = BeanMapper.map(model, MenuDTO.class);
		String path = model.getName();
        Menu tmp = model;
        while (tmp.getParent() != null) {
        	path = tmp.getParent().getName() + " > " + path;
        	tmp = tmp.getParent();
        }
        dto.setPath(path);
        
        dto.setLevelName(MenuLevels.get(model.getLevel()).levelName());
		return dto;
	}
	
	public void save(MenuDTO dto) {
		Menu model = new Menu();
		toModel(model, dto);
		menuDAO.create(model);
	}

	private void toModel(Menu model, MenuDTO dto) {
		BeanMapper.copy(dto, model);
		if (dto.getParentId() != null) {
			Menu parent = menuDAO.load(dto.getParentId());
			model.setParent(parent);
		}
    }
	
	@SuppressWarnings("unused")
    private List<Menu> toModels(List<MenuDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Menu>(0);
		}
		List<Menu> models = new ArrayList<Menu>(dtos.size());
		for (MenuDTO dto : dtos) {
	        Menu model = new Menu();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public MenuDTO load(Long id) {
	    Menu model = menuDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会更新所有发生了变化的字段
	 * @param dto
	 */
	public void updateAllField(MenuDTO dto) {
		Menu model = menuDAO.load(dto.getId());
		toModel(model, dto);
		menuDAO.update(model);
    }

	public void deleteById(Long id) {
		menuDAO.deleteById(id);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
	            menuDAO.deleteById(id);
            }
		}
    }

	public List<MenuDTO> loadByLevel(MenuLevels level) {
	    MenuDTO dto = new MenuDTO();
	    dto.setLevel(level.level());
	    List<Menu> models = menuDAO.find(dto, null);
	    return toDTOs(models);
    }

}
