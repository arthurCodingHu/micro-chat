package com.chat.web.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author franky
 */
public enum UserTypeEnum {

    SHOP_USER_TYPE("1","菜市场用户和商户"),
    ADMIN_USER_TYPE("2","后台管理用户"),
    ALLIANCE_USER_TYPE("3","加盟店");

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

    private UserTypeEnum(String key, String value){
        this.value = value;
        this.key = key;
    }

    public static final Map<String, String> MAP;
    static {
        MAP = new HashMap<String, String>();
        for(UserTypeEnum ds : UserTypeEnum.values()){
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
        for(UserTypeEnum ds : UserTypeEnum.values()){
            if(ds.value.equals(value) || ds.key.equals(value)){
                return ds.key;
            }
        }
        return null;
    }
}
