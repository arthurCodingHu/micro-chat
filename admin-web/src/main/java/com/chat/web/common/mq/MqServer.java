package com.chat.web.common.mq;

import com.bove.commons.serialize.ObjectSerializer;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-18 00:04
 * @Version: 1.0
 */
@Service
@Slf4j
public class MqServer {

    @Autowired
    private ObjectSerializer serializer;

    @RabbitListener(queues = "GT-syn-product-mq")
    public void process(Message message, Channel channel) throws IOException {
        System.out.println(new String(message.getBody()));
        byte[] body = message.getBody();
//        Product p = (Product) serializer.deserialize(body, Product.class);
//        Product product = (Product) SerializeUtil.unserizlize(new String(body, 0, body.length), Product.class);
        // 采用手动应答模式, 手动确认应答更为安全稳定
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.info("receive: " + new String(message.getBody()));
    }

}
