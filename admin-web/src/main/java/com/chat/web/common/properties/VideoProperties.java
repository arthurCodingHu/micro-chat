package com.chat.web.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ll
 * @description
 * @date 2019-06-14
 */
@Component
@ConfigurationProperties(prefix = "video.url")
@Data
public class VideoProperties {
    private String video;
    private String sub_prefix;
    private String agent_url;
}
