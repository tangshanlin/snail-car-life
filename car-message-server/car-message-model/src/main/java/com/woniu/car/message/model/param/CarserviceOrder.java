package com.woniu.car.message.model.param;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 服务订单表
 * </p>
 *
 * @author WP
 * @since 2021-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CarserviceOrder implements Serializable {

    /**
     * 优惠券id
     */
    private Integer couponInfoId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 门店id
     */
    private Integer shopId;

    /**
     * 服务id
     */
    private Integer carserviceId;


    /**
     * 优惠卷面额
     */
    private BigDecimal couponMoney;


    /**
     * 服务名称
     */
    private String carserviceName;

    /**
     * 服务名称
     */
    private BigDecimal carservicePrice;


    /**
     * 订单支付渠道(1.钱包密码，2.支付宝)
     */
    private String payChannel;


    /**
     * 实际付款金额
     */
    private BigDecimal cartserviceOrderAmountTotal;

    /**
     * 付款时间
     */
    private Long payTime;

    /**
     * 订单单号
     */
    private String carserviceOrderNo;

    /**
     * 订单状态(已付款,服务进行中，已完成，退款中，已退款,取消交易)
     */
    private String carserviceOrderStatus;

    /**
     * 买家是否已经评价(1表示已评价，0表示未评价)
     */
    private Integer buyerEate;

    /**
     * 预约时间
     */
    private Long appointTime;

    /**
     * 门店地址
     */
    private String shopAddress;


    /**
     * 门店名字
     */
    private String shopName;


    /**
     * 门店电话
     */
    private String shopTel;


    /**
     * 订单券码
     */
    private String orderCode;


    /**
     * 门店图片
     */
    private String shopImage;

    /**
     * 用户电话
     */
    private String userTel;

    /**
     * 使用二维码图片
     */
    private Integer useQrCode;

    /**
     * 使用码
     */
    private Integer useCode;

}
