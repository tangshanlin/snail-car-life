package com.woniu.car.message.web.listener;


import com.woniu.car.commons.core.dto.ResultEntity;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "${mq.ordercomplains.consumer.group}",topic = "${mq.ordercomplains.topic}"
        // ,selectorExpression = "order_pay"
        //设置顺序(单线程)消费,默认是多线程异步消费
        //,consumeMode = ConsumeMode.ORDERLY
        //广播模式
        //,messageModel = MessageModel.CLUSTERING
)
public class ShopListener implements RocketMQListener<ResultEntity> {
    @Override
    public void onMessage(ResultEntity message) {
        System.out.println("消费消息");
        System.out.println(message.getMessage());
        System.out.println(message.getData());
    }
}