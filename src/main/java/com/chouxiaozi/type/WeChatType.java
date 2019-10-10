package com.chouxiaozi.type;

/**
 * 微信常用字段
 */
public class WeChatType {

    /**
     * 消息类型
     */
    public enum MsgType {
        TEXT("text","文字"),//文字
        IMAGE("image","图片"),//图片
        VOICE("voice","声音"),//声音
        VIDEO("shortvideo","视频"),//视频
        LOCATION("location","地理位置"),//地理位置
        LINK("link","链接"),//链接
        EVENT("event","事件");//事件
        private String type;
        MsgType(String type,String mark) {
            this.type = type;
        }
        public String getType() {
            return type;
        }
    }

    /**
     * 事件类型
     */
    public enum EventType {
        SUBSCRIBE("subscribe","关注"),//文字
        IMAGE("image","图片"),//图片
        VOICE("voice","声音"),//声音
        VIDEO("shortvideo","视频"),//视频
        LOCATION("location","地理位置"),//地理位置
        LINK("link","链接"),//链接
        EVENT("event","事件");//事件
        private String type;
        EventType(String type,String mark) {
            this.type = type;
        }
        public String getType() {
            return type;
        }
    }
}
