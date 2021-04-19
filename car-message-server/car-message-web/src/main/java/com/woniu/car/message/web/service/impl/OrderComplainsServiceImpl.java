package com.woniu.car.message.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.message.client.OrderClient;
import com.woniu.car.message.client.UserClient;
import com.woniu.car.message.model.dto.UserInformation;
import com.woniu.car.message.model.feign.AllOrderParam;
import com.woniu.car.message.model.feign.CarserviceOrder;
import com.woniu.car.message.model.feign.PowerplantOrder;
import com.woniu.car.message.model.param.*;
import com.woniu.car.message.web.domain.OrderComplains;
import com.woniu.car.message.web.domain.ProducerMessage;
import com.woniu.car.message.web.mapper.OrderComplainsMapper;
import com.woniu.car.message.web.mapper.ProducerMessageMapper;
import com.woniu.car.message.web.service.OrderComplainsService;
import lombok.extern.slf4j.Slf4j;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *  订单投诉 服务实现类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Service
@Transactional
@Slf4j
public class OrderComplainsServiceImpl extends ServiceImpl<OrderComplainsMapper, OrderComplains> implements OrderComplainsService {

    @Resource
    private OrderComplainsMapper orderComplainsMapper;

    @Resource
    private OrderClient orderClient;

    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Resource
    private ProducerMessageMapper producerMessageMapper;

    /*

    rocketmq:
  name-server: 192.168.10.93:9876
  producer:
    group: orderComplains
    mq:
  ordercomplains:mq:
  shopordercomplains:
    consumer:
    consumer:
      group: order_consumer_complain
    tag: order_complains
    topic: orderComplainsTopic   powerordercomplains: producerordercomplains:
     */
    @Value("${rocketmq.producer.group}")
    private String goropName;
    @Value("${mq.shopordercomplains.tag}")
    private String shopTag;
    @Value("${mq.shopordercomplains.topic}")
    private String shopTopic;

    @Value("${mq.powerordercomplains.tag}")
    private String powerTag;
    @Value("${mq.powerordercomplains.topic}")
    private String powerTopic;

    @Value("${mq.producerordercomplains.tag}")
    private String proTag;
    @Value("${mq.producerordercomplains.topic}")
    private String proTopic;

    @Resource
    private UserClient userClient;

    private ProducerMessage getProducerMessage(OrderComplains orderComplainsParam){
        ProducerMessage producerMessage = new ProducerMessage();
        producerMessage.setGroupName(goropName);
        producerMessage.setId(UUID.randomUUID().toString());
        producerMessage.setMsgBody(JSON.toJSONString(orderComplainsParam));
        producerMessage.setMsgCreateTime(new Date());
        producerMessage.setMsgStatus(1);
        return producerMessage;
    }


    @Override
    public Integer addOrderComplains(OrderComplainsParam orderComplainsParam) {
        //返回1.订单投诉成功，2订单投诉失败
        log.info("前端传递的参数信息:{}",orderComplainsParam);
        OrderComplains orderComplains = BeanCopyUtil.copyOne(orderComplainsParam, OrderComplains::new);

        //调用登录的用户信息
        UserInformation userInformation = userClient.selectUerInformation().getData();
        log.info("用户的详情信息{}",userInformation);

        if(!ObjectUtils.isEmpty(userInformation)) {
            orderComplains.setComplainUserId(userInformation.getUserId());
            orderComplains.setComplainUsername(userInformation.getUserName());
            orderComplains.setComplainTime(new Date().getTime());
            int insert = orderComplainsMapper.insert(orderComplains);
            if(insert>0){
                //根据订单编号查询订单详情
                OrderVoP orderVo = new OrderVoP();
                orderVo.setOrderNo(orderComplains.getComplaintOrderCode());
                ResultEntity<AllOrderParam> orderInfoByOrderNo = orderClient.findOrderInfoByOrderNo(orderVo);
                String jsonObject= JSON.toJSONString(orderInfoByOrderNo.getData());
                //将json转成需要的对象
                AllOrderParam allOrderParam = JSONObject.parseObject(jsonObject, AllOrderParam.class);
                if(orderInfoByOrderNo.getFlag()){
                    if(orderInfoByOrderNo.getCode().equals(ConstCode.FIND_CARSERVICE_ORDER_SUCCESS)){
                        //门店
                        //给某个门店发送投诉消息
                        final CarserviceOrder carserviceOrder= allOrderParam.getCarserviceOrder();
                        Integer shopId = carserviceOrder.getShopId();
                        orderComplains.setComplainSendId(shopId);
                        log.info("给门店发送投诉消息，门店编号为：{}",shopId);
                        ProducerMessage producerMessage = getProducerMessage(orderComplains);
                        String msgKey = UUID.randomUUID().toString();
                        producerMessage.setMsgKey(msgKey);
                        producerMessage.setMsgTag(shopTag);
                        producerMessage.setMsgTopic(shopTopic);
                        int insert1 = producerMessageMapper.insert(producerMessage);
                        if(insert1>0){
                            //添加消费者信息
                            rocketMQTemplate.asyncSend(shopTopic,
                                    MessageBuilder.withPayload(ResultEntity.buildEntity(ProducerMessage.class).setData(producerMessage)
                                            .setMessage("用户服务投诉信息来了！！！"))
                                            .setHeader("KEYS",msgKey).build(),
                                    new SendCallback() {
                                        @Override
                                        public void onSuccess(SendResult sendResult) {
                                            System.out.println("发送成功: " + sendResult);
                                        }
                                        @Override
                                        public void onException(Throwable e) {
                                            System.out.println("发送失败: " + e.getMessage());
                                        }
                                    });
                        }
                        log.info("门店投诉信息发送成功");
                        return 1;
                    }else if(orderInfoByOrderNo.getCode().equals( ConstCode.FIND_POWERPLANT_ORDER_SUCCESS)){
                        //电桩
                        //给某个电桩发送投诉消息
                        PowerplantOrder powerplantOrder = orderInfoByOrderNo.getData().getPowerplantOrder();
                        Integer powerplantId = powerplantOrder.getPowerplantId();
                        orderComplains.setComplainSendId(powerplantId);
                        log.info("给电桩发送投诉消息，电桩编号为：{}",powerplantId);
                        ProducerMessage producerMessage = getProducerMessage(orderComplains);
                        String msgKey = UUID.randomUUID().toString();
                        producerMessage.setMsgKey(msgKey);
                        producerMessage.setMsgTag(powerTag);
                        producerMessage.setMsgTopic(powerTopic);
                        int insert1 = producerMessageMapper.insert(producerMessage);
                        if(insert1>0){
                            //添加消费者信息
                            rocketMQTemplate.asyncSend(powerTopic,
                                    MessageBuilder.withPayload(ResultEntity.buildEntity(ProducerMessage.class).setData(producerMessage)
                                            .setMessage("用户电站投诉信息来了！！！"))
                                            .setHeader("KEYS",msgKey).build(),
                                    new SendCallback() {
                                        @Override
                                        public void onSuccess(SendResult sendResult) {
                                            System.out.println("发送成功: " + sendResult);
                                        }
                                        @Override
                                        public void onException(Throwable e) {
                                            System.out.println("发送失败: " + e.getMessage());
                                        }
                                    });
                        }
                        log.info("电站投诉信息发送成功");
                        return 1;
                    }else if(orderInfoByOrderNo.getCode().equals(ConstCode.FIND_PRODUCT_ORDER_SUCCESS)){
                        //给平台发送投诉消息,平台管理商品的管理员id=2
                        Integer productManager=2;
                        orderComplains.setComplainSendId(productManager);
                        ProducerMessage producerMessage = getProducerMessage(orderComplains);
                        String msgKey = UUID.randomUUID().toString();
                        producerMessage.setMsgKey(msgKey);
                        producerMessage.setMsgTag(proTag);
                        producerMessage.setMsgTopic(proTopic);
                        int insert1 = producerMessageMapper.insert(producerMessage);
                        if(insert1>0){
                            //添加消费者信息
                            rocketMQTemplate.asyncSend(proTopic,
                                    MessageBuilder.withPayload(ResultEntity.buildEntity(ProducerMessage.class).setData(producerMessage)
                                            .setMessage("用户商品投诉信息来了！！！"))
                                            .setHeader("KEYS",msgKey).build(),
                                    new SendCallback() {
                                        @Override
                                        public void onSuccess(SendResult sendResult) {
                                            System.out.println("发送成功: " + sendResult);
                                        }
                                        @Override
                                        public void onException(Throwable e) {
                                            System.out.println("发送失败: " + e.getMessage());
                                        }
                                    });
                        }
                        log.info("商品投诉信息发送成功");
                        return 1;
                    }else{
                        return 3;
                    }
                }
            }
        }
        log.info("投诉信息发送失败");
        return 2;
    }
}
