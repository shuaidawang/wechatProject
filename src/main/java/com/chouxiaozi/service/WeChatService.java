package com.chouxiaozi.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

public interface WeChatService {
    String checkSignature(String signature,String timestamp, String nonce, String echostr);
    String processMsg(HttpServletRequest request);
}
