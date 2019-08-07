package com.chat.web.common.enums;

import lombok.Getter;

@Getter
public enum GtSynType {
    booth("1", "商户"),
    supplier("2", "供应商"),
    product("3", "产品"),
    purchase_order("4", "进货单");

    private String key;
    private String value;

    GtSynType(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
