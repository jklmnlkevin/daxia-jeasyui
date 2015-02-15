package com.daxia.wy.web.controller.m;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.daxia.core.common.Log;
import com.daxia.core.common.LogModule;
import com.daxia.core.common.Module;
import com.daxia.core.common.UserType;
import com.daxia.core.dto.UserDTO;
import com.daxia.core.util.ImageUtils;
import com.daxia.core.util.ValidationUtils;
import com.daxia.wy.dto.DistrictDTO;
import com.daxia.wy.dto.PushDTO;
import com.daxia.wy.dto.api.UserAPIDTO;
import com.daxia.wy.dto.api.info.UserInfoAPIDTO;

@Controller
@RequestMapping(value = "/m/user", produces = "text/html;charset=UTF-8")
@Module(name = LogModule.MUser)
public class MUserController extends MBaseController {
    
    @Log(operation = "用户登陆")
	@ResponseBody
    @RequestMapping("login")
    public String login(String username, String password, HttpServletRequest request) throws Exception {
		UserDTO user = userService.login(username, password);
		if (user == null || user.getUserType() != UserType.Normal.getValue()) {
			throw new ValidationException("用户名或密码不正确");
		}
		if (StringUtils.isNotBlank(user.getHeadImage())) {
		    user.setHeadImage(request.getSession().getAttribute("ctx").toString() + "/image/" + user.getHeadImage());
		}
		return toApiOutput(user, UserAPIDTO.class, UserInfoAPIDTO.class);
    }
	
	@ResponseBody
    @RequestMapping("forgotPassword")
    public String forgotPassword(String username) throws Exception {
	    userService.forgotPassword(username);
	    return null;
    }
	
	@ResponseBody
    @RequestMapping("resetPassword")
    public String resetPassword(String mobile, String password) throws Exception {
	    ValidationUtils.assertTrue(StringUtils.isNotBlank(mobile), "手机号码不能为空");
	    ValidationUtils.assertTrue(StringUtils.isNotBlank(password), "密码不能为空");
        userService.resetPassword(mobile, password);
        return toJson("true");
    }
	
	@Log(operation = "用户注册")
    @ResponseBody
    @RequestMapping("register")
    public String register(UserDTO dto) throws Exception {
        Long id = userService.register(dto);
        UserDTO user = userService.load(id);
        System.out.println(user);
    	return toApiOutput(user, UserAPIDTO.class, UserInfoAPIDTO.class);
    }
    
    @ResponseBody
    @RequestMapping("isRegistered")
    public String register(String username) throws Exception {
        boolean isRegistered = userService.isUsernameRegistered(username);
        return toJson(isRegistered + "");
    }
    
    @ResponseBody
    @RequestMapping("getMobileValidateCode")
    public String getMobileValidateCode(String mobile) throws Exception {
        ValidationUtils.assertTrue(StringUtils.isNotBlank(mobile), "手机号码不能为空");
        return toJson("888888");
    }
    
    @ResponseBody
    @RequestMapping("changePassword")
    public String changePassword(HttpServletRequest request, String oldPassword, String newPassword) throws Exception {
        Long userId = ServletRequestUtils.getLongParameter(request, "id");
        userService.changePassword(userId, oldPassword, newPassword);
        return toJson("ok");
    }


    @ResponseBody
    @RequestMapping("logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.getSession().invalidate();
        return toJson("ok");
    }
    
    @ResponseBody
    @RequestMapping("changeHeadImage")
    public String changeHeadImage(HttpServletRequest request, Long id, String headImage) throws Exception {
        UserDTO user = userService.load(id);
        user.setHeadImage(headImage);
        userService.updateAllFields(user);
        JSONObject obj = new JSONObject();
        obj.put("name", headImage);
        obj.put("path", ImageUtils.getImageFullPath(request, headImage));
        return toJson(obj);
    }
    
    @ResponseBody
    @RequestMapping("updateReceiveNotice")
    public String updateReceiveNotice(HttpServletRequest request, Long id, boolean receiveNotice) throws Exception {
        UserDTO user = userService.load(id);
        user.setReceiveNotice(receiveNotice);
        userService.updateAllFields(user);
        return toJson("修改成功");
    }
    
    @ResponseBody
    @RequestMapping("updateReceivePayRemind")
    public String updateReceivePayRemind(HttpServletRequest request, Long id, boolean receivePayRemind) throws Exception {
        UserDTO user = userService.load(id);
        user.setReceivePayRemind(receivePayRemind);
        userService.updateAllFields(user);
        return toJson("修改成功");
    }
    
    @ResponseBody
    @RequestMapping("updateOpenMobile")
    public String updateOpenMobile(HttpServletRequest request, Long id, boolean openMobile) throws Exception {
        UserDTO user = userService.load(id);
        user.setOpenMobile(openMobile);
        userService.updateAllFields(user);
        return toJson("修改成功");
    }

    @Log(operation = "修改手机号码")
    @ResponseBody
    @RequestMapping("updateTelephone")
    public String updateTelephone(HttpServletRequest request, Long id, String telephone) throws Exception {
        UserDTO user = userService.load(id);
        ValidationUtils.assertTrue(StringUtils.isNotBlank(telephone) && telephone.trim().length() >= 11, "请输入正确的手机号");
        user.setTelephone(telephone);
        userService.updateAllFields(user);
        return toJson("修改成功");
    }
    
    @ResponseBody
    @RequestMapping("authenticate")
    public String authenticate(HttpServletRequest request, Long id, String images) throws Exception {
        UserDTO user = userService.load(id);
        user.setAuthenticateImages(images);
        userService.updateAllFields(user);
        return toJson("提交成功");
    }
    
    @Log(operation = "changeAddress")
    @ResponseBody
    @RequestMapping("changeAddress")
    public String changeAddress(HttpServletRequest request) throws Exception {
        Long id = ServletRequestUtils.getLongParameter(request, "id");
        Long districtId = ServletRequestUtils.getLongParameter(request, "district.id");
        Long communityId = ServletRequestUtils.getLongParameter(request, "community.id");
        String building = ServletRequestUtils.getStringParameter(request, "building");
        String doorplate = ServletRequestUtils.getStringParameter(request, "doorplate");
        
        ValidationUtils.assertTrue(id != null, "用户不能为空");
        ValidationUtils.assertTrue(districtId != null, "区域不能为空");
        ValidationUtils.assertTrue(communityId != null, "小区不能为空");
        ValidationUtils.assertTrue(StringUtils.isNotBlank(building), "楼栋不能为空");
        ValidationUtils.assertTrue(StringUtils.isNotBlank(doorplate), "门牌号不能为空");
        
        DistrictDTO district = districtService.load(districtId);
        
        UserDTO user = userService.load(id);
        user.setDistrict(district);
        user.setCity(district.getCity());
        user.setProvince(district.getCity().getProvince());
        user.setBuilding(building);
        user.setDoorplate(doorplate);
        
        userService.updateAllFields(user);
        
        return toJson("修改成功");
    }
    
    @Log(operation = "contactUs")
    @ResponseBody
    @RequestMapping("contactUs")
    public String contactUs(HttpServletRequest request) throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("email", "test@xiaoqu.com");
        obj.put("phone", "13838385438");
        return toJson(obj);
    }
    
    @ResponseBody
    @Log(operation = "测试推送")
    @RequestMapping("testPush")
    public String testPush(HttpServletRequest request) throws Exception {
        PushDTO push = new PushDTO();
        push.setCode("0");
        push.setId("1");
        push.setTitle("这是测试推送");
        push.setContent("这是测试推送的内容，省略了几万个字");
        
        String result = pushService.push(push);
        
        JSONObject obj = new JSONObject();
        obj.put("我这边推出去的消息为", push);
        obj.put("从极光得到的推送结果为", result);        
        return toJson(obj);
    }
    
    class CDTO {
        private Long id;
        private String name;
        private String subType;
        private String type;
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getSubType() {
            return subType;
        }
        public void setSubType(String subType) {
            this.subType = subType;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        
    }
    
    @Log(operation = "updatePinyin")
    @RequestMapping("updatePinyin")
    public String updatePinyin() throws Exception {
        userService.updatePinyin();
        return toJson("ok");
    }
    
    @Log(operation = "updatePushRegisterStatus")
    @ResponseBody
    @RequestMapping("updatePushRegisterStatus")
    public String updatePushRegisterStatus(Long id, boolean isSuccess) throws Exception {
        return toJson("ok");
    }
    
}
