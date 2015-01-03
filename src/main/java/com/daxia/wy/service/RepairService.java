package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.dao.UserDAO;
import com.daxia.core.model.User;
import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.wy.common.PushCode;
import com.daxia.wy.common.RepairStatus;
import com.daxia.wy.common.RepairStatus2;
import com.daxia.wy.dao.RepairDAO;
import com.daxia.wy.dao.RepairHistoryDAO;
import com.daxia.wy.dto.PushDTO;
import com.daxia.wy.dto.RepairDTO;
import com.daxia.wy.model.Repair;
import com.daxia.wy.model.RepairHistory;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class RepairService {
	
	@Autowired
	private RepairDAO repairDAO;
	@Autowired
	private RepairHistoryDAO repairHistoryDAO;
	@Autowired
	private PushService pushService;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<RepairDTO> find(RepairDTO dto, Page page) {
		List<Repair> models = repairDAO.find(dto, page);
		List<RepairDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<RepairDTO> toDTOs(List<Repair> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<RepairDTO>(0);
		}
		List<RepairDTO> dtos = new ArrayList<RepairDTO>(models.size());
		for (Repair model : models) {
	        RepairDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private RepairDTO toDTO(Repair model) {
		if (model == null) {
			return null;
		}
		RepairDTO dto = BeanMapper.map(model, RepairDTO.class);
		
		return dto;
	}
	
	public Long create(RepairDTO dto) {
		Repair model = new Repair();
		toModel(model, dto);
		Long id = repairDAO.create(model);
		
		model = repairDAO.load(id);
		List<Integer> list = new ArrayList<Integer>();
		if (model.isUseFund()) {
		    for (RepairStatus s : RepairStatus.values()) {
		        list.add(s.getValue());
		    }
		} else {
		    for (RepairStatus2 s : RepairStatus2.values()) {
                list.add(s.getValue());
            }
		}
		for (int i = 0; i < list.size(); i++) {
		    int s = list.get(i);
    		RepairHistory history = new RepairHistory();
    		history.setRepair(model);
    		history.setState(s);
    		if (s == model.getState()) {
    		    history.setUpdateTime(new Date());
    		}
    		repairHistoryDAO.create(history);
		}
		
		return id;
	}

	private void toModel(Repair model, RepairDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<Repair> toModels(List<RepairDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Repair>(0);
		}
		List<Repair> models = new ArrayList<Repair>(dtos.size());
		for (RepairDTO dto : dtos) {
	        Repair model = new Repair();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public RepairDTO load(Long id) {
	    Repair model = repairDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(RepairDTO dto) {
		Repair model = repairDAO.load(dto.getId());
		int oldState = model.getState();
		toModel(model, dto);
		repairDAO.update(model);
		int newState = model.getState();
		if (newState != oldState) {
		    repairHistoryDAO.updateTime(model.getId(), newState, new Date());
		    // 推送消息
		    PushDTO push = new PushDTO();
		    push.setCode(PushCode.RepairStateChanged.getValue());
		    push.setTitle("您的物业修改状态有变更");
		    push.setId(model.getId().toString());
		    RepairDTO repairDTO = load(model.getId());
		    User user = userDAO.load(model.getUser().getId());
		    String pushResult = pushService.push(push, user.getUsername());
		    System.out.println("推送结果：" + pushResult);
		}
		
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				repairDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public RepairDTO findOne(RepairDTO dto) {
		Repair model = repairDAO.findOne(dto);
		return toDTO(model);
	}
	
	@Autowired
	private UserDAO userDAO;
}
