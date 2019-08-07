package com.chat.web.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author franky
 * @description
 * @date 2019-04-24
 */
@Component
@ConfigurationProperties(prefix = "picture.url")
@Data
public class PictureUrlProperties {
    private String sub_prefix;
    private String agent_url;
    private String agent_url1287;
    private String product_main_picture;
    private String advertising_picture;
    private String alliance_picture;
    private String production_enterprise_business;
    private String production_enterprise_food_business;

}
