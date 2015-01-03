package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daxia.core.dto.UserDTO;
import com.daxia.core.service.UserService;
import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.common.PushCode;
import com.daxia.wy.dao.NoticeDAO;
import com.daxia.wy.dto.NoticeDTO;
import com.daxia.wy.dto.PushDTO;
import com.daxia.wy.model.Notice;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class NoticeService {
	
    @Autowired
    private UserService userService;
	@Autowired
	private NoticeDAO noticeDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<NoticeDTO> find(NoticeDTO dto, Page page) {
		List<Notice> models = noticeDAO.find(dto, page);
		List<NoticeDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<NoticeDTO> toDTOs(List<Notice> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<NoticeDTO>(0);
		}
		List<NoticeDTO> dtos = new ArrayList<NoticeDTO>(models.size());
		for (Notice model : models) {
	        NoticeDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private NoticeDTO toDTO(Notice model) {
		if (model == null) {
			return null;
		}
		
		UserDTO userDTO = userService.findEstateManager(model.getCommunity().getId());
		
		NoticeDTO dto = BeanMapper.map(model, NoticeDTO.class);
		dto.setPublisher(userDTO.getUsername());
		return dto;
	}
	
	public void create(NoticeDTO dto) {
		Notice model = new Notice();
		toModel(model, dto);
		noticeDAO.create(model);
	}

	private void toModel(Notice model, NoticeDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Notice> toModels(List<NoticeDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Notice>(0);
		}
		List<Notice> models = new ArrayList<Notice>(dtos.size());
		for (NoticeDTO dto : dtos) {
	        Notice model = new Notice();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public NoticeDTO load(Long id) {
	    Notice model = noticeDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(NoticeDTO dto) {
		Notice model = noticeDAO.load(dto.getId());
		toModel(model, dto);
		noticeDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				noticeDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public NoticeDTO findOne(NoticeDTO dto) {
		Notice model = noticeDAO.findOne(dto);
		return toDTO(model);
	}

	@Transactional
    public void push(NoticeDTO notice) {
	    // pushService.push(pushDTO);
	    this.updateAllFields(notice);
	    PushDTO push = new PushDTO();
	    push.setCode(PushCode.Notice.getValue());
	    push.setId(notice.getId().toString());
	    push.setTitle(notice.getTitle());
	    
	    pushService.pushByCommunity(push, notice.getCommunity().getId());
    }
	
	@Autowired
	private PushService pushService;
	
}
