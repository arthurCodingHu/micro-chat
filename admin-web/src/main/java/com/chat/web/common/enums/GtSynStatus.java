package com.chat.web.common.enums;

import lombok.Getter;

/**
 *
 * 1处理中 2处理成功 3处理失败
 *
 * @author franky
 * @description
 * @date 2019/7/19
 */
@Getter
public enum GtSynStatus {

    process("1", "处理中"),
    success("2", "成功"),
    failed("3", "失败");

    private String key;
    private String value;

    GtSynStatus(String key, String value) {
        this.key = key;
        this.value = value;
    }




}
