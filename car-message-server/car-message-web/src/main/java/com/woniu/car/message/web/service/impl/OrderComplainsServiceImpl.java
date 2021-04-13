package com.woniu.car.message.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.message.client.OrderClient;
import com.woniu.car.message.model.param.*;
import com.woniu.car.message.web.domain.OrderComplains;
import com.woniu.car.message.web.domain.ProducerMessage;
import com.woniu.car.message.web.mapper.OrderComplainsMapper;
import com.woniu.car.message.web.mapper.ProducerMessageMapper;
import com.woniu.car.message.web.service.OrderComplainsService;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  ordercomplains:
    consumer:
      group: order_consumer_complain
    tag: order_complains
    topic: orderComplainsTopic
     */
    @Value("${rocketmq.producer.group}")
    private String goropName;
    @Value("${mq.ordercomplains.tag}")
    private String msgTag;
    @Value("${mq.ordercomplains.topic}")
    private String msgTopic;

    private ProducerMessage getProducerMessage(OrderComplainsParam orderComplainsParam){
        ProducerMessage producerMessage = new ProducerMessage();
        producerMessage.setGroupName(goropName);
        producerMessage.setId(UUID.randomUUID().toString());
        producerMessage.setMsgBody(JSON.toJSONString(orderComplainsParam));
        producerMessage.setMsgTag(msgTag);
        producerMessage.setMsgCreateTime(new Date());
        producerMessage.setMsgStatus(1);
        producerMessage.setMsgTopic(msgTopic);
        return producerMessage;
    }


    @Override
    public Boolean addOrderComplains(OrderComplainsParam orderComplainsParam) {
        OrderComplains orderComplains = BeanCopyUtil.copyOne(orderComplainsParam, OrderComplains::new);
        orderComplains.setComplainTime(new Date().getTime());
        int insert = orderComplainsMapper.insert(orderComplains);
        if(insert>0){
            //根据订单编号查询订单详情
            OrderVoP orderVo = new OrderVoP();
            orderVo.setOrderNo(orderComplainsParam.getComplaintOrderCode());
            // orderVo.setOrderStatus("");
            ResultEntity<AllOrderParam> orderInfoByOrderNo = orderClient.findOrderInfoByOrderNo(orderVo);
            System.out.println("orderInfoByOrderNo===>>>>"+orderInfoByOrderNo.getCode());
            System.out.println("ConstCode.FIND_CARSERVICE_ORDER_SUCCESS\n"+ConstCode.FIND_CARSERVICE_ORDER_SUCCESS);

            if(orderInfoByOrderNo.getCode().equals(ConstCode.FIND_CARSERVICE_ORDER_SUCCESS)){

                //门店
                //给某个门店发送投诉消息
                CarserviceOrder carserviceOrder = orderInfoByOrderNo.getData().getCarserviceOrder();

                Integer shopId = carserviceOrder.getShopId();
                orderComplainsParam.setComplainId(shopId);
                System.out.println("门店======================================"+shopId);
                ProducerMessage producerMessage = getProducerMessage(orderComplainsParam);
                String msgKey = UUID.randomUUID().toString();
                producerMessage.setMsgKey(msgKey);
                int insert1 = producerMessageMapper.insert(producerMessage);

                if(insert1>0){
                    //添加消费者信息
                    rocketMQTemplate.asyncSend(msgTopic,
                            MessageBuilder.withPayload(ResultEntity.buildEntity(ProducerMessage.class).setData(producerMessage)
                            .setMessage("用户投诉信息来了！！！"))
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
            }
            if(orderInfoByOrderNo.getCode().equals( ConstCode.FIND_POWERPLANT_ORDER_SUCCESS)){
                //电桩
                //给某个电桩发送投诉消息
                System.out.println("电桩======================================");
                PowerplantOrder powerplantOrder = orderInfoByOrderNo.getData().getPowerplantOrder();

                Integer powerplantId = powerplantOrder.getPowerplantId();
                orderComplainsParam.setComplainId(powerplantId);



























            }
            //给平台发送投诉消息,平台管理商品的管理员id=2
            Integer productManager=2;
            orderComplainsParam.setComplainId(productManager);




            return true;
        }
        return false;
    }
}
