package com.chat.web.common.configuration;

import com.bove.commons.serialize.JObjectSerializer;
import com.bove.commons.serialize.ObjectSerializer;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {


    @Bean
    public Queue defaultQueue(){
        Queue queue = new Queue("GT-syn-product-mq",true);
        return queue;
    }

//    /**
//     * 冠通异步同步商品监听
//     * @param connectionFactory
//     * @param synGTProductListener
//     * @return
//     */
//    @Bean("synGTProductContainer")
//    public SimpleMessageListenerContainer synGTProductContainer(ConnectionFactory connectionFactory, SynGTProductListener synGTProductListener) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//        container.setExposeListenerChannel(true);
//        container.setConcurrentConsumers(10);
//        container.setMaxConcurrentConsumers(50);
//        container.setMessageListener(synGTProductListener);
//        return container;
//    }

    /**
     * RabbitMQ对象序列化/反序列化时用到
     * @return
     */
    @Bean
    public ObjectSerializer objectSerializer() {
        return new JObjectSerializer();
    }

}
