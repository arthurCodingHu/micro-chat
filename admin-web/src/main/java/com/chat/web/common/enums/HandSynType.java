package com.chat.web.common.enums;

public enum HandSynType {
    BOOTH_SYN(1),
    SUPPLIER_SYN(2),
    PRODUCT_SYN(3),
    PURCHASE_ORDER_SYN(4);
    private int key;

    HandSynType(Integer key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
