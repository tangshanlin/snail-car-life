package com.woniu.car.message.web.listener;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.message.web.domain.ComsumerMessage;
import com.woniu.car.message.web.domain.OrderComplains;
import com.woniu.car.message.web.domain.ProducerMessage;
import com.woniu.car.message.web.mapper.ComsumerMessageMapper;
import com.woniu.car.message.web.service.ComsumerMessageService;
import lombok.SneakyThrows;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@RocketMQMessageListener(consumerGroup = "${mq.shopordercomplains.consumer.group}",topic = "${mq.shopordercomplains.topic}"
        // ,selectorExpression = "order_pay"
        //设置顺序(单线程)消费,默认是多线程异步消费
        //,consumeMode = ConsumeMode.ORDERLY
        //广播模式
        //,messageModel = MessageModel.CLUSTERING
)
public class ShopListener implements RocketMQListener<MessageExt> {
    @Resource
    private ComsumerMessageMapper comsumerMessageMapper;

    @Value("${mq.shopordercomplains.consumer.group}")
    private String groupName;
    @Value("${mq.shopordercomplains.tag}")
    private String tag;

    @SneakyThrows
    @Override
    public void onMessage(MessageExt message) {
        final String msgId = message.getMsgId();
        final String keys = message.getKeys();
        String body=new String(message.getBody(),"UTF-8");
        System.out.println(msgId);
        System.out.println(keys);
        System.out.println(body);
        //将json转成需要的对象
        ResultEntity orderParam = JSONObject.parseObject(body, ResultEntity.class);
        ProducerMessage orderParams = JSONObject.parseObject(orderParam.getData().toString(), ProducerMessage.class);
        OrderComplains orderComplains = JSONObject.parseObject(orderParams.getMsgBody(), OrderComplains.class);
        final ComsumerMessage message1 = new ComsumerMessage();
        message1.setMsgId(msgId);
        message1.setMsgKey(keys);
        message1.setMsgBody(JSON.toJSONString(orderComplains));
        message1.setConsumerStatus(1);
        message1.setGroupName(groupName);
        message1.setMsgTag(tag);
        message1.setConsumerTimes(1);
        message1.setRemark("用户投诉信息需要处理");
        message1.setConsumerTimestamp(new Date());
        comsumerMessageMapper.insert(message1);
    }
}