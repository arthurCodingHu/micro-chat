package com.chat.web.common.enums;

import lombok.Getter;

@Getter
public enum DateStatus {
    /**
     * 有效
     */
    valid("1"),

    /**
     * 无效
     */
    in_vilid("2");

    private final String key;

    DateStatus(String key) {
        this.key = key;
    }

}
