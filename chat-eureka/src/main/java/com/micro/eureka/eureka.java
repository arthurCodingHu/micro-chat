package com.micro.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-08-06 09:48
 * @Version: 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class eureka {
    public static void main(String[] args) {
        SpringApplication.run(eureka.class, args);
    }
}
