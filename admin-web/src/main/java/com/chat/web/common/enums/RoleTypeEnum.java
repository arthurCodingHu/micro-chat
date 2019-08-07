package com.chat.web.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 角色类别
 */
public enum RoleTypeEnum {

    QYYH("1","企业用户"),
    JSYH("2","角色用户");

    private String value;
    private String key;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private RoleTypeEnum(String key, String value){
        this.value = value;
        this.key = key;
    }

    public static final Map<String, String> MAP;
    static {
        MAP = new HashMap<String, String>();
        for(RoleTypeEnum ds : RoleTypeEnum.values()){
            MAP.put(ds.key, ds.value);
        }
    }

    public static final String getValueByKey(String key){
        if(key == null){
            return null;
        }
        return MAP.get(key);
    }
    /**
     * 增加一个刷选值方法
     */
    public static String getKeyBykrv(String value){
        for(RoleTypeEnum ds : RoleTypeEnum.values()){
            if(ds.value.equals(value) || ds.key.equals(value)){
                return ds.key;
            }
        }
        return null;
    }
}
