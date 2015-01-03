package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.dao.NoticeDAO;
import com.daxia.wy.dao.NoticeReplyDAO;
import com.daxia.wy.dto.NoticeReplyDTO;
import com.daxia.wy.model.Notice;
import com.daxia.wy.model.NoticeReply;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class NoticeReplyService {
	
	@Autowired
	private NoticeReplyDAO noticeReplyDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<NoticeReplyDTO> find(NoticeReplyDTO dto, Page page) {
		List<NoticeReply> models = noticeReplyDAO.find(dto, page);
		List<NoticeReplyDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<NoticeReplyDTO> toDTOs(List<NoticeReply> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<NoticeReplyDTO>(0);
		}
		List<NoticeReplyDTO> dtos = new ArrayList<NoticeReplyDTO>(models.size());
		for (NoticeReply model : models) {
	        NoticeReplyDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private NoticeReplyDTO toDTO(NoticeReply model) {
		if (model == null) {
			return null;
		}
		NoticeReplyDTO dto = BeanMapper.map(model, NoticeReplyDTO.class);
		
		return dto;
	}
	
	public Long create(NoticeReplyDTO dto) {
		NoticeReply model = new NoticeReply();
		toModel(model, dto);
		return noticeReplyDAO.create(model);
	}

	private void toModel(NoticeReply model, NoticeReplyDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<NoticeReply> toModels(List<NoticeReplyDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<NoticeReply>(0);
		}
		List<NoticeReply> models = new ArrayList<NoticeReply>(dtos.size());
		for (NoticeReplyDTO dto : dtos) {
	        NoticeReply model = new NoticeReply();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public NoticeReplyDTO load(Long id) {
	    NoticeReply model = noticeReplyDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(NoticeReplyDTO dto) {
		NoticeReply model = noticeReplyDAO.load(dto.getId());
		toModel(model, dto);
		noticeReplyDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				noticeReplyDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public NoticeReplyDTO findOne(NoticeReplyDTO dto) {
		NoticeReply model = noticeReplyDAO.findOne(dto);
		return toDTO(model);
	}

    public List<NoticeReplyDTO> findByNotice(Long noticeId, Page page) {
        NoticeReplyDTO query = new NoticeReplyDTO();
        query.setNotice(new Notice());
        query.getNotice().setId(noticeId);
        return this.find(query, page);
    }

    @Transactional
    public Long reply(NoticeReplyDTO replyDTO) {
        Notice notice = noticeDAO.load(replyDTO.getNotice().getId());
        Integer floor = notice.getCurrentFloor() + 1;
        replyDTO.setFloor(floor);
        long replyId = this.create(replyDTO);
        
        notice.setCurrentFloor(floor);
        noticeDAO.update(notice);
        return replyId;
    }
    
    @Autowired
    private NoticeDAO noticeDAO;
}
