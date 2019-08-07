package com.farmers.product.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-08-06 13:30
 * @Version: 1.0
 */
@RestController
@RequestMapping("demo")
public class DemoService {

    @RequestMapping("hello")
    public String hello() {
        try {
//            Thread.sleep(500);
            System.out.println("=================inner demo hello ==============");
            return "hello, request success";
        } catch (Exception e) {
            e.printStackTrace();
            return "hello, request exception";
        }
    }

}
