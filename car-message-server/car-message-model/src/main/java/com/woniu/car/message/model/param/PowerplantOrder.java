package com.woniu.car.message.model.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 电站订单表
 * </p>
 *
 * @author WP
 * @since 2021-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PowerplantOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 电桩订单id
     */
    private Integer powerplantOrderId;

    /**
     * 电桩id
     */
    private Integer stationId;

    /**
     * 电站id
     */
    private Integer powerplantId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 充电桩验证码
     */
    private byte[] chargeVerify;

    /**
     * 订单状态
     */
    private String powerplantOrderStatus;

    /**
     * 优惠券id
     */
    private Integer couponInfoId;

}
