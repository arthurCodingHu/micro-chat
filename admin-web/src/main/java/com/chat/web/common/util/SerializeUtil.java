package com.chat.web.common.util;

import com.alibaba.fastjson.JSON;

/**
 * @author franky
 * @description 序列化工具
 * @date 2019-05-10
 */
public class SerializeUtil {

    public static String serialize(Object object) {
        String getString = JSON.toJSONString(object);
        return getString;
    }

    public static Object unserizlize(String jsonString, Class<?> clazz) {
        Object jsonObject = JSON.parseObject(jsonString, clazz);
        return jsonObject;
    }

}
