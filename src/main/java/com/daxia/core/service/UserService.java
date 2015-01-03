package com.daxia.core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.common.UserType;
import com.daxia.core.dao.RoleDAO;
import com.daxia.core.dao.UserDAO;
import com.daxia.core.dto.UserDTO;
import com.daxia.core.exception.ObjectNotFoundException;
import com.daxia.core.model.Role;
import com.daxia.core.model.User;
import com.daxia.core.security.PasswordEncoder;
import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.core.util.PinyinUtil;
import com.daxia.core.util.ValidationUtils;
import com.daxia.wy.dao.CityDAO;
import com.daxia.wy.dao.DistrictDAO;
import com.daxia.wy.dao.ProvinceDAO;
import com.daxia.wy.dto.CityDTO;
import com.daxia.wy.dto.DistrictDTO;
import com.daxia.wy.dto.ProvinceDTO;
import com.daxia.wy.model.City;
import com.daxia.wy.model.Community;
import com.daxia.wy.model.District;
import com.daxia.wy.model.Province;
import com.daxia.wy.service.SMSService;

/**
 * Service层，类要加@Service标识
 * 一般都是由service层操作数据库，
 * 并且，只有save, update, delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext.xml文件的txAdvice定义处配置。
 * @author Kewen.Zhu
 *
 */
@Service
public class UserService {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SMSService smsService;
	
	@Autowired
    private ProvinceDAO provinceDAO;
    @Autowired
    private CityDAO cityDAO;
    @Autowired
    private DistrictDAO districtDAO;
	
	public List<UserDTO> list(UserDTO dto, Page page) {
		List<User> models = userDAO.find(dto, page);
		long start = System.currentTimeMillis();
		List<UserDTO> dtos = toDTOs(models);
		System.out.println("took " + (System.currentTimeMillis() - start) + "ms toDTOs");
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * @param models
	 * @return
	 */
	private List<UserDTO> toDTOs(List<User> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<UserDTO>(0);
		}
		List<UserDTO> dtos = new ArrayList<UserDTO>(models.size());
		for (User model : models) {
	        UserDTO dto = toDTO(model);
	        dtos.add(dto);
        }
	    return dtos;
    }
	
	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * @param model
	 * @return
	 */
	private UserDTO toDTO(User model) {
	    long start = System.currentTimeMillis();
		if (model == null) {
			return null;
		}
		UserDTO dto = BeanMapper.map(model, UserDTO.class);
		System.out.println("took " + (System.currentTimeMillis() - start) + "ms to process single toDTO");
		return dto;
	}
	
	public Long save(UserDTO dto) {
		User model = new User();
		
		toModel(model, dto);
		ValidationUtils.assertTrue(StringUtils.isNotBlank(model.getUsername()) && dto.getUsername().length() >= 1, "用户名不能少于6位");
		ValidationUtils.assertTrue(StringUtils.isNotBlank(model.getPassword()) && dto.getPassword().length() >= 6, "密码不能少于6位");
		model.setPassword(passwordEncoder.encodePassword(model.getPassword(), model.getUsername()));
		
		if (StringUtils.isNotBlank(model.getTelephone())) {
		    ValidationUtils.assertTrue(isMobileRegistered(model.getTelephone()) == false, "该手机号码已经被注册");
		}
		Long userId = userDAO.create(model);
		
		sessionFactory.getCurrentSession().clear();
		
		return userId;
	}

	private void toModel(User model, UserDTO dto) {
		BeanMapper.copy(dto, model);
    }
	
	@SuppressWarnings("unused")
	private List<User> toModels(List<UserDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<User>(0);
		}
		List<User> models = new ArrayList<User>(dtos.size());
		for (UserDTO dto : dtos) {
	        User model = new User();
	        toModel(model, dto);
	        models.add(model);
        }
		return models;
	}

	public UserDTO load(Long id) {
	    User model = userDAO.load(id);
	    return toDTO(model);
    }

	public void updateSign(Long userId, String sign) {
		User model = userDAO.load(userId);
		userDAO.update(model);
	}
	
	public void update(UserDTO dto) {
		User model = userDAO.load(dto.getId());
		// set values
		userDAO.update(model);
    }

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				// userDAO.deleteById(id);
			    User user = userDAO.load(id);
			    user.setUserType(-1);
			    userDAO.update(user);
			}
		}
    }

	public UserDTO uniqueFind(UserDTO dto) {
		User model = userDAO.findOne(dto);
		return toDTO(model);
	}
	

	/**
	 * 判断用户名是否存在
	 * @param username
	 * @return
	 */
	public boolean exists(String username) {
		ValidationUtils.assertTrue(StringUtils.isNotBlank(username), "用户名不能为空");
		UserDTO query = new UserDTO();
		query.setUsername(username);
		User user = userDAO.findOne(query);
		return user != null && username.equalsIgnoreCase(user.getUsername());
	}

	public UserDTO login(String username, String password) {
		ValidationUtils.assertTrue(StringUtils.isNotBlank(username), "用户名不能为空");
		ValidationUtils.assertTrue(StringUtils.isNotBlank(password), "密码不能为空");
		
		UserDTO query = new UserDTO();
		query.setUsername(username);
		query.setPassword(passwordEncoder.encodePassword(password, username));
		
		User user = userDAO.findOne(query);
		if (user == null || !username.equalsIgnoreCase(user.getUsername())) {
			return null;
		} 
		return toDTO(user);
    }

	/**
	 * 按username精确查找，如果没有找到会抛ObjectNotFound异常
	 * @param username
	 * @return
	 */
	public UserDTO loadByUsername(String username) {
		UserDTO query = new UserDTO();
		query.setUsername(username);
		User user = userDAO.findOne(query);
		if (user == null) {
			throw new ObjectNotFoundException("用户" + username + "不存在");
		}
		return toDTO(user);
	}
	
	public void updateAllFields(UserDTO dto) {
	    User model = userDAO.load(dto.getId());
	    toModel(model, dto);
	    userDAO.update(model);
    }

	public void saveRole(Long id, Long[] roleIds) {
		User user = userDAO.load(id);
		Set<Role> roleSet = new HashSet<Role>();
		for (Long roleId : roleIds) {
			roleSet.add(roleDAO.load(roleId));
		}
		user.setRoles(roleSet);
		userDAO.update(user);
    }
	
	public List<UserDTO> recommendFriend(String username) {
		List<User> models = userDAO.recommendFriend(username);
		return toDTOs(models);
	}

	public void updatePassword(Long id, String password) {
		/*User user = userDAO.load(id);
		password = passwordEncoder.encodePassword(password, user.getUsername());
		
		user.setPassword(password);
		userDAO.update(user);*/
    }
	
	/**
	 * 禁用
	 * @param ids
	 */
	public void updateDisabled(Long[] ids) {
	   /* for (Long id : ids) {
	        User user = userDAO.load(id);
	        user.setUserType(UserType.Disabled.value());
	        userDAO.update(user);
        }*/
    }
	
	/**
	 * 解禁
	 * @param ids
	 */
	public void updateActive(Long[] ids) {
	   /* for (Long id : ids) {
	        User user = userDAO.load(id);
	        user.setUserType(UserType.Normal.value());
	        userDAO.update(user);
        }*/
    }

	public Long register(UserDTO dto) {
	    Long id = this.save(dto);
	    return id;
    }

	/**
	 * 忘记密码：
	 * 1， 找到用户对应的手机
	 * 2，发送短信
	 * @param username
	 */
    public void forgotPassword(String username) {
        User user = loadByUsername(username);
        if (user == null) {
            return;
        }
        
        String mobile = user.getTelephone();
        if (!isValidMobile(mobile)) {
            return;
        }
        
        // 生成验证码
        String validCode = generateValidCode();
        
        // 保存验证码
        
        // 发送短信
        String msg = "您的修改密码的验证码为" + validCode;
        
        smsService.sendSms(mobile, msg);
    }

    private String generateValidCode() {
        return null;
    }

    private boolean isValidMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return false;
        }
        return true;
    }

    public void resetPassword(Long userId, String password) {
        User user = userDAO.load(userId);
        if (user != null) {
            password = passwordEncoder.encodePassword(password, user.getUsername());
            user.setPassword(password);
            userDAO.update(user);
        }
        
    }

    public boolean isUsernameRegistered(String username) {
        try {
            loadByUsername(username);
            return true;
        } catch (ObjectNotFoundException e) {
            return false;
        }
    }
    
    public boolean isMobileRegistered(String mobile) {
        UserDTO dto = new UserDTO();
        dto.setTelephone(mobile);
        User user = userDAO.findOne(dto);
        UserDTO findUser = toDTO(user);
        return findUser != null;
    }

    public void resetPassword(String mobile, String password) {
        UserDTO dto = new UserDTO();
        dto.setTelephone(mobile);
        User user = userDAO.findOne(dto);
        if (user != null) {
            password = passwordEncoder.encodePassword(password, user.getUsername());
            user.setPassword(password);
            userDAO.update(user);
        } else {
            throw new RuntimeException("该手机号码未注册");
        }
    }

    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userDAO.load(userId);
        oldPassword = passwordEncoder.encodePassword(oldPassword, user.getUsername());
        if (!user.getPassword().equalsIgnoreCase(oldPassword)) {
            throw new RuntimeException("旧的密码输入不正确");
        }
        newPassword = passwordEncoder.encodePassword(newPassword, user.getUsername());
        user.setPassword(newPassword);
        userDAO.update(user);
    }

    public void updatePinyin() {
        List<Province> provinceList = provinceDAO.find(new ProvinceDTO(), null);
        for (Province p : provinceList) {
            p.setFullLetter(PinyinUtil.getPinYin(p.getName()));
            p.setLetter(p.getFullLetter().substring(0, 1));
            provinceDAO.update(p);
        }
        
        List<City> cityList = cityDAO.find(new CityDTO(), null);
        for (City p : cityList) {
            p.setFullLetter(PinyinUtil.getPinYin(p.getName()));
            p.setLetter(p.getFullLetter().substring(0, 1));
            cityDAO.update(p);
        }
        
        List<District> distinctList = districtDAO.find(new DistrictDTO(), null);
        for (District p : distinctList) {
            p.setFullLetter(PinyinUtil.getPinYin(p.getName()));
            p.setLetter(p.getFullLetter().substring(0, 1));
            districtDAO.update(p);
        }
    }
    
    public UserDTO findEstateManager(Long communityId) {
        UserDTO query = new UserDTO();
        query.setCommunity(new Community());
        query.getCommunity().setId(communityId);
        query.setOnlyFindType(UserType.EstateManager.getValue());
        User user = userDAO.findOne(query);
        return toDTO(user);
    }
}
