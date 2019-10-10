package com.chouxiaozi.service.impl;

import com.chouxiaozi.model.TextMessage;
import com.chouxiaozi.service.WeChatService;
import com.chouxiaozi.util.CheckUtil;
import com.chouxiaozi.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class WeChatServiceImpl implements WeChatService {
    @Override
    public String checkSignature(String signature, String timestamp, String nonce, String echostr) {

        boolean flag = CheckUtil.checkSignature(signature,timestamp,nonce);
        log.info("flag=====  "+flag);
        if(flag){
            return echostr;
        }
        return  "";
    }

    @Override
    public String processMsg(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            Map<String,String> map = MessageUtil.xmlToMap(request);
            String toUserName = map.get("ToUserName");
            String fromUserName  = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String msg = "";
            log.info("msgType== "+msgType);
            if("text".equals(msgType)){
                TextMessage textMessage = new TextMessage();
                textMessage.setMsgType(msgType);
                textMessage.setFromUserName(toUserName);
                textMessage.setToUserName(fromUserName);
                textMessage.setCreateTime(String.valueOf((new Date()).getTime()));
                textMessage.setContent(content+"? 你真帅");
                msg = MessageUtil.textMessageToXml(textMessage);
            }
            return msg;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
