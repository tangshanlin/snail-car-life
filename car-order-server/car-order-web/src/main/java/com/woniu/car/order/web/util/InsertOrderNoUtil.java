package com.woniu.car.order.web.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName InsertOrderNoUtil
 * @Desc TODO
 * @Author 21174
 * @Date 2021/4/9 11:02
 * @Version 1.0
 */
@Slf4j
public class InsertOrderNoUtil {

    /*
     * @Author WangPeng
     * @Description TODO 生成商品订单编号
     * @Date  2021/4/9
     * @Param []
     * @return java.lang.String
     **/
    public static String InsertProductOrderNo(){
        log.info("开始生成商品订单编号");
        String productOrderNo = "pr";

        for (int i = 0; i < 16; i++) {
            int num = (int) (Math.random()*10);
            productOrderNo=productOrderNo+num;
        }
        log.info("生成商品订单编号成功，商品订单编号为:"+productOrderNo);
        return productOrderNo;
    }

    /*
     * @Author WangPeng
     * @Description TODO 生成电站订单编号
     * @Date  2021/4/9
     * @Param []
     * @return java.lang.String
     **/
    public static String InsertPowerplantOrderNo(){
        log.info("开始生成电站订单编号");
        String powerplantOrderNo = "po";

        for (int i = 0; i < 16; i++) {
            int num = (int) (Math.random()*10);
            powerplantOrderNo=powerplantOrderNo+num;
        }
        log.info("生成电站订单编号成功，电站订单编号为:"+powerplantOrderNo);
        return powerplantOrderNo;
    }

    /*
     * @Author WangPeng
     * @Description TODO 生成服务订单编号
     * @Date  2021/4/9
     * @Param []
     * @return java.lang.String
     **/
    public static String InsertCarServiceOrderNo(){
        log.info("开始生成服务订单编号");
        String carServiceOrderNo = "se";

        for (int i = 0; i < 16; i++) {
            int num = (int) (Math.random()*10);
            carServiceOrderNo=carServiceOrderNo+num;
        }
        log.info("生成服务订单编号成功，服务订单编号为:"+carServiceOrderNo);
        return carServiceOrderNo;
    }

    /**
     * @Author WangPeng
     * @Description TODO 生成服务使用编
     * @Date  0:11
     * @Param []
     * @return java.lang.String
     **/
    
    public static String InsertCarserviceUseCode(){
        log.info("开始生成服务使用编");
        String CarserviceUseCode = "use";

        for (int i = 0; i < 10; i++) {
            int num = (int) (Math.random()*10);
            CarserviceUseCode=CarserviceUseCode+num;
        }
        log.info("生成服务订单编号成功，服务使用编为:"+CarserviceUseCode);
        return CarserviceUseCode;
    }

}
