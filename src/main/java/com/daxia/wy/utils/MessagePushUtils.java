package com.daxia.wy.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daxia.core.common.MessagePushType;
import com.daxia.core.common.SystemConfigType;
import com.daxia.core.dto.MessagePushDTO;
import com.daxia.core.service.SystemConfigService;
import com.daxia.core.util.JsonUtils;

@Component
public class MessagePushUtils {
	private static Logger logger = Logger.getLogger(MessagePushUtils.class);
	
	@Autowired
	private SystemConfigService systemConfigService;
	
	/**
	 * 推送消息，如果用户名为空则是广播
	 */
	public void push(String username, MessagePushType messagePushType, String title, Object data) {
		MessagePushDTO dto = new MessagePushDTO();
		dto.setCode(messagePushType.getValue());
		dto.setData(data);
		
		String message = JsonUtils.toJson(dto); 
		
		String url = "http://{server}:{port}/notification.do?action=send";
		url = url.replace("{server}", systemConfigService.get(SystemConfigType.XmppHost));
		url = url.replace("{port}", systemConfigService.get(SystemConfigType.PushServerHttpPort));
		Connection connection = Jsoup.connect(url);
		Map<String, String> params = new HashMap<String, String>();
		if (StringUtils.isNotBlank(username)) {
			params.put("broadcast", "N");
			params.put("username", username);
		} else {
			params.put("broadcast", "Y");
			params.put("username", "");
		}
		params.put("title", title);
		params.put("message", message);
		params.put("uri", "");
		connection = connection.data(params);
		
		int retry = 0;
		while(true) {
			try {
		        connection.post();
		        break;
	        } catch (IOException e) {
	        	logger.error(e, e);
	        	if (retry++ >= 3) {
	        		break;
	        	}
	        }
		}
	}
	
}
