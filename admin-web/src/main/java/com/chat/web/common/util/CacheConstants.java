package com.chat.web.common.util;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author franky
 * @description
 * @date 2019-05-10
 */
public interface CacheConstants {

    /** 缓存过期设置*/
    Integer expires_12 = 60 * 60 * 12;

    Integer expires_24 = 60 * 60 * 24;

    Integer expires_01 = 60;

    Integer expire_1 = 60 * 60 * 1;

    String PRODUCT_PREFIX = "product_prefix:";

    String PRODUCT_COMMON_PREFIX = "PRODUCT_COMMON_PREFIX";

    /**
     * 产品插入锁
     */
    String product_insert_or_update_lock = "product_insert_or_update_lock";

}
