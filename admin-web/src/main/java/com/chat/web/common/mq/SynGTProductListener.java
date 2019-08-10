package com.chat.web.common.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-16 15:45
 * @Version: 1.0
 */
@Component
@Slf4j
public class SynGTProductListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel){
        log.info("开始处理冠通产品同步接口- start");
        System.out.println("====================================");
        byte[] body = message.getBody();
//        Product product = (Product)SerializeUtil.unserizlize(new String(body, 0, body.length), Product.class);
    }
}
