package com.chouxiaozi.controller;

import com.chouxiaozi.service.WeChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping
@Slf4j
public class WeiXinController {

    @Autowired
    private WeChatService weChatService;

    @RequestMapping(value = "/checkSignature",method = RequestMethod.GET, produces ={"application/json;charset=utf-8"})
    @ResponseBody
    public String checkSignature(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        log.info("signature=="+signature);
        log.info("timestamp=="+timestamp);
        log.info("nonce=="+nonce);
        log.info("echostr=="+echostr);
        return weChatService.checkSignature(signature,timestamp,nonce,echostr);
    }

    @RequestMapping(value = "/checkSignature",method = RequestMethod.POST)
    public void processMsg(HttpServletRequest request,HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        /*response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");*/
        try {
            String msg = weChatService.processMsg(request);
            log.info("回复消息内容：" + msg);
            out.print(msg);
        }catch (Exception e){
            out.close();
        }
    }
}
