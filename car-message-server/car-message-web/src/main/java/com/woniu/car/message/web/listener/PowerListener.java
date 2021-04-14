package com.woniu.car.message.web.listener;


import com.woniu.car.commons.core.dto.ResultEntity;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "${mq.powerordercomplains.consumer.group}",topic = "${mq.powerordercomplains.topic}"
        // ,selectorExpression = "order_pay"
        //设置顺序(单线程)消费,默认是多线程异步消费
        //,consumeMode = ConsumeMode.ORDERLY
        //广播模式
        //,messageModel = MessageModel.CLUSTERING
)
public class PowerListener implements RocketMQListener<ResultEntity> {
    @Override
    public void onMessage(ResultEntity message) {
        System.out.println("用户投诉信息来了");
        System.out.println(message.getMessage());
        System.out.println(message.getData());
    }
}