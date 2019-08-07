package com.chat.web.common;

import com.alibaba.fastjson.JSONObject;

/**
 * @author franky
 * @description
 * @date 2019-04-21
 */
public class DTDHelper {
    /**
     * 支持简单消息
     * @param code
     * @param message
     * @return
     */
    public static String json(int code, String message){
        return json(code, message, null);
    }

    /**
     * 支持携带数据
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static String json(int code, String message, Object data){
        JSONObject obj = new JSONObject();
        obj.put("code", code);
        obj.put("message", message);
        if (data != null) {
            obj.put("data", data);
        }
        return obj.toJSONString();
    }
}
