package com.chat.web.common.enums;

import lombok.Getter;

@Getter
public enum GtSupplierSynEnum {
    /**
     * 已同步
     */
    YES(1),
    /**
     * 未同步
     */
    NO(2);

    private int key;

    GtSupplierSynEnum(Integer key) {
        this.key = key;
    }
}
