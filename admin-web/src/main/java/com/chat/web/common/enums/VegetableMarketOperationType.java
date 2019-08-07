package com.chat.web.common.enums;

import lombok.Getter;

@Getter
public enum VegetableMarketOperationType {
    /**
     * 菜市场个人
     */
    person("1"),

    /**
     * 后台admin
     */
    admin("2");

    private final String key;

    VegetableMarketOperationType(String key) {
        this.key = key;
    }

}
