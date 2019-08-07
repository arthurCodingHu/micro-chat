package com.chat.web.common.enums;

/**
 * @author franky
 * @description
 * @date 2019/7/9
 */
public enum  InitMessageEnum {
    YES(1, "初始化"),
    NO(2, "不初始化");

    private Integer key;
    private String value;


    InitMessageEnum(Integer key, String value){
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }}
