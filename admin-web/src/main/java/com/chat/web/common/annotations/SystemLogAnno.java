package com.chat.web.common.annotations;

import java.lang.annotation.*;

/**
 * @author franky
 * @Date: 2019-05-11
 * @Description:
 *
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLogAnno {
    String description()  default "";
}
