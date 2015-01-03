package com.daxia.wy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.wy.dao.PayRecordDAO;
import com.daxia.wy.dto.PayRecordDTO;
import com.daxia.wy.model.PayRecord;
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
public class PayRecordService {
	
	@Autowired
	private PayRecordDAO payRecordDAO;
	
	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<PayRecordDTO> find(PayRecordDTO dto, Page page) {
		List<PayRecord> models = payRecordDAO.find(dto, page);
		List<PayRecordDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<PayRecordDTO> toDTOs(List<PayRecord> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<PayRecordDTO>(0);
		}
		List<PayRecordDTO> dtos = new ArrayList<PayRecordDTO>(models.size());
		for (PayRecord model : models) {
	        PayRecordDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private PayRecordDTO toDTO(PayRecord model) {
		if (model == null) {
			return null;
		}
		PayRecordDTO dto = BeanMapper.map(model, PayRecordDTO.class);
		
		return dto;
	}
	
	public void create(PayRecordDTO dto) {
		PayRecord model = new PayRecord();
		toModel(model, dto);
		payRecordDAO.create(model);
	}

	private void toModel(PayRecord model, PayRecordDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<PayRecord> toModels(List<PayRecordDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<PayRecord>(0);
		}
		List<PayRecord> models = new ArrayList<PayRecord>(dtos.size());
		for (PayRecordDTO dto : dtos) {
	        PayRecord model = new PayRecord();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public PayRecordDTO load(Long id) {
	    PayRecord model = payRecordDAO.load(id);
	    return toDTO(model);
    }

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * @param dto
	 */
	public void updateAllFields(PayRecordDTO dto) {
		PayRecord model = payRecordDAO.load(dto.getId());
		toModel(model, dto);
		payRecordDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				payRecordDAO.deleteById(id);
			}
		}
    }
	
	public void deleteById(Long id) {
		this.deleteByIds(new Long[] {id});
	}

	public PayRecordDTO findOne(PayRecordDTO dto) {
		PayRecord model = payRecordDAO.findOne(dto);
		return toDTO(model);
	}
}
