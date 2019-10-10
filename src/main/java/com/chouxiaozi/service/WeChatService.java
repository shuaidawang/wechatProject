package com.chouxiaozi.service;

import javax.servlet.http.HttpServletRequest;

public interface WeChatService {

    String checkSignature(String signature,String timestamp, String nonce, String echostr);

    String processMsg(HttpServletRequest request);
}
