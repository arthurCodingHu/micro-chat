package com.chat.web.product.third;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-08-06 13:31
 * @Version: 1.0
 */
@FeignClient(name = "chat-product")
public interface DemoFeign {

    @RequestMapping("/demo/hello")
    String hello();
}
