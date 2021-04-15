package com.woniu.car.order.web.util;

import com.woniu.car.order.model.param.OrderVo;
import com.woniu.car.order.web.code.OrderCode;

/**
 * @ClassName OrderUtil
 * @Desc TODO 根据订单单号判断订单类型
 * @Author 21174
 * @Date 2021/4/15 13:35
 * @Version 1.0
 */

public class OrderUtil {
    /**
     * @Author WangPeng
     * @Description TODO 判断订单类型
     * @Date  14:51
     * @Param [orderNo]
     * @return java.lang.String
     **/
    public static String judgeOrderType(String orderNo){
        if("pr".equals(orderNo.substring(0,2))){
            return OrderCode.PRODUCT_ORDER;
        }else if("po".equals(orderNo.substring(0,2))){
            return OrderCode.POWERPLANT_ORDER;
        }else if("se".equals(orderNo.substring(0,2))){
            return OrderCode.CARSERVICE_ORDER;
        }
        return "500";
    }

}
