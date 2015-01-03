package com.daxia.wy.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

import com.daxia.core.util.JsonUtils;
import com.daxia.wy.dto.PushDTO;

@Service
public class PushService {
    private JPushClient jpushClient = new JPushClient("715a594ad32990552fd86e43", "2392c59e197d82beff9e931f", 3);
    
    private static Logger logger = Logger.getLogger(PushService.class);
    
    public void pushAll(PushDTO pushDTO, long communityId) {
        PushPayload payload = buildMessage(JsonUtils.toJson(pushDTO));
        this.push(pushDTO);
    }
    
    public String push(PushDTO push, String username) {
        PushPayload payload = buildMessageByUsername(JsonUtils.toJson(push), username);
        try {
            PushResult result = jpushClient.sendPush(payload);
            return result.toString();
        } catch (Exception e) {
            logger.error(e, e);
            return e.getMessage();
        }   
    }
    
    public void pushAll(PushDTO pushDTO) {
        this.push(pushDTO);
    }
    public String push(PushDTO pushDTO) {

        PushPayload payload = buildMessage(JsonUtils.toJson(pushDTO));

        try {
            PushResult result = jpushClient.sendPush(payload);
            return result.toString();
        } catch (Exception e) {
            logger.error(e, e);
            return e.getMessage();
        }
    }
    
    private static PushPayload buildMessageByUsername(String content, String username) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(username))
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
    
    private static PushPayload buildMessage(String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }

    public String pushByCommunity(PushDTO push, Long id) {
        PushPayload payload = buildMessageByTag(JsonUtils.toJson(push), "community_" + id);
        try {
            PushResult result = jpushClient.sendPush(payload);
            return result.toString();
        } catch (Exception e) {
            logger.error(e, e);
            return e.getMessage();
        }        
    }

    private PushPayload buildMessageByTag(String content, String tag) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag(tag))
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
    
}
