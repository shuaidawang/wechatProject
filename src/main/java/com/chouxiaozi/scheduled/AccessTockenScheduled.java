package com.chouxiaozi.scheduled;

import com.alibaba.fastjson.JSONObject;
import com.chouxiaozi.util.HttpUtils;
import com.chouxiaozi.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AccessTockenScheduled {

    //刷新access_token 100分钟刷新一次,服务器启动的时候刷新一次（access_token有效期是120分钟，我设置的是每100分钟刷新一次）
    @Scheduled(initialDelay = 1000, fixedDelay = 100*60*1000)
    public void get_access_token() {
        try {
            String appid = "wx2bfe10856c232ba3";
            String appsecret = "38f855811be7b851e45bb92835301a9d";
            String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
            requestUrl = requestUrl.replace("APPID",appid).replace("APPSECRET",appsecret);
            JSONObject jsonObject = HttpUtils.httpsRequest(requestUrl,"GET",null);
            log.info("定时刷新access_tocken: "+jsonObject.getString("access_token"));
            if(jsonObject.getString("access_token")!=null){
                Map<String,String> map = new HashMap<String,String>();
                map.put("access_token",jsonObject.getString("access_token"));
                RedisUtil.set(appid, map);
            }else{
                log.info("定时刷新access_token失败，微信返回的信息是"+jsonObject.toJSONString());
            }
        }
        catch (Exception e){
            log.info("更新access_token的过程当中发生了异常，异常的信息是"+e.getMessage());
        }
    }
}
