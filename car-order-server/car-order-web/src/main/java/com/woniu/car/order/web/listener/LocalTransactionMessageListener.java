package com.woniu.car.order.web.listener;//package com.woniu.car.order.web.listener;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
//import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
//import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
//import org.springframework.messaging.Message;
//import org.springframework.stereotype.Component;
//
////2,本地事务执行以及回查程序
//@Slf4j
//@Component
//@RocketMQTransactionListener
//public class LocalTransactionMessageListener implements RocketMQLocalTransactionListener {
//
//    @Override
//    // 执行本地的事务
//    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
//        // 接收到半消息之后执行的流程
//        log.info("开始执行本地事务");
//        // 尝试执行本地事务
//        try{
//            System.out.println("执行本地事务成功");
//            return RocketMQLocalTransactionState.COMMIT;
//        }catch (Exception e){
//            System.out.println("出现异常，开始回滚");
//            return RocketMQLocalTransactionState.ROLLBACK;
//        }
//    }
//
//    @Override
//    // 回查事务
//    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
//        System.out.println("由于没有接收到二次提交的信息，需要进行本地事务回查");
//        // 判断一下message中的信息
//        return null;
//    }
//
//}
