package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daxia.wy.common.PushCode;
import com.daxia.wy.dao.SystemMessageDAO;
import com.daxia.wy.dto.PushDTO;
import com.daxia.wy.dto.SystemMessageDTO;
import com.daxia.wy.model.SystemMessage;
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
public class SystemMessageService {
	
	@Autowired
	private SystemMessageDAO systemMessageDAO;
	@Autowired
	private PushService pushService;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<SystemMessageDTO> find(SystemMessageDTO dto, Page page) {
		List<SystemMessage> models = systemMessageDAO.find(dto, page);
		List<SystemMessageDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<SystemMessageDTO> toDTOs(List<SystemMessage> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<SystemMessageDTO>(0);
		}
		List<SystemMessageDTO> dtos = new ArrayList<SystemMessageDTO>(models.size());
		for (SystemMessage model : models) {
	        SystemMessageDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private SystemMessageDTO toDTO(SystemMessage model) {
		if (model == null) {
			return null;
		}
		SystemMessageDTO dto = BeanMapper.map(model, SystemMessageDTO.class);
		
		return dto;
	}
	
	public void create(SystemMessageDTO dto) {
		SystemMessage model = new SystemMessage();
		toModel(model, dto);
		systemMessageDAO.create(model);
	}

	private void toModel(SystemMessage model, SystemMessageDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<SystemMessage> toModels(List<SystemMessageDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<SystemMessage>(0);
		}
		List<SystemMessage> models = new ArrayList<SystemMessage>(dtos.size());
		for (SystemMessageDTO dto : dtos) {
	        SystemMessage model = new SystemMessage();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public SystemMessageDTO load(Long id) {
	    SystemMessage model = systemMessageDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(SystemMessageDTO dto) {
		SystemMessage model = systemMessageDAO.load(dto.getId());
		toModel(model, dto);
		systemMessageDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				systemMessageDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public SystemMessageDTO findOne(SystemMessageDTO dto) {
		SystemMessage model = systemMessageDAO.findOne(dto);
		return toDTO(model);
	}

	@Transactional
    public void push(SystemMessageDTO systemMessage) {
        SystemMessage model = systemMessageDAO.load(systemMessage.getId());
        PushDTO pushDTO = new PushDTO();
        pushDTO.setCode(PushCode.SystemMessage.getValue());
        pushDTO.setId(systemMessage.getId().toString());
        pushDTO.setTitle(systemMessage.getTitle());
        pushService.push(pushDTO);
        
        model.setPushTime(new Date());
        systemMessageDAO.update(model);
    }
}
