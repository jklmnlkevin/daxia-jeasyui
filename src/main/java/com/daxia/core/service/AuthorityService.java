package com.daxia.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.dao.AuthorityDAO;
import com.daxia.core.dto.AuthorityDTO;
import com.daxia.core.model.Authority;
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
public class AuthorityService {
	
	@Autowired
	private AuthorityDAO authorityDAO;
	
	public List<AuthorityDTO> list(AuthorityDTO dto, Page page) {
		List<Authority> models = authorityDAO.find(dto, page);
		List<AuthorityDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<AuthorityDTO> toDTOs(List<Authority> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<AuthorityDTO>(0);
		}
		List<AuthorityDTO> dtos = new ArrayList<AuthorityDTO>(models.size());
		for (Authority model : models) {
	        AuthorityDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private AuthorityDTO toDTO(Authority model) {
		if (model == null) {
			return null;
		}
		AuthorityDTO dto = BeanMapper.map(model, AuthorityDTO.class);
		
		return dto;
	}
	
	public void save(AuthorityDTO dto) {
		Authority model = new Authority();
		toModel(model, dto);
		authorityDAO.create(model);
	}

	private void toModel(Authority model, AuthorityDTO dto) {
		BeanMapper.copy(dto, model);
		if (dto.getParentAuthority() != null && dto.getParentAuthority().getId() != null) {
			model.setParentAuthority(authorityDAO.load(dto.getParentAuthority().getId()));
		} 
		if (dto.getParentAuthority() == null || dto.getParentAuthority().getId() == null) {
			model.setParentAuthority(null);
		}
    }
	
	@SuppressWarnings("unused")
	private List<Authority> toModels(List<AuthorityDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Authority>(0);
		}
		List<Authority> models = new ArrayList<Authority>(dtos.size());
		for (AuthorityDTO dto : dtos) {
	        Authority model = new Authority();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public AuthorityDTO load(Long id) {
	    Authority model = authorityDAO.load(id);
	    return toDTO(model);
    }

	public void update(AuthorityDTO dto) {
		Authority model = authorityDAO.load(dto.getId());
		toModel(model, dto);
		authorityDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				authorityDAO.deleteById(id);
			}
		}
    }

	public AuthorityDTO uniqueFind(AuthorityDTO dto) {
		Authority model = authorityDAO.uniqueFind(dto);
		return toDTO(model);
	}
	
	public List<AuthorityDTO> findLikeAuthorities(String name) {
	    return toDTOs(authorityDAO.findLikeAuthorities(name));
    }
	
	public List<AuthorityDTO> listTops() {
	    return toDTOs(authorityDAO.listTops());
    }
}
